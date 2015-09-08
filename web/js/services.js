
angular.module('ebay')
    .factory('Item', function($resource) {
        return $resource('/api/items/:id', { id: '@id' }, {
                update: { method: 'PUT' }
            }
        );
    })
    .factory('Category', function($resource) {
        return $resource('/api/categories/:id', { id: '@name' }, {
                update: { method: 'PUT' }
            }
        );
    })
    .factory('User', function($resource) {
        return $resource('/api/users/:id', { id: '@username' }, {
                update: { method: 'PUT' }
            }
        );
    })

    .factory('Authentication', function($http, $cookies){
        var role = $cookies.get('role') || 'GUEST';
        var credentials = {
            username: "",
            password: ""
        }
        $http.defaults.headers.common.Authorization = 'Basic ' + $cookies.get('credentials_base64');

        return {
            getRole: function(){
                return role;
            },
            signIn: function(cred) {
                credentials = cred;
                role = 'USER';
                $cookies.put('role', 'USER');
                var credentials_base64 = btoa(credentials.username + ":" + credentials.password);
                $http.defaults.headers.common.Authorization = 'Basic ' + credentials_base64;
                $cookies.put('credentials_base64', credentials_base64);

                console.log("changed headers");
                /*$http.get('/users/login').
                    then(function(response) {

                        console.log("Ok : " + response);

                    }, function(response) {

                        console.log("Not ok : " + response);
                    });*/
            },
            signUp: function() {

            },
            signOut: function() {
                role = 'GUEST';
                $cookies.put('role', 'GUEST');
                credentials.username = "";
                credentials.password = "";
                $http.defaults.headers.common.Authorization = 'Basic ';
                $cookies.put('credentials_base64', "");
            }
        }
    })