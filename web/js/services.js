
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
    });