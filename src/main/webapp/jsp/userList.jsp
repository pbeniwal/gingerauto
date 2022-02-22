<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="webAppLogin.utils.ServletUtility"%>
<%@page import="webAppLogin.beans.UserBeans"%>
<%@ include file="header.jsp"%>
<main id="UserListPage">
	<div class="container">
		<div class="text-center">
			<h1>User List!!</h1>
		</div>
		<table class="table table-striped table-bordered table-hover">
			<thead class="thead-dark">

				<tr>
					<th scope="col">Id</th>
					<th>Login</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Mobile</th>
				</tr>
			</thead>
			<tbody>
				<%
				int index = 1;
				List<UserBeans> list = (List<UserBeans>) ServletUtility.getObject(request);
				Iterator<UserBeans> it = list.iterator();
				while (it.hasNext()) {
					UserBeans user = (UserBeans) it.next();
				%>
				<tr>
					<th scope="row"><%=index++%></th>
					<td><%=user.getFirstName()%></td>
					<td><%=user.getLastName()%></td>
					<td><%=user.getLogin()%></td>
					<td><%=user.getMobileNo()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</main>
<%@ include file="footer.jsp"%>