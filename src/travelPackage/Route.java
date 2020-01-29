package travelPackage;


public class Route {

	private String departureTime;
	private String arrivalTime;
	private String from;
	private String to;
	private String name;
	private String lineType;
	private String towards;
	private String number;
	private String service;
	private String line;
	
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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getTowards() {
		return towards;
	}

	public void setTowards(String towards) {
		this.towards = towards;
	}
	
	
}
