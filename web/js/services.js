"use strict";

angular.module('ebay')
    //GUEST FETCH ===========
    .factory('Item', function ($resource) {
        return $resource('/api/items/:id/', {id: '@id'}, {
                update: {method: 'PUT'}
            }
        );
    })
    .factory('Category', function ($resource) {
        return $resource('/api/categories/:id', {id: '@name'}, {
                update: {method: 'PUT'}
            }
        );
    })
    .factory('User', function ($resource) {
        return $resource('/api/users/:username', {username: '@username'}, {
                update: {method: 'PUT'}
            }
        );
    })
    .factory('ItemXML', function ($resource){
        return $resource('/api/items/:id/xml', {id: "@id"});
    })

    //SELLER-BIDDER ROLE SERVICE===========
    .factory('Seller', function ($resource) {
        return $resource('/api/sellers/:username', {username: '@username'}, {
                update: {method: 'PUT'}
            }
        );
    })
    .factory('SellerItem', function ($resource, Authentication) {
        return $resource('/api/sellers/:username/items/:id', {
                username: Authentication.getUserName,
                id: '@id'
            }, {
                update: {method: 'PUT'}
            }
        );
    })
    .factory('Bid', function ($resource) {
        return $resource('/api/bids/:username/:itemId', {
                username: '@username',
                itemId: '@itemId'
            }, {
                update: {method: 'PUT'}
            }
        );
    })

    //MESSAGES ==========================
    .factory('SentMessage', function ($resource, Authentication) {
        return $resource('/api/messages/:username/sent/:id', {
                username: Authentication.getUserName,
                id : "@id"
            }, {
                update: {method: 'PUT'}
            }
        );
    })
    .factory('ReceivedMessage', function ($resource, Authentication) {
        return $resource('/api/messages/:username/received/:id', {
                username: Authentication.getUserName,
                id : "@id"
            }, {
                update: {method: 'PUT'}
            }
        );
    })

    //AUTHENTICATION ===================
    .factory('Authentication', function ($http, $cookies, $state) {
        var role = $cookies.get('role') || 'GUEST';
        var credentials = {
            username: $cookies.get('username'),
            password: ""
        }
        $http.defaults.headers.common.Authorization = 'Basic ' + $cookies.get('credentials_base64');

        return {
            getRole: function () {
                return role;
            },
            signIn: function (cred, callback) {
                credentials = cred;
                role = 'USER';

                $cookies.put('role', 'USER');
                var credentials_base64 = btoa(credentials.username + ":" + credentials.password);
                $http.defaults.headers.common.Authorization = 'Basic ' + credentials_base64;
                $cookies.put('credentials_base64', credentials_base64);
                $cookies.put('username', credentials.username);

                $http.get('/api/users/' + credentials.username).
                    then(function (res) {
                        role = res.data.role;
                        $cookies.put('role', role);

                        callback(true);
                    }, function (response) {
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
            getUserName: function () {
                return credentials.username
            },
            signUp: function () {

            },
            signOut: function () {
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
    });