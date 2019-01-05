package com.ujjwal.newsportal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ujjwal.newsportal.dao.LoginUserDao;
import com.ujjwal.newsportal.dao.LoginUserDaoImpl;
import com.ujjwal.newsportal.dao.NewsDao;
import com.ujjwal.newsportal.dao.NewsDaoImpl;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String account(Model model) {
		return "ls";
	}
	
//	@RequestMapping(value = "/news/register", method = RequestMethod.GET)
//	public String register(Model model) {
//		return "registerpage";
//	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, Model model) {
		
		String fn = request.getParameter("fname"); 
		String ln = request.getParameter("lname");
		String email = request.getParameter("email");
		String in = request.getParameter("rusername");
		String pd = request.getParameter("rpassword");
		String cpd = request.getParameter("rcpassword");
		LoginUserDao ud = new LoginUserDaoImpl();
		ud.connection();
//		ud.registeruser(fn, ln, nt, in, pd);
//		if(ud.findUserByKey("email", email)) {
//			model.addAttribute("alert", "User already Exists");

		//} 
	if( !pd.equals(cpd)) {
			model.addAttribute("alert1", "password & confirm password not matched");
		} 
		else if ("".equals(fn)||"".equals(ln)||"".equals(email)||"".equals(in))
			model.addAttribute("alert2", "All fields mandetory");
		else {
			ud.registeruser(fn, ln, email, in, pd);
		}
		return "/ls";
}
	
	@RequestMapping(value = "/login/create", method = RequestMethod.POST)
	public String loggedAccounts(HttpServletRequest request, Model model) {
		System.out.println("Controller in loginnnnnnnnnnnnn");

		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		LoginUserDao ud = new LoginUserDaoImpl();
		ud.connection();
		
		if ("".equals(username)||"".equals(pwd)) {
			model.addAttribute("alert3", "All fields are manditory");
			return "/ls";
		}
		else {
			ud.isValiduser(username, pwd);
		}
			return "insertnews";
	}
	
	
	
	@RequestMapping(value = "/notfound", method = RequestMethod.GET)
	public String userNotFound(Model model) {
		return "404";
	}

	@RequestMapping(value = "/account/login", method = RequestMethod.POST)
	public void logIn(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		LoginUserDao ud = new LoginUserDaoImpl();
		ud.connection();
		boolean validuser = ud.isValiduser(username, pwd);

		try {
			if (validuser) {
				response.sendRedirect("http://localhost:8080/newsportal/insertnews");
			} else {
				response.sendRedirect("http://localhost:8080/newsportal/404");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}