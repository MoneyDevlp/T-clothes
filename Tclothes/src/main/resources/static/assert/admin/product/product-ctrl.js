app.controller("product-ctrl",function($scope, $http, $location){
	$scope.items = [];
	$scope.products = [];
	$scope.form = {};
	
	var u = `/rest/files/images`;
	$scope.u = function(item){
		return `${u}/${item}`;
	}
	$scope.initialize = function(){
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.enteredDate = new Date(item.enteredDate);
			})
			console.log("success",resp)
			$scope.begin = 0;
        	$scope.pageCount = Math.ceil($scope.items.length / 4);
		})
		$scope.prop = "name";
    	$scope.repaginate = function() {
        $scope.begin = 0;
        $scope.pageCount = Math.ceil($scope.items.length / 4);
    }

    	$scope.first = function() {
        $scope.begin = 0;
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
		
		$http.get(u).then(resp => {
			$scope.items = resp.data;
		})
		
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		})
		
		}
		
	$scope.initialize();
	
	$scope.imageChange = function(files){
		var form = new FormData();
		form.append('files',files[0]);
		$http.post("/rest/files/images",form,{
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
		}).then(resp => {
			/*$scope.items.push(...resp.data);*/
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("error image!");
			console.log("Error", error);
		})
	}
	$scope.error=null;
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post("/rest/products",item).then(resp => {
			resp.data.enteredDate = new Date(resp.data.enteredDate);
			$scope.items.push(resp.data);
			swal("Add product successfull!", "You clicked the button!", "success");
			$location.path("/listProduct");
		}).catch(error => {
			swal("Add product error!", "You clicked the button!", "error");
			console.log("error",error.data)
			$scope.error= error.data;
		});
	}
	
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
	}
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.productId}`,item).then(resp => {
			var index = $scope.items.findIndex(p => p.productId == item.productId);
			$scope.items[index] = item;
			swal("Update product successfull!", "You clicked the button!", "success");
		}).catch(error => {
			alert("error add product!");
			console.log("Error", error);
		})
	}
	
	
	$scope.delete = function(item){
		$http.delete(`/rest/products/${item.productId}`).then(resp => {
			var index = $scope.items.findIndex(p => p.productId == item.productId);
			$scope.items.splice(index,1);
			swal("Delete product successfull!", "You clicked the button!", "success");
		}).catch(error => {
			alert("error delete product!");
			console.log("Error", error);
		});
		
	}
	
	
	
	
});