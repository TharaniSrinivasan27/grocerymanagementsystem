// Function to validate the login form
function validateForm(event) {
    // Prevent the form from submitting by default
    event.preventDefault();

    // Hardcoded user data for validation
    var hardcodedUsers = [
         // Admin user
        { username: "Tharani", password: "Tharani2003", role: "admin" },
        // Regular user
        { username: "Varshini", password: "Varshini2003", role: "user" } 
        // Add more users as needed
    ];

    // Get the values from the form
    // Get the username input value
    var username = document.getElementById("username").value; 
    // Get the password input value
    var password = document.getElementById("password").value;
     // Get the role selection value 
    var role = document.getElementById("role").value;

    // Check if the provided credentials match any hardcoded user
    var isValidUser = hardcodedUsers.some(function(user) {
        return user.username === username && user.password === password && user.role === role;
    });

    // If valid user, prompt for navigation choice
    if (isValidUser) {
        // If the user is an admin
        if (role === "admin") { 
            var decision = prompt("Authentication successful. Where do you want to navigate?\n1. Product Form\n2. Customer Form");
            if (decision === "1") {
                //Redirect to product form
                window.location.href = "productForm.html"; 
            } else if (decision === "2") {
                // Redirect to customer form
                window.location.href = "customerForm.html"; 
            } else {
                // Show alert for invalid choice
                alert("Invalid choice."); 
            }
        } else if (role === "user") { 
            // If the user is a regular user
             // Redirect to customer form
            window.location.href = "customerForm.html";
        } else {
            // Show alert for invalid role
            alert("Invalid role."); 
        }
    } else {
        // Show alert for invalid credentials
        alert("Invalid username, password, or role."); 
    }
}
