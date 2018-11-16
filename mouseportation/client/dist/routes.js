function Routes($scope, $http) {

	// TODO: Shouldn't be hardcoded to localhost
	var routesUrl = 'http://localhost:8080/places/routes';
	var NICE_MODES = {
		"BUS" : "Take a bus",
		"MONORAIL" : "Ride the Monorail",
		"BOAT" : "Take a boat",
		"WALK" : "Walk"
	};
	
	function friendlyIfy(data) {
		// friendly-ify the mode text
		data.forEach(function(route){
			route.segments.forEach(function(segment) {
				segment.mode = NICE_MODES[segment.mode];
			});
		});
	}
	
	function rememberChoices() {
		if(typeof(Storage) !== "undefined") {
			localStorage.setItem("choices", JSON.stringify($scope.search));
		}
	}
	
	function recallChoices() {
		if(typeof(Storage) !== "undefined" && localStorage.getItem("choices")) {
			var choices = localStorage.getItem("choices");
			$scope.search = JSON.parse(choices);
		} else {
			$scope.search = {origin:'mk', destination:'ep', limit:5};
		}
	}
	
	recallChoices();

	$scope.searchRoutes = function() {
		var url = routesUrl + '?o=' + $scope.search.origin + '&d=' + $scope.search.destination + '&l=' + $scope.search.limit;
		$http.get(url).
			success(function(data){
				friendlyIfy(data);
				$scope.routes = data;
				rememberChoices();
			});
	};

	$http.get('http://localhost:8080/places').
		success(function(data){
			$scope.places = data;
			$scope.searchRoutes();
		});
}