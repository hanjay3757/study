<%@page import="com.peisia.mapper.VideoMapper"%>
<%@page import="com.peisia.dto.VideoVO"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<%
    // Spring ApplicationContext에서 VideoMapper 빈을 가져옴
    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
    VideoMapper videoMapper = context.getBean(VideoMapper.class);
    
    // VideoMapper를 통해 비디오 목록을 직접 가져옴
    request.setAttribute("list", videoMapper.getList());
    
    // VideoMapper의 다른 메소드들도 테스트
    Long testVno = 1L;
    
    // read 테스트
    request.setAttribute("video", videoMapper.read(testVno));
    
    // insert 테스트
    VideoVO newVideo = new VideoVO();
    newVideo.setTitle("테스트 비디오");
    newVideo.setContent("테스트 내용");
    newVideo.setWriter("작성자");
    videoMapper.insert(newVideo);
    
    // update 테스트 
    VideoVO updateVideo = videoMapper.read(testVno);
    if(updateVideo != null) {
        updateVideo.setTitle("수정된 제목");
        updateVideo.setContent("수정된 내용");
        updateVideo.setWriter("수정된 작성자");
        videoMapper.update(updateVideo);
    }
    
    // delete 테스트
    // videoMapper.delete(testVno);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비디오 목록 및 테스트</title>
</head>
<body>
<h1>비디오 목록</h1>

<h2>전체 비디오 목록:</h2>
<c:forEach var="video" items="${list}">
    <div>
        <p>비디오 번호: ${video.v_no}</p>
        <p>제목: ${video.v_title}</p>
        <p>내용: ${video.v_content}</p>
        <p>작성자: ${video.v_writer}</p>
        <hr>
    </div>
</c:forEach>

<h2>단일 비디오 조회 결과:</h2>
<div>
    <p>비디오 번호: ${video.v_no}</p>
    <p>제목: ${video.v_title}</p>
    <p>내용: ${video.v_content}</p>
    <p>작성자: ${video.v_writer}</p>
</div>

</body>
</html>