<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/earth.css">
<title>決済確認</title>
</head>
<body>

	<div class="header">
		<%@include file="header.jsp"%>
	</div>


	<div class="main">
		<h1>決済確認画面</h1>
		<s:if test="destinationInfoDTO.size() == 0">
			<h3 class="success">宛先情報がありません。</h3>
		</s:if>
		<s:else>
			<s:form action="SettlementCompleteAction">
				<h3 class="success">宛先情報を選択してください。</h3>
				<div class="overflow">
					<table>
						<tr class="midashi" align="center">
							<th>#</th>
							<th>姓</th>
							<th>名</th>
							<th>ふりがな</th>
							<th>住所</th>
							<th>電話番号</th>
							<th>メールアドレス</th>
						</tr>
						<s:iterator value="destinationInfoDTO" status="st">
							<tr>
								<td><s:if test="#st.index == 0">
										<input type="radio" name="destinationid" checked="checked"
											value="<s:property value='id'/>" />
									</s:if>
									<s:else>
										<input type="radio" name="destinationid" value="<s:property value='id'/>" />
									</s:else></td>
								<td><s:property value="familyName" /></td>
								<td><s:property value="firstName" /></td>
								<td><s:property value="familyNameKana" /><span> </span> <s:property
										value="firstNameKana" /><br></td>
								<td><s:property value="userAddress" /></td>
								<td><s:property value="telNumber" /></td>
								<td><s:property value="email" /></td>

							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="submit">
					<s:submit value="決済" class="submit_btn" theme="simple" />
				</div>
			</s:form>

		</s:else>
		<s:form action="CreateDestinationAction">
			<div class="submit">
				<s:submit value="新規宛先登録" class="submit_btn" theme="simple" />
			</div>
		</s:form>
	</div>
</body>
</html>