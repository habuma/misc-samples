package mouseportation;

import static java.util.Collections.unmodifiableList;

import java.util.List;

public class Route {
	
	private final List<Segment> segments;
	private final long totalTime;

	public Route(List<Segment> segments) {
		this.segments = segments;
		
		long time = 0;
		for (Segment segment : segments) {
			time += segment.getTime();
		}
		this.totalTime = time;
	}
	
	public List<Segment> getSegments() {
		return unmodifiableList(segments);
	}
	
	public long getTotalTime() {
		return totalTime;
	}

}
