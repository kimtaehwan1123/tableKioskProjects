<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>구매 확인</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .checkout-table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        .checkout-table th, .checkout-table td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        .checkout-table th {
            background-color: #f2f2f2;
            text-align: center;
        }
        .checkout-table td {
            text-align: center;
        }
        .total-price {
            font-weight: bold;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2>구매 확인</h2>
    <c:choose>
        <c:when test="${not empty cart}">
            <table class="checkout-table">
                <thead>
                <tr>
                    <th>메뉴 ID</th>
                    <th>수량</th>
                    <th>가격</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cart}">
                    <tr>
                        <td>${item.mno}</td>
                        <td>${item.quantity}</td>
                        <td>${item.total_price}원</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="2" class="text-right total-price">총 가격</td>
                    <td class="total-price">${totalPrice}원</td>
                </tr>
                </tfoot>
            </table>
            <!-- 구매 완료 버튼 -->
            <form method="post" action="${pageContext.request.contextPath}/finalizeOrder">
                <button type="submit" class="btn btn-success mt-3">구매 완료</button>
            </form>
        </c:when>
        <c:otherwise>
            <p class="text-danger">장바구니가 비어 있습니다.</p>
        </c:otherwise>
    </c:choose>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
