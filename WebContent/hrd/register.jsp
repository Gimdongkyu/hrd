<%@page import="hrd.dao.HrdDao"%>
<%@page import="hrd.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    <%
    request.setCharacterEncoding("UTF-8");
    
	MemberVO vo = new MemberVO(Integer.parseInt(request.getParameter("seq")),
			request.getParameter("name"),
			request.getParameter("tel"),
			request.getParameter("address"),
			null,
			request.getParameter("regdate"),
			request.getParameter("grade"),
			request.getParameter("city")
			);
    
    //out.print(vo);
    HrdDao dao = HrdDao.getInstance();
    int cnt = dao.registMember(vo);
    out.print("<script>");
    if(cnt==1) {
    	out.print("alert('회원등록이 완료되었습니다.');");
    	out.print("location.href='memberList.jsp';");
    }else {
    	out.print("alert('회원등록 실패하였습니다.');");
    	out.print("history.back();");
    }
    out.print("</script>");
    
    %>