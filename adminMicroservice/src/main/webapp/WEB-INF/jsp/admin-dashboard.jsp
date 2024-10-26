<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - MyFinBank</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* General styling */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8fafc; /* Light grey background for a clean look */
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* Navigation bar */
        nav {
            background-color: #2e7d32; /* Green theme for header */
            color: white;
            padding: 20px 30px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        nav h2 {
            margin: 0;
            font-size: 24px;
            font-weight: 600;
        }

        nav ul {
            list-style: none;
            display: flex;
            margin: 0;
            padding: 0;
        }

        nav ul li {
            margin-right: 20px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        nav ul li a:hover {
            background-color: #1b5e20;
        }

        .logout-button {
            background: none;
            border: none;
            color: white;
            cursor: pointer;
            font-size: 16px;
            font-weight: 500;
        }

        /* Header */
        h1 {
            text-align: center;
            margin-top: 40px;
            color: #2e7d32;
            font-size: 28px;
            font-weight: 600;
        }

        /* Action cards */
        .action-list {
            display: grid;
            grid-template-columns: repeat(3, 1fr); /* 3 columns on large screens */
            gap: 25px;
            padding: 40px 20px;
            max-width: 1100px;
            margin: auto;
        }

        .action-list a {
            background-color: #66bb6a; /* Soft green */
            color: white;
            padding: 30px 20px;
            border-radius: 12px;
            text-decoration: none;
            text-align: center;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            font-size: 18px;
            font-weight: 500;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .action-list a i {
            font-size: 28px;
            margin-bottom: 10px;
        }

        .action-list a:hover {
            transform: scale(1.03); /* Slight scale-up on hover */
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15); /* Stronger shadow on hover */
        }

        /* Responsive adjustments */
        @media (max-width: 992px) {
            .action-list {
                grid-template-columns: repeat(2, 1fr); /* 2 columns for medium screens */
            }
        }

        @media (max-width: 600px) {
            .action-list {
                grid-template-columns: 1fr; /* 1 column for small screens */
            }
        }
    </style>
</head>
<body>
    <nav>
        <h2>MyFinBank</h2>
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

    <h1>Welcome to the Admin Dashboard</h1>
    <div class="action-list">
        <a href="/api/admin/customers"><i class="fas fa-users"></i> View Customers</a>
        <a href="/api/admin/accounts"><i class="fas fa-list"></i> View Accounts</a>
        <a href="/api/admin/loans"><i class="fas fa-money-bill"></i> View Loans</a>
        <a href="/api/admin/notifications"><i class="fas fa-bell"></i> Notifications</a>
        <a href="/api/admin/support"><i class="fas fa-headset"></i> Customer Support</a>
    </div>
</body>
</html>
