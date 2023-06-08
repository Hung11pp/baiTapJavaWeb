<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Spring MVC Form Handling</title>
</head>

<body>
	<h2>Employee</h2>
	<table>
		<tr>
			<td>Id: </td>
			<td>${employee.id }</td>
		</tr>
		<tr>
			<td>Name: </td>
			<td>${employee.name }</td>
		</tr>
		<tr>
			<td>Address: </td>
			<td>${employee.address }</td>
		</tr>
		<tr>
			<td>Email: </td>
			<td>${employee.email }</td>
		</tr>
		<tr>
			<td>Gender: </td>
			<td>${employee.gender }</td>
		</tr>
		<tr>
			<td>Favorite: </td>
			<td>
				<c:if test="${not empty employee.favorites}">
					<c:forEach var="favorite" items="${employee.favorites}">${favorite}</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Position: </td>
			<td>${employee.position}</td>
		</tr>
	</table>
</body>
</html>
