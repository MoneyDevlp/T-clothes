app.controller("order-ctrl",function($scope,$http, $location){
	$scope.items = [];
	$scope.details = [];
	
	$scope.initialize = function(){
		$http.get("/rest/orders").then(resp => {
			$scope.items = resp.data;
			console.log("success",resp)
		})	
		}
		
	$scope.initialize();
	
	$scope.edit = function(orderId){
		var url = `/rest/orders/${orderId}`;
		$http.get(url).then(resp => {
			$scope.details = resp.data;
			console.log("success",resp)	
		})
	}
})