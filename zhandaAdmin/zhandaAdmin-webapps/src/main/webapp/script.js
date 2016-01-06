// script.js

// create the module and name it scotchApp
// also include ngRoute for all our routing needs
var scotchApp = angular.module('scotchApp', ['ngRoute']);

// configure our routes
scotchApp.config(function ($routeProvider) {
    $routeProvider

    // route for the about page
        .when('/company', {
            templateUrl: 'pages/company/companyList.html',
            controller: 'companyController'
        })

        // route for the contact page
        .when('/product', {
            templateUrl: 'pages/product/productEditor.html',
            controller: 'contactController'
        });
});

// create the controller and inject Angular's $scope
scotchApp.controller('mainController', function ($scope) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
    //菜单列表
    $scope.menus = [
        {href: "#product", text: "生产单", sub: [
            {href: "#", text: "新建生产单"},
            {href: "#", text: "生产单列表"},
            {href: "#", text: "生产记录"}
        ]},
        {href:"#",text:"采购单",sub:[
            {href:"#",text:"未处理"},
            {href:"#",text:"新建采购单"},
            {href:"#",text:"采购单列表"},
            {href:"#",text:"采购记录"}
        ]},
        {href:"#",text:"送货单",sub:[
            {href:"#",text:"未处理"},
            {href:"#",text:"新建送货单"},
            {href:"#",text:"送货单列表"},
            {href:"#",text:"送货记录"}
        ]},
        {href:"#",text:"产品",sub:[
            {href:"#",text:"新建产品"},
            {href:"#",text:"产品信息列表"}
        ]},
        {href:"#",text:"基础设置",sub:[
            {href:"#company",text:"公司"},
            {href:"#",text:"纸类"},
            {href:"#",text:"尺寸"},
            {href:"#",text:"打印模板"}
        ]},

    ]
    //当前选中菜单
    $scope.menuSelected =$scope.menus[0];
    //更改选中菜单
    $scope.changeMenu = function(index){
        $scope.menuSelected = $scope.menus[index];
    }
});

scotchApp.controller('aboutController', function ($scope) {
    $scope.message = 'Look! I am an about page.';
});

scotchApp.controller('contactController', function ($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});