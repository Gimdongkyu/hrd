<%@page import="hrd.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="hrd.dao.HrdDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HrdDao dao = HrdDao.getInstance();
	
	List<MemberVO> list = dao.getMembers();
	request.setAttribute("list", list);
	pageContext.forward("memberList.jsp");
%>