function containsObject(obj, list) {
    var i;
    for (i = 0; i < list.length; i++) {
        if (list[i].name == obj.name) {
            return true;
        }
    }

    return false;
}

var removeByAttr = function(arr, attr, value){
    var i = arr.length;
    while(i--){
        if( arr[i]
            && arr[i].hasOwnProperty(attr)
            && (arguments.length > 2 && arr[i][attr] === value ) ){

            arr.splice(i,1);

        }
    }
    return arr;
}


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
                $state.go("listUsers");
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
    .controller('ItemViewController', function($scope, $state, $stateParams, Item, Bid, Authentication) {
        $scope.imageIndex = 0;
        $scope.imageCount = 0;

        $scope.role = Authentication.getRole;
        $scope.bid = new Bid();

        $scope.initMaps = function(){
            var location = new google.maps.LatLng($scope.item.location.longitude, $scope.item.location.latitude);

            var map = new google.maps.Map(
                document.getElementById("map-container"), {
                    center: location,
                    zoom: 14
                });

            var marker = new google.maps.Marker({
                position: location,
                map: map,
                title:$scope.item.location.name
            });

            marker.setMap(map);
        }

        $scope.item = Item.get({
            id: $stateParams.id
        }, function(){
            $scope.imageCount = $scope.item.images.length;
            $scope.bid.amount = parseFloat($scope.item.currently) + 1;
            $scope.initMaps();
        });

        $scope.createBid = function(){
            $scope.bid.$save({
                username: Authentication.getUserName(),
                itemId: $scope.item.id
            }, function(){
                $state.go("viewUser", {username: Authentication.getUserName()})
            });
        }
    })

    .controller('SellerItemsViewController', function($scope, $stateParams, SellerItem) {
        $scope.items = SellerItem.query();
    })

    .controller('SellerItemCreateController', function($scope, $stateParams, Seller,
                                                       Authentication, $state , SellerItem, Upload, $timeout, Category) {

        $scope.categories = Category.query();
        $scope.addCategory = function(cat){
            if (typeof $scope.item.categories == 'undefined'){
                $scope.item.categories = []
            }
            if (!containsObject({name: cat}, $scope.item.categories)){
                $scope.item.categories.push({name: cat});
            }
        }
        $scope.removeCategory = function(cat){
            console.log("called");
            removeByAttr($scope.item.categories, "name", cat.name);
        }

        $scope.files = [];
        $scope.fileList = [];
        $scope.log = '';
        $scope.item = new SellerItem();
        $scope.createItem = function() {
            $scope.item.images = [];
            for (var i = 0; i < $scope.fileList.length; i++) {
                $scope.item.images.push({
                    caption: "none",
                    url: $scope.fileList[i].name
                });
            }
            $scope.item.$save();

            $state.go("viewUser", {username: $stateParams.username});
        };

        $scope.$watch('files', function () {
            if (typeof $scope.files != 'undefined') {
                for (var i = 0; i < $scope.files.length; i++) {
                    $scope.fileList.push($scope.files[i]);
                }
            }

            $scope.upload($scope.files);
        });

        $scope.upload = function (files) {
            if (files && files.length) {
                for (var i = 0; i < files.length; i++) {
                    var file = files[i];
                    if (!file.$error) {
                        Upload.upload({
                            url: '/api/files/upload',
                            fields: {
                                'username': $scope.username
                            },
                            file: file
                        }).progress(function (evt) {
                            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            $scope.log = 'progress: ' + progressPercentage + '% ' +
                                evt.config.file.name + '\n' + $scope.log;
                        }).success(function (data, status, headers, config) {
                            $timeout(function() {
                                $scope.log = 'file: ' + config.file.name + ', Response: ' + JSON.stringify(data) + '\n' + $scope.log;
                            });
                        });
                    }
                }
            }
        };

    })
    .controller('SellerItemEditController', function($scope, $stateParams, SellerItem,
                                            Authentication, $state, $stateParams, Upload, $timeout) {
        $scope.item = SellerItem.get({
            id: $stateParams.id
        }, function(){
            for (var i = 0; i < $scope.item.images.length; i++) {
                $scope.fileList.push({
                    name: $scope.item.images[i].url,
                    caption: $scope.item.images[i].caption
                });
            }
        });

        $scope.files = [];
        $scope.fileList = [];
        $scope.log = '';

        $scope.editItem = function() {
            for (var i = 0; i < $scope.fileList.length; i++) {
                $scope.item.images.push({
                    caption: $scope.fileList[i].caption,
                    url: $scope.fileList[i].name
                });
            }

            $scope.item.$update();

            $state.go("viewUser", {username: $stateParams.username});
        };

        $scope.$watch('files', function () {
            if (typeof $scope.files != 'undefined') {
                for (var i = 0; i < $scope.files.length; i++) {
                    $scope.fileList.push($scope.files[i]);
                }
            }

            $scope.upload($scope.files);
        });

        $scope.upload = function (files) {
            if (files && files.length) {
                for (var i = 0; i < files.length; i++) {
                    var file = files[i];
                    if (!file.$error) {
                        Upload.upload({
                            url: '/api/files/upload',
                            fields: {
                                'username': $scope.username
                            },
                            file: file
                        }).progress(function (evt) {
                            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            $scope.log = 'progress: ' + progressPercentage + '% ' +
                                evt.config.file.name + '\n' + $scope.log;
                        }).success(function (data, status, headers, config) {
                            $timeout(function() {
                                $scope.log = 'file: ' + config.file.name + ', Response: ' + JSON.stringify(data) + '\n' + $scope.log;
                            });
                        });
                    }
                }
            }
        };

    })



    .controller('UserViewController', function($scope, $state, $stateParams, $http, User) {
       /* $scope.newauction = {
            currently : null,
            numberOfBids : null,
            started : new Date(),
            seller : null,
            bids : null
        };*/

        $scope.user = User.get({
            username: $stateParams.username
        });

        /*$scope.createAuction = function() {

            var data = $scope.newauction;
            data.location = {
                name : $scope.newauction.country,
                lat: $scope.newauction.location.split(",")[0],
                lng: $scope.newauction.location.split(",")[1]};

            $http.post("/api/items",data).then(function(res){
                console.log(res);

            });

        };

        $state.go("viewUser.createItem");*/

    })
    .controller('AdminViewController', function($scope, $http) {

        $http.get("/api/users").then(function(res) {
            $scope.users = res.data;
        }, function(response) {
            //something went wrong
        });


    })

    .controller('AdminController', function($scope, User) {
        $scope.reject = function() {
            $scope.user.accountStatus = 'REJECTED';
            $scope.user.$update();
        };

        $scope.accept = function() {
            $scope.user.accountStatus = 'ACCEPTED';
            $scope.user.$update();
        };

        $scope.revoke = function() {
            $scope.user.accountStatus = 'REVOKED';
            $scope.user.$update();
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
                    if (Authentication.getRole() == "ADMIN") {
                        $state.go("listUsers");
                    } else {
                        $state.go("viewUser",{username:Authentication.getUserName()});
                    }
                } else {
                    $scope.signInStatus = "Something went wrong";
                }
            });
        }
        $scope.signOut = function() {
            Authentication.signOut();
        }
    })

    //MESSAGE CONTROLLERS================
    .controller('UserCreateMessageController', function($scope, SentMessage, Authentication, $state){
        $scope.message = new SentMessage()

        $scope.createMessage = function(){
            $scope.message.fromUsername = Authentication.getUserName()
            $scope.message.$save()

            $state.go("viewUser.sentMessages")
        }
    })
    .controller('UserReceivedMessagesController', function($scope, ReceivedMessage){
        $scope.messages = ReceivedMessage.query({},function(){
            for (var i =0; i < $scope.messages.length; i++){
                $scope.$watch('messages['+i+']', function(newMessage, oldMessage){
                    console.log("updateMsg");
                    newMessage.$update()
                }, true);
            }
        });
    })
    .controller('UserSentMessagesController', function($scope, SentMessage){
        $scope.messages = SentMessage.query({},function(){
            for (var i =0; i < $scope.messages.length; i++){
                $scope.$watch('messages['+i+']', function(newMessage, oldMessage){
                    console.log("updateMsg");
                    newMessage.$update()
                }, true);
            }
        });
    })
