app.controller('sellerController' ,function($scope,$controller,sellerService){

    $controller('baseController',{$scope:$scope});//继承
//新增
    $scope.add=function(){
        sellerService.add($scope.entity).success(
            function(response){
                if(response.success){
                    //如果注册成功，跳转到登录页
                    location.href="shoplogin.html";
                }else{
                    alert(response.message);
                }
            }
        );
    }
});