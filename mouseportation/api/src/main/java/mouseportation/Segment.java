package mouseportation;

public class Segment {

	private final Place start;
	private final Place end;
	private final String mode;
	private final long time;

	public Segment(Place start, Place end, String mode, long time) {
		this.start = start;
		this.end = end;
		this.mode = mode;
		this.time = time;
	}
	
	public Place getStart() {
		return start;
	}

	public Place getEnd() {
		return end;
	}

	public String getMode() {
		return mode;
	}

	public long getTime() {
		return time;
	}
	
	public String toString() {
		return mode + " from " + start + " to " + end + " (" + time + " minutes)";
	}

}
