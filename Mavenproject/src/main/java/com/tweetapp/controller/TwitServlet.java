package com.tweetapp.controller;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tweetapp.business.Twit;
import com.tweetapp.business.User;
import com.tweetapp.dataccess.TwitDB;
import com.tweetapp.dataccess.UserDB;

@WebServlet(name = "twitServlet", urlPatterns = {"/twit"})
public class TwitServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        String fileName = "database.txt";
        ServletContext sc = this.getServletContext();
        String pathName = sc.getRealPath(request.getContextPath());
        pathName = pathName.substring(0, pathName.length() - 20) + "/web/" + fileName;
        
        String url = "";
        String message = "";
                
        Twit twit = new Twit("","","","","");
        User user = (User)session.getAttribute("user");
        
        // get current action
        String action = "";
        action = request.getParameter("action");

        // perform action and set URL to appropriate page
        if (action.equals("twit")) {
            
            String userEmail = request.getParameter("useremail");
            String text = request.getParameter("text");
            String mentioneduseremail=request.getParameter("mentioneduseremail");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = (String)dtf.format(now);
            
            twit.setUseremail(userEmail);
            twit.setText(text);
            twit.setTwitdate(date);
            twit.setMentioneduseremail(mentioneduseremail);
            
            // check if a user was mentioned.
            if (text != null){
                String[] splt = text.split(" ");
                String firstName = "";
                String lastName = "";

                // look for @ tag, get mentioned userId
                for(int i=0; i < splt.length; i++){
                    if (splt[i].charAt(0) == '@'){
                        // get the first name
                        firstName = splt[i].substring(1, splt[i].length());
                        if (i < splt.length-1){
                            lastName = splt[i+1];
                        }
                        mentioneduseremail = UserDB.getemail;
                        break;
                    }
                }
            }
            twit.setMentioneduseremail(mentioneduseremail);

            
            
            if (userEmail == null || userEmail.isEmpty() || text == null ||
                text.isEmpty() || date == null ||  date.isEmpty()) { 
                message = "Twit validation failed.";
                url = "/home.jsp";
            }
            else{ // user has passed validation
                url = "/home.jsp"; // redirect user to home if signup succeeds
                // update user to increment numTwits
                UserDB.update(user);
                // insert new twit into twitdb
                TwitDB.insert(twit);
                
            request.setAttribute("user", user);
            request.setAttribute("message", message);
        }
        if (action.equals("delete")){
            
            // get twitID to delete
            String twitID = request.getParameter("delTwitID");
            
            // if user refreshes page, delete might attempt twice
            if (TwitDB.hasTwit(twitID)){
                
                // get the twit
                Twit twitToDel = TwitDB.getTwitByID(twitID);
                // check if twit has hashtag
                String[] splt = twitToDel.getText().split(" ");
                List<String> hashtagsText = new ArrayList<String>();
                // look for # tag, handle hashtag
                for(int i=0; i < splt.length; i++){
                     if (splt[i] != null && splt[i].length() > 0){
                        if(splt[i].charAt(0) == '#'){
                            hashtagsText.add(splt[i]);
                        }
                    }
                }
                
                TwitDB.delete(twitID);
                // decrement the number of twits
                user.setNumTwits(Integer.toString(Integer.parseInt(user.getNumTwits())-1));
                // update user for decremented twits
                UserDB.update(user);
                
            }
            
            url = "/home.jsp";
        }
        
        if (url.equals("/home.jsp")){
            List<User> allUsers = UserDB.selectAll();
            List<Twit> allTwits = TwitDB.search(user);
            List<Twit> entireTwitDB = TwitDB.getAll();
           

            session.setAttribute("allUsers", allUsers);
            session.setAttribute("allTwits", allTwits);
            session.setAttribute("entireTwitDB", entireTwitDB);
           
        }
        
        getServletContext()
               .getRequestDispatcher(url)
                .forward(request, response);
    }
	}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This servlet inserts new twits into the TwitDB.";
   

}

}
