app.service('goodsService',function($http){

    this.updateStatus=function(ids,status){
        return $http.get('http://localhost:8086/Goods/updateStatus?ids='+ids+"&status="+status);
    }
    //增加
    this.add=function(entity){
        return  $http.post('http://localhost:8086/Goods/add',entity);
    }
    this.update=function(entity){
        return  $http.post('http://localhost:8086/Goods/update',entity);
    }
    this.findPage=function(page,rows){
        return  $http.post('http://localhost:8086/Goods/findPage?page=' + page + '&rows=' + rows);
    }
    this.findOne=function(id){
        return  $http.post('http://localhost:8086/Goods/findOne?id='+id);
    }
    this.delete=function(ids){
        return  $http.post('http://localhost:8086/Goods/delete?ids='+ids);
    }
});