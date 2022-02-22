package webAppLogin.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
@WebServlet(name = "UserList", urlPatterns = { "/UserList" })
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = new UserModel();
		List<UserBeans> list = null;
		list = model.getUserlist();
		System.out.println(list.size());

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("Record Not Found", request);
		}

		ServletUtility.setObject(list, request);

		int index = 1;
		list = (List<UserBeans>) ServletUtility.getObject(request);
		Iterator<UserBeans> it = list.iterator();
		while (it.hasNext()) {
			UserBeans user = (UserBeans) it.next();
			System.out.println(index++);
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
			System.out.println(user.getLogin());
			System.out.println(user.getMobileNo());

		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletUtility.forward("jsp/userList.jsp", request, response);
	}
}
