app.controller('brandController' ,function($scope,brandService){

    $scope.reloadList = function () {
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();
        }
    };
    $scope.findPage = function (page, rows) {
        brandService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows; //当前页的数据
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    $scope.save = function () {
        var object=null;
        if ($scope.entity.id!=null){
      object=brandService.update($scope.entity);
        }else {
            object=brandService.save($scope.entity);
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
    //根据id查
    $scope.findOne=function(id){
        brandService.findOne(id).success(
            function(response){
                $scope.entity= response;
            }
        );
    }

    $scope.selectIds=[];//选中的ID集合
//更新复选
    $scope.updateSelection = function($event, id) {
        if($event.target.checked){//如果是被选中,则增加到数组
            $scope.selectIds.push( id);
        }else{
            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx, 1);//删除
        }
    }
//批量删除
    $scope.delete=function(){
        //获取选中的复选框
        brandService.delete($scope.selectIds).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }
    $scope.searchEntity={};//定义搜索对象

    $scope.search=function(page,rows){
        brandService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.rows;//给列表变量赋值
            }
        );
    }
    // $scope.selectbylike=function(){
    //     brandService.selectbylike($scope.entity).success(
    //
    //             function(response){
    //                 $scope.list = response.rows; //当前页的数据
    //                 $scope.paginationConf.totalItems = response.total;//更新总记录数
    //             }
    //
    //     );
    // }
});