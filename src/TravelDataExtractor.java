

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TravelDataExtractor {
	
	private Document document;
	private TravelData travelData = new TravelData();
	private boolean dataExtracted = false;
	
	public TravelDataExtractor(Document document) {
		
		this.document = document;
	}
	
	public Element getDocumentElement() {
		
		return document.getDocumentElement();
	}
	
	public void normalizeDocumentElement() {
		
		//normalize the XML response
		document.getDocumentElement().normalize();
	}
	
	private NodeList getJourneys() {
		
		return document.getElementsByTagName("Journey");
	}
	
	public TravelData getTravelData() {
		
		if (!dataExtracted) {
			
			extract();
		}
		
		return travelData;
	}

	public void extract() {
		
		System.out.println("Extract travelData");
		
		NodeList journeys = getJourneys();
		
		for (int i = 0; i < journeys.getLength(); i++) {
			
			Node journey = journeys.item(i);
			
			handleJourneyNode(journey);
		}
		
		dataExtracted = true;
	}
	
	public void handleJourneyNode(Node journeyNode) {
		
//		NodeList nodes = journeyNode.getChildNodes();
		Node node = journeyNode.getFirstChild();
		Journey journey = new Journey();
		
		System.out.println("Handle journey node");

		while (node != null) {
			
			String name = node.getNodeName();
//			System.out.println("Node name: " + name);
			
			switch (name) {
			case "DepDateTime":
				journey.setDepartureTime(node.getFirstChild().getNodeValue());
				System.out.println("Dep. time: " + journey.getDepartureTime());
				break;
			case "ArrDateTime":
				journey.setArrivalTime(node.getFirstChild().getNodeValue());
				System.out.println("Arr. time: " + journey.getArrivalTime());
				break;
			case "RouteLinks":
				handleRouteLinks(node, journey);
				break;
			default:
				break;
			}
			
			node = node.getNextSibling();
		}
		
		travelData.addJourney(journey);
	}

	private void handleRouteLinks(Node routesNode, Journey journey) {
		
		NodeList routes = routesNode.getChildNodes();
		
		for (int i = 0; i < routes.getLength(); i++) {
			
			handleRoute(routes.item(i), journey);
		}
	
	}

	private void handleRoute(Node routeNode, Journey journey) {
	
		Node node = routeNode.getFirstChild();
		Route route = new Route();
		
		System.out.println("Handle route node");

		while (node != null) {
			
			String name = node.getNodeName();
//			System.out.println("Node name: " + name);

			switch (name) {
			case "DepDateTime":
				route.setDepartureTime(node.getFirstChild().getNodeValue());
				System.out.println("Dep. time: " + route.getDepartureTime());
				break;
			case "ArrDateTime":
				route.setArrivalTime(node.getFirstChild().getNodeValue());
				System.out.println("Arr. time: " + route.getArrivalTime());
				break;
			case "From":
				route.setFrom(getStopPointName(node));
				break;
			case "To":
				route.setTo(getStopPointName(node));
				break;
			case "Line":
				handleLine(node, route);
				break;
			default:
				break;
			}
			
			node = node.getNextSibling();
		}
		
		journey.addRoute(route);
	}

	private String getStopPointName(Node stopPointNode) {

		Node node = stopPointNode.getFirstChild();
		String stopPointName = "";
		
		while (node != null) {
			
			String name = node.getNodeName();
			
//			System.out.println("Node name: " + name);
			if (name == "Name") {
				
				stopPointName = node.getFirstChild().getNodeValue();
				System.out.println(stopPointName);
				break;
			}
			node = node.getNextSibling();
		}
		
		return stopPointName;
	}

	private void handleLine(Node lineNode, Route route) {

		Node node = lineNode.getFirstChild();
		
		System.out.println("Handle line node");

		while (node != null) {
			
			String name = node.getNodeName();
//			System.out.println("Node name: " + name);

			switch (name) {
			case "Name":
				route.setName(node.getFirstChild().getNodeValue());
				System.out.println(route.getName());
				break;
			case "LineTypeName":
				route.setLineType(node.getFirstChild().getNodeValue());
				System.out.println(route.getLineType());
				break;
			case "Towards":
				route.setTowards(node.getFirstChild().getNodeValue());
				System.out.println(route.getTowards());
				break;
			default:
				break;
			}
			
			node = node.getNextSibling();
		}	
	}
}
