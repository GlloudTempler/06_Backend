<%-- utf, jsp library --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<jsp:include page="../../common/header.jsp"/>

<div class="container">
    <%-- TODO : insert -> post 방식으로 벡엔드 연결 --%>
    <form action="/exam06/member/add" method="post">
        <%-- TODO : 사원명 입력양식 --%>
        <div class="mb-3">
            <label for="ename" class="form-label">사원 이름</label>
            <input type="text" class="form-control" id="ename" required name="ename">
        </div>
        <%-- TODO : 직위 입력양식--%>
        <div class="mb-3">
            <label for="job" class="form-label">사원 직위</label>
            <input type="text" class="form-control" id="job" required name="job">
        </div>
         <%-- TODO : manager 입력양식 --%>
        <div class="mb-3">
            <label for="manager" class="form-label">담당자</label>
            <input type="text" class="form-control" id="manager" required name="manager">
        </div>
        <div class="mb-3">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>

<jsp:include page="../../common/footer.jsp" />
</body>
</html>