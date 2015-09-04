
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

app.controller("SignInController", ['$scope', '$http', function($scope, $http) {
    var sictrl = this;
    sictrl.role = "guest";
    $scope.signInStatus = "";
    $scope.formCredentials = {
        username: "",
        password: ""
    }
    $scope.credentials = {
        username: "",
        password: ""
    }

    $scope.signIn = function() {
        $scope.credentials.username = $scope.formCredentials.username;
        $scope.credentials.password = $scope.credentials.password;

        $http.post('/api/users/login', {
            headers: {'Authentication': btoa($scope.credentials.username + ":" + $scope.credentials.password) }
        }).then(function(response) {
            sictrl.role = "user";
            $scope.signInStatus = "logged in succesfuly" + response;
        }, function(response) {
            sictrl.role = "guest";
            $scope.signInStatus = "wrong username or password" + response;
        });
    }

    $scope.signOut = function() {
        sictrl.role = "guest";
    }
}]);