app.controller('goodsController' ,function($scope,$controller,goodsService,itemCatService,){

    $controller('baseController',{$scope:$scope});//继承

    $scope.findPage=function(page,rows){
        goodsService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数

            }
        );
    }


    $scope.status=['未审核','已审核','审核未通过','关闭'];//商品状态
    $scope.itemCatList=[];//商品分类列表
//加载商品分类列表
    $scope.findItemCatList=function(){
        itemCatService.findall().success(
            function(response){
                for(var i=0;i<response.length;i++){
                    $scope.itemCatList[response[i].id]=response[i].name;
                }
            }
        );
    }
    $scope.updateStatus=function(status){
        goodsService.updateStatus($scope.selectIds,status).success(
            function(response){
                if(response.success){//成功
                    $scope.reloadList();//刷新列表
                    $scope.selectIds=[];//清空ID集合
                }else{
                    alert(response.message);
                }
            }
        );
    }
    $scope.dele=function(){
        goodsService.delete($scope.selectIds).success(
            function(response){
                if(response.success){//成功
                    $scope.reloadList();//刷新列表
                    $scope.selectIds=[];//清空ID集合
                }else{
                    alert(response.message);
                }
            }
        );
    }
});