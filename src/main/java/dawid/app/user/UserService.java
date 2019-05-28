package dawid.app.user;


import java.sql.Date;


public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);

    public void updateUserPassword(String newPassword, String email);

    public void updateUserActivation(int activeCode, String activationCode);

    public void updateUserProfile(String newEmail, int id);

    public void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id);

    public void updateRegisterStepThree(String newFreeTime, String newPhysicalActivity, String newWhoSearch, String newDescription, int id);



}
