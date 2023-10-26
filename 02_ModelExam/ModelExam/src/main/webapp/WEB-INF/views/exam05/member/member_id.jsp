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
<%-- TODO : header --%>
<jsp:include page="../../common/header.jsp"/>

<%-- TODO : 본문--%>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Job</th>
            <th scope="col">Manager</th>
            <th scope="col">등록일자</th>
            <th scope="col">수정일자</th>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td>${member.eno}</td>
            <td>${member.ename}</td>
            <td>${member.job}</td>
            <td>${member.manager}</td>
            <td>${member.insertTime}</td>
            <td>${member.updateTime}</td>
        </tr>
        </tbody>
    </table>

</div>

<script>
    let obj = "${member}"
    console.log(obj);
</script>
<%-- TODO : footer--%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
