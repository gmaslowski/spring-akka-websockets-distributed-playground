angular.module("processingApp.services")
    .service("processingService", function ($q, $timeout) {

        var service = {}, listener = $q.defer(), socket = {
            client: null,
            stomp: null
        };

        service.RECONNECT_TIMEOUT = 30000;
        service.SOCKET_URL = "/process";

        service.PROCESSING_TOPIC = "/topic/processing";
        service.PROCESSING_BROKER = "/app/process";

        service.receive = function () {
            return listener.promise;
        };

        service.send = function (message) {
            socket.stomp.send(service.PROCESSING_BROKER, {
                priority: 9
            }, JSON.stringify({
                content: "client"
            }));
        };

        var reconnect = function () {
            $timeout(function () {
                initialize();
            }, this.RECONNECT_TIMEOUT);
        };

        var getMessage = function (data) {
            var message = JSON.parse(data), out = {};
            out.text = message.content;
            return out;
        };

        var startListener = function () {
            socket.stomp.subscribe(service.PROCESSING_TOPIC, function (data) {
                listener.notify(getMessage(data.body));
            });
        };

        var initialize = function () {
            socket.client = new SockJS(service.SOCKET_URL);
            socket.stomp = Stomp.over(socket.client);
            socket.stomp.connect({}, startListener);
            socket.stomp.onclose = reconnect;
        };

        initialize();
        return service;
    });
