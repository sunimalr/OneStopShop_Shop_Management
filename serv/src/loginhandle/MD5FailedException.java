package loginhandle;
import java.lang.Exception;

public class MD5FailedException extends Exception {
	public String getMessage(){
		return "MD5 Hash generation fialed.";
	}
}
