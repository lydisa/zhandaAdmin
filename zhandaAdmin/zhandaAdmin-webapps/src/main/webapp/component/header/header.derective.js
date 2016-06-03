(function() {
    'use strict';

    /**
     * header
     */
    angular
        .module('component')
        .directive('zhandaHeader', function($rootScope, $location, $timeout) {
            return {
                //compile里面先对dom进行操作, 再对$socpe进行监听;
                restrict:'E',
                scope:false,
                replace: true,
                // controller:"NavigationCtrl",
                templateUrl:"component/header/header.view.html"
            };

        });
})();/**
 * Created by admin on 2016/6/2.
 */
