var user = {};
angular.module('ebay')
    .controller('GlobalController', function($scope, $state, Category, Item, Authentication) {
        $scope.triedToBid = false;

        //fetch all categories. Issues a GET to /api/categories
        $scope.categories = Category.query();

        //fetch all items. Issues a GET to /api/items
        $scope.items = Item.query();

        $scope.bid = function(item) {
           var userRole = Authentication.getRole();
            if  (userRole == "GUEST") {
                $scope.triedToBid = true;
                $("#signInModal").modal();
            } else  {
                $state.go("viewItem",{id:item.id});
            }
        }

        $scope.goToAccount = function() {
            if (Authentication.getUserName()) {
                $state.go("viewUser", {username: Authentication.getUserName()});
            }
        }
        $scope.goToAdmin = function() {
            if (Authentication.getRole() == "ADMIN") {
                $state.go("viewAdmin");
            }
        }

        $scope.getRole = Authentication.getRole;
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

    .controller('UserViewController', function($scope, $state, $stateParams, $http, User) {
        $scope.newauction = {
            currently : null,
            numberOfBids : null,
            started : new Date(),
            seller : null,
            bids : null
        };

        $scope.user = User.get({
            username: $stateParams.username
        });

        $scope.createAuction = function() {

            var data = $scope.newauction;
            data.location = {
                name : $scope.newauction.country,
                lat: $scope.newauction.location.split(",")[0],
                lng: $scope.newauction.location.split(",")[1]};

            $http.post("/api/items",data).then(function(res){
                console.log(res);

            });

        };

        $state.go("viewUser.new_auction");

    })
    .controller('AdminViewController', function($scope, $http) {

        $http.get("/api/users").then(function(res) {
            $scope.users = res.data;
        }, function(response) {
            //something went wrong
        });


    })

    .controller('AdminController', function($scope, $http) {

        $scope.reject = function(username) {
            $http.put("/api/users/admin/account/"+username+"/reject").then(function(res){
                location.reload();
            });
        };

        $scope.accept = function(username) {
            $http.put("/api/users/admin/account/"+username+"/accept").then(function(res){
                location.reload();
            });
        };

        $scope.revoke = function(username) {
            $http.put("/api/users/admin/account/"+username+"/revoke").then(function(res){
                location.reload();
            });
        };


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