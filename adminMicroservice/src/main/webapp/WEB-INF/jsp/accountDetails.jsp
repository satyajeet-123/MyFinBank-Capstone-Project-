<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Detail</title>
    <style>
        /* General styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #e0f7e9; /* Light green background */
            color: #2e7d32; /* Dark green text color */
        }

        /* Header styling */
        h1 {
            color: #1b5e20; /* Darker green for the header */
            border-bottom: 2px solid #2e7d32;
            padding-bottom: 10px;
        }

        /* Paragraph styling */
        p {
            font-size: 1.1em;
            color: #388e3c; /* Medium green color */
        }

        /* Link styling */
        a {
            color: #2e7d32; /* Dark green */
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            color: #1b5e20; /* Darker green on hover */
        }

        /* Container for account details */
        .account-details {
            background-color: #a5d6a7; /* Light green card background */
            border: 1px solid #66bb6a;
            padding: 15px;
            border-radius: 8px;
            width: 300px;
            margin: 20px auto;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Account Detail</h1>

    <div class="account-details">
        <p><strong>Account ID:</strong> ${account.id}</p>
        <p><strong>Account Number:</strong> ${account.accountNumber}</p>
        <p><strong>Balance:</strong> ${account.balance}</p>
    </div>

    <div style="text-align: center;">
        <a href="/api/admin/accounts">Back to Account List</a>
    </div>
</body> 
</html>
