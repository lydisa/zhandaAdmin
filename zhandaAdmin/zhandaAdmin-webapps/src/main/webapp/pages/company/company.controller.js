(function() {
    angular
        .module('scotchApp')
        .controller('companyController', function ($scope,CompanyService) {
            $scope.message = 'Look! I am an about page.';

            //测试
            CompanyService.getInfoItem().then(function (data) {
                $scope.infoItems = data.data;
            });
            
            // 获取主机配置列表
            $scope.getCompanyList=function(){
                CompanyService.get().then(function(data) {
                    $scope.companyList = data;
                    $scope.message = data;
                });
            }
            $scope.getCompanyList();
        });
})()
/**
 * Created by admin on 2016/1/6.
 */
