package travelPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String rawFrom;
		String rawTo;
		String from = "";
		String to = "";

		request.setCharacterEncoding("UTF-8");

		rawFrom = request.getParameter("current");
		rawTo = request.getParameter("to");
		
		if (rawFrom != null) {
			from = URLEncoder.encode(rawFrom, "UTF-8");
		}

		if (rawTo != null) {
			to = URLEncoder.encode(rawTo, "UTF-8");
		}

		
		// Building the Api so we can search the start point!
		String URLtoSend = "http://labs.skanetrafiken.se/v2.2/querystation.asp?inpPointFr=" + from;

		System.out.println(URLtoSend);

		// Set the URL that will be sent
		URL line_api_url = new URL(URLtoSend);

		// Create a HTTP connection to sent the GET request over
		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		// Make a Buffer to read the response from the API
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream(), "UTF-8"));

		// a String to temp save each line in the response
		String inputLine;

		// a String to save the full response to use later
		String ApiResponse = "";

		// loop through the whole response
		while ((inputLine = in.readLine()) != null) {

			// Save the temp line into the full response
			ApiResponse += inputLine;
		}
		in.close();
		System.out.println(ApiResponse);

		// Call a method to make a XMLdoc out of the full response
		Document doc = convertStringToXMLDocument(ApiResponse);

		// normalize the XML response
		doc.getDocumentElement().normalize();
		// check that the XML response is OK by getting the Root element
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		// Create a Node list that gets everything in and under Startpoints!
		NodeList nList = doc.getElementsByTagName("StartPoints");

		int startID = idFetch(nList, rawFrom); // fetches the ID  for the startStation

		// Building an apifor the To search as well! :)
		URLtoSend = "http://labs.skanetrafiken.se/v2.2/querystation.asp?inpPointFr=" + to;

		System.out.println(URLtoSend);

		// Set the URL that will be sent
		line_api_url = new URL(URLtoSend);

		// Create a HTTP connection to sent the GET request over
		linec = (HttpURLConnection) line_api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		// Make a Buffer to read the response from the API
		in = new BufferedReader(new InputStreamReader(linec.getInputStream(), "UTF-8"));

		// Emptying the apiResponse string for the loop downunder!
		ApiResponse = "";

		// loop through the whole response
		while ((inputLine = in.readLine()) != null) {

			// Save the temp line into the full response
			ApiResponse += inputLine;
		}
		in.close(); // closing in. because its best to do it
		System.out.println(ApiResponse);

		// Call the method to make a XMLdoc out of the full response
		doc = convertStringToXMLDocument(ApiResponse);

		// normalize the XML response
		doc.getDocumentElement().normalize();

		// check that the XML response is OK by getting the Root element
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		// Create a Node list that gets everything in and under Startpoints!
		NodeList dList = doc.getElementsByTagName("StartPoints");

		int endID = idFetch(dList, rawTo);

		// Making an array to store the two Ids that is needed for the next API response

		int travelIDs[] = { startID, endID };

		request.setAttribute("travelID", travelIDs);

		// Sending the saved id Data to the Travelservlet
		RequestDispatcher idSender = request.getRequestDispatcher("TravelDataServlet");
		idSender.forward(request, response);

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

	private int idFetch(NodeList node, String name) {
		NodeList nList = node.item(0).getChildNodes();
		int idNumber = 0;
		for (int i = 0; i < nList.getLength(); i++) { // går i genom alla points en efter en tills man hittar rätt
														// point!

			NodeList pointNode = nList.item(i).getChildNodes();

			for (int j = 0; j < pointNode.getLength(); j++) { // Ska hittar rätt ID och name
				if (pointNode.item(j).getNodeName().equals("Id")) {
					try {
						idNumber = Integer.parseInt(pointNode.item(j).getTextContent());
					} catch (NumberFormatException e) {
						idNumber = 0;
					}
				}

				if (pointNode.item(j).getTextContent().equals(name)) {
					return idNumber;
				}

			}

		}
		return idNumber;
	}

}
