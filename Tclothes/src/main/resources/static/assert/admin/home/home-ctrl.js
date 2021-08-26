app.controller("home-ctrl",function($scope,$http, $location){
	$scope.items = [];
	$scope.amounts = [];
	$scope.orders = [];
	$scope.ordersmonth = [];
	$scope.form = {};
	
	$scope.initialize = function(){
		$http.get("/rest/reports").then(resp => {
			$scope.items = resp.data;
			console.log("success",resp)
			$scope.begin = 0;
        	$scope.pageCount = Math.ceil($scope.items.length / 4);
		})
		$scope.prop = "name";
    	$scope.repaginate = function() {
        $scope.begin = 0;
        $scope.pageCount = Math.ceil($scope.items.length / 4);
    }

    	$scope.prev = function() {
        if ($scope.begin > 0) {
            $scope.begin -= 4;
        }
    }
    	$scope.next = function() {
        if ($scope.begin < ($scope.pageCount - 1) * 4) {
            $scope.begin += 4;
        }
    }
		
		
		$http.get("/rest/reports/amount").then(resp => {
			$scope.amounts = resp.data;
			console.log("success",resp)
			
		})
		
		}
		
	$scope.initialize();
	
	$scope.filldate = function(day,month, year) {
		var url = `/rest/reports/order/${day}/${month}/${year}`;
        $http.get(url).then(resp => {
            $scope.orders = resp.data;
            console.log($scope.orders);
        })
        }
        
    $scope.getDate = function() {
        var dateObj = $scope.date;
        var day = dateObj.getDate();
        var month = dateObj.getMonth() + 1;
        var year = dateObj.getFullYear();
        $scope.filldate(day,month, year);
    }
    
    $scope.fillmonth = function(month, year) {
		var url = `/rest/reports/ordermonth/${month}/${year}`;
        $http.get(url).then(resp => {
            $scope.ordersmonth = resp.data;
            console.log($scope.orders);
        })
        }
        
    $scope.getMonth = function() {
        var dateObj = $scope.month;
        var month = dateObj.getMonth() + 1;
        var year = dateObj.getFullYear();
        $scope.fillmonth(month, year);
    }

});

