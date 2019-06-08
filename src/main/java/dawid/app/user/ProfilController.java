package dawid.app.user;

import dawid.app.mainController.MainPageController;
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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
        UserProfile userProfile = userProfileService.findUserProfileById(user.getId());
        model.addAttribute("user", userProfile);
        model.addAttribute("photo", photo);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        return "profil";
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

        LOG.info("**** WYWOŁANO > registersteptwo()");
        String username = UserUtilities.getLoggedUser();
        LOG.info("**** WYWOŁANO > registersteptwo1()");
        User user = userService.findUserByEmail(username);
        LOG.info("**** WYWOŁANO > registersteptwo2()");
        UserProfile userProfile = userProfileService.findUserProfileById(user.getId());
        LOG.info("**** WYWOŁANO > registersteptwo3()");
        model.addAttribute("userProfile", userProfile);
        LOG.info("**** WYWOŁANO > registersteptwo4()");

        return "registersteptwo";
    }

    @POST
    @RequestMapping(value = "/registersteptwoend")
    public String registerStepTwoEnd(UserProfile userProfile, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > registersteptwoEnd()");
        userProfileService.updateRegisterStepTwo(userProfile.getLanguage(), userProfile.getNumber(), userProfile.getCharacter(), userProfile.getBirthDate(), userProfile.getId());
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
        model.addAttribute("userProfile", userProfile);

        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthree")
    public String registerstepthree(Model model) {

        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        UserProfile userProfile = userProfileService.findUserProfileById(user.getId());
        model.addAttribute("userProfile", userProfile);

        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthreeend")
    public String registerStepThreeEnd(UserProfile userProfile, Photo photo, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > endthree()");
        UserProfile userProfileFreeTime = userProfileService.findUserProfileById(userProfile.getId());
        userProfileFreeTime.setFreeTimes(userProfile.getFreeTimes());
        userProfileFreeTime.setDescription(userProfile.getDescription());
        userProfileFreeTime.setFreeTime(userProfile.getFreeTime());
        userProfileFreeTime.setWhoSearch(userProfile.getWhoSearch());
        userProfileFreeTime.setPhysicalActivities(userProfile.getPhysicalActivities());
        userProfileFreeTime.setPhysicalActivity(userProfile.getPhysicalActivity());
        LOG.info("_______________", userProfileFreeTime.getCity());
        userProfileService.saveUserProfileFreeTimeActivities(userProfileFreeTime);
        profileControllerCalculator.insertEmptyPhoto(photo);
        profileControllerCalculator.builderPhoto(photo);
        try {
            profileControllerCalculator.getProfilePhotoEncoded(userProfile.getId());
        } catch (NullPointerException e) {
            photo.setProfilePhoto(1);
            photo.setName("Default");
            photoService.savePhoto(photo);
        }
        profileControllerCalculator.groupSearch(userProfileFreeTime);


        // userService.updateRegisterStepThree(userProfile.getFreeTime(), user.getPhysicalActivity(), user.getWhoSearch(), user.getDescription(), user.getId());
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(userProfile.getId()));

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
        UserProfile userProfile = userProfileService.findUserProfileById(user.getId());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("user", userProfile);
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        LOG.info("**** WYWOŁANO > changePhoto2()");
        return "profil";
    }


}