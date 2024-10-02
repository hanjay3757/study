<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculator</title>
</head>
<body>
    <h1>Calculator</h1>
    <form action="CalculatorServlet" method="post"> <!-- 서블릿으로 POST 요청 -->
        <label for="y">Enter y:</label>
        <input type="number" name="y" required>
        <br>
        <h2>x: 3000</h2> <!-- x 값을 보여주는 요소 -->
        <br>
        <button type="submit">Multiply</button> <!-- 폼 제출 -->
    </form>
    <h2 id="result">
        <% 
            // 결과 출력 부분
            String result = (String) request.getAttribute("result");
            if (result != null) {
                out.println("Result: " + result);
            }
        %>
    </h2>
</body>
</html>
