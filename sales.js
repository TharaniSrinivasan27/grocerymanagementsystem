document.addEventListener("DOMContentLoaded", function() {
    const salesDetailsServer = "http://localhost:8081/salesdetails/";

    // Add event listener to submit button
    document.getElementById("submitBtn").addEventListener("click", function(event) {
        event.preventDefault(); // Prevent form submission
        calculateTotalUnitPrice(); // Calculate total unit price
    });

    // Function to set current date and time automatically
    function setCurrentDateTime() {
        const curDateTimeInput = document.getElementById("curDateTime");
        const now = new Date();
        const localDateTime = now.toISOString().substring(0, 19);
        curDateTimeInput.value = localDateTime;
    }
    setCurrentDateTime();

    // Function to parse query parameters and extract selected product IDs
    function getSelectedProductIds() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('products') ? urlParams.get('products').split(',') : [];
    }

    // Function to fetch selected products from the server
    function fetchSelectedProducts(selectedProductIds) {
        fetch(`http://localhost:8081/product/getSelectedProducts?ids=${selectedProductIds.join(',')}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Failed to fetch selected products.");
                }
            })
            .then(data => {
                // Retrieve existing products
                const existingProducts = Array.from(document.querySelectorAll("#selected-products-list tr"));

                // Create a set to store existing product IDs for efficient lookup
                const existingProductIds = new Set(existingProducts.map(product => product.getAttribute('data-product-id')));

                // Filter out new products that are not already in the list
                const newProducts = data.filter(product => !existingProductIds.has(product.product_id));

                // Append new products to the existing products
                const mergedProducts = [...existingProducts, ...newProducts];

                // Display merged products
                displaySelectedProducts(mergedProducts);
            })
            .catch(error => {
                console.error("Error fetching selected products:", error);
                alert("Failed to fetch selected products. Please try again later.");
            });
    }

    // Function to display selected products in the table
    function displaySelectedProducts(products) {
        const selectedProductsList = document.getElementById("selected-products-list");

        if (products.length === 0) {
            selectedProductsList.innerHTML = "<tr><td colspan='7'>No selected products</td></tr>";
            return;
        }

        selectedProductsList.innerHTML = products.map(product => `
            <tr data-product-id="${product.product_id}">
                <td>${product.product_id}</td>
                <td>${product.product_name}</td>
                <td>${product.category}</td>
                <td>${product.price}</td>
                <td><input type="number" class="quantity-input" value="1" min="1"></td>
                <td><span class="unit-price">--</span></td>
                <td class="action-buttons">
                    <button class="update-btn">Update</button>
                    <button class="delete-btn">Delete</button>
                </td>
            </tr>
        `).join('');

        const quantityInputs = document.querySelectorAll('.quantity-input');
        quantityInputs.forEach(input => {
            input.addEventListener('input', calculateUnitPrice);
        });

        const updateButtons = document.querySelectorAll('.update-btn');
        updateButtons.forEach(button => {
            button.addEventListener('click', updateProduct);
        });

        const deleteButtons = document.querySelectorAll('.delete-btn');
        deleteButtons.forEach(button => {
            button.addEventListener('click', deleteProduct);
        });

        // Calculate total unit price when products are displayed
        calculateTotalUnitPrice();
    }

    // Function to calculate unit price
    function calculateUnitPrice() {
        const row = this.closest('tr');
        const price = parseFloat(row.querySelector('td:nth-child(4)').innerText);
        const quantity = parseInt(this.value);
        const unitPrice = price * quantity;
        row.querySelector('.unit-price').innerText = unitPrice.toFixed(2);

        // Recalculate total unit price when quantity changes
        calculateTotalUnitPrice();
    }

    // Function to calculate total unit price
    function calculateTotalUnitPrice() {
        const unitPrices = Array.from(document.querySelectorAll(".unit-price"))
            .map(span => parseFloat(span.innerText));
        const totalUnitPrice = unitPrices.reduce((acc, val) => acc + val, 0);
        document.getElementById("totalPrice").innerText = totalUnitPrice.toFixed(2);
    }

    // Function to update product quantity
    function updateProduct() {
        const row = this.closest('tr');
        const productId = row.getAttribute('data-product-id');
        const quantityInput = row.querySelector('.quantity-input');
        const price = parseFloat(row.querySelector('td:nth-child(4)').innerText);
        const quantity = parseInt(quantityInput.value);
        const unitPrice = price * quantity;
        row.querySelector('.unit-price').innerText = unitPrice.toFixed(2);

        // Prompt user for updated quantity
        const updatedQuantity = prompt(`Enter updated quantity for product with ID ${productId}:`, quantity);
        if (updatedQuantity !== null) {
            const newQuantity = parseInt(updatedQuantity);
            if (!isNaN(newQuantity) && newQuantity >= 0) {
                quantityInput.value = newQuantity;
                const updatedUnitPrice = price * newQuantity;
                row.querySelector('.unit-price').innerText = updatedUnitPrice.toFixed(2);

                // Fetch request to update product quantity on the backend
                fetch(`http://localhost:8081/salesdetails/update`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        productId: productId,
                        quantity: newQuantity
                    })
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Failed to update product quantity.");
                    }
                    alert(`Product with ID ${productId} updated successfully. New quantity: ${newQuantity}`);
                })
                .catch(error => {
                    console.error("Error updating product quantity:", error);
                    alert("Failed to update product quantity. Please try again later.");
                });
            } else {
                alert('Invalid quantity. Please enter a valid positive number.');
            }
        }
    }

    // Function to delete a product from the list
    function deleteProduct() {
        const row = this.closest('tr');
        const productId = row.getAttribute('data-product-id');
        row.remove();
        calculateTotalUnitPrice();

        fetch(`${salesDetailsServer}deleteProduct`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                product_id: productId
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to delete product.");
            }
        })
        .catch(error => {
            console.error("Error deleting product:", error);
            alert("Failed to delete product. Please try again later.");
        });
    }

    // Function to add a new product to the list
    function addNewProduct(event) {
        event.preventDefault();

        // Retrieve form inputs
        const newProductId = document.getElementById("newProductId").value;
        const newProductName = document.getElementById("newProductName").value;
        const newProductCategory = document.getElementById("newProductCategory").value;
        const newProductPrice = parseFloat(document.getElementById("newProductPrice").value);
        const newProductQuantity = parseInt(document.getElementById("newProductQuantity").value);
        const salesId = document.getElementById("salesId").value;
        const curDateTime = document.getElementById("curDateTime").value;

        // Retrieve order status
        const orderStatus = document.getElementById("orderStatus").value;

        // Add new product to the table
        const selectedProductsList = document.getElementById("selected-products-list");
        const newRow = document.createElement("tr");
        newRow.setAttribute("data-product-id", newProductId);
        newRow.innerHTML = `
            <td>${newProductId}</td>
            <td>${newProductName}</td>
            <td>${newProductCategory}</td>
            <td>${newProductPrice.toFixed(2)}</td>
            <td><input type="number" class="quantity-input" value="${newProductQuantity}" min="1"></td>
            <td><span class="unit-price">${(newProductPrice * newProductQuantity).toFixed(2)}</span></td>
            <td class="action-buttons">
                <button class="update-btn">Update</button>
                <button class="delete-btn">Delete</button>
            </td>
        `;
        selectedProductsList.appendChild(newRow);

        // Fetch request to add new product to the backend
        fetch(`${salesDetailsServer}addProduct`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                product_id: newProductId,
                product_name: newProductName,
                category: newProductCategory,
                price: newProductPrice,
                quantity: newProductQuantity,
                sales_id: salesId,
                date_time: curDateTime,
                order_status: orderStatus 
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to add new product. Server responded with status: " + response.status);
            }
        })
        .catch(error => {
            console.error("Error adding new product:", error);
            alert("Failed to add new product. Please try again later.");
        });

        // Reset form inputs and update current date time
        document.getElementById("addProductForm").reset();
        setCurrentDateTime();

        // Recalculate total unit price when a new product is added
        calculateTotalUnitPrice();
    }

    // Function to display sales details by division
    function displaySalesDetails(salesDetails) {
        const salesDetailsContainer = document.getElementById("salesDetailsContainer");
        salesDetailsContainer.innerHTML = `
            <h2>Sales Details by Division</h2>
            <table>
                <thead>
                    <tr>
                        <th>Sales ID</th>
                        <th>Total Amount</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                    </tr>
                </thead>
                <tbody>
                    ${salesDetails.map(sale => `
                        <tr>
                            <td>${sale.sales_id}</td>
                            <td>${sale.total_amount}</td>
                            <td>${sale.product_id}</td>
                            <td>${sale.quantity}</td>
                            <td>${sale.unit_price}</td>
                        </tr>
                    `).join('')}
                </tbody>
            </table>
        `;
    }

    // Function to fetch selected products from the server
    function fetchSelectedProducts(selectedProductIds) {
        fetch(`http://localhost:8081/product/getSelectedProducts?ids=${selectedProductIds.join(',')}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Failed to fetch selected products.");
                }
            })
            .then(data => {
                // Retrieve existing products
                const existingProducts = Array.from(document.querySelectorAll("#selected-products-list tr"));
                
                // Create a map to store existing product IDs for efficient lookup
                const existingProductIds = new Set(existingProducts.map(product => product.getAttribute('data-product-id')));
                
                // Filter out new products that are not already in the list
                const newProducts = data.filter(product => !existingProductIds.has(product.product_id));
                
                // Append new products to the existing products
                const mergedProducts = [...existingProducts, ...newProducts];
                
                // Display merged products
                displaySelectedProducts(mergedProducts);
            })
            .catch(error => {
                console.error("Error fetching selected products:", error);
                alert("Failed to fetch selected products. Please try again later.");
            });
    }

    // Add event listeners for the add product form and submit button
    document.getElementById("addProductForm").addEventListener("submit", addNewProduct);
    document.getElementById("submitBtn").addEventListener("click", calculateTotalUnitPrice);

    // Get selected product IDs from the query parameters and fetch the products
    const selectedProductIds = getSelectedProductIds();
    if (selectedProductIds.length > 0) {
        fetchSelectedProducts(selectedProductIds);
    } else {
        displaySelectedProducts([]);
    }
    fetchSalesDetails();
});
