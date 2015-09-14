//ebay app
angular.module('ebay', ['ui.router', 'ngResource', 'ngAnimate', 'ngCookies', 'ngFileUpload']);

//ebay config
angular.module('ebay').config(function ($stateProvider,$httpProvider) {
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
    })
        //USER PANEL
       .state('viewUser', {
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
    }).state('viewUser.activeAuctions', {
        url: '/auctions/active',
        templateUrl: 'partials/seller-active_auctions.html',
        controller: 'SellerItemsViewController'
    }).state('viewUser.activeBids', {
        url: '/bids/active',
        templateUrl: 'partials/bidder-active_bids.html',
        controller: 'BidderActiveBids'
    })
        //MESSAGES
      .state('viewUser.createMessage', {
        url: '/create',
        templateUrl: 'partials/user-messages-create.html',
        controller: 'UserCreateMessageController'
    }).state('viewUser.receivedMessages', {
        url: '/received',
        templateUrl: 'partials/user-messages-received.html',
        controller: 'UserReceivedMessagesController'
    }).state('viewUser.sentMessages', {
        url: '/sent',
        templateUrl: 'partials/user-messages-sent.html',
        controller: 'UserSentMessagesController'
    }).state('listUsers', {
        url: '/users',
        templateUrl: 'partials/users-list.html',
        controller: 'AdminViewController'
    }).state('account-pending', {
        url: '/account-pending',
        templateUrl: 'partials/account-pending.html',
        controller: ''
    });



    $httpProvider.interceptors.push(function ($q) {
        return {
            'responseError': function (response) {
                console.log(response.status, response)

                //Will only be called for HTTP up to 300
                if (response.status == 405) {
                    location.href = '/account-pending.html';
                }
                return response;
            }
        };
    });
});


//ebay run
angular.module('ebay').run(function ($state) {
    $state.go('welcome');
});


