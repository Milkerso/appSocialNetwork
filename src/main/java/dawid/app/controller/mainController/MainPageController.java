package dawid.app.controller.mainController;

import javax.ws.rs.GET;

import dawid.app.model.UserProfile;
import dawid.app.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dawid.app.model.User;
import dawid.app.service.UserService;
import dawid.app.utilities.UserUtilities;

@Controller
public class MainPageController {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private MessageSource messageSource;

    @GET
    @RequestMapping(value = {"/", "/index"})
    public String showMainPage(Model model) {


        LOG.info("**** WYWOŁANO > showMainPage()");
        try {
            String username = UserUtilities.getLoggedUser();
            User user = userService.findUserByEmail(username);
            UserProfile userProfile=user.getUserProfile();
            int nrRoli = user.getRoles().iterator().next().getId();
            user.setNrRoli(nrRoli);
            LOG.info("**** WYWOŁANO > showMainPage1()"+userProfile.getId());
            if (userProfile.getCharacter()==null) {
                LOG.info("**** WYWOŁANO > showMainPage2()");
                model.addAttribute("userProfile",userProfile);
                return "registersteptwo";
            }
                else {
                LOG.info("**** WYWOŁANO > showMainPage4()");
                return "index";
            }
        } catch (Exception e) {
            LOG.info("**** WYWOŁANO > showMainPage5()"+e);
            return "index";
        }

    }

}
