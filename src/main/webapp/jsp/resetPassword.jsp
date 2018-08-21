<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
	set new password:
	<form action="resetProcess" method="post">
		<table>
			<tr>
				<td><input type="hidden" name="token" value="${token}"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="Password">
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="Re-Enter Password">
				</td>
			</tr>
			<tr>
				<td><input type="Submit" value="Submit"></td>
				<td><input type="reset" value="Cancel"></td>
			</tr>
		</table>
	</form>
</body>
</html>