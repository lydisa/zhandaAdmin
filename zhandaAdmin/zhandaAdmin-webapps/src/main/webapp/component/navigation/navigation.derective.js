(function() {
    'use strict';

    /**
     * 平面展开图插件
     */
    angular
        .module('component')
        .directive('zhandaNavigation', function($rootScope, $location, $timeout) {
                return {
                //compile里面先对dom进行操作, 再对$socpe进行监听;
                restrict:'E',
                scope:false,
                replace: true,
                controller:"NavigationCtrl",
                templateUrl:"component/navigation/navigation.view.html",
            link:link
            };
            function link(scope, element) {
                element.delegate('.sidebar > ul > li > a', 'click', function () {
                    var e = angular.element(this);
                    var i = e.find('i');
                    if(i.hasClass('glyphicon-chevron-down')) {
                        i.removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up').end();
                    }else{
                        i.removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down').end();
                    }

                });
            }
        });
})();