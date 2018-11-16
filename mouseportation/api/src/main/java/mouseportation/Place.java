package mouseportation;


public class Place {

	private final String name;
	private final String abbreviation;

	public Place(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return abbreviation;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
}
