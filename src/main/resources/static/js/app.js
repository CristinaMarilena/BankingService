var app = angular.module('app', []);

app.controller('UserController', ['$scope', 'UserService', 'AccountService', function($scope, UserService, AccountService) {

    $scope.hideLogin = false;
    $scope.credentials = {};

    $scope.getUser = function() {
        if ($scope.credentials != {}) {
            debugger;
            var id = 1;
            var username = $scope.username;
            var password = $scope.password;

            UserService.getUser(username, password)
                .then(function success(response) {
                        $scope.credentials = response.data;
                        $scope.message = '';
                        $scope.errorMessage = '';
                        $scope.hideLogin = true;
                    },
                    function error(response) {
                        $scope.message = '';
                        if (response.status === 404) {
                            $scope.errorMessage = 'User not found!';
                        } else {
                            $scope.errorMessage = "Error getting user!";
                        }
                    });
        }
    }

    $scope.createSecondaryAccountForUser = function() {
            if ($scope.credentials != {}) {
                debugger;
                var userId = $scope.credentials.id;

                AccountService.createSecondaryAccountForUser(userId, $scope.initialCredit)
                    .then(function success(response) {
                       $scope.message = '';
                        $scope.errorMessage = '';
                        UserService.getUserByUserId(userId)
                                        .then(function success(response) {
                                                $scope.credentials = response.data;
                                                $scope.message = '';
                                                $scope.errorMessage = '';
                                                $scope.hideLogin = true;
                                            },
                                            function error(response) {
                                                $scope.message = '';
                                                if (response.status === 404) {
                                                    $scope.errorMessage = 'User not found!';
                                                } else {
                                                    $scope.errorMessage = "Error getting user!";
                                                }
                                            });
                        },
                        function error(response) {
                            $scope.message = '';
                            if (response.status === 404) {
                                $scope.errorMessage = 'User not found!';
                            } else {
                                $scope.errorMessage = "Error getting user!";
                            }
                        });
            }
        }

}]);

app
    .service('AccountService', ['$http', function($http) {

    this.createSecondaryAccountForUser = function createSecondaryAccountForUser(userId, initialCredit) {
        return $http({
            method: 'POST',
            url: 'account/' + userId + "/" + initialCredit,
        });
    }

}])
    .service('UserService', ['$http', function($http) {

    this.getUser = function getUser(username, password) {
        return $http({
            method: 'GET',
            url: 'user/login',
            params: {
                username: username,
                password: password
            }
        });
    }

    this.getUserByUserId = function getUserByUserId(userId) {
            return $http({
                method: 'GET',
                url: 'user/' + userId
            });
        }

}])
