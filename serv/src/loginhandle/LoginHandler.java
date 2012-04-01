package loginhandle;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import entities.Users;

import tableaccess.UsersTableAccess;

public class LoginHandler {

	private ArrayList<String> loggedInUsers;
	private static LoginHandler instance = null;

	protected LoginHandler() {
		loggedInUsers = new ArrayList<String>();
	}

	public static LoginHandler getInstance() {
		if(instance == null) {
			instance = new LoginHandler();
		}
		return instance;
	}

	public synchronized boolean isLoggedIn(String userID) {
		for (String id : loggedInUsers) {
			if (id.equals(userID)) {
				return true;
			}
		}
		return false;
	}

	public synchronized boolean login(String userID, String password) {
		try {
			String encodedPassword = getMD5(password);
			
			String dbPassword = UsersTableAccess.getConnection().get(userID).getPassword();

			if (encodedPassword.equals(dbPassword)) {
				loggedInUsers.add(userID);
				return true;
			} 
		} catch (MD5FailedException e) {
			return false;
		}
		return false;
	}

	public synchronized void logout(String userID) {
		for (String id : loggedInUsers) {
			if (id.equals(userID)) {
				loggedInUsers.remove(id);
			}
		}
	}

	public String getMD5(String message) throws MD5FailedException {
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(message.getBytes("UTF8"));
			final byte[] resultByte = messageDigest.digest();
			final String result = (new BigInteger(1,resultByte)).toString(16);
			return result;
		} catch (UnsupportedEncodingException e) {
			throw new MD5FailedException();
		} catch (NoSuchAlgorithmException e){
			throw new MD5FailedException();
		}
	}

}
