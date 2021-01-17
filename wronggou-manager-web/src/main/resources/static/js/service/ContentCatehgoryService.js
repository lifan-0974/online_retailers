app.service('ContentCatehgoryService',function($http){

    this.findAll=function(){
        return  $http.get('http://localhost:8086/ContentCatehgory/selectall');
    }
});