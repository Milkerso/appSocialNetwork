package dawid.app.user.userProfile;

import java.sql.Date;

public interface UserProfileService {

    void saveUserProfile(UserProfile userProfile);

    void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id);

    void updateRegisterStepThree(String newFreeTime, String newPhysicalActivity, String newWhoSearch, String newDescription, int id);
}
