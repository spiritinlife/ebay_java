
angular.module('ebay')
    .controller('GlobalController', function($scope, Category, Item, Authentication) {
        $scope.triedToBid = false;

        //fetch all categories. Issues a GET to /api/categories
        $scope.categories = Category.query();

        //fetch all items. Issues a GET to /api/items
        $scope.items = Item.query();

        $scope.bid = function() {
           var userRole = Authentication.getRole();
            if  (userRole != "USER") {
                $scope.triedToBid = true;
                $("#signInModal").modal();

            }
        }
    })

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

    .controller('UserViewController', function($scope, $state, $stateParams, User) {

        $scope.user = User.get({
            username: $stateParams.username
        });

        $state.go("viewUser.settings");

    })

    .controller('UserCreateController', function($scope, $state, $stateParams, User) {
        $scope.user = new User();  //create new user instance. Properties will be set via ng-model on UI

        //create a new user. Issues a POST to /api/users
        $scope.addUser = function() {
            $scope.user.$save(function() {
                //$state.go('welcome'); // on success go back to home
            });
        };
    })

    .controller('UserSignInController', function($scope, $state, $stateParams, Authentication) {
        $scope.role = Authentication.getRole;
        $scope.signInStatus = "";
        $scope.signIn = function() {
            $scope.signInStatus = "pending..";
            Authentication.signIn($scope.credentials, function(success) {
                if (success) {
                    $("#signInModal").modal('hide');
                    $state.go("viewUser",{username:Authentication.getUserName()});
                } else {
                    $scope.signInStatus = "Something went wrong";
                }
            });
        }
        $scope.signOut = function() {
            Authentication.signOut();
        }
    });




    /*.controller("SignUpController", ['$scope', '$http', function($scope, $http) {
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
            res.success(function(data, status, headers, config) {
                $scope.message = data;
            });
            res.error(function(data, status, headers, config) {
                alert( "failure message: " + JSON.stringify({data: data}));
            });
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
    }]);*/