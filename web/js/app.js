
//ebay app
angular.module('ebay', ['ui.router', 'ngResource', 'ngAnimate', 'ngCookies', 'ngFileUpload']);

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
        url: '/users/{:username}/view',
        templateUrl: 'partials/user-view.html',
        controller: 'UserViewController'
    }).state('viewUser.settings', {
        url: '/settings',
        templateUrl: 'partials/user-view-settings.html',
        controller: ''
    }).state('viewUser.createItem', {
        url: '/items/create',
        templateUrl: 'partials/seller-create_item.html',
        controller: 'SellerItemCreateController'
    }).state('viewUser.editItem', {
        url: '/items/{:id}/edit',
        templateUrl: 'partials/seller-edit_item.html',
        controller: 'SellerItemEditController'
    }).state('viewUser.boughtItems', {
        url: '/items/bought',
        templateUrl: 'partials/seller-bought_items.html',
        controller: ''
    }).state('viewUser.soldItems', {
        url: '/items/sold',
        templateUrl: 'partials/seller-sold_items.html',
        controller: 'SellerItemsViewController'
    }).state('viewUser.bids', {
        url: '/bids',
        templateUrl: 'partials/user-view-bids.html',
        controller: ''
    }).state('listUsers', {
        url: '/users',
        templateUrl: 'partials/users-list.html',
        controller: 'AdminViewController'
    });
});

//ebay run
angular.module('ebay').run(function($state) {
    $state.go('welcome');
});

