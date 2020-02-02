<%@page import="travelPackage.TravelData"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="travelPackage.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
<link rel="stylesheet" href="Style.css">
</head>
<body id="body">

<!-- data retrieval and presentation of result -->
<fieldset class="container">
	<legend><b>RESULT:</b></legend>
	<div class="row">
   		<div class="column">
			<%
			
			TravelData travelData = (TravelData) request.getAttribute("travelData");
			ArrayList<Journey> journeys = travelData.getJourneys();
			
			for (int i = 0; i < journeys.size(); i++) {
				out.println("<ul class='list'>" + "<b>" + "From: " + "</b>" + journeys.get(i).getFrom());
				out.println("<b>" + " To: " + "</b>" + journeys.get(i).getTo() + "<br />");
				out.println("<b>" + "Departure time: " + "</b>" + journeys.get(i).getDepartureTime() + "<br />");
				out.println("<b>" + "Arrival Time: " + "</b>" + journeys.get(i).getArrivalTime() + "</ul>");
			}
				
			out.println("<h2>" + "Have a nice trip!" + "</h2>");
			
			%>
 		</div>
 		<!-- code for map and gif -->
 		<div class="column">
			<iframe class="ledebur" src="https://www.openstreetmap.org/export/embed.html?bbox=13.008858561515808%2C55.59824121680327%2C13.012479543685913%2C55.5993050325025&amp;layer=mapnik&amp;marker=55.5987731282586%2C13.01066905260086"></iframe>	
			<br/><small><a href="https://www.openstreetmap.org/?mlat=55.59877&amp;mlon=13.01067#map=19/55.59877/13.01067">You are here! Click for bigger map.</a></small>	
			<center><img src=bus.gif></center>
		</div>
	</div>
</fieldset>

<br><br>
<!-- code to show cookies -->
<p>Cookie testing / refresh page.</p>
<%
try {
	response.setContentType("text/html");
	
	Cookie cookies[] = request.getCookies();
	String cookieName1 = cookies[0].getName();
	String cookieName2 = cookies[1].getName();
	String cookieName3 = cookies[2].getName();
	String cookieName4 = cookies[3].getName();
	
	out.println("<br><b>Last search: </b>");
	
	if (cookieName1.contentEquals("currentPos")) {
		String currentPosition = cookies[0].getValue(); 
		out.println("<br><b> From: </b>" + currentPosition);
	}
	else if (cookieName2.contentEquals("currentPos")) {
		String currentPosition = cookies[1].getValue(); 
		out.println("<br><b> From: </b>" + currentPosition);
	}
	else if (cookieName3.contentEquals("currentPos")) {
		String currentPosition = cookies[2].getValue(); 
		out.println("<br><b> From: </b>" + currentPosition);
	}
	else if (cookieName3.contentEquals("currentPos")) {
		String currentPosition = cookies[2].getValue(); 
		out.println("<br><b> From: </b>" + currentPosition);
	}
	else {
		out.println("<br><b> From: </b>There is no recent searches");
	}

	if (cookieName1.contentEquals("searchPos")) {
		String searchPosition = cookies[0].getValue(); 
		out.println("<br><b> To: </b>" + searchPosition);
	}
	else if (cookieName2.contentEquals("searchPos")) {
		String searchPosition = cookies[1].getValue(); 
		out.println("<br><b> To: </b>" + searchPosition);
	}
	else if (cookieName3.contentEquals("searchPos")) {
		String searchPosition = cookies[2].getValue(); 
		out.println("<br><b> To: </b>" + searchPosition);
	}
	else if (cookieName3.contentEquals("searchPos")) {
		String searchPosition = cookies[2].getValue(); 
		out.println("<br><b> To: </b>" + searchPosition);
	}
	else {
		out.println("<br><b> To: </b>There is no recent searches");
	}
	
	out.println("<br><b>Cookies: </b>");
	for(int i = 0; i < cookies.length; i++) {  
		out.println("<br>" + cookies[i].getValue());
	} 		
}
catch (Exception e) {
	System.out.println(e);
}			
%>	

</body>
</html>