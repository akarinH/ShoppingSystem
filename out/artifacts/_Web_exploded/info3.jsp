<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>招贤纳士</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <style>
        * {
          margin: 0;
          padding: 0;
          box-sizing: border-box;
        }
    
        .join-us {
          width: 1100px;
          height: 800px;
          background-color: #fff;
          margin: 50px auto;
        }
    
        .join-us-title {
          width: 1100px;
          height: 100px;
        }
    
        .join-us-title h3 {
          font-weight: 400;
          margin: 0;
          font-size: 28px;
          line-height: 36px;
          color: #000;
          text-align: center;
        }
    
        .join-us-title div {
          font-size: 20px;
          line-height: 28px;
          color: rgb(188, 195, 204);
          letter-spacing: 0;
          text-align: center;
        }
    
        .join-us-content {
          display: flex;
          margin: 0 0 36px;
          height: 550px;
          justify-content: space-between;
          align-content: space-between;
        }
    
        .join-us-column {
          width: 270px;
          height: 450px;
          display: flex;
          flex-wrap: wrap;
          align-content: space-between;
          justify-content: space-between;
        }
    
        .join-us-block {
          background-color: rgb(246, 246, 246);
          position: relative;
          width: 400px;
          padding: 24px;
          margin: 10px;
          border-radius: 10px;
          box-sizing: border-box;
        }
    
        .join-us-block h4 {
          font-weight: 400;
          margin: 0 0 24px 0;
          font-size: 22px;
          color: #000;
          line-height: 24px;
        }
    
        .join-us-block p {
          margin: 0;
          font-size: 16px;
          color: rgb(148, 148, 148);
          line-height: 24px;
        }
    
        .join-us-pic {
          margin-top: 100px;
        }
      </style>
</head>

<body>
<div class="container-fluid">

    <!-- 引入header.jsp -->
    <jsp:include page="/header.jsp"></jsp:include>

    <div class="container-fluid">

    </div>
</div>

<div class="join-us">
    <div class="join-us-title">
      <h3>成长和发展</h3>
      <div>Growth and Development</div>
    </div>
    <div>
      <div class="join-us-content">
        <div class="join-us-column">
          <div class="join-us-block">
            <h4>新人启航</h4>
            <p>每一位加入我们的同学，都会有自己的师兄，他们会帮助你融入团队、熟悉部门业务并陪伴你的成长。我们还为你准备了《深入浅出HTML》《深入浅出CSS》等课程，助力你的编程之旅顺利启航！</p>
          </div>
          <div class="join-us-block">
            <h4>专业成长</h4>
            <p>在这里，我们关注各个领域同学的发展。无论你是什么岗位，都可以找到属于自己专业领域的圈子，和同领域的伙伴一起精进专业，更能和大神一起拓宽视野、学习进步。</p>
          </div>
        </div>
        <div class="join-us-pic"><img
          src="./img/develop.png"
          alt="develop">
        </div>
        <div class="join-us-column">
          <div class="join-us-block">
            <h4>领导力培养</h4>
            <p>如果你期待成为一名管理者，我们还为你准备了《管理者应知应会》等在线学习课程，同时还有《侠客行》《功守道》《DARE》《DISCOVER》等线下领导力培养项目，助你向管理更进一步。</p>
          </div>
          <div class="join-us-block">
            <h4>学习平台</h4>
            <p>我们把海量学习资源汇总在bilibli等视频网站上，涵盖文化、管理、专业、业务、通用等五大知识类目，为你提供更多的学习机会。还有哈佛商学院等精品课程，帮助爱学习的你发现更多可能。</p>
          </div>
        </div>
      </div>
    </div>
  </div>

<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>