
app.controller("GlobalScopeController", function($scope,$resource) {
    $scope.items = $resource('/api/items').query();
    $scope.allItems = $resource('/api/items').query();
    $scope.categories = $resource('/api/categories').query();
});

app.controller("SignUpController", ['$scope', '$http', function($scope, $http) {
    $scope.registerUser = function(){
        var dataObj = {
            id: 10,
            firstName: $scope.firstName,
            lastName: $scope.lastName,
            username: $scope.username,
            password: $scope.password,
            email: $scope.email,
            socialSecurityNumber: $scope.socialSecurityNumber,
            address: $scope.address,
            phoneNumber: $scope.phoneNumber
        };
        var res = $http.post('/api/users', dataObj);
        /*res.success(function(data, status, headers, config) {
            $scope.message = data;
        });
        res.error(function(data, status, headers, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
        });*/
    };
}]);