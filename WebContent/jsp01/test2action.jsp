<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	font-family: 굴림체;
}

table.table {
	width: 500px;
}

thead tr {
	background-color: #eee;
}
</style>
</head>
<body>

<%
String s1 = request.getParameter("number1");
int number1 = s1 != null ? Integer.parseInt(s1) : 5;
String s2 = request.getParameter("number2");
int number2 = s2 != null ? Integer.parseInt(s2) : 5;

%>
	<div class="container">
		<h1>request parameter</h1>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Parameter Name</th>
					<th>Parameter Value</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>number1</td>
					<td><%= number1 %></td>
				</tr>
				<tr>
					<td>number2</td>
					<td><%= number2 %></td>
				</tr>
			</tbody>
		</table>
		
		<table border = 1>
		<%for(int i = 0; i < number1; i++) {%>
			<tr>
		<%	for(int j = 0; j < number2; j++) {%>
				<td style = 'background-color:<%= (i + j) % 2 == 0 ? "#ffffcc" :  "#ccffcc" %>; padding: 5px;'> 
				</td>
		<%	} %>
			</tr>
		<%} %>
		</table>
		
		
	</div>
</body>
</html>

