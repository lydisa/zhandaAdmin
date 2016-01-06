(function() {
    'use strict';

    /**
     * 公司信息操作
     */
    angular
        .module('scotchApp')
        .service('CompanyService', function($http, $q, _) {
            var _cache = [];

            // 获取工作信息
            this.get = function() {
                var deferred = $q.defer();
                $http.post('company/getAllCompany', {})
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

            //// 新增主机配置
            //this.save = function(host) {
            //    var deferred = $q.defer();
            //    $http.post('/udalAdminWeb/udal.htm?action=config.saveHost', host)
            //        .success(function(data) {
            //            if(data.resultCode == '0') {
            //                //host.hostId = data.data.hostId;
            //                deferred.resolve(host);
            //            } else {
            //                deferred.reject(data);
            //            }
            //        }).error(function(data) {
            //        deferred.reject(data);
            //    });
            //
            //    return deferred.promise;
            //}
            //
            //// 删除当前主机配置
            //this.delete = function(host) {
            //    var deferred = $q.defer();
            //    $http.post('/udalAdminWeb/udal.htm?action=config.delHostById', {"hostId": host.hostId})
            //        .success(function(data) {
            //            if(data.resultCode == '0') {
            //                deferred.resolve(data);
            //            } else {
            //                deferred.reject(data);
            //            }
            //        }).error(function(data) {
            //        deferred.reject(data);
            //    });
            //
            //    return deferred.promise;
            //}
            //
            //// 根据操作系统类别获取版本号
            //this.getOsVersion = function(osType) {
            //    var deferred = $q.defer();
            //    if(_cache['osVersion' + osType]) {
            //        deferred.resolve(_cache['osVersion' + osType]);
            //    } else {
            //        $http.post('/udalAdminWeb/udal.htm?action=systemMgr.queryDictsByCode', {"typeCode": "os_" + osType})
            //            .success(function(data) {
            //                if(data.resultCode == '0') {
            //                    _cache['osVersion' + osType] = data.data;
            //                    deferred.resolve(data.data);
            //                } else {
            //                    deferred.reject(data);
            //                }
            //            }).error(function(data) {
            //            deferred.reject(data);
            //        });
            //    }
            //
            //    return deferred.promise;
            //}
            //
            //// 获取操作系统类型
            //this.getOsType = function() {
            //    var deferred = $q.defer();
            //    if(_cache['osType']) {
            //        deferred.resolve(_cache['osType']);
            //    } else {
            //        $http.post('/udalAdminWeb/udal.htm?action=systemMgr.queryDictsByCode', {"typeCode": "system_type"})
            //            .success(function(data) {
            //                if(data.resultCode == '0') {
            //                    _cache['osType'] = data.data;
            //                    deferred.resolve(data.data);
            //                } else {
            //                    deferred.reject(data);
            //                }
            //            }).error(function(data) {
            //            deferred.reject(data);
            //        });
            //    }
            //
            //    return deferred.promise;
            //}
            //
            //this.getOsTypeById = function(id) {
            //    var osTypeList = this.getOsType();
            //    var osType = _.where(osTypeList, {osTypeId: id});
            //    if(osType) {
            //        return osType[0];
            //    }
            //    return null;
            //}
        });
})();
/**
 * Created by admin on 2016/1/6.
 */
