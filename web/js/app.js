
//ebay app
angular.module('ebay', ['ui.router', 'ngResource', 'ngAnimate', 'ngCookies']);

//ebay config
angular.module('ebay').config(function ($stateProvider) {
    $stateProvider.state('welcome', {
        url: '/welcome',
        templateUrl: 'partials/welcome.html',
        controller: ''
    }).state('listItems', {
        url: '/items',
        templateUrl: 'partials/items-list.html',
        controller: 'ItemListController'
    }).state('viewItem', {
        url: '/items/{:id}/view',
        templateUrl: 'partials/item-view.html',
        controller: 'ItemViewController'
    }).state('viewUser', {
        url: '/users/{:id}/view',
        templateUrl: 'partials/user-view.html',
        controller: 'UserViewController'
    }).state('viewUser.settings', {
        url: '/settings',
        templateUrl: 'partials/user-view-settings.html',
        controller: ''
    }).state('viewUser.bought_items', {
        url: '/bought-items',
        templateUrl: 'partials/user-view-bought_items.html',
        controller: ''
    }).state('viewUser.sold_items', {
        url: '/sold-items',
        templateUrl: 'partials/user-view-sold_items.html',
        controller: ''
    }).state('viewUser.bids', {
        url: '/bids',
        templateUrl: 'partials/user-view-bids.html',
        controller: ''
    });
});

//ebay run
angular.module('ebay').run(function($state) {
    $state.go('welcome');
});

