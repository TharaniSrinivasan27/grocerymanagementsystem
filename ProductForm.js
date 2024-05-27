document.addEventListener("DOMContentLoaded", function() {
    // Get the form element for adding a new product
    const addProductForm = document.getElementById("add-product-form");

    // Add an event listener to handle form submission
    addProductForm.addEventListener("submit", function(event) {
        // Prevent the default form submission behavior
        event.preventDefault();

        // Get the values from the form fields
        const productName = document.getElementById("productName").value;
        const category = document.getElementById("category").value;
        const price = parseInt(document.getElementById("price").value);
        const availability = document.getElementById("availability").value; 

        // Create an object with the product data
        const productData = {
            product_name: productName,
            category: category,
            price: price,
            availability: availability 
        };

        // Send a POST request to the server to create a new product
        fetch("http://localhost:8081/product/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(productData) 
        })
        .then(response => response.json()) 
        .then(data => {
            // Display a success message and redirect to the product details page
            alert("Product added successfully!");
            window.location.href = "ProductDetails.html";
        })
        .catch(error => console.error("Error adding product:", error)); 
    });
});
