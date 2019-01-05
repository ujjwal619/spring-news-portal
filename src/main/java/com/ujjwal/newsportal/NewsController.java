package com.ujjwal.newsportal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ujjwal.newsportal.dao.NewsDao;
import com.ujjwal.newsportal.dao.NewsDaoImpl;
import com.ujjwal.newsportal.model.News;

@Controller
public class NewsController {

	@RequestMapping(value = "/news/submit", method = RequestMethod.POST)
	public String newssubmit(HttpServletRequest request) {

		String fn = request.getParameter("firstname"); 
		String ln = request.getParameter("lastname");
		String nt = request.getParameter("newstitle");
		String in = request.getParameter("insertnewsTextarea");
		String pd = request.getParameter("publishdate");
		
		NewsDao ud = new NewsDaoImpl();
		ud.connection();
		ud.insertnews(fn, ln, nt, in, pd);
		return "success";
	}
		
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String test(Model model) {
		NewsDao impl = new NewsDaoImpl();
		List<News>newsList = impl.listNews();
		System.out.println("control check for news in database");

		System.out.println("newslist size="+ newsList.size());
		model.addAttribute("news", newsList);
		return "/news";
	}
}
