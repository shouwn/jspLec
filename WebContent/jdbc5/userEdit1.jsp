<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="lecture1.jdbc5.*, java.net.*, java.util.*, lecture1.*" %>
<%
request.setCharacterEncoding("UTF-8");

String 에러메시지 = null;
String s1 = request.getParameter("id");
int id = ParseUtils.parseInt(s1, 0);

String pg = request.getParameter("pg");
String srchText = request.getParameter("srchText");
if (srchText == null) srchText = "";
String srchTextEncoded = URLEncoder.encode(srchText, "UTF-8");

User user = null;

if (request.getMethod().equals("GET")) {
    user = UserDAO.findOne(id);
}
else {
    user = new User();
    user.setId(id);
    user.setUserid(request.getParameter("userid"));
    user.setName(request.getParameter("userName"));
    user.setEmail(request.getParameter("userEmail"));
    String s2 = request.getParameter("departmentId");
    user.setDepartmentId(ParseUtils.parseInt(s2, -1));
    String s3 = request.getParameter("userEnabled");
    user.setEnabled("true".equals(s3));
    user.setUserType(request.getParameter("userType"));
    
    if (s1 == null || s1.length() == 0) 
        에러메시지 = "ID를 입력하세요";
    else if (user.getUserid() == null || user.getUserid().length() == 0) 
        에러메시지 = "유저 아이디를 입력하세요";
    else if (user.getName() == null || user.getName().length() == 0) 
        에러메시지 = "이름을 입력하세요";
    else if (user.getEmail() == null || user.getEmail().length() == 0) 
        에러메시지 = "이메일을 입력하세요";
    else if (user.getDepartmentId() == -1)
        에러메시지 = "학과를 선택하세요";
    else if (user.getUserType() == null || user.getUserType().length() == 0) 
        에러메시지 = "이메일을 입력하세요";
    else {
        UserDAO.update(user);
        response.sendRedirect("userList1.jsp?pg=" + pg + "&srchText=" + srchTextEncoded);
        return;
    }
}
%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
      body { font-family: 굴림체; }
      input.form-control, select.form-control { width: 200px; }
  </style>
</head>
<body>

<div class="container">

<h1>유저 등록</h1>
<hr />

<form method="post">
  <div class="form-group">
    <label>유저 아이디</label>
    <input type="text" class="form-control" name="userid" 
           value="<%= user.getUserid() %>" />
  </div>
  <div class="form-group">
    <label>이름</label>
    <input type="text" class="form-control" name="userName" value="<%= user.getName() %>" />
  </div>
  <div class="form-group">
    <label>이메일</label>
    <input type="text" class="form-control" name="userEmail" value="<%= user.getEmail() %>" />
  </div>
  <div class="form-group">
    <label>학과</label>
    <select class="form-control" name="departmentId">
      <% for (Department d : DepartmentDAO.findAll()) { %>
          <% String selected = user.getDepartmentId()==d.getId() ? "selected" : ""; %>
          <option value="<%= d.getId() %>" <%= selected %>>
            <%= d.getDepartmentName() %>
          </option>
      <% } %>
    </select>
  </div>
  <div class="checkbox">
    <label>
    	<input type="checkbox" name="userEnabled" value="true" <%= user.isEnabled() ? "checked" : "" %> /> enabled
    </label>
  </div>
  <div class="form-group">
    <label>사용자 타입</label><br/>
    <div class="radio">
    <%for(String type : UserDAO.findUserType()) {%>
    <label>
    	<input type="radio" name="userType" value="<%= type %>" <%= type.equals(user.getUserType()) ? "checked" : ""%> />
    	<%= type %>
    </label>
    <%} %>
    </div>
  </div>
  <button type="submit" class="btn btn-primary">
    <i class="glyphicon glyphicon-ok"></i> 저장
  </button>
  <a href="userDelete1.jsp?id=<%= id %>&pg=<%= pg %>&srchText=<%= srchTextEncoded %>" 
     class="btn btn-danger" onclick="return confirm('삭제하시겠습니까?')">
    <i class="glyphicon glyphicon-trash"></i> 삭제
  </a>
  <a href="userList1.jsp?pg=<%= pg %>&srchText=<%= srchTextEncoded %>" 
     class="btn btn-default">
    <i class="glyphicon glyphicon-list"></i> 목록으로
  </a>
</form>

<hr />
<% if (에러메시지 != null) { %>
  <div class="alert alert-danger">
    학생등록 실패: <%= 에러메시지 %>
  </div>
<% } %>
</div>
</body>
</html>
