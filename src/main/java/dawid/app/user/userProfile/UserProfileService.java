package dawid.app.user.userProfile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;


public interface UserProfileService {

    UserProfile findUserProfileById(int userProfileId);
    void saveUserProfile(UserProfile userProfile);

    void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id);

    void updateRegisterStepThree(String newWhoSearch, String newDescription, int id);

}
