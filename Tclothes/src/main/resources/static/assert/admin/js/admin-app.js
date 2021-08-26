app = angular.module("admin-app",["ngRoute"]);

app.config(function ($routeProvider){
	$routeProvider
	.when("/category",{
		templateUrl: "/assert/admin/category/categoryForm.html",
		controller: "category-ctrl"
	})
	.when("/listCategory",{
		templateUrl: "/assert/admin/category/listCategory.html",
		controller: "category-ctrl"
	})
	.when("/product",{
		templateUrl: "/assert/admin/product/productForm.html",
		controller: "product-ctrl"
	})
	.when("/listProduct",{
		templateUrl: "/assert/admin/product/listProduct.html",
		controller: "product-ctrl"
	})
	.when("/account",{
		templateUrl: "/assert/admin/account/accountForm.html",
		controller: "account-ctrl"
	})
	.when("/listAccount",{
		templateUrl: "/assert/admin/account/listAccount.html",
		controller: "account-ctrl"
	})
	.when("/authority",{
		templateUrl: "/assert/admin/authority/authority.html",
		controller: "authority-ctrl"
	})
	.when("/order",{
		templateUrl: "/assert/admin/order/listOrder.html",
		controller: "order-ctrl"
	})
	.when("/home", {
		templateUrl: "/assert/admin/home/home.html",
		controller: "home-ctrl"
	})
	.otherwise({
        redirectTo: "/home"
    })
})