<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myfinbank.admin.dto.CustomerAccountDTO" %>
<%@ page import="com.myfinbank.admin.dto.AccountDTO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Detail - MyFinBank</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5; /* Light background */
            color: #495057; /* Darker text for readability */
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #2e7d32; /* Dark green header */
            color: white;
            padding: 15px 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        nav ul {
            list-style: none;
            padding: 0;
            display: flex;
        }

        nav ul li {
            margin-right: 20px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        nav ul li a:hover {
            background-color: #1b5e20; /* Darker green on hover */
        }

        .logout-button {
            background: none;
            border: none;
            color: white;
            cursor: pointer;
            font-size: 16px;
        }

        h1, h2 {
            text-align: center;
            color: #2e7d32; /* Green for headings */
            margin: 20px 0;
        }

        .customer-info {
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px; /* Centering and restricting width */
        }

        .customer-info p {
            margin: 10px 0; /* Uniform margin for paragraphs */
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4caf50; /* Header green */
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1; /* Light grey on row hover */
        }

        .action-button {
            background-color: #4caf50; /* Primary green button */
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin: 0 5px; /* Spacing between buttons */
        }

        .action-button:hover {
            background-color: #388e3c; /* Darker green on button hover */
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-size: 16px;
            color: #2e7d32; /* Green link color */
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline; /* Underline effect on hover */
        }

        .center-button {
            display: flex;
            justify-content: center;
            margin: 20px 0; /* Space around the button */
        }
    </style>
</head>
<body>
    <nav>
        <div>
            <h2 style="margin: 0;">MyFinBank</h2>
        </div>
        <ul>
            <li><a href="/api/admin/dashboard"><i class="fas fa-tachometer-alt"></i> Dashboard</a></li>
            <li><a href="/api/admin/customers"><i class="fas fa-users"></i> Manage Customers</a></li>
            <li><a href="/api/admin/loans"><i class="fas fa-money-bill"></i> Manage Loans</a></li>
            <li>
                <form action="/api/admin/logout" method="POST" style="display: inline;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="logout-button">Logout</button>
                </form>
            </li>
        </ul>
    </nav>

    <h1>Customer Detail</h1>
    <%
        CustomerAccountDTO customer = (CustomerAccountDTO) request.getAttribute("customer");
        if (customer != null) {
    %>
    <div class="customer-info">
        <p><strong>ID:</strong> <%= customer.getCustomerId() %></p>
        <p><strong>Username:</strong> <%= customer.getUsername() %></p>
        <p><strong>Email:</strong> <%= customer.getEmail() %></p>
        <p><strong>Status:</strong> <%= customer.isActive() ? "Active" : "Inactive" %></p>
    </div>

    <h2>Accounts</h2>
    <table>
        <thead>
            <tr>
                <th>Account Number</th>
                <th>Balance</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<AccountDTO> accounts = customer.getAccounts();
                if (accounts != null) {
                    for (AccountDTO account : accounts) {
            %>
            <tr>
                <td><%= account.getAccountNumber() %></td>
                <td><%= account.getBalance() %></td>
                <td>
                    <div style="display: flex; justify-content: center;"> <!-- Aligning buttons -->
                        <a href="/admin/accounts/<%= account.getId() %>/edit" class="action-button">Update</a>
                        <form action="/admin/accounts/<%= account.getId() %>" method="post" style="display: inline;">
                            <input type="submit" value="Delete" class="action-button" />
                        </form>
                    </div>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <div class="center-button">
        <a href="/api/admin/customers/<%= customer.getCustomerId() %>/accounts/create" class="action-button">Create New Account</a>
    </div>

    <a href="/api/admin/customers" class="back-link">Back to Customer List</a>
    <%
        }
    %>
</body>
</html>
