package travelPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;


/**
 * Servlet implementation class TravelDataServlet
 */
@WebServlet("/TravelDataServlet")
public class TravelDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TravelDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//
//		// Check if the right info got sent
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		String cityStr = request.getParameter("city");
//		out.print("<br>");
//		out.print("City= " + cityStr);
//
//		out.print("<br>");
//
//		String countryStr = request.getParameter("country");
//		out.print("Country= " + countryStr);
//		out.print("<br>");

		// Build the API call
		String URLtoSend = "http://www.labs.skanetrafiken.se/v2.2/resultspage.asp?"
				+ "cmdaction=next&selPointFr=malm%F6%20Stadshuset|80118|0&selPointTo=landskrona|82000|0&"
				+ "LastStart=2020-01-23%2016:38";

		System.out.println(URLtoSend);

		// Set the URL that will be sent
		URL line_api_url = new URL(URLtoSend);

		// Create a HTTP connection to sent the GET request over
		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		// Make a Buffer to read the response from the API
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		// a String to temp save each line in the response
		String inputLine;

		// a String to save the full response to use later
		String ApiResponse = "";

		// loop through the whole response
		while ((inputLine = in.readLine()) != null) {

			// System.out.println(inputLine);
			// Save the temp line into the full response
			ApiResponse += inputLine;
		}
		in.close();
		System.out.println(ApiResponse);

		// Call a method to make a XMLdoc out of the full response
		Document doc = convertStringToXMLDocument(ApiResponse);

		TravelDataExtractor dataExtractor = new TravelDataExtractor(doc);

		// normalize the XML response
		dataExtractor.normalizeDocumentElement();
		// check that the XML response is OK by getting the Root element
		System.out.println("Root element :" + dataExtractor.getDocumentElement().getNodeName());

		TravelData travelData = dataExtractor.getTravelData();

//		System.out.print(travelData.getJourneys().get(0).getRoutes().get(0).getFrom());
		
		request.setAttribute("travelData", travelData);
		RequestDispatcher rd = request.getRequestDispatcher("Result.jsp");
		rd.forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Method the makes a XML doc out of a string, if it is in a XML format.
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
