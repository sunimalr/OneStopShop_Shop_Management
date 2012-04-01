/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communicator;

import java.io.Serializable;
/**
 *
 * @author 090018T
 */
public class Packet implements Serializable{
    private PacketTypes PacketType;
    private String userID;
    private FlagType flag;
    
    public static enum FlagType implements Serializable{
    	NO_ERROR,
    	NO_SUCH_USER,
    	NO_PERMISSION,
    	NOT_LOGGED_IN
    };
    

	private Object message;
	
	public Packet(PacketTypes type, String id, Object message) {
		setPacketType(type);
		setUserID(id);
		setMessage(message);
		setFlag(FlagType.NO_ERROR);
	}
	
	public Packet(FlagType error) {
		setPacketType(PacketType.RESPONSE);
		setUserID(null);
		setMessage(null);
		this.flag = FlagType.NO_ERROR;
	}

    /**
     * @return the PacketType
     */
    public PacketTypes getPacketType() {
        return PacketType;
    }

    /**
     * @param PacketType the PacketType to set
     */
    private void setPacketType(PacketTypes PacketType) {
        this.PacketType = PacketType;
    }

    /**
     * @return the message
     */
    public Object getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    private void setMessage(Object message) {
        this.message = message;
    }

	public String getUserID() {
		return userID;
	}

	private void setUserID(String userID) {
		this.userID = userID;
	}

	public FlagType getFlag() {
		return flag;
	}

	public void setFlag(FlagType errorFlag) {
		this.flag = errorFlag;
	}
}