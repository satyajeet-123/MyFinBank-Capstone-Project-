<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Register - MyFinBank</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* General styling */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8fafc; /* Light background */
            color: #495057;
            margin: 0;
            padding: 0;
        }

        /* Navigation bar */
        nav {
            background-color: #2e7d32; /* Green header */
            color: white;
            padding: 15px 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        nav ul {
            list-style: none;
            padding: 0;
            display: flex;
            justify-content: flex-end;
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

        /* Form container */
        .container {
            width: 400px;
            margin: auto;
            padding: 30px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }

        h2 {
            text-align: center;
            color: #2e7d32; /* Green theme for header text */
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        /* Input fields */
        input[type="text"], input[type="password"], input[type="email"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            transition: border-color 0.3s;
        }

        /* Input focus styles */
        input[type="text"]:focus, input[type="password"]:focus, input[type="email"]:focus {
            border-color: #2e7d32; /* Green border on focus */
            outline: none;
        }

        /* Button styling */
        .btn {
            width: 100%;
            padding: 12px;
            background-color: #4caf50; /* Primary green button */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #388e3c; /* Darker green on hover */
        }

        /* Error message */
        .error {
            color: red;
            text-align: center;
            margin: 10px 0;
        }

        /* Register link */
        .link {
            text-align: center;
            margin-top: 15px;
        }

        .link a {
            color: #388e3c; /* Green link */
            text-decoration: none;
        }

        .link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <nav>
        <ul>
            <li><a href="/api/admin/login"><i class="fas fa-user-plus"></i> Admin Login</a></li>
        </ul>
    </nav>
    <div class="container">
        <h2>Create Admin Account</h2>
        <form action="/api/admin/register" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" required>
            <label for="email">Email:</label>
            <input type="email" name="email" required>
            <label for="password">Password:</label>
            <input type="password" name="password" required>
            <button type="submit" class="btn">Register</button>
        </form>
        <p class="link">Already have an account? <a href="/api/admin/login">Login here</a></p>
    </div>
</body>
</html>
