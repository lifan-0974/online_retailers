app.controller('contentController' ,function($scope,$controller ,uploadService,ContentService,ContentCatehgoryService) {
    $controller('baseController',{$scope:$scope});


    $scope.findPage = function (page, rows) {
        ContentService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows; //当前页的数据
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }
    $scope.uploadFile=function(){
        uploadService.uploadFile().success(
            function(response){
                if(response.success){
                    $scope.entity.pic=response.message;
                }else{
                    alert("上传失败！");
                }
            }
        ).error(
            function(){
                alert("上传出错！");
            }
        );
    }
    $scope.save = function () {
        var object=null;
        if ($scope.entity.id!=null){
            object=ContentService.update($scope.entity);
        }else {
            object=ContentService.save($scope.entity);
        }
        object.success(

            function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    }
    $scope.findContentCategoryList=function(){
        ContentCatehgoryService.findAll().success(
            function(response){
                $scope.contentCategoryList=response;
            }
        );
    }
    $scope.status=["无效","有效"];

    //根据id查
    $scope.findOne=function(id){
        ContentService.findOne(id).success(
            function(response){
                $scope.entity= response;
            }
        );
    }
    //批量删除
    $scope.delete=function(){
        //获取选中的复选框
        ContentService.delete($scope.selectIds).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }
});