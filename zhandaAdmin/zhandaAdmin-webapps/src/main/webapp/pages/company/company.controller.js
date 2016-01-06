(function() {
    angular
        .module('scotchApp')
        .controller('companyController', function ($scope,CompanyService) {
            $scope.message = 'Look! I am an about page.';

            // 获取主机配置列表
            $scope.getCompanyList=function(){
                CompanyService.get().then(function(data) {
                    $scope.companyList = data;
                });
            }
        });
})()
/**
 * Created by admin on 2016/1/6.
 */
