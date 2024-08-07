
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>시작 화면</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            text-align: center;
            padding: 50px;
        }
        .start-title {
            font-size: 2.5rem;
            margin-bottom: 20px;
        }
        .start-description {
            font-size: 1.25rem;
            margin-bottom: 40px;
        }
        .btn-start {
            font-size: 1.5rem;
            padding: 10px 20px;
        }
    </style>
</head>
<body>

<header class="bg-dark text-white py-3">
    <h1 class="start-title">올드팝 키오스크</h1>
    <p class="start-description">맛있는 안주와 함께하는 즐거운 시간을 시작하세요!</p>
</header>

<div>
    <a href="kiosk" class="btn btn-primary btn-start">메뉴 보기</a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>