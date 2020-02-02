<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/cookieconsent2/3.0.3/cookieconsent.min.css" />
<script src="//cdnjs.cloudflare.com/ajax/libs/cookieconsent2/3.0.3/cookieconsent.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"></script>
<title>Index Skane App</title>
<link rel="stylesheet" href="Style.css">
</head>

<body id="body">

<!-- script code for map -->
<div id="map"></div>
    <script>
    var map = L.map('map').setView([55.604981, 13.003822], 14);
       
    L.tileLayer('https://api.maptiler.com/maps/basic/256/{z}/{x}/{y}.png?key=xmfJHLqvFpBzkbiNM8ov', {
    	attribution: '<a href="https://www.maptiler.com/copyright/" target="_blank">&copy; MapTiler</a> <a href="https://www.openstreetmap.org/copyright" layer=transportmap&amp target="_blank">&copy; OpenStreetMap contributors</a>',
    }).addTo(map);
    
    var marker = L.marker([55.59880451132639, 13.00845021573216]).addto(map);
    
	</script>


<!-- form to retrieve data -->
<form action="<%= request.getContextPath() %>/Servlet" method="post">

	<h1 id="h1"> Where do you want to go?</h1>
	
	<ul id="current">
		<li>Current position: </li>
	   	<li> <input id="current" type="text" name="current">
	</ul>  
	
	<ul id="go">
		<li>Search for address, stop or station: </li>
		<li> <input id="go" type="text" name="to">
	</ul> 
	<input type="submit" value="Go!">

</form>

<br><br>

<!-- code to show cookies (with one word and with swedish letters) -->
<p>Last search / refresh page.</p>
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

<br><br>

<!-- script code for cookie message -->
<script>
window.addEventListener("load", function(){
    window.cookieconsent.initialise({
        "palette": {
            "popup": {
                "background": "#edeff5",
                "text": "#838391"
            },
            "button": {
                "background": "#4b81e8"
            }
        },
        "theme": "classic",
        "type": "opt-in",
        onInitialise: function (status) {
            var type = this.options.type;
            var didConsent = this.hasConsented();
            if (type == 'opt-in' && didConsent) {
                console.log('Ready to fire cookies');
            }
            if (type == 'opt-out' && !didConsent) {
                // disable cookies
            }
        },
    })
});
</script>

</body>
</html>