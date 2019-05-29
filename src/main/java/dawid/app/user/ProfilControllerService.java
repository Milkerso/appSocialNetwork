package dawid.app.user;

import dawid.app.mainController.MainPageController;
import dawid.app.user.photo.Photo;
import dawid.app.user.photo.PhotoService;
import dawid.app.utilities.UserUtilities;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Controller
public class ProfilControllerService {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    public void changeAvatar( Photo photo, BindingResult result, Model model, Locale locale) {

        this.builderPhoto(photo);
        photoService.updateUserProfilePhoto(photo.getName(),photo.getDescription(),photo.getData(),photo.getUserId());
    }

    public User onlineUser() {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        int nrRoli = user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);
        return user;
    }
    public Photo getProfilPhoto(int id)
    {
        System.out.println("1-----------------");
        return photoService.findByUserID(id);

    }
    public String getProfilPhotoEncoded(int id)
    {
        System.out.println("2-----------------");
        Photo photo= this.getProfilPhoto(id);
        byte[] encoded = Base64.encodeBase64(photo.getData());
        return new String(encoded);

    }
    public void insertEmptyPhoto(Photo photo) {
        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\photo.jpg");
        String name = "photo.jpg";
        String originalFileName = "photo.jpg";
        String contentType = "image/png";
        byte[] content = null;
        LOG.info("****jest okej");
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            LOG.info("**** WYWOŁANO >blad)");
        }
        MultipartFile multipartFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        photo.setMultipartFile(multipartFile);
        LOG.info("**** okej2");
    }
    public void builderPhoto(Photo photo)
    {
        User user= this.onlineUser();
        try {
            photo.setData(photo.getMultipartFile().getBytes());
        } catch (IOException e) {
            LOG.info("**** okejooo");
            e.printStackTrace();
        }
        LOG.info("**** okej3");
        photo.setUserId(user.getId());
        LOG.info("**** WYWOŁANO > registersteptwo()"+ photo.getData().toString());

    }

}
