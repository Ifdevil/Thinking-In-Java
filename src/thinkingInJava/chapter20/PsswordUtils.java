package thinkingInJava.chapter20;

import java.util.List;

public class PsswordUtils {

    @UserCase(id=1,description = "Password must contain at least one number")
    public boolean validatePassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }
    @UserCase(id=2)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }
    @UserCase(id=3,description = "new passwords can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords,String password){
        return !prevPasswords.contains(password);
    }
}
