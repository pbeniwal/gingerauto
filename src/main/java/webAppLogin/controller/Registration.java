package webAppLogin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webAppLogin.beans.UserBeans;
import webAppLogin.model.UserModel;
import webAppLogin.utils.ServletUtility;

/**
 * Servlet implementation class RegistrationCTL
 */
@WebServlet(name = "Registration", urlPatterns = { "/Registration" })
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// ServletUtility.forward("jsp/registration.jsp", request, response);
		request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
		// response.sendRedirect("jsp/registration.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserBeans user = new UserBeans();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		//user.setMobileNo(request.getParameter("mobile"));

		if (UserModel.duplicateLogin(user.getLogin())) {
			ServletUtility.setErrorMessage("login already in use", request);
			ServletUtility.forward("jsp/registration.jsp",request, response);
		} else {
			long i = UserModel.addUser(user);
			if (i > 0) {
				ServletUtility.setObjectOnSession(user, request);
				ServletUtility.redirect("Welcome", request, response);

			} else {
				ServletUtility.setErrorMessage("Not inserted", request);
				ServletUtility.forward("jsp/registration.jsp",request, response);
			}
		}
		
	}
}
