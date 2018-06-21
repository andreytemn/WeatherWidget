var serviceMap = {
	"Yahoo" : "yahooService",
	"World Weather Online" : "worldWeatherOnlineService"
}

var fetchWeather = function(scope, http, location, cookieStore) {
	var url = location.absUrl() + "weather?city=" + scope.city + "&service="
			+ serviceMap[scope.service];

	http.get(url).then(function(response) {
		scope.response = response.data;
		scope.weatherInCity = "Weather in " + scope.city;
		scope.errorMessage = undefined;
		prepareViewableData(scope.response);
	}, function error(response) {
		scope.response = undefined;
		scope.weatherInCity = undefined;
		scope.errorMessage = response.data;
	});

	cookieStore.put('weatherWidgetCity', scope.city);
	cookieStore.put('weatherWidgetService', scope.service);
}

var prepareViewableData = function(response) {
	response.temp = "Temp: " + response.temp + " ℃";
	response.humidity = "Humidity: " + response.humidity + "%";
	response.windSpeed = "Wind speed: " + response.windSpeed + " m/s";
	response.windDirection = "Wind direction: " + response.windDirection;
}

var app = angular.module('weatherWidget', [ 'ngCookies' ]);

function weatherCtrl($scope, $cookieStore, $location, $http) {

	$scope.cities = [ "Chelyabinsk", "Yekaterinburg", "Moscow" ];
	$scope.services = Object.keys(serviceMap);

	$scope.city = $cookieStore.get('weatherWidgetCity');
	$scope.service = $cookieStore.get('weatherWidgetService');

	if ($scope.city == undefined) {
		$scope.city = $scope.cities[0];
	}
	if ($scope.service == undefined) {
		$scope.service = $scope.services[0];
	}
	fetchWeather($scope, $http, $location, $cookieStore);
}

app.controller('getCtrl', function($scope, $http, $location, $cookieStore) {
	$scope.getWeather = function() {
		return fetchWeather($scope, $http, $location, $cookieStore);
	}
});