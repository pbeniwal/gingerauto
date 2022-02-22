<%@page import="webAppLogin.utils.ServletUtility"%>
<%@ include file="header.jsp"%>
<main id="RegistrationPage" class="login-form">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header text-center">
						<h1>User Registration</h1>
					</div>
					<%=ServletUtility.getSuccessMessage(request)%>
					<%=ServletUtility.getErrorMessage(request)%>
					<div class="card-body">
						<form action="./Registration" method="post">
							<input type="hidden" name="uri" value=""> <input
								type="hidden" name="id" value=""> <input type="hidden"
								name="createdBy" value=""> <input type="hidden"
								name="modifiedBy" value=""> <input type="hidden"
								name="createdDatetime" value=""> <input type="hidden"
								name="modifiedDatetime" value="">
							<div class="form-group row">
								<label for="firsName"
									class="col-md-4 col-form-label text-md-right">First
									Name<font color="red"></font>
								</label>
								<div class="col-md-6">
									<input type="text" id="firstName" class="form-control"
										placeholder="Enter First Name" name="firstName" value="">
									<font color="red"></font>
								</div>
							</div>

							<div class="form-group row">
								<label for="lastName"
									class="col-md-4 col-form-label text-md-right">Last Name<font
									color="red"></font></label>
								<div class="col-md-6">
									<input type="text" id="lastName" class="form-control"
										placeholder="Enter Last Name" name="lastName" value="">
									<font color="red"></font>
								</div>
							</div>
							<div class="form-group row">
								<label for="login" class="col-md-4 col-form-label text-md-right">Login
									Id<font color="red"></font>
								</label>
								<div class="col-md-6">
									<input type="text" id="login" class="form-control"
										placeholder="Enter Login Id" name="login" value=""> <font
										color="red"></font>
								</div>
							</div>

							<div class="form-group row">
								<label for="password"
									class="col-md-4 col-form-label text-md-right">Password<font
									color="red"></font></label>
								<div class="col-md-6">
									<input type="password" id="password" class="form-control"
										placeholder="Enter password" name="password" value="">
									<font color="red"></font>
								</div>
							</div>

							<div class="form-group row">
								<label for="mobile"
									class="col-md-4 col-form-label text-md-right">Mobile
									No.<font color="red"></font>
								</label>
								<div class="col-md-6">
									<input type="text" id="mobile" class="form-control"
										placeholder="Enter Mobile No" name="mobile" value="">
									<font color="red"></font>
								</div>
							</div>
							<div class="col-md-6 offset-md-4">
								<input id="submit" type="submit" class="btn btn-primary"
									name="operation" value="Register">

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<%@ include file="footer.jsp"%>