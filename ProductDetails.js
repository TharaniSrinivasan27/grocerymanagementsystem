document.addEventListener("DOMContentLoaded", function() {
    const selectedProducts = []; // Array to store selected products

    // Function to display product details in the table
    function displayProducts(products) {
        const productList = document.getElementById("product-list");
        productList.innerHTML = "";

        // Loop through each product and create table rows to display them
        products.forEach(product => {
            const row = document.createElement("tr");
            row.dataset.id = product.product_id;
            row.innerHTML = `
                <td>${product.product_id}</td>
                <td>${product.product_name}</td>
                <td>${product.category}</td>
                <td>${product.price}</td>
                <td class="availability">${product.availability}</td>
                <td class="action-buttons">
                    <button class="update-btn" data-id="${product.product_id}">Update</button>
                    <button class="delete-btn" data-id="${product.product_id}">Delete</button>
                </td>
                <td><input type="checkbox" class="product-checkbox" data-id="${product.product_id}" ${product.availability === 'unavailable' ? 'disabled' : ''}></td>
            `;
            productList.appendChild(row);
        });

        // Attach event listeners to delete buttons
        const deleteButtons = document.querySelectorAll(".delete-btn");
        deleteButtons.forEach(button => {
            button.addEventListener("click", function() {
                const productId = this.getAttribute("data-id");
                deleteProduct(productId);
            });
        });

        // Attach event listeners to update buttons
        const updateButtons = document.querySelectorAll(".update-btn");
        updateButtons.forEach(button => {
            button.addEventListener("click", function() {
                const productId = this.getAttribute("data-id");
                updateProduct(productId);
            });
        });

        // Attach event listener to product checkboxes
        const productCheckboxes = document.querySelectorAll(".product-checkbox");
        productCheckboxes.forEach(checkbox => {
            checkbox.addEventListener("change", function() {
                const productId = this.getAttribute("data-id");
                if (this.checked) {
                    const selectedProduct = products.find(product => product.product_id === parseInt(productId));
                    if (selectedProduct) {
                        selectedProducts.push(selectedProduct);
                    }
                } else {
                    const index = selectedProducts.findIndex(product => product.product_id === parseInt(productId));
                    if (index !== -1) {
                        selectedProducts.splice(index, 1);
                    }
                }
            });
        });
    }

    // Function to fetch product details from the server
    function fetchProducts() {
        fetch("http://localhost:8081/product/getallproducts")
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Failed to fetch products.");
                }
            })
            // Display fetched products
            .then(data => displayProducts(data))
            .catch(error => {
                console.error("Error fetching products:", error);
                alert("Failed to fetch products. Please try again later.");
            });
    }

    // Function to delete the product
    function deleteProduct(productId) {
        const row = document.querySelector(`tr[data-id="${productId}"]`);

        // Check if the row exists
        if (row) {
            // Display confirmation dialog
            const confirmation = confirm("Are you sure you want to delete this product?");

            // If user confirms deletion, remove the row
            if (confirmation) {
                row.remove();
                // Perform deletion on the server using fetch API
                fetch(`http://localhost:8081/product/delete/${productId}`, {
                        method: "DELETE"
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
        } else {
            console.error("Row not found for product ID:", productId);
        }
    }

    // Function to prompt user for field to update and new value
    function updateProduct(productId) {
        // Prompt the user to choose the field to update
        const fieldToUpdate = prompt("Enter the field you want to update (name, price, or availability):");

        // Check if a valid field is provided
        if (fieldToUpdate) {
            // Check if the field is availability
            if (fieldToUpdate.toLowerCase() === "availability") {
                // Prompt the user to choose between available and unavailable
                const availabilityChoice = prompt("Choose availability (available/unavailable):");

                // Check if a valid choice is provided
                if (availabilityChoice && (availabilityChoice.toLowerCase() === "available" || availabilityChoice.toLowerCase() === "unavailable")) {
                    // Display confirmation dialog
                    const confirmation = confirm(`Are you sure you want to update ${fieldToUpdate} to ${availabilityChoice}?`);

                    // If user confirms, call the function to update the specific field of the product
                    if (confirmation) {
                        updateProductField(productId, fieldToUpdate, availabilityChoice);
                    } else {
                        alert("Update canceled.");
                    }
                } else {
                    alert("Please enter a valid availability choice (available/unavailable).");
                }
            } else {
                // Prompt the user for the new value based on the chosen field
                const newValue = prompt(`Enter the new value for ${fieldToUpdate}:`);

                // Check if a new value is provided
                if (newValue) {
                    // Display confirmation dialog
                    const confirmation = confirm(`Are you sure you want to update ${fieldToUpdate} to ${newValue}?`);

                    // If user confirms, call the function to update the specific field of the product
                    if (confirmation) {
                        updateProductField(productId, fieldToUpdate, newValue);
                    } else {
                        alert("Update canceled.");
                    }
                } else {
                    alert("Please enter a valid value.");
                }
            }
        }
    }
    // Function to update specific field of a product
    function updateProductField(productId, fieldToUpdate, newValue) {
        // Construct URL based on fieldToUpdate
        let url = "";
        switch (fieldToUpdate.toLowerCase()) {
            case "name":
                url = `http://localhost:8081/product/updateproductname/${productId}/${newValue}`;
                break;
            case "price":
                url = `http://localhost:8081/product/updateproductprice/${productId}/${newValue}`;
                break;
            case "availability":
                url = `http://localhost:8081/product/updateproductavailability/${productId}/${newValue}`;
                break;
            default:
                alert("Invalid field to update.");
                return;
        }

        // Send PUT request to update the specific field
        fetch(url, {
                method: "PUT"
            })
            .then(response => {
                if (response.ok) {
                    alert(`Product ${fieldToUpdate} updated successfully!`);

                    // Fetch and display updated product list
                    fetchProducts();

                    // Update the checkbox state based on the new availability
                    if (fieldToUpdate.toLowerCase() === "availability") {
                        const row = document.querySelector(`tr[data-id="${productId}"]`);
                        const checkbox = row.querySelector('.product-checkbox');
                        const availabilityCell = row.querySelector('.availability');

                        // Update availability text in the table
                        availabilityCell.textContent = newValue;

                        // Enable or disable the checkbox based on the new availability
                        if (newValue.toLowerCase() === "available") {
                            checkbox.disabled = false;
                        } else {
                            checkbox.disabled = true;
                            checkbox.checked = false;
                        }
                    }
                } else {
                    throw new Error("Failed to update product.");
                }
            })
            .catch(error => {
                console.error("Error updating product:", error);
                alert("Failed to update product. Please try again later.");
            });
    }

    // Function to redirect to the sales page with selected product IDs
    function redirectToSalesPage() {
        const selectedProductIds = selectedProducts.map(product => product.product_id);
        const queryString = selectedProductIds.length > 0 ? `?products=${selectedProductIds.join(",")}` : "";
        window.location.href = `sales.html${queryString}`;
    }

    // Attach event listener to the "Billing" button
    const billingButton = document.getElementById("billing-button");
    billingButton.addEventListener("click", redirectToSalesPage);

    // Fetch and display product details when the page loads
    fetchProducts();
});