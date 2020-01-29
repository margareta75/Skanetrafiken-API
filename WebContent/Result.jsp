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
<body>


<div >
<fieldset class="container">
  	<legend><b>RESULT:</b></legend>
<%

TravelData travelData = (TravelData) request.getAttribute("travelData");

ArrayList<Journey> journeys = travelData.getJourneys();

for ( int i = 0; i < journeys.size(); i++) {
	out.println("<b>" + "From: " + "</b>" + journeys.get(i).getFrom()  + "<br />");
	out.println("<b>" + "To: " + "</b>" + journeys.get(i).getTo() + "<br />");
	out.println("<b>" + "Arrival Time: " + "</b>" + journeys.get(i).getArrivalTime() + "<br />");
	out.println("<b>" + "Departure time: " + "</b>" + journeys.get(i).getDepartureTime() + "<br />");
	out.println("<br />");
}

	
out.println("<h2>" + "Have a nice trip!" + "</h2>");

out.println("<center><img src=bus.gif></center>");

%>
</fieldset>
</div>

</body>
</html>