
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
    }).state('userView', {
        url: '/users/{:id}',
        templateUrl: 'partials/user.html',
        controller: ''
    });
});

//ebay run
angular.module('ebay').run(function($state) {
    $state.go('welcome');
});

