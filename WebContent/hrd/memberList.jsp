<%@page import="hrd.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>HRD Example</title>
    <link rel="stylesheet" href="table.css" />
    <link rel="stylesheet" href="layout.css">
  </head>
  <body>
 <%@include file="header.jsp" %> 
 <%
 @SuppressWarnings("unchecked")
 	List<MemberVO> list = (List<MemberVO>)request.getAttribute("list");
 %>
<main>
    <div>
      <h3>회원목록조회/수정</h3>
      <table>
        <tr>
          <th>회원번호</th>
          <th>회원성명</th>
          <th>전화번호</th>
          <th>주소</th>
          <th>가입일자</th>
          <th>고객등급</th>
          <th>거주지역</th>
        </tr>
      <%
		for(MemberVO vo : list){      
      %>
        <tr class="center">
          <td><a href="updateForm.jsp?custno=<%= vo.getCustno() %>"><%= vo.getCustno() %></a></td>
          <td><%= vo.getCustname() %></td>
          <td><%= vo.getPhone() %></td>
          <td><%= vo.getAddress() %></td>
          <td><%= vo.getJoindate() %></td> <!-- 출력형식 yyyy-mm-dd 이므로 Date 타입사용 -->
          <td><%= vo.getGrade() %></td>
          <td><%= vo.getCity() %></td>
        </tr>
      <%
		}
      %>
      </table>
    </div>
</main>  
<%@include file="footer.jsp" %>
  </body>
</html>
