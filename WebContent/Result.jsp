<%@page import="travelPackage.aBean"%>
<%@page import="travelPackage.TravelData"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
<link rel="stylesheet" href="Style.css">
</head>
<body>


<div >
<fieldset class="container">
  	<legend><b>Result:</b></legend>
<%


String resultat1 = request.getParameter("resultat1");
String r2 = request.getParameter("r2");
String r3 = request.getParameter("r3");
String r4 = request.getParameter("r4");
String r5 = request.getParameter("r5");
String r6 = request.getParameter("r6");


out.println("<b>" + "R1: " + "</b>" + resultat1);
out.println("<b>" + "R2: " + "</b>" + r2 + "<br />");
out.println("<b>" + "R3: " + "</b>" + r3 + "<br />");
out.println("<b>" + "R4: " + "</b>" + r4 + "<br />");
out.println("<b>" + "R5: " + "</b>" + r5 + "<br />");
out.println("<b>" + "R6: " + "</b>" + r5 + "<br />");


if(resultat1.isEmpty() || r2.isEmpty() || r3.isEmpty() || 
	r4.isEmpty() || r5.isEmpty() || r6.isEmpty())
	out.println("<h2>" + "You have to choose destination!" + "</h2>" + "<br />" + "<br />");
else 
	out.println("<h2>" + "Have a nice trip!" + "</h2>"+ "<br />" + "<br />");


out.println("<center><img src=bus.gif></center>");

%>
</fieldset>
</div>

</body>
</html>