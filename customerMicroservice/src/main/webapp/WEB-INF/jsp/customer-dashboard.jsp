<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page import="com.myfinbank.customer.dto.AccountDTO" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard - MyFinBank</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e9f5ec; /* Light green background */
            color: #495057;
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #2ecc71; /* Green navigation bar */
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
            background-color: #27ae60; /* Darker green on hover */
        }

        .logout-button {
            background: none;
            border: none;
            color: white;
            cursor: pointer;
            font-size: 16px;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
            color: #2ecc71; /* Green heading */
        }

        .action-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
            gap: 20px;
            padding: 20px;
            max-width: 800px;
            margin: auto;
        }

        .action-list a {
            background-color: #27ae60; /* Green background for action items */
            color: white;
            padding: 20px;
            border-radius: 8px;
            text-decoration: none;
            text-align: center;
            transition: background-color 0.3s, transform 0.3s;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            font-size: 16px;
        }

        .action-list a:hover {
            background-color: #1e8449; /* Darker green on hover */
            transform: translateY(-3px);
        }

        .action-list i {
            display: block;
            font-size: 24px;
            margin-bottom: 10px;
        }

        @media (max-width: 600px) {
            nav ul {
                flex-direction: column;
                align-items: center;
            }
            nav ul li {
                margin-bottom: 10px;
            }
        }
    </style>
</head>
<body>
    <nav>
        <div>
            <h2 style="margin: 0;">MyFinBank</h2>
        </div>
        <ul>
            <li><a href="/api/customer/dashboard"><i class="fas fa-tachometer-alt"></i> Dashboard</a></li>
            <li><a href="/api/customer/accounts"><i class="fas fa-wallet"></i> Account Details</a></li>
            <li>
                <form action="/api/customer/logout" method="POST" style="display: inline;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="logout-button">Logout</button>
                </form>
            </li>
        </ul>
    </nav>

    <h1>Welcome to Your Dashboard</h1>
    <div class="action-list">
        <a href="/api/customer/accounts"><i class="fas fa-eye"></i> View Account Details</a>
        <a href="/api/customer/deposit"><i class="fas fa-plus"></i> Deposit Funds</a>
        <a href="/api/customer/withdraw"><i class="fas fa-minus"></i> Withdraw Funds</a>
        <a href="/api/customer/transfer"><i class="fas fa-exchange-alt"></i> Transfer Funds</a>
        <a href="/api/customer/transactions"><i class="fas fa-history"></i> View Transactions</a>
        <a href="/api/customer/calculate-emi"><i class="fas fa-calculator"></i> Calculate Loan EMI</a>
        <a href="/api/customer/applyLoan"><i class="fas fa-paper-plane"></i> Apply for Loan</a>
        <a href="/api/customer/investments/invest"><i class="fas fa-dollar-sign"></i> Invest</a>
        <a href="/api/customer/investments?accountId=${param.accountId}"><i class="fas fa-list"></i> View Investments</a>
        <a href="/api/admin/support"><i class="fas fa-headset"></i> Customer Support</a>
    </div>
</body>
</html>
