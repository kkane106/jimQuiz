<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Question</title>
</head>
<body>
	<h1>Add Question</h1>
	<form action="addNewQuestion.do" method="POST">
	  <p>Enter a Question:</p><br />
	  <textarea cols="50" rows="5" name="question" ></textarea><br />
	  <input type="text" size="50" name="answer" placeholder="first answer" />
	  <input type="radio" name="correct" value="0" /><br/>
	  <input type="text" size="50" name="answer" placeholder="second answer" />
	  <input type="radio" name="correct" value="1" /><br/>
	  <input type="text" size="50" name="answer" placeholder="third answer" />
	  <input type="radio" name="correct" value="2" /><br/>
	  <input type="text" size="50" name="answer" placeholder="fourth answer" />
	  <input type="radio" name="correct" value="3" /><br/>
	  <input type="submit" value="Create Question" />
	</form>
</body>
</html>