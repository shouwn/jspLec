<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>Servlet 3.0 File Upload Example</title>
</head>
<body>

  <div class="container">
    <h1>파일업로드 결과</h1>

    <table class="table table-bordered">
      <tr>
        <td>제목:</td>
        <td><%= request.getAttribute("제목") %></td>
      </tr>
      <tr>
        <td>파일명:</td>
        <td><%= request.getAttribute("파일명") %></td>
      </tr>
      <tr>
        <td>파일크기:</td>
        <td><%= request.getAttribute("파일크기") %></td>
      </tr>
      <% if (request.getAttribute("저장된파일") != null) { %>
        <tr>
          <td>저장된파일:</td>
          <td><%= request.getAttribute("저장된파일") %></td>
        </tr>
        <tr>
          <td>다운로드 링크:</td>
          <td><%= request.getAttribute("다운로드 링크") %></td>
        </tr>
      <% } %>
    </table>
  </div>
</body>
</html>
