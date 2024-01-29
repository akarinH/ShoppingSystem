<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script type="text/javascript">
//使用JSON完成导航栏
$(function(){
	$.post(
			"${pageContext.request.contextPath }/product?method=productCategory",
			{type: 1},
			function(data){
				var content ="";
				for (var i = 0; i < data.length; i++) {
					sort="sort";
					content +="<li><a href='${pageContext.request.contextPath}/product?method=product_list&cid="+data[i].cid+"&sort="+sort+"'>"+data[i].cname+"</a></li>";
				}
				$("#categoryUl").html(content);

			},
			"json"
	);
});



</script>


<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img width="70px" height="50px" src="img\logo.jpg">

	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			<c:if test="${empty user }">
				<li><a href="login.jsp">登录</a></li>
				<li><a href="register.jsp">注册</a></li>
			</c:if>
			<c:if test="${!empty user }">
				<li><a href="index.jsp">${user.username }</a></li>
				<li><a href="${pageContext.request.contextPath }/UserServlet?method=quitUser">退出</a></li>
			</c:if>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/order?method=myOrder">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath }">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categoryUl">
<%--					--%>
				</ul>
				<form class="navbar-form navbar-right" method="post" action="${pageContext.request.contextPath }/product?method=product_search">
					<div class="form-group">
						<input id="text" name="text" type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>
			</div>
		</div>
	</nav>
</div>