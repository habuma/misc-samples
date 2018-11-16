package mouseportation.web;

import java.util.List;

import mouseportation.Place;
import mouseportation.Route;
import mouseportation.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places")
public class RouteController {

	private RouteService routeService;

	@Autowired
	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Place> places() {
		return routeService.findAllPlaces();
	}
	
	@RequestMapping(value="/routes", method=RequestMethod.GET)
	public List<Route> routes(
			@RequestParam(value="o") String origin, 
			@RequestParam(value="d") String destination,
			@RequestParam(value="l", defaultValue="5") int limit) {
		return routeService.findRoutes(origin, destination, limit);
	}
	
}
