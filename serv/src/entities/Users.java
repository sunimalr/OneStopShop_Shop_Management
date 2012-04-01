package entities;

import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Users implements Serializable{
    private String username;
    private String password;
    private int accessLevel;
    public static final int ADMIN = 1;
    public static final int SALES_PERSON = 2;

    public Users(String name, String passwd, int access) {
    	username = name;
    	password = passwd;
    	accessLevel = access;
    }
    public Users(){
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
