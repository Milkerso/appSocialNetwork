package dawid.app.controller;

import dawid.app.controller.mainController.MainPageController;
import dawid.app.emailSender.EmailSender;
import dawid.app.service.UserProfileService;
import dawid.app.service.UserService;
import dawid.app.model.User;
import dawid.app.utilities.AppdemoUtils;
import dawid.app.validators.UserRegisterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.*;
import java.util.Locale;

@Controller
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);
    @Autowired
    MessageSource messageSource;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserProfileService userProfileService;

    @Value("${spring.http.address}")
    String address;


    @GET
    @RequestMapping(value = "/register")
    public String registerForm(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "register";
    }

    @POST
    @RequestMapping(value = "/adduser")
    public String registerAction(User user, BindingResult result, Model model, Locale locale) {

        String returnPage;

        User userExist = userService.findUserByEmail(user.getEmail());

        new UserRegisterValidator().validateEmailExist(userExist, result);

        new UserRegisterValidator().validate(user, result);

        if (result.hasErrors()) {
            returnPage = "register";
        } else {
            user.setActivationCode(AppdemoUtils.randomStringGenerator());
            String emailContent="";
            String title="";
            try {
                emailContent = getEmailContentFromFile(getClass().getResource("/email/templates/pl/confirm_password.txt").getPath());
                title = getEmailContentFromFile(getClass().getResource("/email/templates/pl/confirm_password_title.txt").getPath());
            }
            catch (Exception e)
            {
                LOG.info(e.toString());
            }
            String activationLink = address + user.getActivationCode();
            emailContent = emailContent.replaceFirst("<userName>", user.getUserProfile().getName());
            emailContent = emailContent.replaceFirst("<userGetActivationLink>", activationLink);
            userProfileService.saveUserProfile(user.getUserProfile());
            user.setId(user.getUserProfile().getId());
            userService.saveUser(user);
            emailSender.sendEmail(user.getEmail(), title, emailContent);
            model.addAttribute("message", messageSource.getMessage("user.register.success.email", null, locale));
            returnPage = "index";

        }
        return returnPage;
    }

    @POST
    @RequestMapping(value = "/activatelink/{activationCode}")
    public String activateAccount(@PathVariable("activationCode") String activationCode, Model model, Locale locale) {

        userService.updateUserActivation(1, activationCode);

        model.addAttribute("message", messageSource.getMessage("user.register.success", null, locale));

        return "index";
    }


    public String getEmailContentFromFile(String path) throws IOException {

        File fileDir=new File(path);
        String content = "";
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileDir),"UTF-8"
                )
        );

        String textLine = bufferedReader.readLine();
        do {
            content = content + textLine;
            textLine = bufferedReader.readLine();
        } while (textLine != null);

        bufferedReader.close();
        return content;

    }
}
