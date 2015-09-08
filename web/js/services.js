
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
        return $resource('/api/users/:username', { username: '@username' }, {
                update: { method: 'PUT' }
            }
        );
    })

    .factory('Authentication', function($http, $cookies, $state){
        var role = $cookies.get('role') || 'GUEST';
        var credentials = {
            username: $cookies.get('username'),
            password: ""
        }
        $http.defaults.headers.common.Authorization = 'Basic ' + $cookies.get('credentials_base64');

        return {
            getRole: function(){
                return role;
            },
            signIn: function(cred, callback) {
                credentials = cred;
                role = 'USER';

                $cookies.put('role', 'USER');
                var credentials_base64 = btoa(credentials.username + ":" + credentials.password);
                $http.defaults.headers.common.Authorization = 'Basic ' + credentials_base64;
                $cookies.put('credentials_base64', credentials_base64);
                $cookies.put('username', credentials.username);

                $http.get('/api/users/'+credentials.username).
                    then(function(response) {
                        callback(true);
                    }, function(response) {
                        role = 'GUEST';
                        $cookies.put('role', 'GUEST');
                        credentials.username = "";
                        credentials.password = "";
                        $http.defaults.headers.common.Authorization = 'Basic ';
                        $cookies.put('credentials_base64', "");
                        $cookies.put('username', "");
                        callback(false);
                    });
            },
            getUserName : function() { return credentials.username } ,
            signUp: function() {

            },
            signOut: function() {
                role = 'GUEST';
                $cookies.put('role', 'GUEST');
                credentials.username = "";
                credentials.password = "";
                $http.defaults.headers.common.Authorization = 'Basic ';
                $cookies.put('credentials_base64', "");
                $cookies.put('username', "");
                $state.go("welcome");
            }
        }
    })