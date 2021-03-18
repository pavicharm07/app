package com.tweetapp.dataccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.tweetapp.business.Twit;
import com.tweetapp.business.User;
public class TwitDB {
	 public static int insert(Twit twit) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        
	        String query
	                = "INSERT INTO twit (useremail, text, twitdate, mentionedUseremail,twitid) "
	                + "VALUES (?, ?, ?, ?, ?, ?,?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, twit.getUseremail());
	            ps.setString(2, twit.getText());
	            ps.setString(3, twit.getTwitdate());
	            ps.setString(4, twit.getMentioneduseremail());
	            ps.setString(4, twit.getTwitid());

	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	    
	    public static ArrayList<Twit> search(User user)
	    {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        String query = "SELECT * FROM twit "
	                + " WHERE useremail = ? "
	                + " or mentioneduseremail = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, user.getEmail());

	            rs = ps.executeQuery();
	            ArrayList<Twit> twitList = new ArrayList<Twit>();
	            
	            Twit twit = null;
	            while (rs.next()) {
	                twit = new Twit();
	                                
	               
	                twit.setUseremail(rs.getString("useremail"));
	                String text = rs.getString("text");
	                String[] splt = text.split(" ");
	                // make any hashtag or @ tag blue.
	                for(int i=0; i < splt.length; i++){
	                    if (splt[i].charAt(0) == '#'){
	                        splt[i] = "<span style = 'color: blue'>" 
	                                + "<a href=\"hashtag.jsp?hashtag=" + splt[i].substring(1) + "\">"
	                                + splt[i] + 
	                                "</a>"
	                                + "</span>";
	                    }else if(splt[i].charAt(0) == '@'){
	                        if (i < splt.length){
	                            if (Integer.parseInt(rs.getString("mentionedUserID")) != -1){
	                                splt[i] = "<span style = 'color: blue'>" + splt[i] + "</span>";
	                                splt[i+1] = "<span style = 'color: blue'>" + splt[i+1] + "</span>";
	                            }
	                        }
	                    }
	                }
	                StringBuilder strBuilder = new StringBuilder();
	                for (int i = 0; i < splt.length; i++) {
	                   strBuilder.append(splt[i]+ " ");
	                }
	                text = strBuilder.toString();
	                
	                twit.setText(text);
	                twit.setTwitdate(rs.getString("twitdate"));
	                twit.setMentioneduseremail((rs.getString("mentioneduseremail"))); 
	                twit.setTwitid((rs.getString("twitid")));
	                twitList.add(twit);
	            }
	            
	            // reverse sort twitList (so newest ones are first)
	            Collections.reverse(twitList);

	            return twitList;
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	    
	    public static ArrayList<Twit> getAll()
	    {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        String query = "SELECT * FROM twit ";
	        try {
	            ps = connection.prepareStatement(query);

	            rs = ps.executeQuery();
	            ArrayList<Twit> twitList = new ArrayList<Twit>();
	            
	            Twit twit = null;
	            while (rs.next()) {
	                twit = new Twit();
	                                
	              
	                twit.setUseremail(rs.getString("useremail"));
	                String text = rs.getString("text");
	                String[] splt = text.split(" ");
	                // make any hashtag or @ tag blue.
	                for(int i=0; i < splt.length; i++){
	                    if (splt[i].charAt(0) == '#'){
	                        splt[i] = "<span style = 'color: blue'>" 
	                                + "<a href=\"hashtag.jsp?hashtag=" + splt[i].substring(1) + "\">"
	                                + splt[i] + 
	                                "</a>"
	                                + "</span>";
	                    }else if(splt[i].charAt(0) == '@'){
	                        if (i < splt.length){
	                            if (Integer.parseInt(rs.getString("mentionedUserID")) != -1){
	                                splt[i] = "<span style = 'color: blue'>" + splt[i] + "</span>";
	                                splt[i+1] = "<span style = 'color: blue'>" + splt[i+1] + "</span>";
	                            }
	                        }
	                    }
	                }
	                StringBuilder strBuilder = new StringBuilder();
	                for (int i = 0; i < splt.length; i++) {
	                   strBuilder.append(splt[i]+ " ");
	                }
	                text = strBuilder.toString();
	                
	                twit.setText(text);
	                twit.setTwitdate(rs.getString("twitdate"));
	                twit.setMentioneduseremail(rs.getString("mentionedUserID"));
	                twit.setTwitid((rs.getString("twitid")));
	                twitList.add(twit);
	            }
	            
	            // reverse sort twitList (so newest ones are first)
	            Collections.reverse(twitList);

	            return twitList;
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	    
	  
	    public static int delete(String twitID) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query = "DELETE FROM Twit "
	                + "WHERE twitid = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, twitID);
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	    public static Twit getTwitByID(String twitID)
	    {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        
	        Twit twit = null;
	        String query = "SELECT * FROM twit "
	                + " WHERE twitiD = ? ";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, twitID);
	            rs = ps.executeQuery();
	            
	            while (rs.next()) {
	                twit = new Twit();
	                twit.setTwitid(rs.getString("twitid"));
	                twit.setUseremail(rs.getString("fullname"));
	                twit.setText(rs.getString("text"));
	                twit.setTwitdate(rs.getString("twitdate"));
	                twit.setMentioneduseremail(rs.getString("mentionedUserID"));
	                return twit;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return twit;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return twit;
	    }
	    
	    public static int getTwitID(Twit twit){
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        String query = "SELECT twitid FROM Twit "
	                + " WHERE fullname = ?"
	                + " AND useremail = ?"
	                + " AND text = ?"
	                + " AND twitdate = ?"
	                + " AND mentionedUserID = ?"
	                + " AND userid = ?";

	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, twit.getUseremail());
	            ps.setString(2, twit.getTwitid());
	            ps.setString(3, twit.getText());
	            ps.setString(4, twit.getTwitdate());
	            ps.setString(5,twit.getMentioneduseremail());

	            rs = ps.executeQuery();
	            
	            while (rs.next()) {
	                return Integer.parseInt(rs.getString("twitid"));
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return -1;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return -1;
	    }

	    public static ArrayList<Twit> getNotifications(User user)
	    {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        String query = "SELECT * FROM Twit "
	                + " WHERE mentioneduseremail= ? "
	                + " AND twitdate >= ?";
	        try {
	                        
	            ps = connection.prepareStatement(query);
	            ps.setString(1,user.getEmail());
	            //ps.setString(2, user.getLastLogin());
	            rs = ps.executeQuery();
	            ArrayList<Twit> twitList = new ArrayList<Twit>();
	            
	            Twit twit = null;
	            while (rs.next()) {
	                twit = new Twit();
	                                
	           
	               
	                twit.setUseremail(rs.getString("useremail"));
	                String text = rs.getString("text");
	                String[] splt = text.split(" ");
	                // make any hashtag or @ tag blue.
	                for(int i=0; i < splt.length; i++){
	                    if (splt[i].charAt(0) == '#'){
	                        splt[i] = "<span style = 'color: blue'>" + splt[i] + "</span>";
	                    }else if(splt[i].charAt(0) == '@'){
	                        if (i < splt.length){
	                            if (Integer.parseInt(rs.getString("mentionedUserID")) != -1){
	                                splt[i] = "<span style = 'color: blue'>" + splt[i] + "</span>";
	                                splt[i+1] = "<span style = 'color: blue'>" + splt[i+1] + "</span>";
	                            }
	                        }
	                    }
	                }
	                StringBuilder strBuilder = new StringBuilder();
	                for (int i = 0; i < splt.length; i++) {
	                   strBuilder.append(splt[i]+ " ");
	                }
	                text = strBuilder.toString();
	                
	                twit.setText(text);
	                twit.setTwitdate(rs.getString("twitdate"));
	                twit.setMentioneduseremail(rs.getString("mentioneduseremail"));
	                twit.setTwitid((rs.getString("twitid")));
	                twitList.add(twit);
	            }
	            
	            // reverse sort twitList (so newest ones are first)
	            Collections.reverse(twitList);

	            return twitList;
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }

		public static boolean hasTwit(String twitID) {
			// TODO Auto-generated method stub
			return false;
		}
	}
