'use strict';

var app = angular.module('ebay', ['ngResource','ngRoute','ngAnimate']);

app.config(function ($routeProvider) {
    $routeProvider.when(
        '/welcome',
        {
            templateUrl: 'partials/welcome.html',
            controller: ''
        });
    $routeProvider.when(
        '/store',
        {
            templateUrl: 'partials/store.html',
            controller: ''
        });
    $routeProvider.otherwise(
        {
            redirectTo: '/welcome'
        });
});

