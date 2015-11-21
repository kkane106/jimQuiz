<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="yellow">
<h2>${question}</h2>
<br/>
<form action="getQandA.do" method="get">
<c:forEach var ="a" items ="${answers}">
<input type="radio" name="answer" value="${a}">${a}</input>
<br/>
</c:forEach>
<br/>
Questions Array List size:
${size}
<p>
Counter equals:
${counter}
 <input type="submit" value="Next Question">
</form>
<form action="endTest.do" method="get">
<p>
 <input type="submit" value="End Test">
</form>
</body>
</html>