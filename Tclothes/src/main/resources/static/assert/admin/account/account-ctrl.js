app.controller("account-ctrl",function($scope,$http, $location){
	$scope.items = [];
	$scope.form = {};
	
	$scope.initialize = function(){
		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
			console.log("success",resp);
			$scope.pageCount = Math.ceil($scope.items.length / 4);
			console.log("success",resp)
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
		}
		
	$scope.initialize();
	$scope.error=null;
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post("/rest/accounts",item).then(resp => {
			$scope.items.push(item);
			swal("Add account successfull!", "You clicked the button!", "success");
			$location.path("/listAccount");
		}).catch(error => {
		    swal("Add account error!", "You clicked the button!", "error");
			console.log("error",error.data)
			$scope.error= error.data;
		});
	}
	
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
	}
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		var url = `/rest/accounts/${$scope.form.username}`;
		$http.put(url,item).then(resp => {
			var index = $scope.items.findIndex(item => item.username == $scope.form.username);
			$scope.items[index] = resp.data;
			swal("Update account successfull!", "You clicked the button!", "success");
			console.log("success",resp)
		}).catch(error => {
			console.log("error",error)
		});
	}
	
	$scope.delete = function(username){
		var url = `/rest/accounts/${username}`;
		$http.delete(url).then(resp => {
			var index = $scope.items.findIndex(item => item.username == username);
			$scope.items.splice(index,1);
			swal("Delete account successfull!", "You clicked the button!", "success");
			console.log("success",resp)
		}).catch(error => {
			console.log("error",error)
		});
	}
	
});