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
                    var height = node.getAttribute('height') || '1200';
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
                    var labelColor = node.getAttribute('label-color') || '#000000';
                    var labelFont = node.getAttribute('label-font') || '50pt Calibri';
                    
                    var draw = function(boards){
                        //board.width = height board.length = width;
                        canvas.setAttribute('width', width);
                        canvas.setAttribute('height', height);
                        var ctx = canvas.getContext('2d');
                        ctx.clearRect(0, 0, width, height);
                        var padding = 30;
                        var totalWidth = 0;
                        for(var i=0;i<boards.length;i++){
                            totalWidth+=2* padding +boards[i].matchArea.width;
                        }
                        var percent = width/totalWidth;
                        ctx.scale(percent,percent);
                        var offsetX = padding;
                        var offsetY = padding;
                        for(var i=0;i<boards.length;i++){
                            //绘制matchArea
                            ctx.strokeStyle= fixedBoardColor;
                            ctx.strokeRect(boards[i].matchArea.point.x+offsetX,boards[i].matchArea.point.y+offsetY,boards[i].matchArea.width,boards[i].matchArea.length);
                            printText(boards[i].matchArea,ctx,offsetX,offsetY,true);
                            //绘制Area
                            ctx.fillStyle = boardColor;
                            ctx.fillRect(boards[i].area.point.x+offsetX,boards[i].area.point.y+offsetY,boards[i].area.width,boards[i].area.length);
                            //绘制element
                            ctx.strokeStyle = lineColor;
                            ctx.fillStyle = labelColor;
                            for(var j=0;j<boards[i].elements.length;j++){
                                ctx.strokeRect(boards[i].elements[j].point.x+offsetX,boards[i].elements[j].point.y+offsetY,boards[i].elements[j].width,boards[i].elements[j].length);
                                //绘制text
                                printText(boards[i].elements[j],ctx,offsetX,offsetY,false);
                            }
                            offsetX += boards[i].matchArea.width+padding;
                        }
                    }

                    var printText = function(board,ctx,offsetX,offsetY,upsideDown){
                        var rotate = Math.PI;
                        var m1 = 11;
                        var m2 = 12;
                        var m3 = 2;
                        if(upsideDown){
                            m1=-2;
                            m2=-2;
                            m3=-12;
                        }
                        if(board.leftId==""){
                            ctx.save();
                            ctx.translate(board.point.x+m1+offsetX,board.point.y+board.length*0.55+offsetY);
                            ctx.rotate(-rotate / 2);
                            ctx.fillText(board.length,0,0);
                            ctx.restore();
                        }
                        if(board.rightId=="-1"||(board.id!="-1"&&board.rightId=="")){
                            ctx.save();
                            ctx.translate(board.point.x+board.width-m1+offsetX,board.point.y+board.length*0.45+offsetY);
                            ctx.rotate(rotate / 2);
                            ctx.fillText(board.length,0,0);
                            ctx.restore();
                        }
                        if(board.topId==""){
                            ctx.save();
                            ctx.translate(board.point.x+board.width*0.45+offsetX,board.point.y+m2+offsetY);
                            ctx.fillText(board.width,0,0);
                            ctx.restore();
                        }
                        if(board.buttonId==""){
                            ctx.save();
                            ctx.translate(board.point.x+board.width*0.45+offsetX,board.point.y+board.length-m3+offsetY);
                            ctx.fillText(board.width,0,0);
                            ctx.restore();
                        }
                    }
                    return {
                        pre: function preLink(scope, instanceElement, instanceAttributes, controller) {
                            scope.$watch('boards', function (newValue, oldValue) {
                                scope.boards = newValue;
                                draw(scope.boards);
                            }, true);
                            scope.$watch('size', function (newValue, oldValue) {
                                width = newValue.width;
                                draw(scope.boards);
                            }, true);
                        },
                        post: function postLink(scope, instanceElement, instanceAttributes, controller) {}
                    };
                }
            };
            var roundProgress = {
                //compile里面先对dom进行操作, 再对$socpe进行监听;
                restrict:'E',
                compile: compilationFunction,
                scope:false,
                replace: true
            };
            
            return roundProgress;

        });
})();