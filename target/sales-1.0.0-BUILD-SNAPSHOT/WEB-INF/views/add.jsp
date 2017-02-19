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
		<div class="main">
			<div class="message">
				<c:out value="${message}" />
			</div>
			<div>
				<span class="itemName">追加画面</span>
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
			<div>
				<input type="submit" name="add" value="明細追加" />
			</div>
			<%-- 			<c:out value="${errorAdd}" /> --%>

			<div class="errormessage">
				<form:errors path="quantity" />
			</div>
			<div class="errormessage">
				<form:errors path="id" />
			</div>
			<div class="normalmessage">
				<c:out value="${addmessage}" />
			</div>
		</div>
	</form:form>
	<form:form modelAttribute="salesForm" action="/sales/system">
		<div>
			<div>
				<table>
					<tr>
						<th>削除</th>
						<th>商品ID</th>
						<th>商品名</th>
						<th>単価</th>
						<th>点数</th>
						<th>小計</th>
					</tr>
					<c:forEach var="sItem" items="${selectedItems}">

						<tr>
							<th><form:radiobutton path="idToBeDel" value="${sItem.key }" /></th>
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

				<%-- 				<c:out value="${errorDel}" /> --%>
				<div class="errormessage">
					<form:errors path="idToBeDel" />
				</div>
				<div class="normalmessage">
					<c:out value="${delmessage}" />
				</div>
			</div>

		</div>


		<div class="footer">
			<div>
				<input type="submit" name="delete" value="削除" />
			</div>
			<div>
				<input type="submit" name="fix" value="確定" />
			</div>
		</div>
	</form:form>

</body>
</html>
