<%@page import="hrd.dao.HrdDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>HRD Example</title>
    <link rel="stylesheet" href="table.css" />
    <link rel="stylesheet" href="layout.css">
    <script src="validForm.js"></script>
    <link rel="stylesheet" href="table.css"> 
    <link rel="stylesheet" href="layout.css"> 
  </head>
  <body>
  <%@include file="header.jsp" %>
  <%
  	HrdDao dao = HrdDao.getInstance();
  	int seq = dao.getNextSeq();
  %>
  <main>
    <div>
      <h3>학생 등록</h3>
      <form action="register.jsp" method="post">
        <table>
          <tr>
            <th>순번(자동발생)</th>
            <td><input type="text" name="seq" value = "<%=seq %>"  class="small_box" /></td>
          </tr>
          <tr>
            <th>회원성명</th>
            <td><input type="text" name="name" class="small_box" /></td>
          </tr>
        <tr>
          <th>회원전화</th>
          <td><input type="text" name="tel" class="mid_box" /></td>
        </tr>
        <tr>
          <th>회원주소</th>
          <td><input type="text" name="address" class="large_box" /></td>
        </tr>
        <tr>
          <th>가입일자</th>
          <td><input type="text" name="regdate" class="small_box" /></td>
        </tr>
        <tr>
          <th>고객등급 [A:VIP,B:일반,C:직원]</th>
          <td><input type="text" name="grade" class="small_box" /></td>
        </tr>
        <tr>
          <th>도시코드</th>
          <td><input type="number" name="city" class="small_box" /></td>
        </tr>
        <tr>
          <td colspan="2" style="text-align: center;">
            <button type="button" onclick="valid_check()">등록</button>
            <button type="button" onclick="location.href='members.jsp'">조회</button> <!-- //메모장 설명 16번 참고 -->
          </td>
        </tr>
      </table>
    </form>
    </div>
  </main>
  <%@include file="footer.jsp" %>
  </body>
</html>
