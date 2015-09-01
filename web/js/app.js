'use strict';

var app = angular.module('ebay', ['ngResource','ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider.when(
        '/welcome',
        {
            templateUrl: 'partials/welcome.html',
            controller: 'MyCtrl1'
        });
    $routeProvider.when(
        '/store',
        {
            templateUrl: 'partials/store.html',
            controller: 'EbayController'
        });
    $routeProvider.otherwise(
        {
            redirectTo: '/store'
        });
});
