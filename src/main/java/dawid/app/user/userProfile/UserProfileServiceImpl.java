package dawid.app.user.userProfile;

import dawid.app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    UserProfileRepository userProfile;

    @Override
    public UserProfile findUserProfileById(int userProfileId) {
        return userProfileRepository.findUserProfileById(userProfileId);
    }

    @Override
    public void updateRegisterStepThree(String newWhoSearch, String newDescription, int id)
    {
        userProfileRepository.updateRegisterStepThree(newWhoSearch,newDescription,id);
    }
    @Override
    public void saveUserProfile(UserProfile userProfile) {

        userProfileRepository.save(userProfile);
    }


    @Override
    public void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id) {
        userProfileRepository.updateRegisterStepTwo(newlanguage, newnumber, newcharacter, newbirthDate, id);
    }


}
