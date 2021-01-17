app.service('sellerService',function($http){


    //增加
    this.add=function(entity){
        return  $http.post('http://localhost:8087/add',entity);
    }

});