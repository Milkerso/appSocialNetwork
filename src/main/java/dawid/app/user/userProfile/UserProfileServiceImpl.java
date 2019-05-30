package dawid.app.user.userProfile;

import dawid.app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.IntegerSyntax;
import java.sql.Date;
import java.util.*;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    UserProfileRepository userProfile;
    @Autowired
    FreeTimeRepository freeTimeRepository;
    @Autowired
    PhysicalActivityRepository physicalActivityRepository;


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
    public void saveUserProfileFreeTimeActivities(UserProfile userProfile)
    {
        List freeTimesList=new ArrayList<FreeTime>();
        List activitiesList=new ArrayList<PhysicalActivity>();
        List <Integer> freeTimeList=userProfile.getFreeTime();
        List <Integer> activityList=userProfile.getPhysicalActivity();
        for(int i=0;i<freeTimeList.size();i++)
        {
            freeTimesList.add(freeTimeRepository.findById(userProfile.getFreeTime().get(i).intValue()));
        }
        for(int i=0;i<activityList.size();i++)
        {
            activitiesList.add(physicalActivityRepository.findById(userProfile.getPhysicalActivity().get(i).intValue()));
        }
        userProfile.setFreeTimes(new HashSet<FreeTime>(freeTimesList));
        userProfile.setPhysicalActivities(new HashSet<PhysicalActivity>(activitiesList));
        userProfileRepository.save(userProfile);
    }





    @Override
    public void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id) {
        userProfileRepository.updateRegisterStepTwo(newlanguage, newnumber, newcharacter, newbirthDate, id);
    }


}
