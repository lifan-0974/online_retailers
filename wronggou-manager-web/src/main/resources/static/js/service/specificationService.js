app.service('specificationService',function($http){


    //分页
    this.findPage=function(page,rows){
        return $http.get('http://localhost:8086/specification/findPage?page='+page+'&rows='+rows);
    }
    this.save=function(entity){
        return   $http.post('http://localhost:8086/specification/add',entity);
    }
    this.findOne=function(id){
        return $http.get('http://localhost:8086/specification/findOne?id='+id);
    }
    this.update=function(entity){
        return   $http.post('http://localhost:8086/specification/update',entity);
    }
    this.delete=function(selectIds){
        return  $http.get('http://localhost:8086/specification/delete?ids='+selectIds);
    }
    this.selectOptionList=function(){
        return $http.get('http://localhost:8086/specification/selectOptionList');
    }
});