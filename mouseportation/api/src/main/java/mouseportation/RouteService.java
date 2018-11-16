package mouseportation;

import java.util.List;

public interface RouteService {

	List<Place> findAllPlaces();
	
	List<Route> findRoutes(String origin, String destination, int limit);
	
}
