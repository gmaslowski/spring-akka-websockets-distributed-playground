angular.module("processingApp.controllers")
    .controller("processingController", function ($scope, processingService) {

        $scope.messages = [];

        $scope.startProcessing = function () {
            processingService.startProcessing();
        };

        processingService.receive().then(null, null, function (incoming) {
            $scope.messages.push(incoming.text);
        });
    });
