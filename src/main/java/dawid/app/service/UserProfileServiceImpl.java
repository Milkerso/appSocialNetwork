package dawid.app.service;

import dawid.app.model.FreeTime;
import dawid.app.model.PhysicalActivity;
import dawid.app.model.UserProfile;
import dawid.app.repository.FreeTimeRepository;
import dawid.app.repository.PhysicalActivityRepository;
import dawid.app.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    public void saveUserProfile(UserProfile userProfile) {

        userProfileRepository.save(userProfile);

    }

    @Override
    public void saveUserProfileFreeTimeActivities(UserProfile userProfile) {
        List<FreeTime> freeTimesList = new ArrayList<>();
        List<PhysicalActivity> activitiesList = new ArrayList<>();
        List<Integer> freeTimeList = userProfile.getFreeTime();
        List<Integer> activityList = userProfile.getPhysicalActivity();
        for (int i = 0; i < freeTimeList.size(); i++) {
            freeTimesList.add(freeTimeRepository.findById(userProfile.getFreeTime().get(i).intValue()));
        }
        for (int i = 0; i < activityList.size(); i++) {
            activitiesList.add(physicalActivityRepository.findById(userProfile.getPhysicalActivity().get(i).intValue()));
        }
        userProfile.setFreeTimes(new ArrayList<>(freeTimesList));
        userProfile.setPhysicalActivities(new ArrayList<>(activitiesList));
        userProfileRepository.save(userProfile);
    }


    @Override
    public void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id) {
        userProfileRepository.updateRegisterStepTwo(newlanguage, newnumber, newcharacter, newbirthDate, id);
    }


}
