<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.myfinbank.customer.model.Transaction"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Transactions - MyFinBank</title>
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #eafaf1; /* Light green background */
        color: #2f4f4f; /* Dark slate gray text */
    }
    nav {
        background-color: #4caf50; /* Green navbar */
        padding: 15px;
    }
    nav ul {
        list-style-type: none;
        padding: 0;
    }
    nav li {
        display: inline;
        margin-right: 20px;
    }
    nav a {
        color: white;
        text-decoration: none;
        font-weight: bold;
    }
    nav a:hover {
        text-decoration: underline;
    }
    .container {
        margin: 20px;
        padding: 20px;
        background-color: #fff; /* White background for container */
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
        color: #4caf50; /* Green heading */
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        padding: 12px;
        text-align: left;
        border: 1px solid #4caf50; /* Green border */
    }
    th {
        background-color: #d9f2e6; /* Light green for table header */
        color: #4caf50; /* Dark green text for header */
    }
    tr:nth-child(even) {
        background-color: #f2f2f2; /* Light gray for even rows */
    }
    tr:hover {
        background-color: #c8e6c9; /* Slightly darker green on hover */
    }
    .link {
        margin-top: 20px;
    }
</style>
</head>
<body>
	<nav>
		<ul>
			<li><a href="/api/customer/dashboard" aria-label="Dashboard"><i class="fas fa-tachometer-alt"></i> Dashboard</a></li>
			<li><a href="/api/customer/account/details" aria-label="Account Details"><i class="fas fa-wallet"></i> Account Details</a></li>
		</ul>
	</nav>
	<div class="container">
		<h2>Transaction History for Account ID: <%=request.getAttribute("accountId")%></h2>

		<table>
			<tr>
				<th>Transaction ID</th>
				<th>Amount</th>
				<th>Type</th>
				<th>Date</th>
			</tr>
			<%
			List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			if (transactions != null && !transactions.isEmpty()) {
				for (Transaction transaction : transactions) {
			%>
			<tr>
				<td><%=transaction.getTransactionId()%></td>
				<td><%=transaction.getAmount()%></td>
				<td><%=transaction.getType()%></td>
				<td><%=transaction.getDate().format(formatter) %></td>
			</tr>
			<%
				}
			} else {
			%>
			<tr>
				<td colspan="4">No transactions found.</td>
			</tr>
			<%
			}
			%>
		</table>

		<a href="/api/customer/transactions">Back to Transaction Form</a>
		
		<p class="link">Back to <a href="/api/customer/dashboard">Dashboard</a></p>
	</div>
</body>
</html>
