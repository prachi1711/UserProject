<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">  
  <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/js/assets/jquery-1.11.2.min.js" />"></script>
  <script src="<c:url value="/resources/js/assets/angular.min.js" />"></script>
  <script src="<c:url value="/resources/js/home.js" />"></script>
	<title>Home</title>
	<script>
   
	</script>
</head>
<body>
<h2>
	User Project  
</h2>  
	<div>
 	    <textarea rows="1" cols="90" name="userPost" id="userPost" placeholder="Enter text here..."> </textarea>
 	    <input type="submit" value="Done" id="doneBtn" name="doneBtn">
	</div>
  <div> <span id="outputTxt"></span></div>  
</body>
</html>


