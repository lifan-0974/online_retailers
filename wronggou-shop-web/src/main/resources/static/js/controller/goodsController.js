app.controller('goodsController' ,function($scope,$controller,$location,goodsService,uploadService,itemCatService,bTypeTemplateService){

    $controller('baseController',{$scope:$scope});//继承

    $scope.findPage=function(page,rows){
        goodsService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数

            }
        );
    }

   $scope.add=function(){
       $scope.entity.goodsDesc.introduction=editor.html();
       $scope.entity.goodsDesc.itemImages= JSON.stringify( $scope.entity.goodsDesc.itemImages);
       $scope.entity.goodsDesc.customAttributeItems= JSON.stringify( $scope.entity.goodsDesc.customAttributeItems);
       $scope.entity.goodsDesc.specificationItems=JSON.stringify(  $scope.entity.goodsDesc.specificationItems);
       $scope.entity.itemList=JSON.stringify($scope.entity.itemList);
       console.log( $scope.entity);
       var serviceObject;//服务层对象
       if($scope.entity.goods.id!=null){//如果有ID
           serviceObject=goodsService.update( $scope.entity ); //修改
       }else{
           serviceObject=goodsService.add( $scope.entity  );//增加
       }

       serviceObject.success(

           function (response) {

               if (response.success) {
                   location.href="./goods";
               } else {
                   alert(response.message);
               }
           }
       );

}
    $scope.uploadFile=function(){
        uploadService.uploadFile().success(function(response) {
            if(response.success){//如果上传成功，取出url
                $scope.image_entity.url=response.message;//设置文件地址
            }else{
                alert(response.message);
            }
        }).error(function() {
            alert("上传发生错误");
        });
    };

    $scope.entity={goods:{},goodsDesc:{itemImages:[]}};//定义页面实体结构
    //添加图片列表
    $scope.add_image_entity=function(){
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    }
    $scope.remove_image_entity=function(index){
        $scope.entity.goodsDesc.itemImages.splice(index,1);
    }
    $scope.selectItemCat1List=function(){
        itemCatService.findByParentId(0).success(
            function(response){
                $scope.itemCat1List=response;
            }
        );
    }
    $scope.$watch('entity.goods.category1Id', function(newValue, oldValue) {
        //根据选择的值，查询二级分类
        itemCatService.findByParentId(newValue).success(
            function(response){
                $scope.itemCat2List=response;
            }
        );
    });
    $scope.$watch('entity.goods.category2Id', function(newValue, oldValue) {
        //根据选择的值，查询二级分类
        itemCatService.findByParentId(newValue).success(
            function(response){
                $scope.itemCat3List=response;
            }
        );
    });
    $scope.$watch('entity.goods.category3Id', function(newValue, oldValue) {
        itemCatService.findOne(newValue).success(
            function(response){
                $scope.entity.goods.typeTemplateId=response.typeId; //更新模板ID
            }
        );
    });
    $scope.$watch('entity.goods.typeTemplateId', function(newValue, oldValue) {
        bTypeTemplateService.findOne(newValue).success(
            function(response){
                $scope.typeTemplate=response;//获取类型模板
                $scope.typeTemplate.brandIds= JSON.parse( $scope.typeTemplate.brandIds);//品牌列表
                if($location.search()['id']==null){
                $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);//扩展属性
            }
        }
        );
        bTypeTemplateService.findSpecList(newValue).success(
            function(response){
                $scope.specList=response;
            }
        );
    });
    $scope.entity={ goodsDesc:{itemImages:[],specificationItems:[]}  };

    $scope.updateSpecAttribute=function($event,name,value){
        var object= $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems ,'attributeName', name);
        if(object!=null){
            if($event.target.checked ){
                object.attributeValue.push(value);
            }else{//取消勾选
               object.attributeValue.splice( object.attributeValue.indexOf(value ) ,1);//移除选项
                //如果选项都取消了，将此条记录移除
                if(object.attributeValue.length==0){
                    $scope.entity.goodsDesc.specificationItems.splice(
                        $scope.entity.goodsDesc.specificationItems.indexOf(object),1);
                }
            }
        }else{
            $scope.entity.goodsDesc.specificationItems.push({"attributeName":name,"attributeValue":[value]});
        }
    }
    $scope.createItemList=function(){
        $scope.entity.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0' } ];//初始
        var items=  $scope.entity.goodsDesc.specificationItems;
        console.log(items);
        for(var i=0;i< items.length;i++){
            $scope.entity.itemList = addColumn( $scope.entity.itemList,items[i].attributeName,items[i].attributeValue );
        }
    }
//添加列值
    addColumn=function(list,columnName,conlumnValues){
        var newList=[];//新的集合
        for(var i=0;i<list.length;i++){
            var oldRow= list[i];
            for(var j=0;j<conlumnValues.length;j++){
                var newRow= JSON.parse( JSON.stringify( oldRow )  );//深克隆
                newRow.spec[columnName]=conlumnValues[j];
                newList.push(newRow);
            }
        }
        return newList;
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
    $scope.findOne=function(){
        var id= $location.search()['id'];//获取参数值
        if(id==null){

            return ;
        }
        goodsService.findOne(id).success(
            function(response){
                $scope.entity= response;
                //向富文本编辑器添加商品介绍
                editor.html($scope.entity.goodsDesc.introduction);
                //显示图片列表
                $scope.entity.goodsDesc.itemImages=JSON.parse($scope.entity.goodsDesc.itemImages);
                //显示扩展属性
                $scope.entity.goodsDesc.customAttributeItems=  JSON.parse($scope.entity.goodsDesc.customAttributeItems);
                //规格
                $scope.entity.goodsDesc.specificationItems=JSON.parse($scope.entity.goodsDesc.specificationItems);
                for( var i=0;i<$scope.entity.itemList.length;i++ ){
                    $scope.entity.itemList[i].spec =
                        JSON.parse( $scope.entity.itemList[i].spec);
                }
            }
        );
    }
    $scope.checkAttributeValue=function(specName,optionName){
        var items= $scope.entity.goodsDesc.specificationItems;
        var object= $scope.searchObjectByKey(items,'attributeName',specName);
        if(object==null){
            return false;
        }else{
            if(object.attributeValue.indexOf(optionName)>=0){
                return true;
            }else{
                return false;
            }
        }
    }
    $scope.toDetails=function (id) {
        var hre = 'http://localhost:8087/goods_edit#?id=' +id; //跳转页
        location.href = hre;
    };
});