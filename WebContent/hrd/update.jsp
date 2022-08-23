<%@page import="hrd.dao.HrdDao"%>
<%@page import="hrd.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    <%
    request.setCharacterEncoding("UTF-8");
   int seq = Integer.parseInt(request.getParameter("seq"));
	MemberVO vo = new MemberVO(seq,
			null,
			request.getParameter("tel"),
			request.getParameter("address"),
			null,
			null,
			request.getParameter("grade"),
			request.getParameter("city")
			);
    
    //out.print(vo);
    HrdDao dao = HrdDao.getInstance();
    int cnt = dao.updateMember(vo);
    out.print("<script>");
    if(cnt==1) {
    	out.print("alert('회원수정이 완료되었습니다.');");
    	out.print("location.href='updateForm.jsp?custno="+ seq +"';");
    }else {
    	out.print("alert('회원수정 실패하였습니다.');");
    	out.print("history.back();");
    }
    out.print("</script>");
    
    %>