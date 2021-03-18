package com.tweetapp.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tweetapp.business.Twit;
import com.tweetapp.business.User;
import com.tweetapp.dataccess.TwitDB;
import com.tweetapp.dataccess.UserDB;
import com.tweetapp.util.CookieUtil;

@WebServlet(name = "redirectionServlet", urlPatterns = {"/redirection"})
public class RedirectionServlet extends HttpServlet{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doPost(request, response);
	    }

	    /**
	     * Handles the HTTP <code>POST</code> method.
	     *
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        ServletContext sc = this.getServletContext();
	        HttpSession session = request.getSession();
	        User user = null;
	        boolean loggedIn = false;

	        String url = "/login.jsp";
	        Cookie[] cookies = request.getCookies();
	        String email = CookieUtil.getCookieValue(cookies, "emailTwotter");

	        // if cookie doesn't exist, go to Registration page
	        if (email == null || email.equals("")) {
	            loggedIn = false;
	            url = "/login.jsp";
	        }
	        // if cookie exists, create User object and go to home page
	        else {
	            System.out.println("cookie exists...");
	            user = UserDB.search(email);
	            session.setAttribute("user", user);
	            loggedIn = true;
	            url = "/home.jsp";
	        }

	        request.setAttribute("checkedCookies",true);
	                List<User> allUsers = UserDB.selectAll();  
	                
	        session.setAttribute("loggedIn", loggedIn);
	        if(loggedIn){
	            if(user != null){
	                session.setAttribute("user", user);
	                if (allUsers != null){
	                    session.setAttribute("allUsers",allUsers);
	                }
	            }
	            if (url.equals("/home.jsp")){
	                List<Twit> allTwits = TwitDB.search(user);
	                session.setAttribute("allUsers", allUsers);
	                session.setAttribute("allTwits", allTwits);                
	            }
	        }
	        // forward to the view
	        getServletContext()
	                .getRequestDispatcher(url)
	                .forward(request, response);
	    }

	    /**
	     * Returns a short description of the servlet.
	     *
	     * @return a String containing servlet description
	     */
	    @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>

	}

