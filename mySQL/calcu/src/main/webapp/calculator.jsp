<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculator</title>
</head>
<body>
    <h1>Calculator</h1>
    <form action="CalculatorServlet" method="post"> <!-- 서블릿으로 POST 요청을 보냄 -->
        <h2>x: 3000</h2> <!-- x 값을 고정된 값으로 표시 -->
        <label for="y">Enter y:</label>
        <input type="number" name="y" required> <!-- 사용자로부터 y 값을 입력 받음 -->
        <button type="submit">Multiply</button> <!-- 곱셈 버튼 -->
    </form>
    <h2 id="result">
        <% 
            // 세션에서 결과를 가져옴
            String result = (String) session.getAttribute("result");
            if (result != null) {
                out.println("Result: " + result); // 결과를 출력
                // 결과를 한 번만 보여주기 위해 세션에서 제거
                session.removeAttribute("result");
            }
        %>
    </h2>
</body>
</html>
