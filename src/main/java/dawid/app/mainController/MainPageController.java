package dawid.app.mainController;

import javax.ws.rs.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dawid.app.user.User;
import dawid.app.user.UserService;
import dawid.app.utilities.UserUtilities;

@Controller
public class MainPageController {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @GET
    @RequestMapping(value = {"/", "/index"})
    public String showMainPage() {

        LOG.info("**** WYWOŁANO > showMainPage()");
        try {
            String username = UserUtilities.getLoggedUser();
            User user = userService.findUserByEmail(username);
            int nrRoli = user.getRoles().iterator().next().getId();
            user.setNrRoli(nrRoli);
            if (user.getcity().length() == 0)
                return "registersteptwo";
            else {
                return "index";
            }
        } catch (Exception e) {
            return "index";
        }

    }

}
