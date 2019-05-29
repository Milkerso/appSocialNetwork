package dawid.app.user;


import java.sql.Date;


public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void updateUserPassword(String newPassword, String email);

    void updateUserActivation(int activeCode, String activationCode);

     void updateUserProfile(String newEmail, int id);



}
