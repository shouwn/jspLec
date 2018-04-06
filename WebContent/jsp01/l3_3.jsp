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
String s = request.getParameter("select");
String one_selected = "one".equals(s) ? "selected" : "";
String two_selected = "two".equals(s) ? "selected" : "";
String three_selected = "three".equals(s) ? "selected" : "";

String one_checked = "one".equals(s) ? "checked" : "";
String two_checked = "two".equals(s) ? "checked" : "";
String three_checked = "three".equals(s) ? "checked" : "";
String text2 = s == null ? "one" : s;
%>

	<div class="container">
		<form>
			<h1>number:</h1>

			<div class="form-group">
				<select name="select" class="form-control">
					<option <%= one_selected %>>one</option>
					<option <%= two_selected %>>two</option>
					<option <%= three_selected %>>three</option>
				</select>
			</div>

			<div class="radio">
				<label><input type = "radio" name = "radio" value = "one" 
					<%= one_checked %> /> one
				</label>
				<label><input type = "radio" name = "radio" value = "two" 
					<%= two_checked %> /> two
				</label>
				<label><input type = "radio" name = "radio" value = "three" 
					<%= three_checked %> /> three
				</label>
			</div>
			
			<div class="form-group">
				<input type="text" class="btn btn-default" name="text2" value=<%= text2 %> />
			</div>

			<div class="form-group">
				<input type="submit" class="btn btn-default" name="cmd" value="확인" />
			</div>

		</form>
	</div>
</body>
</html>


