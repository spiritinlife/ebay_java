
angular.module('ebay')
    .factory('Item', function($resource) {
        return $resource('/api/items/:id', { id: '@id' }, {
                update: { method: 'PUT' }
            }
        );
    })
    .factory('Category', function($resource) {
        return $resource('/api/categories/:name', { id: '@name' }, {
                update: { method: 'PUT' }
            }
        );
    })
    .factory('User', function($resource) {
        return $resource('/api/users/:username', { id: '@username' }, {
                update: { method: 'PUT' }
            }
        );
    })

    .factory('Authentication', function($http){
        var role = 'GUEST';
        var credentials = {
            username: "",
            password: ""
        }

        return {
            getRole: function(){
                return role;
            },
            setCredenetials: function(c) {
                credentials = c;
            },
            signIn: function(cred) {
                credentials = cred;
                role = 'USER';
                $http.defaults.headers.common['Authorization'] = 'Basic ' + btoa(credentials.username + ":" + credentials.password);
            },
            signUp: function() {

            },
            signOut: function() {
                role = 'GUEST';
                credentials.username = "";
                credentials.password = "";
                $http.defaults.headers.common['Authorization'] = 'Basic ';
            }
        }
    })