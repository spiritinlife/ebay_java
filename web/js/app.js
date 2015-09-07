
//ebay app
angular.module('ebay', ['ui.router', 'ngResource', 'ngAnimate']);

//ebay config
angular.module('ebay').config(function ($stateProvider) {
    $stateProvider.state('welcome', {
        url: '/welcome',
        templateUrl: 'partials/welcome.html',
        controller: ''
    }).state('listItems', {
        url: '/items',
        templateUrl: 'partials/items.html',
        controller: 'ItemListController'
    }).state('viewItem', {
        url: '/items/{:id}/view',
        templateUrl: 'partials/item-view.html',
        controller: 'ItemViewController'
    }).state('userPanel', {
        url: '/users/{:id}',
        templateUrl: 'partials/user-panel.html',
        controller: 'UserPanelController'
    }).state('userPanel.settings', {
        url: '/users/{:id}/settings',
        templateUrl: 'partials/user-panel-account-settings.html',
        controller: ''
    }).state('userPanel.bought_products', {
        url: '/users/{:id}/bought-products',
        templateUrl: 'partials/user-panel-bought-products.html',
        controller: ''
    }).state('userPanel.selled_products', {
        url: '/users/{:id}/selled-products',
        templateUrl: 'partials/user-panel-selled-products.html',
        controller: ''
    }).state('userPanel.bids', {
        url: '/users/{:id}/bids',
        templateUrl: 'partials/user-panel-bids.html',
        controller: ''
    });
});

//ebay run
angular.module('ebay').run(function($state) {
    $state.go('welcome');
});

