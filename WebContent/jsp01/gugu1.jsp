<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border = 1>
	<%
		for(int i = 1; i <= 9; i++){
			out.println("\t<tr>");
			for(int k = 2; k <= 9; k++){
				out.println("\t\t<td>" + k + " x " + i + " = " + i * k + "</td>");
			}
			out.println("\t</tr>");
		}
	%>
	
	</table>
	
</body>
</html>