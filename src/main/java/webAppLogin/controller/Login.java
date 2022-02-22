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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
    ServletUtility.forward("jsp/login.jsp",request, response);
  }
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  UserBeans user = new UserBeans();
      String login=request.getParameter("login");
      String pwd=request.getParameter("password");
      
      user = UserModel.userLogin(login,pwd);
      if(user != null) {
          //ServletUtility.setSuccessMessage(request.getParameter("login")+ " is login successfully", request);
    	  ServletUtility.setObjectOnSession(user, request);
          ServletUtility.redirect("Welcome", request, response);
      }else {
          ServletUtility.setErrorMessage("Invalid User", request);
          ServletUtility.forward("jsp/login.jsp", request, response);
      }
  }
}
