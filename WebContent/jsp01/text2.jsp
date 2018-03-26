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
input.form-control {
	width: 200px;
}
</style>
</head>
<body>
<%
String s1 = request.getParameter("number1");
Integer number1 = s1 != null ? Integer.parseInt(s1) : null;
String s2 = request.getParameter("number2");
Integer number2 = s2 != null ? Integer.parseInt(s2) : null;

String cmd = request.getParameter("cmd");
Integer result = null;
if(cmd != null){
if (cmd.equals("+")) result = number1 + number2;
else if (cmd.equals("-")) result = number1 - number2;
else if (cmd.equals("*")) result = number1 * number2;
else if (cmd.equals("/")) result = number1 / number2;
}
%>
	<div class="container">
		<form action="" method="get">
			<h1>텍스트 입력폼 02</h1>

			<div class="form-group">
				<label>number1:</label> <input type="text" name="number1"
					class="form-control" value=<%= result != null ? result : " " %> />
			</div>

			<div class="form-group">
				<label>number2:</label> <input type="text" name="number2"
					class="form-control" />
			</div>

			<div class="form-group">
				<input type="submit" class="btn btn-default" name="cmd" value="+">
				<input type="submit" class="btn btn-default" name="cmd" value="-">
				<input type="submit" class="btn btn-default" name="cmd" value="*">
				<input type="submit" class="btn btn-default" name="cmd" value="/">
			</div>
		</form>
	</div>
</body>
</html>

