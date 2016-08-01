(function() {
    'use strict';

    /**
     * 公司信息操作
     */
    angular
        .module('scotchApp')
        .service('CompanyService', function($http, $q) {

            // 获取工作信息
            this.get = function() {
                var deferred = $q.defer();
                $http.post('company/getAllCompany', {"comId":"1"})
                    .success(function(data) {
                        if(data.resultCode == '0') {
                            deferred.resolve(data.data);
                        } else {
                            deferred.reject(data);
                        }
                    }).error(function(data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            } 
            
            
            
            // 测试信息项
            this.getInfoItem = function() {
                var deferred = $q.defer();
                $http.post('info/getInfoItems', {
                    bean:{
                        companys:[{
                            comId:1000,
                            comName:"company1"
                        },{
                            comId:2000,
                            comName:"company2"
                        }],
                        unionName:"china",
                        mainCompany:{
                            comId:4444,
                            comName:"company3"
                        }
                    },
                    className:"com.zhandaAdmin.data.dao.entity.testEntity",
                    formId:"1111"
                })
                    .success(function(data) {
                        if(data.resultCode == '0') {
                            deferred.resolve(data.data);
                        } else {
                            deferred.reject(data);
                        }
                    }).error(function(data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            }
        
            
        
        });
})()


