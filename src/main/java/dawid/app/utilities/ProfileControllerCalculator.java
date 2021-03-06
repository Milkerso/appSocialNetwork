package dawid.app.utilities;

import dawid.app.controller.mainController.MainPageController;
import dawid.app.service.UserProfileService;
import dawid.app.model.*;
import dawid.app.repository.PostRepository;
import dawid.app.service.GroupService;
import dawid.app.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public
class ProfileControllerCalculator {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository.PhotoService photoService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserProfileService userProfileService;

    public void changeAvatar(Photo photo) {

        this.builderPhoto(photo);
        LOG.info(photo.getName() + "photo name");
        LOG.info(photo.getId() + "photo id");
        User user = onlineUser();
        photoService.updateUserProfilePhoto(photo.getName(), photo.getDescription(), photo.getData(), user.getId());
    }

    public User onlineUser() {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        int nrRoli = user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);
        return user;
    }

    private Photo getProfilePhoto(int id) {
        return photoService.findByUserIDProfilPhoto(id);

    }

    public String getProfilePhotoEncoded(int id) {
        Photo photo = this.getProfilePhoto(id);
        System.out.println(id + "id uzytkownika");
        byte[] encoded = Base64.encodeBase64(photo.getData());
        return new String(encoded);
    }

    public String getBuilderPhotoDefault(byte[] encoded) {
        encoded = Base64.encodeBase64(encoded);
        return new String(encoded);
    }

    public String getPhotoEncoded(Photo photo) {
        byte[] encoded = Base64.encodeBase64(photo.getData());
        return new String(encoded);

    }

    public byte[] insertEmptyPhotoPlace(Photo photo) {
        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\build.jpg");
        String name = "photo.jpg";
        String originalFileName = "photo.jpg";
        String contentType = "image/png";
        byte[] content = null;
        LOG.info("Wywolano insertEmptyPhoto");
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            LOG.info("**** WYWOŁANO >blad--InsertEmpty photo)");
        }
        MultipartFile multipartFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        photo.setMultipartFile(multipartFile);
        LOG.info("Zdjecie wrzucone insertEmptyPhoyo");
        return content;
    }

    public void insertEmptyPhoto(Photo photo) {
        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\photo.jpg");
        String name = "photo.jpg";
        String originalFileName = "photo.jpg";
        String contentType = "image/png";
        byte[] content = null;
        LOG.info("Wywolano insertEmptyPhoto");
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            LOG.info("**** WYWOŁANO >blad--InsertEmpty photo)");
        }
        MultipartFile multipartFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        photo.setMultipartFile(multipartFile);
        LOG.info("Zdjecie wrzucone insertEmptyPhoyo");
    }

    public void builderPhoto(Photo photo) {
        User user = this.onlineUser();
        try {
            photo.setData(photo.getMultipartFile().getBytes());
        } catch (IOException e) {
            LOG.info("Blod przy wrzuceniu zdjecia");
            e.printStackTrace();
        }
        LOG.info("Photo dobrze ustawione");
        photo.setUserId(user.getId());


    }

    public void groupSearch(UserProfile userProfile) {
        LOG.info("Wywolano groupsearch" + userProfile.getCity());

        AllGroup allGroup;


        for (int i = 0; i < userProfile.getPhysicalActivities().size(); i++) {
            for (int j = 0; j < userProfile.getFreeTimes().size(); j++) {
                allGroup = groupService.searchGroupByAllArgument(userProfile.getPhysicalActivities().get(i).getId(),
                        userProfile.getFreeTimes().get(j).getId(),
                        userProfile.getCity());
                if (allGroup == null) {
                    allGroup = new AllGroup();
                    allGroup.setCommonCity(userProfile.getCity());
                    allGroup.setCommonFreeTime(userProfile.getFreeTimes().get(j));
                    allGroup.setCommonPhysicalActivities(userProfile.getPhysicalActivities().get(i));
                    List<FreeTime> freeTimesList = new ArrayList<>(userProfile.getFreeTimes());
                    List<PhysicalActivity> physicalActivitiesList = new ArrayList<>(userProfile.getPhysicalActivities());
                    allGroup.setName(freeTimesList.get(j).getName() + " " + physicalActivitiesList.get(i).getName());
                    allGroup.setUserProfiles(new HashSet<>(Collections.singletonList(userProfile)));
                    groupService.saveGroup(allGroup);
                } else {
                    userProfile.getAllGroups().add(allGroup);
                    userProfileService.saveUserProfile(userProfile);
                }
            }
        }


    }
}

