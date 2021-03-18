package com.tweetapp.controller;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tweetapp.business.User;
import com.tweetapp.dataccess.MailUtilGmail;
import com.tweetapp.dataccess.UserDB;
import com.tweetapp.util.PasswordUtil;
@WebServlet(name = "forgotPasswordServlet", urlPatterns = {"/forgotpassword"})
public class ForgotPasswordServlet extends HttpServlet {

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {  
	        
	        boolean passGenerated = false;

	        HttpSession session = request.getSession();
	        User user = new User();

	        // get current action
	        String action = request.getParameter("action");

	        String message = "";
	        String url = "/forgotpassword.jsp";

	        if (action.equals("forgotpassword")) {
	            // get parameters from the request
	            String email = request.getParameter("email");
	            if (UserDB.emailExists(email)){
	                user = UserDB.search(email);
	                String firstname = UserDB.getFirstName(email);
	                String newpassword = generatePassword();
	                // hash and salt password
	                String salt = "";
	                String saltedAndHashedPassword = "";
	                try {
	                    salt = PasswordUtil.getSalt();
	                    saltedAndHashedPassword = PasswordUtil.hashAndSaltPassword(newpassword, salt);                    

	                } catch (NoSuchAlgorithmException ex) {
	                    saltedAndHashedPassword = ex.getMessage();
	                }

	                if (salt != ""){
	                    user.setSalt(salt);
	                }
	                if (saltedAndHashedPassword != ""){
	                    user.setPassword(saltedAndHashedPassword);
	                }
	                // send email to user
	                String to = email;
	                String from = "forgotpassword@twotter.com";
	                String subject = "Temporary password - TWEET APP";
	                String body = "Dear " + firstname + ",\n\n" +
	                    "Your new generated password is " + newpassword + ".\n"
	                  + "Thank you for using TWEET APP! KEEP TWEETING!";
	                boolean isBodyHTML = false;

	                try
	                {
	                    MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
	                    passGenerated = true;
	                    UserDB.update(user);
	                    url = "/login.jsp";
	                }
	                catch (MessagingException e)
	                {
	                    String errorMessage = 
	                        "ERROR: Unable to send email. " + 
	                            "Check Tomcat logs for details.<br>" +
	                        "NOTE: You may need to configure your system " + 
	                            "as described in chapter 14.<br>" +
	                        "ERROR MESSAGE: " + e.getMessage();
	                    request.setAttribute("errorMessage", errorMessage);
	                    this.log(
	                        "Unable to send email. \n" +
	                        "Here is the email you tried to send: \n" +
	                        "=====================================\n" +
	                        "TO: " + email + "\n" +
	                        "FROM: " + from + "\n" +
	                        "SUBJECT: " + subject + "\n" +
	                        "\n" +
	                        body + "\n\n");
	                }
	            }else{
	                message = "The submitted email does\nnot exist in the database.";
	            }
	        }
	        
	        request.setAttribute("message",message);
	        request.setAttribute("passGenerated",passGenerated);
	        
	        getServletContext()
	                .getRequestDispatcher(url)
	                .forward(request, response);
	    }
	    
	    public static String generatePassword() {
	        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXY"
	                     + "abcdefghijklmnopqrstuvwxyz"
	                     + "0123456789";
	        StringBuilder sb = new StringBuilder();
	        Random random = new Random();
	        for (int i = 0; i < 8; i++) {
	            sb.append(chars.charAt(random.nextInt(chars
	                    .length())));
	        }
	     return sb.toString();
	    }
	    
	}

