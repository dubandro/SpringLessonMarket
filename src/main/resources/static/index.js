angular.module('angular', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/product/all')
            .then(function (response) {
                $scope.productList = response.data;
                $scope.size = response.data.length;
            });
    };
    $scope.loadProducts();

    $scope.changeValue = function (productID, delta) {
        $http({
            url: contextPath + '/product/change_count',
            method: 'GET',
            params: {
                productID: productID,
                delta: delta
            }
        }).then(function (){
            $scope.loadProducts();
        });
    };

    $scope.deleteProduct = function (productID) {
        $http({
            url: contextPath + '/product/delete_product',
            method: 'GET',
            params: {
                productID: productID
            }
        }).then(function (){
            $scope.loadProducts();
        });
    };
});