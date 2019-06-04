package dawid.app.user;

import dawid.app.emailSender.EmailSender;
import dawid.app.user.userProfile.UserProfile;
import dawid.app.user.userProfile.UserProfileService;
import dawid.app.utilities.AppdemoUtils;
import dawid.app.validators.UserRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Locale;

@Controller
public class RegisterController {

    @Autowired
    MessageSource messageSource;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserProfileService userProfileService;

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
        UserProfile userProfile = new UserProfile();

        new UserRegisterValidator().validateEmailExist(userExist, result);

        new UserRegisterValidator().validate(user, result);

        if (result.hasErrors()) {
            returnPage = "register";
        } else {
            user.setActivationCode(AppdemoUtils.randomStringGenerator());

            String content = "Wymagane potwierdzenie rejestracji. Kliknij w poniższy link aby aktywować konto: " +
                    "http://localhost:8080/activatelink/" + user.getActivationCode();

            userService.saveUser(user);
            userProfile.setId(user.getId());
            userProfile.setName(user.getName());
            userProfile.setLastName(user.getLastName());
            userProfile.setCity(user.getCity());
            userProfile.setSex(user.getSex());
            userProfileService.saveUserProfile(userProfile);
            emailSender.sendEmail(user.getEmail(), "Potwierdzenie rejestracji", content);
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
}
