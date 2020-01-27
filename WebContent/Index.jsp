<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Style.css">
</head>
<body id="body">

   <div id="map"></div>
    <script>
		function initMap() {
  		var malmo = {lat: 55.604980, lng: 13.003822};
  		var map = new google.maps.Map(
      		document.getElementById('map'), {zoom: 4, center: malmo});
  		var marker = new google.maps.Marker({position: uluru, map: map});
		}
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC_tDySudXVuZRb7_O-x4bBYasm9_eThn4&callback=initMap">
    </script>

<form action="<%= request.getContextPath() %>/Servlet" method="post">



<h1 id="h1"
> Where do you want to go?</h1>

Current position: <input id="current" type="text" name="current"><br>
Search for address, stop or station: <input id="go" type="text" name="to"><br>
<input type="submit" value="Search">

</form>




</body>
</html>