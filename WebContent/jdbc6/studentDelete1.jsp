<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.net.*, lecture1.jdbc6.*, lecture1.*" %>
<%
String s1 = request.getParameter("id");
int id = ParseUtils.parseInt(s1, 0);
String pg = request.getParameter("pg");
String srchText = request.getParameter("st");
if (srchText == null) srchText = "";
String srchTextEncoded = URLEncoder.encode(srchText, "UTF-8");
String od = request.getParameter("od");

StudentDAO.delete(id);
response.sendRedirect("studentList1.jsp?pg=" + pg + "&st=" + srchTextEncoded + "&od=" + od);
%>
