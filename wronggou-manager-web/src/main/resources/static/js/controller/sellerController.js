app.controller('sellerController' ,function($scope,$controller,sellerService){

    $controller('baseController',{$scope:$scope});//继承
    //分页
    $scope.findPage=function(page,rows){
        sellerService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数

            }
        );
    }

    //根据id查
    $scope.findOne=function(sellerId){
        sellerService.findOne(sellerId).success(
            function(response){
                $scope.entity= response;
            }
        );
    }

    $scope.updateStatus=function(sellerId,status){
        sellerService.updateStatus(sellerId,status).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }else{
                    alert("失败");
                }
            }
        );
    }
});