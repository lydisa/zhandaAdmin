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
                    var boardsCopy;
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
                        //board.width = height board.length = width;
                        var ctx = canvas.getContext('2d');
                        ctx.clearRect(0, 0, width, height);
                        var padding = 5;
                        var totalWidth = 0;
                        for(var board in boards){
                            totalWidth+=2* padding +board.matchArea.length;
                        }
                        var percent = width/totalWidth;
                        ctx.scale(percent,percent);
                        var offsetX = padding;
                        var offsetY = padding;
                        for(var board in boards){
                            //绘制matchArea
                            ctx.strokeStyle= fixedBoardColor;
                            ctx.fillRect(board.matchArea.point.x+offsetX,board.matchArea.point.y+offsetY,board.matchArea.length,matchArea.width);
                            //绘制Area
                            ctx.strokeStyle = boardColor;
                            ctx.fillRect(board.area.point.x+offsetX,board.area.point.y+offsetY,board.area.length,area.width);
                            //绘制element
                            ctx.strokeStyle = lineColor;
                            for(var e in board.elements){
                                ctx.strokeRect(e.point.x+offsetX,e.point.y+offsetY,e.length,e.width);
                            }
                            offsetX += board.matchArea.length+2*padding;
                        }
                    }
                    
                    return {
                        pre: function preLink(scope, instanceElement, instanceAttributes, controller) {
                            scope.$watch(scope.boards, function (newValue, oldValue) {
                                boardsCopy = scope.boards;
                                draw(boardsCopy);
                            }, true);
                            scope.$watch(scope.size, function (newValue, oldValue) {
                                width = size.width;
                                draw(boardsCopy);
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