<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="date" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/sales/resources/css/common.css" />
<title>王航オンラインショップ</title>
</head>
<body>
	<form:form modelAttribute="salesForm" action="/sales/system">
		<div class="header">
			<span class="titleName">王航のオンラインショップ</span>
			<div class="date">
				<script>
					//今日の日付データを変数todayに格納
					var today = new Date();

					//年・月・日・曜日を取得する

					var month = today.getMonth() + 1;
					var week = today.getDay();
					var day = today.getDate();

					var yobi = new Array("日", "月", "火", "水", "木", "金", "土");

					document.write(month + "月" + day + "日 " + "(" + yobi[week]
							+ ")");
				</script>
			</div>

		</div>
		<div>
			<div class="message">
				<c:out value="${message}" />
			</div>
			<div>
				<span class="itemName">商品:</span>
			</div>
			<form:select path="id" itemLabel="name" itemValue="id"
				items="${itemList}" size="3" />
			<div>
				<span class="itemQuantity">数量:</span>
				<form:input path="quantity" size="31" />
			</div>

		</div>
		<div >
			<div>
				<input type="submit" name="add" value="明細追加" />
			</div>
			<%-- 			<c:out value="${errorAdd}" /> --%>
			<div class="errormessage" ><form:errors path="quantity" /></div>
			<div class="errormessage"><form:errors path="id" /></div>
		</div>
	</form:form>
</body>
</html>
