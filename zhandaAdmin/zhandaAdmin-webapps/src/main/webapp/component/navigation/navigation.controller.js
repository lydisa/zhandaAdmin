(function () {
    'use strict';

    /**
     * 子导航 (控制器)
     */
    angular.module('scotchApp')
        .controller('NavigationCtrl', function ($rootScope, $scope, $timeout) {
            $rootScope.hideNav = false;
            $scope.menus = [
                {
                    href: "#product", text: "生产单", sub: [
                    {href: "#", text: "生产单历史"},
                ]
                },
                {
                    href: "#", text: "采购单", sub: [
                    {href: "#", text: "采购单历史"},
                ]
                },
                {
                    href: "#", text: "送货单", sub: [
                    {href: "#", text: "送货单历史"},
                ]
                },
                {href: "#", text: "产品", sub: null},
                {
                    href: "#", text: "基础设置", sub: [
                    {href: "#company", text: "公司"},
                    {href: "#", text: "纸类"},
                    {href: "#", text: "尺寸"},
                    {href: "#", text: "打印模板"}
                ]
                },

            ]
            //当前选中菜单
            $scope.menuSelected = $scope.menus[0];
            //$scope.menuSelected.checked = true;
            //更改选中菜单
            $scope.changeMenu = function (index) {
                $scope.menus[index].checked = !$scope.menus[index].checked;
            }

        });
})();

