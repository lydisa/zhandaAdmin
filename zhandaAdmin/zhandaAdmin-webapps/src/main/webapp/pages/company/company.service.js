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
        });
})()


