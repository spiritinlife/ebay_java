
angular.module('ebay')

    .controller('CategoryListController', function($scope, Category) {
        //fetch all categories. Issues a GET to /api/categories
        $scope.categories = Category.query();
    })

    .controller('ItemListController', function($scope, Item) {
        //fetch all items. Issues a GET to /api/items
        $scope.items = Item.query();
    })
    .controller('ItemViewController', function($scope, $stateParams, Item) {
        //Get a single item. Issues a GET to /api/items/:id
        $scope.item = Item.get({
            id: $stateParams.id
        });
    })

    .controller("SignUpController", ['$scope', '$http', function($scope, $http) {
        $scope.registerUser = function(){
            var dataObj = {
                username: $scope.username,
                password: $scope.password,
                role: "USER",
                firstName: $scope.firstName,
                lastName: $scope.lastName,
                email: $scope.email,
                address: $scope.address,
                country: $scope.country,
                socialSecurityNumber: $scope.socialSecurityNumber,
                phoneNumber: $scope.phoneNumber
            };
            var res = $http.post('/api/users/signup', dataObj);
            /*res.success(function(data, status, headers, config) {
                $scope.message = data;
            });
            res.error(function(data, status, headers, config) {
                alert( "failure message: " + JSON.stringify({data: data}));
            });*/
        };
    }])

    .controller("SignInController", ['$scope', '$http', function($scope, $http) {
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