<%@page import="webAppLogin.utils.ServletUtility"%>
<%@page import="webAppLogin.beans.UserBeans"%>
<%@ include file="header.jsp"%>
<main id="WelcomePage">
	<div class="container">
		<div class="jumbotron text-center">
			<h3>
				Welcome
				<%
			UserBeans user = (UserBeans) ServletUtility.getObject(request);
			if (user != null) {
				out.print(user.getFirstName() + " " + user.getLastName() + "!!");
			}
			%>
			</h3>
		</div>
		<div class="jumbotron text-center">
			<h3>It's Nice To Meet You</h3>
		</div>
	</div>
</main>
<%@ include file="footer.jsp"%>