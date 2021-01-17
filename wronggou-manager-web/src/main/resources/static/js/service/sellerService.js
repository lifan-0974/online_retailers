app.service('sellerService',function($http){

    this.findPage=function(page, rows){
        return  $http.get('http://localhost:8086/seller/findPage?page=' + page + '&rows=' + rows);
    }

    this.updateStatus=function(sellerId,status){
        return $http.get('http://localhost:8086/seller/updateStatus?sellerId='+sellerId+'&status='+status);
    }
    this.findOne=function(sellerId){
        return $http.get('http://localhost:8086/seller/findOne?id='+sellerId);
    }
});