<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Loans by Account</title>
</head>
<body>
    <h1>Loans for Account ID: ${accountId}</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Amount</th>
            <th>Status</th>
        </tr>
        <c:forEach var="loan" items="${loans}">
            <tr>
                <td>${loan.id}</td>
                <td>${loan.amount}</td>
                <td>${loan.status}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/admin/loans">Back to Loan List</a>
</body>
</html>