<%@page import="webAppLogin.utils.ServletUtility"%>
<%@ include file="header.jsp"%>
<main id="LoginPage" class="login-form">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header text-center">
						<h1>Login</h1>
						<h6 style="color: red;"><%= ServletUtility.getErrorMessage(request) %></h6>
						<h6 style="color: green;"></h6>
					</div>
					<div class="card-body">
						<form action="Login" method="post" class="needs-validation"
							novalidate>
							<div class="form-group row">
								<label for="email_address"
									class="col-md-4 col-form-label text-md-right">Login Id<font
									color="red">*</font></label>
								<div class="col-md-6">
									<input type="text" id="login" class="form-control"
										placeholder="Enter Login Id" name="login" required>
									<div class="invalid-feedback">Please fill out this field.</div>
								</div>
							</div>

							<div class="form-group row">
								<label for="password"
									class="col-md-4 col-form-label text-md-right">Password<font
									color="red">*</font></label>
								<div class="col-md-6">
									<input type="password" id="password" class="form-control"
										placeholder="Enter Password" name="password" required>
									<div class="invalid-feedback">Please fill out this field.</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-4 col-form-label text-md-right"></label>
								<div class="col-md-6">
									<input id="agreebutton" class="form-control-checkbox" type="checkbox"
										name="TermNCondition" required> <font color="red">*</font>
									I agree...
									<div class="invalid-feedback">Check this check box to
										continue.</div>
								</div>
							</div>
							<div class="col-md-6 offset-md-4">
								<input type="submit" class="btn btn-primary" name="operation"
									value="Login"> <a href="" class="btn btn-link">
									Forgot Your Password? </a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>


<script>
// Disable form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Get the forms we want to add validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>
<%@ include file="footer.jsp"%>
