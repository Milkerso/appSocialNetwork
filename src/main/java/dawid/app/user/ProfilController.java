package dawid.app.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import dawid.app.user.photo.Photo;
import dawid.app.user.photo.PhotoService;
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
    private ProfilControllerService profilControllerService;

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
        model.addAttribute("user", user);
        model.addAttribute("photo", photo);
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

        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        model.addAttribute("user", user);

        return "registersteptwo";
    }

    @POST
    @RequestMapping(value = "/registersteptwoend")
    public String registerStepTwoEnd(User user, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > end()");
        LOG.info(Integer.toString(user.getNumber()));
        LOG.info(user.getCharacter());
        userService.updateRegisterStepTwo(user.getLanguage(), user.getNumber(), user.getCharacter(), user.getBirthDate(), user.getId());
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));


        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthree")
    public String registerstepthree(Model model) {

        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        model.addAttribute("user", user);

        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthreeend")
    public String registerStepThreeEnd(User user,Photo photo, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > endthree()");

        profilControllerService.insertEmptyPhoto(photo);
        profilControllerService.changeAvatar(user,photo, result, model, locale);

        userService.updateRegisterStepThree(user.getFreeTime(), user.getPhysicalActivity(), user.getWhoSearch(), user.getDescription(), user.getId());
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(user.getId()));

        return "registerstepfourth";
    }



    @POST
    @RequestMapping(value = "/registerstepfourth")
    public String registerstepfourth(Model model) {

        User user = profilControllerService.onlineUser();
        model.addAttribute("user", user);
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(user.getId()));

        return "registerstepfourth";
    }

    @POST
    @RequestMapping(value = "/registerstepfourthend")
    public String registerStepFourthEnds( Photo photo, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > endfourth()");
        User user = profilControllerService.onlineUser();
        profilControllerService.changeAvatar(user,photo, result, model, locale);
        model.addAttribute("user", user);
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(user.getId()));
        return "registerstepfourth";
    }

    @POST
    @RequestMapping(value = "/changephoto")
    public String changePhoto(Photo photo, BindingResult result, Model model, Locale locale) {
        LOG.info("**** WYWOŁANO > changePhoto()");
        User user =profilControllerService.onlineUser();
        profilControllerService.changeAvatar(user, photo, result, model, locale);
        model.addAttribute("user", user);
        model.addAttribute("image", profilControllerService.getProfilPhotoEncoded(user.getId()));
        LOG.info("**** WYWOŁANO > changePhoto2()");
        return "profil";
    }





}