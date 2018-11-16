package mouseportation.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mouseportation.Place;
import mouseportation.Route;
import mouseportation.RouteService;
import mouseportation.Segment;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Neo4jRouteService implements RouteService {

	private Neo4jTemplate neo4j;

	@Autowired
	public Neo4jRouteService(Neo4jTemplate neo4j) {
		this.neo4j = neo4j;
	}
	
	@Transactional
	public List<Place> findAllPlaces() {
		Result<Map<String, Object>> result = neo4j.query("MATCH (n) RETURN n ORDER by n.name", new HashMap<String, Object>());
		List<Place> places = new ArrayList<Place>();
		for(Iterator<Map<String, Object>> iterator = result.iterator(); iterator.hasNext();) {
			Map<String, Object> next = iterator.next();
			Node n = (Node) next.get("n");
			places.add(new Place((String) n.getProperty("name"), (String) n.getProperty("abbrev")));
		}
		return places;
	}
	
	@Transactional
	public List<Route> findRoutes(String origin, String destination, int limit) {		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("origin", origin);
		params.put("destination", destination);
		params.put("limit", limit);
		Result<Map<String, Object>> result = neo4j.query(SHORTEST_PATHS_QUERY, params);
		List<Route> routes = new ArrayList<Route>();
		for(Iterator<Map<String, Object>> iterator = result.iterator(); iterator.hasNext();) {
			Map<String, Object> next = iterator.next();
			Path path = (Path) next.get("p");
			Iterable<Relationship> relationships = path.relationships();
			List<Segment> segments = new ArrayList<Segment>();
			for (Relationship rel : relationships) {
				segments.add(new Segment(
						nodeToPlace(rel.getStartNode()),
						nodeToPlace(rel.getEndNode()),
						rel.getType().name(), 
						(long) rel.getProperty("time")));
			}
			if (segments.size() > 0) {
				routes.add(new Route(segments));
			}
		}
		
		return routes;
	}

	private Place nodeToPlace(Node node) {
		return new Place((String) node.getProperty("name"), (String) node.getProperty("abbrev"));
	}
	
	private static final String SHORTEST_PATHS_QUERY = 
			"MATCH (orig { abbrev:{origin} }),(dest { abbrev:{destination} }), p = allShortestPaths((orig)-[*]->(dest)) "
			+ "RETURN p, reduce(time=0, r in relationships(p) | time+r.time) AS totalTime ORDER BY totalTime LIMIT {limit}";

}
