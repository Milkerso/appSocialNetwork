package dawid.app.user;

import dawid.app.mainController.MainPageController;
import dawid.app.user.group.AllGroup;
import dawid.app.user.group.GroupService;
import dawid.app.user.photo.Photo;
import dawid.app.user.photo.PhotoService;
import dawid.app.user.userProfile.*;
import dawid.app.utilities.UserUtilities;
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
    private PhotoService photoService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private FreeTimeRepository freeTimeRepository;

    void changeAvatar(Photo photo) {

        this.builderPhoto(photo);
        photoService.updateUserProfilePhoto(photo.getName(), photo.getDescription(), photo.getData(), photo.getUserId());
    }

    public User onlineUser() {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        int nrRoli = user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);
        return user;
    }

    private Photo getProfilePhoto(int id) {
        return photoService.findByUserID(id);

    }

    String getProfilePhotoEncoded(int id) {
        Photo photo = this.getProfilePhoto(id);
        byte[] encoded = Base64.encodeBase64(photo.getData());
        return new String(encoded);

    }

    void insertEmptyPhoto(Photo photo) {
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

    void builderPhoto(Photo photo) {
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

    void groupSearch(UserProfile userProfile) {
        LOG.info("Wywolano groupsearch" + userProfile.getCity());

        AllGroup allGroup;
        for (int i = 0; i < userProfile.getPhysicalActivities().size(); i++) {
            for (int j = 0; j < userProfile.getFreeTime().size(); j++) {


                allGroup = groupService.searchGroupByAllArgument(userProfile.getPhysicalActivity().get(i), userProfile.getFreeTime().get(j), userProfile.getCity());
                if (allGroup == null) {
                    allGroup = new AllGroup();
                    allGroup.setCommonCity(userProfile.getCity());
                    allGroup.setCommonFreeTime(userProfile.getFreeTime().get(j));
                    allGroup.setCommonPhysicalActivities(userProfile.getPhysicalActivity().get(i));
                    List<FreeTime> freeTimesList = new ArrayList<>(userProfile.getFreeTimes());
                    List<PhysicalActivity> physicalActivitiesList = new ArrayList<>(userProfile.getPhysicalActivities());
                    allGroup.setName(freeTimesList.get(j).getName() + " " + physicalActivitiesList.get(i).getName() + " ");
                    allGroup.setUserProfiles(new HashSet<>(Collections.singletonList(userProfile)));
                    groupService.saveGroup(allGroup);
                    LOG.info("nie ma grupy tworzenie jej");
                } else {
                    userProfile.getAllGroups().add(allGroup);
                    userProfileService.saveUserProfile(userProfile);
                }

            }

        }
    }
}

