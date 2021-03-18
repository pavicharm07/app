package com.tweetapp.serviceimpl;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.mail.MessagingException;

import com.tweetapp.dataccess.MailUtilGmail;
import com.tweetapp.dataccess.UserDB;
import com.tweetapp.service.ForgotPasswordService;
import com.tweetapp.util.PasswordUtil;

public class ForgotPassworImpl implements ForgotPasswordService{
	public String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXY"
                     + "abcdefghijklmnopqrstuvwxyz"
                     + "0123456789"
                     + "#!@$%^&*";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(chars.charAt(random.nextInt(chars
                    .length())));
        }
     return sb.toString();
    }

}