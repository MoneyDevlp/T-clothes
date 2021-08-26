const app = angular.module("shopping-cart",[]);

app.controller("shopping-cart-ctrl",function($scope,$http){
	$scope.cart = {
		
		items: [],
		
		add(productId){
			var item = this.items.find(item => item.productId == productId);
			if(item){
				item.quantity ++;
				this.saveToLocalStorage();
				swal("Add cart successfull!", "", "success");
			}
			else{
				$http.get(`/rest/products/${productId}`).then(resp => {
					resp.data.quantity = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
					swal("Add cart successfull!", "", "success");
				})
			}
		},
		
		remove(productId){
			var index = this.items.findIndex(item => item.productId == productId);
			this.items.splice(index,1);
			this.saveToLocalStorage();
		},
		
		clear(){
			this.items=[];
			this.saveToLocalStorage();
		},
		
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart",json);
		},
		
		get count(){
			return this.items.map(item => item.quantity).reduce((total,qty) => total+= qty,0);
		},
		
		get amount(){
			return this.items.map(item => item.quantity * item.unitPrice).reduce((total,qty) => total+= qty,0);
		},
		
		loadLocalStorage(){
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
		
	}
	$scope.cart.loadLocalStorage();
		$scope.order = {
			orderDate: new Date(),
			adress: "",
			account: {username:$("#username").text()},
			get orderDetails(){
				return $scope.cart.items.map(item => {
					return{
						product:{productId:item.productId},
						unitPrice: item.unitPrice,
						quantity: item.quantity
					}
				});
			},
			placeorder(){
				var order = angular.copy(this);
				$http.post("/rest/orders",order).then(resp => {
					$scope.cart.clear();
					swal("Order successfull!", "You clicked the button!", "success");
					/*location.href = "/order/detail/" + resp.data.orderId;*/
					
				}).catch(error => {
					swal("Order error!", "You clicked the button!", "error");
					console.log("error",error)
				})
			}
		}
})