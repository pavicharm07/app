package com.tweetapp.business;

public class Twit {
	private String useremail;
	private String text;
	private String twitdate;
	private String mentioneduseremail;
	private String twitid;

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTwitdate() {
		return twitdate;
	}

	public void setTwitdate(String twitdate) {
		this.twitdate = twitdate;
	}

	public String getMentioneduseremail() {
		return mentioneduseremail;
	}

	public void setMentioneduseremail(String mentioneduseremail) {
		this.mentioneduseremail = mentioneduseremail;
	}

	public String getTwitid() {
		return twitid;
	}

	public void setTwitid(String twitid) {
		this.twitid = twitid;
	}

	@Override
	public String toString() {
		return "Twit [useremail=" + useremail + ", text=" + text + ", twitdate=" + twitdate + ", mentioneduseremail="
				+ mentioneduseremail + ", twitid=" + twitid + "]";
	}

	public Twit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Twit(String useremail, String text, String twitdate, String mentioneduseremail, String twitid) {
		super();
		this.useremail = useremail;
		this.text = text;
		this.twitdate = twitdate;
		this.mentioneduseremail = mentioneduseremail;
		this.twitid = twitid;
	}




}