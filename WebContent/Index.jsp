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


   <div id="map"></div>
    
    
 <script>
    var map = L.map('map').setView([0, 0], 1);
    
    L.tileLayer('https://api.maptiler.com/maps/basic/256/{z}/{x}/{y}.png?key=xmfJHLqvFpBzkbiNM8ov', {
    	attribution: '<a href="https://www.maptiler.com/copyright/" target="_blank">&copy; MapTiler</a> <a href="https://www.openstreetmap.org/copyright" target="_blank">&copy; OpenStreetMap contributors</a>',
    }).addTo(map);

</script>


<form action="<%= request.getContextPath() %>/Servlet" method="post">



<h1 id="h1"
> Where do you want to go?</h1>

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

<script>
function char_convert() {

    var chars = ["Å","Ä","Ö"]; 
    var codes = ["a", "a", "o"];

    for(x=0; x<chars.length; x++){
        for (i=0; i<arguments.length; i++){
            arguments[i].value = arguments[i].value.replace(chars[x], codes[x]);
        }
        document.getElementById("current");

    }
 }

char_convert(this);

</script>

</body>
</html>