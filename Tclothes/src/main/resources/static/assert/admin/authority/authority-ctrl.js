app.controller("authority-ctrl",function($scope,$http){
	
	$scope.initialize = function(){
		$http.get("/rest/authorities").then(resp => {
		$scope.db = resp.data;
	})
	}
	
	$scope.initialize();
	
	$scope.index = function(username, role){
		return $scope.db.authorities.findIndex(a => a.account.username == username && a.role.id == role)
	}
	
	$scope.update = function(username, role){
		var index = $scope.index(username, role);
		if(index >= 0){
			var id = $scope.db.authorities[index].id;
			$http.delete(`/rest/authorities/${id}`).then(resp => {
				$scope.db.authorities.splice(index,1);
				swal("Delete authority successfull!", "You clicked the button!", "error");
			})
		}
		else{
			var authority = {
				account:{username: username},
				role:{id:role}
			};
			$http.post("/rest/authorities",authority).then(resp => {				
				$scope.db.authorities.push(resp.data);
				swal("Authority account successfull!", "You clicked the button!", "success");
			});
		}
	}
	
		
});