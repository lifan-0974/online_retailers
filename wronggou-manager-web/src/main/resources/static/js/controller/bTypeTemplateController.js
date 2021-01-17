app.controller('bTypeTemplateController' ,function($scope,$controller,bTypeTemplateService,brandService,specificationService){

    $controller('baseController',{$scope:$scope});



    //分页
    $scope.findPage=function(page,rows){
        bTypeTemplateService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数

            }
        );
    }
    $scope.brandList={data:[]};//品牌列表
    //读取品牌列表
    $scope.findBrandList=function(){
        brandService.selectOptionList().success(
            function(response){
                $scope.brandList={data:response};
            }
        );
    }
    $scope.specList={data:[]};//品牌列表
    //读取品牌列表
    $scope.findSpecList=function(){
        specificationService.selectOptionList().success(
            function(response){
                $scope.specList={data:response};
            }
        );


    }

    $scope.addTableRow=function(){
        $scope.entity.customAttributeItems.push({});
    }
    $scope.deleTableRow=function(index){
        $scope.entity.customAttributeItems.splice(index,1);//删除
    }

    //根据id查
    $scope.findOne=function(id){
        bTypeTemplateService.findOne(id).success(
            function(response){
                $scope.entity= response;
                $scope.entity.brandIds=  JSON.parse($scope.entity.brandIds);//转换品牌列表
                $scope.entity.specIds=  JSON.parse($scope.entity.specIds);//转换规格列表
                $scope.entity.customAttributeItems= JSON.parse($scope.entity.customAttributeItems);//转换扩展属性
            }
        );
    }
    $scope.jsonToString=function(jsonString,key){
        var json=JSON.parse(jsonString);//将json字符串转换为json对象
        var value="";
        for(var i=0;i<json.length;i++){
            if(i>0){
                value+=","
            }
            value+=json[i][key];
        }
        return value;
    }


    $scope.save = function () {
        var serverObject=null;
        if ($scope.entity.id!=null){
            $scope.entity.brandIds= JSON.stringify($scope.entity.brandIds);//转换品牌列表
            $scope.entity.specIds=  JSON.stringify($scope.entity.specIds);//转换规格列表
            $scope.entity.customAttributeItems= JSON.stringify($scope.entity.customAttributeItems);//转换扩展属性
            serverObject=bTypeTemplateService.update($scope.entity);
        }else {
            $scope.entity.brandIds= JSON.stringify($scope.entity.brandIds);//转换品牌列表
            $scope.entity.specIds=  JSON.stringify($scope.entity.specIds);//转换规格列表
            $scope.entity.customAttributeItems= JSON.stringify($scope.entity.customAttributeItems);//转换扩展属性

            serverObject=bTypeTemplateService.save($scope.entity);
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
        bTypeTemplateService.delete($scope.selectIds).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }

});