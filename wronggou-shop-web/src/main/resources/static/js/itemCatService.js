app.service('itemCatService',function($http){
    this.findPage=function(page,rows){
        return $http.get('http://localhost:8087/TbItemCat/findPage?page='+page+'&rows='+rows);
    }
    this.findall=function(){
        return $http.get('http://localhost:8087/TbItemCat/findall');
    }
    this.findByParentId=function(parentId){
        return $http.get('http://localhost:8087/TbItemCat/findOne?parentId='+parentId);
    }
    this.save=function(entity){
        return   $http.post('http://localhost:8087/TbItemCat/add',entity);
    }
    this.update=function(entity){
        return   $http.post('http://localhost:8087/TbItemCat/update', entity);
    }
    this.findOne=function(id){
        return $http.get('http://localhost:8087/TbItemCat/findOne1?id='+id);
    }
    this.delete=function(selectIds){
        return  $http.get('http://localhost:8087/TbItemCat/delete?ids='+selectIds);
    }
});