app.service('RegisterService', function ($http) {
    return {
        registerUser: function (user) {
            $http({
                method: 'POST',
                url: 'http://localhost:8080/api/users',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    id: 10,
                    firstName: user.firstName,
                    lastName: user.lastName,
                    username: user.username,
                    password: user.password,
                    email: user.email,
                    socialSecurityNumber: user.socialSecurityNumber,
                    address: user.address,
                    phoneNumber: user.phoneNumber
                }
            }).success(function (user) {
                return user;
            }).error(function (data) {
            });
        }
    }
});