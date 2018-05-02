<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	table { border-collapse: collapse; }
	td { padding: 5px; border: solid 1px gray; }
</style>
</head>
<body>

<table border = 1>
<%
int line = -1;
for(int i : new int[] {2, 6}){ 
	line++;
%>
	<tr>
<%	for(int max = i + 3; i <= max; i++) {%>
		<td style = "background:<%= (i + line) % 2 == 0 ? "#ccffcc" : "#ffffcc"%>">
<%		for(int j = 1; j < 10; j++){ %>
			<%= i + " x " + j + " = " + (i * j)%><br/>
<%		} %>
		</td>
<%	} %>
	</tr>
<%} %>
</table>
</body>
</html>