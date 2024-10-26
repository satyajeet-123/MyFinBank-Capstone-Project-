<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Loan List</title>
</head>
<body>
    <h1>All Loans</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="loan" items="${loans}">
            <tr>
                <td>${loan.id}</td>
                <td>${loan.amount}</td>
                <td>${loan.status}</td>
                <td>
                    <form action="/admin/loans/${loan.id}/approve" method="post">
                        <button type="submit">Approve</button>
                    </form>
                    <form action="/admin/loans/${loan.id}/deny" method="post">
                        <button type="submit">Deny</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Home</a>
</body>
</html>