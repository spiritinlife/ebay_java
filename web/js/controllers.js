app.controller('EbayController', function ($scope, $resource) {
    this.items = $resource('/api/items').query();
    this.allItems = $resource('/api/items').query();
    this.categories = $resource('/api/categories').query();
    console.log($resource('/api/users').query());
});

/*app.controller("SignUpController", function () {
    this.user = {};
    this.addUser = function () {
        console.log("user added:");
        console.log(this.user);

    }
});*/

/*app.controller('SignUpController', ['$http', function($http){
 var store = this;
 store.user = {}
 this.addUser = function(){
 $http.post('/api/users/:username', [user]).success(function(data){
 store.items = data;
 });
 }
 }]);*/



app.controller('SignUpController', function ($scope, RegisterService) {
    var bla = this;
    bla.user = {};
    this.register = function () {
        console.log(angular.toJson(bla.user));
        console.log("sent item");
        RegisterService.registerUser(angular.toJson(bla.user));
    }

});