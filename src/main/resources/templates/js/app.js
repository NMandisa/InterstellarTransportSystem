/**
 * Created by NMandisa MKhungo on 8/12/2016.
 */
var baseUrl = "http://localhost:8080";
var app = angular.module('app', ['ui.router']);
app.config(function ($stateProvider, $urlRouterProvider) {
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("");
    // Now set up the states
    $stateProvider
        .state('login', {
            url: "/login",
            templateUrl: "partials/login.html",
            controller: "loadPlanetsCtrl"
        })
        .state('path', {
            url: "/path",
            templateUrl: "partials/shortest-route.html",
            controller: "loadPlanetsCtrl"
        })
        .state('register-route', {
            url: "/createRoute",
            templateUrl: "partials/route/register-route.html",
            controller: "routeCtrl"
        })
        .state('update-route', {
            url: "/updateRoute",
            templateUrl: "partials/route/update-route.html",
            controller: "routeCtrl"
        })
        .state('delete-route', {
            url: "/deleteRoute",
            templateUrl: "partials/route/delete-route.html",
            controller: "routeCtrl"
        })
        .state('register-planet', {
            url: "/createPlanet",
            templateUrl: "partials/planet-node/register-planet.html",
            controller: "nodeCtrl"
        })
        .state('update-planet', {
            url: "/updatePlanet",
            templateUrl: "partials/planet-node/update-planet.html",
            controller: "nodeCtrl"
        })
        .state('delete-planet', {
            url: "/deletePlanet",
            templateUrl: "partials/planet-node/delete-planet.html",
            controller: "nodeCtrl"
        })

        .state('dashboard', {
            url: "/dashboard",
            templateUrl: "partials/shortest-route.html",
            parent: "layout",
            controller: "loadPlanetsCtrl"
        })
        .state('layout', {
            url: "",
            templateUrl: 'app-layout.html',
            controller: 'loadPlanetsCtrl',
            abstract: true,
            resolve: {
                //result_data: function ($q, $timeout)//,CommonService)
                //{
                //    //return resolve_homepage($q,CommonService)
                //    var deferred = $q.defer();
                //    $timeout(function(){
                //        deferred.resolve("from a parent");
                //    }, 500);
                //    return deferred.promise;
                //}
            }

        })

});


app.controller('loadPlanetsCtrl', function ($scope, $http) {

    $scope.getPlanetNodes = function () {

        $scope.availPlanet = [];
        console.log('in get getPlanetNodes function');
        $http({
            method: 'get',
            url: baseUrl + "/planetNodes/"
        }).then(function successCallback(response) {
            console.log('Success');
            console.log(response.data);
            $scope.availPlanet = response.data;
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.loginError = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.shortestPath = function () {
        $scope.availableRoutes = [];
        console.log('in  shortestPath function');
        var dataPath;
        dataPath = {
            "start": $scope.startNode,
            "end": $scope.endNode
        };
        var parameter = JSON.stringify(dataPath);
        console.log(parameter.start);
        console.log(parameter.end);
        $http({
            method: 'post',
            url: baseUrl + "/routes/path",
            data: parameter
        }).then(function successCallback(response) {
            console.log(' shortestPath Success');
            console.log(response.data);
            $scope.availableRoutes = response.data;
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.pathErorr = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };
});

app.controller('nodeCtrl', function ($scope, $http) {

    var selPlanetId;
    $scope.findPlanet = function () {
        $scope.routefound;
        selPlanetId=$scope.ddlPlanetId;
        console.log('in get findRoute function');
        $http({
            method: 'get',
            url: baseUrl + "/routes/"+selPlanetId
        }).then(function successCallback(response) {

            console.log('Success');
            console.log(response.data);
            $scope.routefound = response.data;
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.loginError = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.savePlanetNode = function () {
        console.log('in  save PlanetNode function');
        var planetNode;
        planetNode = {
            "planetName": $scope.planetname,
            "planetNode": $scope.planetnode
        };
        var parameter = JSON.stringify(planetNode);
        console.log(parameter.planetName);
        console.log(parameter.planetNode);
        $http({
            method: 'post',
            url: baseUrl + "/planetNodes/create",
            data: parameter
        }).then(function successCallback(response) {
            console.log(' shortestPath Success');
            console.log(response.data);
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.pathErorr = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.updatePlanetNode = function () {
        console.log('in  save PlanetNode function');
       //var planetNodeId = "";
        var planetNode;
        planetNode = {
            "planetName": $scope.planetname,
            "planetNode": $scope.planetnode
        };
        var parameter = JSON.stringify(planetNode);
        console.log(parameter.planetName);
        console.log(parameter.planetNode);
        $http({
            method: 'post',
            url: baseUrl + "/planetNodes/update/" + selPlanetId,
            data: parameter
        }).then(function successCallback(response) {
            console.log(' shortestPath Success');
            console.log(response.data);
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.pathErorr = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.deletePlanetNode = function () {
        console.log('in  delete Planet Node function');
        $http({
            method: 'post',
            url: baseUrl + "/planetNodes/delete/" + selPlanetId
        }).then(function successCallback() {
            console.log(' delete PlanetNode Success');
            console.log('response == OK');
            $scope.deletePlanetSuccess = true;
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.deletePlanetError = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

});

app.controller('routeCtrl', function ($scope, $http) {

        var selrouteId;
        $scope.getRoutes = function () {
        $scope.availableRoutes = [];
        console.log('in get getRoutes function');
        $http({
            method: 'get',
            url: baseUrl + "/routes/"
        }).then(function successCallback(response) {
            console.log('Success');
            console.log(response.data);
            $scope.availPlanet = response.data;
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.loginError = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.findRoute = function () {
        $scope.routefound;
        selrouteId=$scope.ddlRouteId;
        console.log('in get findRoute function');
        $http({
            method: 'get',
            url: baseUrl + "/routes/"+selrouteId
        }).then(function successCallback(response) {

            console.log('Success');
            console.log(response.data);
            $scope.routefound = response.data;
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.loginError = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.saveRoute = function () {
        console.log('in  saveRoute function');
        var dataPath;
        dataPath = {
            "routeId":$scope.saveRouteId,
            "planetOrigin": $scope.savePlanetOrigin,
            "planetDestination": $scope.savePlanetDestination,
            "distance": $scope.saveDistance
        };
        var parameter = JSON.stringify(dataPath);
        console.log(parameter.planetOrigin);
        console.log(parameter.planetDestination);
        $http({
            method: 'post',
            url: baseUrl + "/routes/create",
            data: parameter
        }).then(function successCallback(response) {
            console.log(' save Route Success');
            console.log(response.data);
            $scope.registeredRouteSuccess = true;
            $scope.availableRoutes = response.data;
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.registeredRouteError = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.updateRoute = function () {
        console.log('in  updateRoute function');
        //var routeId = "";
        var dataPath;
        dataPath = {
            "planetOrigin": $scope.updatePlanetOrigin,
            "planetDestination": $scope.updatePlanetDestination,
            "distance": $scope.updateDistance
        };
        var parameter = JSON.stringify(dataPath);
        console.log(parameter.start);
        console.log(parameter.end);
        $http({
            method: 'post',
            url: baseUrl + "/routes/update/" + selrouteId,
            data: parameter
        }).then(function successCallback(response) {
            console.log(' updateRoute Success');
            console.log(response.data);
            $scope.availableRoutes = response.data;
            $scope.updateRouteSuccess = true;
            console.log('response == OK');
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.updateRouteError = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.deleteRoute = function () {
        console.log('in  deleteRoute function');
        $http({
            method: 'post',
            url: baseUrl + "/routes/delete/" + selrouteId
        }).then(function successCallback() {
            console.log(' delete Route Success');
            $scope.deleteRouteSuccess = true;
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            console.log('Failed' + response);
            $scope.deleteRouteError = true;
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };


});



