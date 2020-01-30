<%@page import="travelPackage.aBean"%>
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
				out.println("<b>" + "From: " + "</b>" + journeys.get(i).getFrom()  + "<br />");
				out.println("<b>" + "To: " + "</b>" + journeys.get(i).getTo() + "<br />");
				out.println("<b>" + "Departure time: " + "</b>" + journeys.get(i).getDepartureTime() + "<br />");
				out.println("<b>" + "Arrival Time: " + "</b>" + journeys.get(i).getArrivalTime() + "<br />");
				out.println("<br />");
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

</body>
</html>