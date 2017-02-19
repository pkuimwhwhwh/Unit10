<%@ page session="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			<span class="titleName">王航オンラインショップ</span>
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
			<span class="itemName">売上登録画面</span>
		</div>

		<div class="body">
			<div class="normalmessage">
				<c:out value="${fixmessage}" />
			</div>
			<div>
				売上ID:
				<c:out value="${salesID} "></c:out>
			</div>

			<div>
				<table>
					<tr>

						<th>商品ID</th>
						<th>商品名</th>
						<th>単価</th>
						<th>点数</th>
						<th>小計</th>
					</tr>
					<c:forEach var="sItem" items="${selectedItems}">

						<tr>
							<th><c:out value="${sItem.value.id}" /></th>
							<th><c:out value="${sItem.value.name}" /></th>
							<th><c:out value="${sItem.value.price}" /></th>
							<th><c:out value="${sItem.value.quantity}" /></th>
							<th><c:out value="${sItem.value.subtotal}" /></th>
						</tr>
					</c:forEach>
				</table>
				<div>
					合計
					<c:out value="${total}" />
				</div>
			</div>

		</div>
		<div class="footer">
			<div>
				<input type="submit" name="toInit" value="終了" />
			</div>
		</div>
	</form:form>
</body>
</html>
