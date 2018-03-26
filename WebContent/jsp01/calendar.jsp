<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

input.form-control {
	width: 200px;
}
</style>

<title>Insert title here</title>
</head>
<body>
	<%
String[] day = {"일", "월", "화", "수", "목", "금", "토"};

request.setCharacterEncoding("utf-8");

Calendar calendar = Calendar.getInstance();
String year = request.getParameter("year");
String month = request.getParameter("month");

if(year != null){ calendar.set(Calendar.YEAR, Integer.valueOf(year)); }
if(month != null) { calendar.set(Calendar.MONTH, Integer.valueOf(month) - 1); }
calendar.set(Calendar.DATE, 1);
%>

	<div class="container">
		<form action="" method="post">
			<h1>날짜 입력 폼</h1>

			<div class="form-group">
				<label>YEAR:</label> <input type="text" name="year"
					class="form-contrl" value=<%= calendar.get(Calendar.YEAR)%> />
			</div>

			<div class="form-group">
				<label>MONTH:</label> <input type="text" name="month"
					class="form-contrl" value=<%= calendar.get(Calendar.MONTH) + 1%> />
			</div>

			<div class="form-group">
				<input type="submit" class="btn btn-primary" name="cmd" value="확인" />
				<input type="reset" class="btn btn-default" value="취소" />
			</div>

		</form>
	</div>
	<table border=1>

		<tr>
			<td colspan=7><%= calendar.get(Calendar.YEAR) %></td>
		</tr>
		<tr>
			<td colspan=7><%= calendar.get(Calendar.MONTH) + 1 %></td>
		</tr>

		<tr>
			<%   for(int i = 0; i < day.length; i++){ %>
			<td><%= day[i] %></td>
			<%} %>
		</tr>
		<% 
	    int lastDay = calendar.getActualMaximum(Calendar.DATE);
	    int current = calendar.get(Calendar.DAY_OF_WEEK);
	    int date = 1;
	    %>
		<tr>
		<%for(int i = 0; i < current - 1; i++) {%>
			<td> </td>
		<%} %>
		
		<%for(int i = current; i < 8; i++) {%>
			<td><%= date++ %> </td>
		<%} %>
		</tr>
		
		<%while(date <= lastDay){ %>
		<tr>
			<%for(int i = 0; i < 7 && date <= lastDay; i++) {%>
			<td><%= date++ %></td>
			<%} %>
		</tr>
		<%} %>

	</table>

</body>
</html>