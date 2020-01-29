package travelPackage;


import java.util.ArrayList;

public class TravelData {
	
	private String from;
	private String to;
	private ArrayList<Journey> journeys = new ArrayList<Journey>();
	
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

	public ArrayList<Journey> getJourneys() {
		return journeys;
	}
	
	public void addJourney(Journey journey) {
		this.journeys.add(journey);
	}


	

}
