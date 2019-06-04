package dawid.app.user.userProfile;

import java.sql.Date;


public interface UserProfileService {

    UserProfile findUserProfileById(int userProfileId);

    void saveUserProfile(UserProfile userProfile);

    void saveUserProfileFreeTimeActivities(UserProfile userProfile);

    void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id);


}
