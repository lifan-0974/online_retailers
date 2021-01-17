app.service('goodsService',function($http){


    //增加
    this.add=function(entity){

        return  $http.post('http://localhost:8087/Goods/add',entity);
    }
    this.update=function(entity){
        return  $http.post('http://localhost:8087/Goods/update',entity);
    }
    this.findPage=function(page,rows){
        return  $http.post('http://localhost:8087/Goods/findPage?page=' + page + '&rows=' + rows);
    }
    this.findOne=function(id){
        return  $http.post('http://localhost:8087/Goods/findOne?id='+id);
    }
});