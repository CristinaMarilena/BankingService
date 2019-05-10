var app = angular.module('app', []);

app.controller('UserController',
    ['$scope', 'UserService', 'AccountService', function($scope, UserService, AccountService) {

        $scope.hideLogin = false;
        $scope.credentials = {};
        $scope.userNotFound = true;
        $scope.notEnoughCredit = true;

        $scope.getUser = function() {
            var username = $scope.username;
            var password = $scope.password;

            UserService.getUser(username, password)
                .then(function success(response) {
                        $scope.credentials = response.data;
                        $scope.hideLogin = true;
                    },
                    function error(response) {
                        $scope.userNotFound = false;
                    });
        }

        $scope.createSecondaryAccountForUser = function() {
            if ($scope.credentials != {}) {

                var userId = $scope.credentials.id;

                AccountService.createSecondaryAccountForUser(userId, $scope.initialCredit)
                    .then(function success(response) {
                            $scope.notEnoughCredit = true;
                            UserService.getUserByUserId(userId)
                                .then(function success(response) {
                                        $scope.credentials = response.data;
                                        $scope.hideLogin = true;
                                    },
                                    function error(response) {
                                        $scope.message = '';
                                        if (response.status === 404) {
                                            $scope.errorMessage = 'User not found!';
                                        }
                                    });
                        },
                        function error(response) {
                            $scope.notEnoughCredit = false;
                        });
            }
        }

    }]);

app
    .service('AccountService', ['$http', function($http) {

        this.createSecondaryAccountForUser = function createSecondaryAccountForUser(userId, initialCredit) {
            return $http({
                method: 'POST',
                url: 'accounts/' + userId + "/" + initialCredit,
            });
        }

    }])
    .service('UserService', ['$http', function($http) {

        this.getUser = function getUser(username, password) {
            return $http({
                method: 'GET',
                url: 'users/login',
                params: {
                    username: username,
                    password: password
                }
            });
        }

        this.getUserByUserId = function getUserByUserId(userId) {
            return $http({
                method: 'GET',
                url: 'users/' + userId
            });
        }
    }])