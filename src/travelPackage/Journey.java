package travelPackage;


import java.util.ArrayList;

public class Journey {
	
	private String departureTime;
	private String arrivalTime;
	private String from;
	private String to;
	private ArrayList<Route> routes = new ArrayList<Route>();
	
	public String getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public String getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	public void addRoute(Route route) {
		this.routes.add(route);
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
