<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>关于我们</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="container-fluid">

			<!-- 引入header.jsp -->
			<jsp:include page="/header.jsp"></jsp:include>
			<h1 align="center" style="color: black">小组成员:</h1>
			<h2 align="center" style="color: black">洪冠诚</h2>
			<h2 align="center" style="color: black">洪恒辉</h2>
			<h2 align="center" style="color: black">黄楠</h2>
			<h2 align="center" style="color: black">柯昊旸</h2>
			<h2><a href="product?method=index">点击此处回到首页</a></h2>

		</div>
		
		<!-- 引入footer.jsp -->
		<jsp:include page="/footer.jsp"></jsp:include>

	</body>

</html>