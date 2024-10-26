<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Loan Status - MyFinBank</title>
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

        .container {
            width: 400px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin: 100px auto;
        }

        h1 {
            color: #2ecc71; /* Green heading */
            text-align: center;
        }

        label {
            font-size: 16px;
            margin-top: 10px;
            display: block;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #27ae60; /* Green button */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }

        button:hover {
            background-color: #1e8449; /* Darker green on hover */
        }

        .loan-list {
            margin-top: 20px;
        }

        .loan-list li {
            list-style: none;
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }

        .loan-list li:last-child {
            border-bottom: none;
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
            <li><a href="/api/customer/account/details"><i class="fas fa-wallet"></i> Account Details</a></li>
            <li>
                <form action="/api/customer/logout" method="POST" style="display: inline;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="logout-button">Logout</button>
                </form>
            </li>
        </ul>
    </nav>

    <div class="container">
        <h1>Check Loan Status</h1>
        <form action="<c:url value='/api/customer/loanStatus'/>" method="GET">
            <label for="accountId">Account ID:</label>
            <input type="number" id="accountId" name="accountId" required>
            <button type="submit">Check Loan Status</button>
        </form>
        <c:if test="${not empty loans}">
            <h2>Your Loans:</h2>
            <ul class="loan-list">
                <c:forEach var="loan" items="${loans}">
                    <li>Loan ID: ${loan.id}, Amount: Rs. ${loan.amount}, Status: ${loan.status}</li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty loans}">
            <p>No loans found for this account.</p>
        </c:if>
    </div>
</body>
</html>
