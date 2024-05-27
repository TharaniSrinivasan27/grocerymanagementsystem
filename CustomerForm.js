document.addEventListener("DOMContentLoaded", function() {
            const addCustomerForm = document.getElementById("add-customer-form");
            // Function to display customer details in the table
            function displayCustomers(customers) {
                const customerList = document.getElementById("customer-list");
                customerList.innerHTML = ""; // Clear existing table rows

                customers.forEach(customer => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${customer.customer_id}</td>
                        <td>${customer.customer_name}</td>
                        <td>${customer.address}</td>
                        <td>${customer.mobile_num}</td>
                        <td class="action-buttons">
                            <button class="update-btn" data-id="${customer.customer_id}">Update</button>
                            <button class="delete-btn" data-id="${customer.customer_id}">Delete</button>
                        </td>
                    `;
                    customerList.appendChild(row);
                });

                // Attach event listeners to delete buttons
                document.querySelectorAll(".delete-btn").forEach(button => {
                    button.addEventListener("click", function() {
                        const customerId = this.getAttribute("data-id");
                        deleteCustomer(customerId);
                    });
                });

                // Attach event listeners to update buttons
                document.querySelectorAll(".update-btn").forEach(button => {
                    button.addEventListener("click", function() {
                        const customerId = this.getAttribute("data-id");
                        updateCustomer(customerId);
                    });
                });
            }

            // Function to fetch customer details from the server
            function fetchCustomers() {
                fetch("http://localhost:8081/customer/getall")
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error("Failed to fetch customers.");
                        }
                    })
                    .then(data => displayCustomers(data))
                    .catch(error => console.error("Error fetching customers:", error));
            }

            // Function to update customer details based on user choice
            function updateCustomer(customerId) {
                const fieldToUpdate = prompt("Which field do you want to update? (name/address/mobile)");
                if (!fieldToUpdate) return;

                let updatedValue;
                switch (fieldToUpdate) {
                    case "name":
                        updatedValue = prompt("Enter the updated name:");
                        break;
                    case "address":
                        updatedValue = prompt("Enter the updated address:");
                        break;
                    case "mobile":
                        updatedValue = prompt("Enter the updated mobile number:");
                        break;
                    default:
                        alert("Invalid field selection. Please choose 'name', 'address', or 'mobile'.");
                        return;
                }

                if (!updatedValue) {
                    alert("Please provide a valid value.");
                    return;
                }

                fetch(`http://localhost:8081/customer/${customerId}`)
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error("Failed to fetch customer details.");
                        }
                    })
                    .then(customer => {
                        switch (fieldToUpdate) {
                            case "name":
                                customer.customer_name = updatedValue;
                                break;
                            case "address":
                                customer.address = updatedValue;
                                break;
                            case "mobile":
                                customer.mobile_num = updatedValue;
                                break;
                        }

                        return fetch(`http://localhost:8081/customer/update/${customerId}`, {
                            method: "PUT",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify(customer)
                        });
                    })
                    .then(response => {
                        if (response.ok) {
                            alert(`Customer ${fieldToUpdate} updated successfully!`);
                            fetchCustomers();
                        } else {
                            throw new Error(`Failed to update customer ${fieldToUpdate}.`);
                        }
                    })
                    .catch(error => {
                        console.error(`Error updating customer ${fieldToUpdate}:`, error);
                        alert(error.message);
                    });
            }

            // Function to delete a customer
            function deleteCustomer(customerId) {
                if (confirm("Are you sure you want to delete this customer?")) {
                    fetch(`http://localhost:8081/customer/delete/${customerId}`, {
                        method: "DELETE"
                    })
                    .then(response => {
                        if (response.ok) {
                            alert("Customer deleted successfully!");
                            fetchCustomers();
                        } else {
                            throw new Error("Failed to delete customer.");
                        }
                    })
                    .catch(error => console.error("Error deleting customer:", error));
                }
            }

            // Function to validate the name
            function isValidName(name) {
                return /^[a-zA-Z]+$/.test(name);
            }

            // Function to validate the mobile number
            function isValidMobileNumber(mobileNum) {
                return /^\d{10}$/.test(mobileNum);
            }

            // Event listener for form submission
            addCustomerForm.addEventListener("submit", function(event) {
                event.preventDefault();

                const customerName = document.getElementById("customer-name").value;
                const address = document.getElementById("address").value;
                const mobileNum = document.getElementById("mobile-num").value;

                if (!isValidName(customerName)) {
                    alert("Please enter a valid name with only letters.");
                    return;
                }

                if (!isValidMobileNumber(mobileNum)) {
                    alert("Please enter a valid mobile number with 10 digits.");
                    return;
                }

                if (!address) {
                    alert("Please fill in the address field.");
                    return;
                }

                const newCustomer = {
                    customer_name: customerName,
                    address: address,
                    mobile_num: mobileNum
                };

                fetch("http://localhost:8081/customer/create", { 
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(newCustomer)
                })
                .then(response => {
                    if (response.ok) {
                        alert("Customer created successfully!");
                        fetchCustomers();
                        window.location.href = "ProductDetails.html";
                    } else {
                        throw new Error("Failed to create customer.");
                    }
                })
                .catch(error => console.error("Error creating customer:", error));
            });

            fetchCustomers();
        });