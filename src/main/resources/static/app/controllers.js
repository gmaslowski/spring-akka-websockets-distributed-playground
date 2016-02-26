angular.module("processingApp.controllers")
    .controller("processingController", function ($scope, processingService) {

        $scope.messages = [];
        $scope.message = "";

        $scope.addMessage = function () {
            processingService.send($scope.message);
            $scope.message = "";
        };

        processingService.receive().then(null, null, function (message) {
            $scope.messages.push(message.text);
        });
    });
