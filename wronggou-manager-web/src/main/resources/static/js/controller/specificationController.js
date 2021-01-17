app.controller('specificationController' ,function($scope,$controller,specificationService){

    $controller('baseController',{$scope:$scope});



    //分页
    $scope.findPage=function(page,rows){
        specificationService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数

            }
        );
    }
    $scope.addTableRow=function(){
        $scope.entity.specificationOptionList.push({});
    }
    $scope.deleTableRow=function(index){
        $scope.entity.specificationOptionList.splice(index,1);//删除
    }
    //根据id查
    $scope.findOne=function(id){
        specificationService.findOne(id).success(
            function(response){
                $scope.entity= response;
            }
        );
    }


    $scope.save = function () {

      var serverObject=null;
if ($scope.entity.specification.id!=null){
    serverObject=specificationService.update($scope.entity);
}else {
    serverObject=specificationService.save($scope.entity);
}
        serverObject.success(

            function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    }
    //批量删除
    $scope.delete=function(){
        //获取选中的复选框
        specificationService.delete($scope.selectIds).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }
});