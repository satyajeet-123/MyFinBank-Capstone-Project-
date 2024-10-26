<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Update Account</title>
</head>
<body>
<h2>Update Account</h2>
<form:form method="post" modelAttribute="accountDTO" action="/api/admin/accounts/update/${accountDTO.id}">
    <label for="accountNumber">Account Number:</label>
    <form:input path="accountNumber" id="accountNumber" required="true" /><br/>

    <label for="balance">Balance:</label>
    <form:input path="balance" id="balance" required="true" type="number" step="0.01"/><br/>

    <label for="customerId">Customer:</label>
    <form:select path="customer.id" id="customerId" required="true">
        <c:forEach var="customer" items="${customers}">
            <option value="${customer.id}" <c:if test="${customer.id == accountDTO.customer.id}">selected</c:if>>${customer.name}</option>
        </c:forEach>
    </form:select><br/>

    <input type="submit" value="Update Account" />
</form:form>
<a href="/api/admin/accounts/allaccounts">Back to Account List</a>
</body>
</html>
