/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communicator;

/**
 *
 * @author User
 */
public class Users {
    private String username;
    private String password;
    private int accessLevel;

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
