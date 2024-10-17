<%@page import="com.peisia.dto.GuestDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    // 1. request 객체에서 "list"라는 이름으로 저장된 데이터를 가져옵니다.
    //    "list"는 이전에 서블릿이나 다른 JSP 페이지에서 setAttribute("list", list)로 저장된 데이터입니다.
    //    이 데이터는 ArrayList<GuestDto> 타입이어야 합니다.
    Object o = request.getAttribute("list");
    
    // 2. "o" 객체가 ArrayList<?> 타입인지 확인하는 과정입니다.
    //    여기서 "?"는 실제 타입이 무엇이든 상관없이 ArrayList의 객체인지만 체크하는 방식입니다.
    //    이를 통해 잘못된 타입을 처리할 수 있습니다.
    if (o instanceof ArrayList<?>) {
        ArrayList<?> tempList = (ArrayList<?>) o; // Object를 ArrayList<?>로 먼저 캐스팅합니다.
        
        // 3. tempList가 비어 있지 않으면, 첫 번째 요소가 실제로 GuestDto 객체인지 확인합니다.
        //    첫 번째 요소의 타입을 확인하여 "list"가 실제로 GuestDto 객체를 포함하는지 확인하는 단계입니다.
        //    이렇게 해야 타입 안전성이 보장됩니다.
        if (!tempList.isEmpty() && tempList.get(0) instanceof GuestDto) {
            // 4. 위 조건이 참이라면, 안전하게 ArrayList<GuestDto>로 캐스팅합니다.
            //    이제 tempList는 확실히 GuestDto 객체들로 이루어진 리스트입니다.
            ArrayList<GuestDto> list = (ArrayList<GuestDto>) tempList; 
            
            // 5. 리스트의 크기만큼 반복문을 돌며 각 객체를 처리합니다.
            //    list.size()는 리스트에 담긴 요소의 수를 반환합니다.
            for (int i = 0; i < list.size(); i++) {
                // 6. 리스트에서 GuestDto 객체를 하나씩 꺼내서 처리합니다.
                //    list.get(i)는 index i에 있는 GuestDto 객체를 반환합니다.
                Long bno = list.get(i).getBno();  // 게시글 번호를 가져옵니다.
                String btext = list.get(i).getBtext(); // 게시글 내용을 가져옵니다.
%>        
                <!-- 7. 게시판 번호(bno)와 게시글 내용(btext)을 화면에 출력합니다. -->
                <%= bno %>  <!-- 게시판 번호를 HTML로 출력 -->
                <%= btext %>  <!-- 게시글 내용을 HTML로 출력 -->
                <hr>  <!-- 게시글 사이에 수평선(hr)을 넣어 각 게시글을 구분합니다. -->
<%       
            }
        } else {
            // 8. 만약 리스트가 비어 있거나, 리스트의 첫 번째 항목이 GuestDto 객체가 아닌 경우
            //    해당 메시지를 출력하여 문제를 알립니다.
            out.print("List does not contain valid GuestDto objects.");
        }
    } else {
        // 9. 만약 "list" 객체가 ArrayList가 아닌 경우, 즉 타입이 맞지 않거나 null인 경우
        //    아래 메시지를 출력합니다. 이를 통해 문제를 쉽게 파악할 수 있습니다.
        out.print("The object 'list' is either null or not a valid ArrayList.");
    }
%>
</body>
</html>
