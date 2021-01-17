app.service('bTypeTemplateService',function($http){

    this.findPage=function(page, rows){
        return  $http.get('http://localhost:8086/bTypeTemplate/findPage?page=' + page + '&rows=' + rows);
    }
    this.save=function(entity){
        return   $http.post('http://localhost:8086/bTypeTemplate/add',entity);
    }
    this.findOne=function(id){
        return $http.get('http://localhost:8086/bTypeTemplate/findOne?id='+id);
    }
    this.update=function(entity){
        return   $http.post('http://localhost:8086/bTypeTemplate/update',entity);
    }
    this.delete=function(selectIds){
        return  $http.get('http://localhost:8086/bTypeTemplate/delete?ids='+selectIds);
    }
});