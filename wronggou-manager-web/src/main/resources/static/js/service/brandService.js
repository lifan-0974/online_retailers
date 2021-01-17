app.service('brandService',function($http){

    this.findPage=function(page, rows){
        return  $http.get('http://localhost:8086/findPage?page=' + page + '&rows=' + rows);
    }
    this.save=function(entity){
        return   $http.post('http://localhost:8086/add',entity);
    }
    this.update=function(entity){
        return   $http.post('http://localhost:8086/update', entity);
    }
    this.findOne=function(id){
        return $http.get('http://localhost:8086/findOne?id='+id);
    }
    this.delete=function(selectIds){
        return  $http.get('http://localhost:8086/delete?ids='+selectIds);
    }
    this.search=function(page,rows,searchEntity){
        return  $http.post('http://localhost:8086/search?page='+page+"&rows="+rows,searchEntity);
    }
    // this.selectbylike=function(entity){
    //     return  $http.post('http://localhost:8086/selectbylike',entity);
    // }
    this.selectOptionList=function(){
        return $http.get('http://localhost:8086/selectOptionList');
    }
});