app.service("contentService",function($http){
    //根据分类ID查询广告列表
    this.findByCategoryId=function(categoryId){
        return $http.get("http://localhost:8088/findByCategoryId?categoryId="+categoryId);
    }
});