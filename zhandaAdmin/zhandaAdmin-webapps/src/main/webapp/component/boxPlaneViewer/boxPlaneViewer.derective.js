(function() {
    'use strict';

    /**
     * 平面展开图插件
     */
    angular
        .module('component')
        .directive('boxPlaneViewer', function($rootScope, $location, $timeout) {
            var compilationFunction = function (templateElement, templateAttributes, transclude) {
                if (templateElement.length === 1) {
                    //初始化DOM模型, 包括初始化canvas等;
                    var node = templateElement[0];
                    var width = node.getAttribute('width') || '400';
                    var height = node.getAttribute('height') || '400';
                    var canvas = document.createElement('canvas');
                    canvas.setAttribute('width', width);
                    canvas.setAttribute('height', height);
                    //相当于demo, 替换原来的元素;
                    node.parentNode.replaceChild(canvas, node);
                    //各种配置;

                    var fixedBoardColor = node.getAttribute('fixed-board-color') || '#505769';
                    var boardColor = node.getAttribute('board-color') || '#12eeb9';
                    var lineColor = node.getAttribute('lineColor') || '#505769';
                    var labelColor = node.getAttribute('label-color') || '#12eeb9';
                    var labelFont = node.getAttribute('label-font') || '50pt Calibri';
                    
                    var draw = function(boards){
                        var ctx = canvas.getContext('2d');
                        ctx.clearRect(0, 0, width, height);
                        
                    }
                    
                    return {
                        pre: function preLink(scope, instanceElement, instanceAttributes, controller) {
                            //监听模型, O了
                            //就监听一个属性;
                            scope.$watch(scope.boards, function (newValue, oldValue) {
                                // Create the content of the canvas
                                //包括新建和重绘;
                                var ctx = canvas.getContext('2d');
                                ctx.clearRect(0, 0, width, height);
                            }, true);
                            scope.$watch(scope.size, function (newValue, oldValue) {
                                width = size.width;
                                height= size.height;
                            }, true);
                        },
                        post: function postLink(scope, instanceElement, instanceAttributes, controller) {}
                    };
                }
            };
            var roundProgress = {
                //compile里面先对dom进行操作, 再对$socpe进行监听;
                compile: compilationFunction,
                scope:{
                    boards:"=boards",
                    size:"=size"
                },
                replace: true
            };
            
            return roundProgress;

        });
})();