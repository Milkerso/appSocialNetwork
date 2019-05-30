package dawid.app.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import dawid.app.user.photo.Photo;
import dawid.app.user.photo.PhotoService;
import dawid.app.user.userProfile.FreeTime;
import dawid.app.user.userProfile.FreeTimeRepository;
import dawid.app.user.userProfile.UserProfile;
import dawid.app.user.userProfile.UserProfileService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dawid.app.mainController.MainPageController;
import dawid.app.utilities.UserUtilities;
import dawid.app.validators.ChangePasswordValidator;
import dawid.app.validators.EditUserProfileValidator;

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
    private ProfilControllerService profilControllerService;
    @Autowired
    private FreeTimeRepository freeTimeService;


    @GET
    @RequestMapping(value = "/profil")
    public String showUserProfilePage(Model model) {
        System.out.println("3-----------------");
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        int nrRoli = user.getRoles().iterator().next().getId();
        System.out.println("4-----------------");
        user.setNrRoli(nrRoli);
        System.out.println("laaaaaaaaaaaaaaaaaaaaa" +user.getId());
        Photo photo= new Photo();
        UserProfile userProfile=userProfileService.findUserProfileById(user.getId());
        model.addAttribute("user", userProfile);
        model.addAttribute("photo", photo);
        model.addAttribute("email", user.getEmail());
        System.out.println("5-----------------");
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(user.getId()));
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
        UserProfile userProfile=userProfileService.findUserProfileById(user.getId());
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
        model.addAttribute("userProfile",userProfile);

        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthree")
    public String registerstepthree(Model model) {

        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        UserProfile userProfile=userProfileService.findUserProfileById(user.getId());
        model.addAttribute("userProfile", userProfile);

        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthreeend")
    public String registerStepThreeEnd(UserProfile userProfile,Photo photo, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > endthree()");
        UserProfile userProfileFreeTime=userProfileService.findUserProfileById(userProfile.getId());
        userProfileFreeTime.setFreeTimes(userProfile.getFreeTimes());
        userProfileFreeTime.setDescription(userProfile.getDescription());
        userProfileFreeTime.setFreeTime(userProfile.getFreeTime());
        userProfileFreeTime.setWhoSearch(userProfile.getWhoSearch());
        userProfileFreeTime.setPhysicalActivities(userProfile.getPhysicalActivities());
        userProfileFreeTime.setPhysicalActivity(userProfile.getPhysicalActivity());
        userProfileService.saveUserProfileFreeTimeActivities(userProfileFreeTime);
        profilControllerService.insertEmptyPhoto(photo);
        profilControllerService.builderPhoto(photo);
       try {
           profilControllerService.getProfilPhotoEncoded(userProfile.getId());
       }catch (NullPointerException e)
       {
           photo.setProfilePhoto(1);
           photo.setName("Default");
           photoService.savePhoto(photo);
       }



       // userService.updateRegisterStepThree(userProfile.getFreeTime(), user.getPhysicalActivity(), user.getWhoSearch(), user.getDescription(), user.getId());
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(userProfile.getId()));

        return "registerstepfourth";
    }



    @POST
    @RequestMapping(value = "/registerstepfourth")
    public String registerstepfourth(Model model) {

        User user = profilControllerService.onlineUser();
        Photo photo =new Photo();
        model.addAttribute("photo", photo);
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(user.getId()));
        LOG.info("**** WYWOŁANO > endfo2urth()");
        return "registerstepfourth";
    }

    @POST
    @RequestMapping(value = "/registerstepfourthend")
    public String registerstepfourthend( Photo photo, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > endfourth()");
        photo.setName("lol");
        User user = profilControllerService.onlineUser();
        profilControllerService.changeAvatar(photo, result, model, locale);
        model.addAttribute("user", user);
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(user.getId()));
        return "registerstepfourth";
    }

    @POST
    @RequestMapping(value = "/changephoto")
    public String changePhoto(Photo photo, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > changePhoto()");
        User user =profilControllerService.onlineUser();
        photo.setName("lol");
        photo.setDescription("lolll");
        profilControllerService.changeAvatar( photo, result, model, locale);
        UserProfile userProfile=userProfileService.findUserProfileById(user.getId());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("user", userProfile);
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(user.getId()));
        LOG.info("**** WYWOŁANO > changePhoto2()");
        return "profil";
    }




}