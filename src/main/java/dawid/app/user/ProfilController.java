package dawid.app.user;

import dawid.app.mainController.MainPageController;
import dawid.app.post.Post;
import dawid.app.user.group.AllGroup;
import dawid.app.user.group.GroupService;
import dawid.app.user.photo.Photo;
import dawid.app.user.photo.PhotoService;
import dawid.app.user.userProfile.UserProfile;
import dawid.app.user.userProfile.UserProfileService;
import dawid.app.utilities.UserUtilities;
import dawid.app.validators.ChangePasswordValidator;
import dawid.app.validators.EditUserProfileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class ProfilController {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private ProfileControllerCalculator profileControllerCalculator;


    @Autowired
    private GroupService groupService;


    @GET
    @RequestMapping(value = "/profil")
    public String showUserProfilePage(Model model) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        int nrRoli = user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);
        Photo photo = new Photo();
        UserProfile userProfile = user.getUserProfile();
        model.addAttribute("user", userProfile);
        model.addAttribute("photo", photo);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        return "profil";
    }

    @GET
    @RequestMapping(value = "/places")
    public String showPlaces(Model model) {

        User user =profileControllerCalculator.onlineUser();


        return "places";
    }
    @GET
    @RequestMapping(value = "/allpeople")
    public String showAllPeople(Model model) {

        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        AllGroup allGroup= allGroupList.get(0);

        List<User> users=userService.findAllByGroups(allGroup);
        List<UserProfile> userProfiles=new ArrayList<>();
        for (User userProf:users
             ) {
            UserProfile userProfile=userProf.getUserProfile();
            userProfile.setEmail(user.getEmail());
            userProfile.setPhotoEncoded(profileControllerCalculator.getProfilePhotoEncoded(userProf.getId()));
            userProfiles.add(userProfile);
        }
        model.addAttribute("allGroup",allGroup);
        model.addAttribute("groupList",allGroupList);
        model.addAttribute("users",userProfiles);

        return "allpeople";
    }

    @GET
    @RequestMapping(value = "/allpeople/{groupId}")
    public String showAllPeopleGroupId(@PathVariable("groupId") int groupId,Model model) {

        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        AllGroup allGroup= groupService.findByGroupId(groupId);
        List<User> users=userService.findAllByGroups(allGroup);
        List<UserProfile> userProfiles=new ArrayList<>();
        for (User userProf:users
        ) {
            UserProfile userProfile=userProf.getUserProfile();
            userProfile.setEmail(user.getEmail());
            userProfile.setPhotoEncoded(profileControllerCalculator.getProfilePhotoEncoded(userProf.getId()));
            userProfiles.add(userProfile);
        }
        model.addAttribute("allGroup",allGroup);
        model.addAttribute("groupList",allGroupList);
        model.addAttribute("users",userProfiles);


        return "allpeople";
    }

    @GET
    @RequestMapping(value = "/editpassword")
    public String editUserPassword(Model model) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        model.addAttribute("user", user);
        return "editpassword";
    }

    @POST
    @RequestMapping(value = "/updatepass")
    public String changeUSerPassword(User user, BindingResult result, Model model, Locale locale) {
        String returnPage;
        new ChangePasswordValidator().validate(user, result);
        new ChangePasswordValidator().checkPasswords(user.getNewPassword(), result);
        if (result.hasErrors()) {
            returnPage = "editpassword";
        } else {
            userService.updateUserPassword(user.getNewPassword(), user.getEmail());
            returnPage = "editpassword";
            model.addAttribute("message", messageSource.getMessage("passwordChange.success", null, locale));
        }
        return returnPage;
    }

    @GET
    @RequestMapping(value = "/editprofil")
    public String changeUserData(Model model) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        model.addAttribute("user", user);
        return "editprofil";
    }


    @POST
    @RequestMapping(value = "/updateprofil")
    public String changeUserDataAction(User user, BindingResult result, Model model, Locale locale) {
        String returnPage;
        new EditUserProfileValidator().validate(user, result);
        if (result.hasErrors()) {
            returnPage = "editprofil";
        } else {
            userService.updateUserProfile(user.getEmail(), user.getId());
            model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
            returnPage = "registersteptwo";
        }
        return returnPage;
    }

    @POST
    @RequestMapping(value = "/registersteptwo")
    public String registerStepTwo(Model model) {

        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        UserProfile userProfile = user.getUserProfile();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+userProfile.getId());
        model.addAttribute("userProfile", userProfile);

        return "registersteptwo";
    }

    @POST
    @RequestMapping(value = "/registersteptwoend")
    public String registerStepTwoEnd(UserProfile userProfile, BindingResult result, Model model, Locale locale) {
        userProfileService.updateRegisterStepTwo(userProfile.getLanguage(), userProfile.getNumber(), userProfile.getCharacter(), userProfile.getBirthDate(), userProfile.getId());
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthree")
    public String registerstepthree(Model model) {

        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        UserProfile userProfile = user.getUserProfile();
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("photo", new Photo());

        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthreeend")
    public String registerStepThreeEnd(UserProfile userProfile, Photo photo, BindingResult result, Model model, Locale locale) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        UserProfile userProfileFreeTime = user.getUserProfile();
        userProfileFreeTime.setFreeTimes(userProfile.getFreeTimes());
        userProfileFreeTime.setDescription(userProfile.getDescription());
        userProfileFreeTime.setFreeTime(userProfile.getFreeTime());
        userProfileFreeTime.setWhoSearch(userProfile.getWhoSearch());
        userProfileFreeTime.setPhysicalActivities(userProfile.getPhysicalActivities());
        userProfileFreeTime.setPhysicalActivity(userProfile.getPhysicalActivity());
        userProfileService.saveUserProfileFreeTimeActivities(userProfileFreeTime);
        profileControllerCalculator.insertEmptyPhoto(photo);
        profileControllerCalculator.builderPhoto(photo);
        try {
            profileControllerCalculator.getProfilePhotoEncoded(user.getId());
        } catch (NullPointerException e) {
            photo.setProfilePhoto(1);
            photo.setName("Default");
            photoService.savePhoto(photo);
        }
        profileControllerCalculator.groupSearch(userProfileFreeTime);
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));

        return "registerstepfourth";
    }


    @POST
    @RequestMapping(value = "/registerstepfourth")
    public String registerstepfourth(Model model) {

        User user = profileControllerCalculator.onlineUser();
        Photo photo = new Photo();
        model.addAttribute("photo", photo);
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        LOG.info("**** WYWOŁANO > registerstepfourth()");
        return "registerstepfourth";
    }

    @POST
    @RequestMapping(value = "/registerstepfourthend")
    public String registerstepfourthend(Photo photo, Model model) {
        LOG.info("**** WYWOŁANO > endfourth()");
        photo.setName("lol");
        photo.setDescription("lolll");
        User user = profileControllerCalculator.onlineUser();
        profileControllerCalculator.changeAvatar(photo);
        model.addAttribute("user", user);
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        return "registerstepfourth";
    }

    @POST
    @RequestMapping(value = "/changephoto")
    public String changePhoto(Photo photo, Model model) {
        LOG.info("**** WYWOŁANO > changePhoto()");
        User user = profileControllerCalculator.onlineUser();
        photo.setName("lol");
        photo.setDescription("lolll");
        profileControllerCalculator.changeAvatar(photo);
        UserProfile userProfile = user.getUserProfile();
        model.addAttribute("email", user.getEmail());
        model.addAttribute("user", userProfile);
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        LOG.info("**** WYWOŁANO > changePhoto2()");
        return "profil";
    }


}