app.service('ContentService',function($http){

    this.findPage=function(page, rows){
        return  $http.get('http://localhost:8086/content/findPage?page=' + page + '&rows=' + rows);
    }
    this.save=function(entity){
        return   $http.post('http://localhost:8086/content/add',entity);
    }
    this.update=function(entity){
        return   $http.post('http://localhost:8086/content/update', entity);
    }
    this.findOne=function(id){
        return $http.get('http://localhost:8086/content/findOne?id='+id);
    }
    this.delete=function(selectIds){
        return  $http.get('http://localhost:8086/content/delete?ids='+selectIds);
    }

});