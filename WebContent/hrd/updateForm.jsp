<%@page import="hrd.vo.MemberVO"%>
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
  </head>
  <body>
  <%@include file="header.jsp" %>
  <%
  	HrdDao dao = HrdDao.getInstance();
  	MemberVO vo = null;

  //http://localhost:8080/hrd/updateForm.jsp?custno=100002 와 같이
  //method='get' 으로 입력파라미터를 보내면 url에서 수정될 경우 오류가 생기므로 try catch 꼭 씁니다. 
  	try{		
		int custno = Integer.parseInt(request.getParameter("custno"));  		
		vo = dao.getMember(custno);
		if(vo==null) throw new Exception();
  	}catch (Exception e){
  %>
  	<script type="text/javascript">
  		alert('회원번호가 잘못된 값 또는 없는 값 입니다.');
  		history.back();
  	</script>   
  	<!-- custno 파라미터가 없을 때 또는 잘못된 값으로 vo 가 null 일때 위의 스크립트 실행해야하는데 
  		그러기 전에 아래 출력하는 vo.getCustno() 에서 먼저 오류가 생깁니다. 그래서 38번 라인 코드 추가합니다.
  	 -->
  <%		
  	}
  
  	if(vo!=null) {
  %>
  <main>
    <div>
      <h3>홈쇼핑 회원 정보 수정</h3>
      <form action="update.jsp" method="post">
        <table>
          <tr>
            <th>회원번호(자동발생)</th>
            <td><input type="text" name="seq" value = "<%= vo.getCustno() %>"  class="small_box" readonly/></td>
          </tr>
          <tr>
            <th>회원성명</th>
            <td><input type="text" name="name" class="small_box" value="<%=vo.getCustname()%>" readonly/></td>
          </tr>
        <tr>
          <th>회원전화</th>
          <td><input type="text" name="tel" class="mid_box" value="<%=vo.getPhone() %>" /></td>
        </tr>
        <tr>
          <th>회원주소</th>
          <td><input type="text" name="address" class="large_box" value="<%=vo.getAddress() %>" /></td>
        </tr>
        <tr>
          <th>가입일자</th>
          <td><input type="text" name="regdate" class="small_box" value="<%=vo.getJoindate() %>" readonly/></td>
        </tr>
        <tr>
          <th>고객등급 [A:VIP,B:일반,C:직원]</th>
          <td><input type="text" name="grade" class="small_box" value="<%=vo.getGrade() %>" /></td>
        </tr>
        <tr>
          <th>도시코드</th>
          <td><input type="number" name="city" class="small_box" value="<%=vo.getCity() %>" /></td>
        </tr>
        <tr>
          <td colspan="2" style="text-align: center;">
            <button type="button" onclick="valid_check()">수정</button>
            <button type="button" onclick="location.href='members.jsp'">조회</button> <!-- //메모장 설명 16번 참고 -->
          </td>
        </tr>
      </table>
    </form>
    </div>
  </main>
  <%
  	}
  %>
  <%@include file="footer.jsp" %>
  </body>
</html>
