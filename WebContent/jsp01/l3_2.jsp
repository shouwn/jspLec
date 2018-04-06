<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
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
body form label {
	margin-right: 20px;
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
String s = request.getParameter("number");
int number = s != null ? Integer.valueOf(s) : 0;
String cmd = request.getParameter("cmd");

if("++".equals(cmd)) number++;
else if("--".equals(cmd)) number--;	
%>
	
	<div class="container">
		<form>
			<h1>number:</h1>

			<div class="form-group">
				<input type="text" name="number"
					class="form-contrl" value=<%= number%> />
			</div>

			<div class="form-group">
				<input type="submit" class="btn btn-default" name="cmd" value="++" />
				<input type="submit" class="btn btn-default" name="cmd" value="--" />
			</div>

		</form>
	</div>
</body>
</html>


