app.controller("category-ctrl",function($scope,$http, $location){
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function(){
		$http.get("/rest/categories").then(resp => {
			$scope.items = resp.data;
			$scope.begin = 0;
        	$scope.pageCount = Math.ceil($scope.items.length / 4);
			console.log("success",resp)
		});
		$scope.prop = "name";
    	$scope.repaginate = function() {
        $scope.begin = 0;
        $scope.pageCount = Math.ceil($scope.items.length / 4);
    }

    	/*$scope.first = function() {
        $scope.begin = 0;
    }*/
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
		$http.post("/rest/categories",item).then(resp => {
			$scope.items.push(item);
			swal("Add category successfull!", "You clicked the button!", "success");
			$location.path("/listCategory");
		}).catch(error => {
			swal("Add category error!", "You clicked the button!", "error");
			console.log("error",error.data)
			$scope.error= error.data;
		});
	}
	
	$scope.edit = function(categoryId){
		var url = `/rest/categories/${categoryId}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			console.log("success",resp)
			/*$location.path("/category");*/
		}).catch(error => {
			console.log("error",error)
		});
	}
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		var url = `/rest/categories/${$scope.form.categoryId}`;
		$http.put(url,item).then(resp => {
			var index = $scope.items.findIndex(item => item.categoryId == $scope.form.categoryId);
			$scope.items[index] = resp.data;
			swal("Update category successfull!", "You clicked the button!", "success");
			console.log("success",resp)
		}).catch(error => {
			console.log("error",error)
		});
	}
	
	$scope.delete = function(categoryId){
		var url = `/rest/categories/${categoryId}`;
		$http.delete(url).then(resp => {
			var index = $scope.items.findIndex(item => item.categoryId == categoryId);
			$scope.items.splice(index,1);
			swal("Delete category successfull!", "You clicked the button!", "success");
			console.log("success",resp)
		}).catch(error => {
			console.log("error",error)
		});
	}
	
	/*$scope.reset = function(){
		$scope.form = {
			
		}
	}*/
})	