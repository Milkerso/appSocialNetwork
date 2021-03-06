package dawid.app.controller;

import dawid.app.controller.mainController.MainPageController;
import dawid.app.model.*;
import dawid.app.repository.PlaceRepository;
import dawid.app.repository.PostRepository;
import dawid.app.utilities.ProfileControllerCalculator;
import dawid.app.repository.UserRepository;
import dawid.app.service.UserService;
import dawid.app.service.GroupService;
import dawid.app.repository.PhotoRepository;
import dawid.app.service.UserProfileService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.*;

@Controller
public class ProfilController {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository.PhotoService photoService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private ProfileControllerCalculator profileControllerCalculator;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;


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
        UserProfile userProfile = user.getUserProfile();
        model.addAttribute("user", userProfile);
        model.addAttribute("photo", photo);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        return "profil";
    }


    @GET
    @RequestMapping(value = "/otherprofile/{userId}")
    public String showOtherUserProfilePage(@PathVariable("userId") int userId, Model model) {

        User user = userRepository.findById(userId);
        Photo photo = new Photo();
        UserProfile userProfile = user.getUserProfile();
        model.addAttribute("user", userProfile);
        model.addAttribute("photo", photo);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        return "otherprofile";
    }

    @GET
    @RequestMapping(value = "/places")
    public String showPlaces(Model model) {
        boolean ifDelete = false;

        User user = profileControllerCalculator.onlineUser();
        List<Place> places = placeRepository.findAll();
        Iterator<Place> it = places.iterator();
        while (it.hasNext()) {
            Place place = it.next();
            ifDelete = false;
            place.setPhotoEncoded(profileControllerCalculator.getBuilderPhotoDefault(place.getPhotos().get(0).getData()));
            if (!isTheSameFreeTime(place, user.getUserProfile().getFreeTimes())) {
                System.out.println(place.getId() + "first");
                it.remove();
                ifDelete = true;
            }
            if (!ifDelete && !isTheSamePhysicalActivites(place, user.getUserProfile().getPhysicalActivities())) {
                System.out.println(place.getId() + "secodn");
                it.remove();
            }
        }

        model.addAttribute("places", places);

        return "places";
    }

    public boolean isTheSameFreeTime(Place place, List<FreeTime> freeTimes) {
        return place.getFreeTimes().stream()
                .anyMatch(freeTime1 -> freeTimes.stream()
                        .anyMatch(freeTime -> freeTime1.equals(freeTime)));
    }

    public boolean isTheSamePhysicalActivites(Place place, List<PhysicalActivity> physicalActivities) {
        return place.getPhysicalActivities().stream()
                .anyMatch(physicalActivity1 -> physicalActivities.stream()
                        .anyMatch(physicalActivity -> physicalActivity1.equals(physicalActivity)));
    }

    @GET
    @RequestMapping(value = "/allpeople")
    public String showAllPeople(Model model) {

        User user = profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList = groupService.findByUserID(user.getId());
        AllGroup allGroup = allGroupList.get(0);

        List<User> users = userService.findAllByGroups(allGroup);
        List<UserProfile> userProfiles = new ArrayList<>();
        for (User userProf : users
        ) {
            UserProfile userProfile = userProf.getUserProfile();
            userProfile.setEmail(userProf.getEmail());
            userProfile.setPhotoEncoded(profileControllerCalculator.getProfilePhotoEncoded(userProf.getId()));
            Date date = Calendar.getInstance().getTime();
            userProfile.setAge(Integer.toString(date.getYear() - userProfile.getBirthDate().getYear()) + " lat");
            userProfiles.add(userProfile);

        }
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("groupList", allGroupList);
        model.addAttribute("users", userProfiles);

        return "allpeople";
    }

    @GET
    @RequestMapping(value = "/allpeople/{groupId}")
    public String showAllPeopleGroupId(@PathVariable("groupId") int groupId, Model model) {

        User user = profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList = groupService.findByUserID(user.getId());
        AllGroup allGroup = groupService.findByGroupId(groupId);
        List<User> users = userService.findAllByGroups(allGroup);
        List<UserProfile> userProfiles = new ArrayList<>();
        for (User userProf : users
        ) {
            UserProfile userProfile = userProf.getUserProfile();
            userProfile.setEmail(user.getEmail());
            userProfile.setPhotoEncoded(profileControllerCalculator.getProfilePhotoEncoded(userProf.getId()));
            userProfiles.add(userProfile);
        }
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("groupList", allGroupList);
        model.addAttribute("users", userProfiles);


        return "allpeople";
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
        String username = UserUtilities.getLoggedUser();
        User newUser = userService.findUserByEmail(username);
        newUser.getUserProfile().setName(user.getUserProfile().getName());
        newUser.getUserProfile().setLastName(user.getUserProfile().getLastName());
        newUser.setEmail(user.getEmail());
        new EditUserProfileValidator().validate(user, result);
        if (result.hasErrors()) {
            returnPage = "editprofil";
        } else {
            userProfileService.saveUserProfile(newUser.getUserProfile());
            userRepository.save(newUser);
            model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
            model.addAttribute("userProfile", newUser.getUserProfile());
            returnPage = "registersteptwo";
        }
        return returnPage;
    }

    @POST
    @RequestMapping(value = "/registersteptwo")
    public String registerStepTwo(Model model) {

        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        UserProfile userProfile = user.getUserProfile();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + userProfile.getId());
        model.addAttribute("userProfile", userProfile);

        return "registersteptwo";
    }

    @POST
    @RequestMapping(value = "/registersteptwoend")
    public String registerStepTwoEnd(UserProfile userProfile, BindingResult result, Model model, Locale locale) {
        userProfileService.updateRegisterStepTwo(userProfile.getLanguage(), userProfile.getNumber(), userProfile.getCharacter(), userProfile.getBirthDate(), userProfile.getId());
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthree")
    public String registerstepthree(Model model) {

        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        UserProfile userProfile = user.getUserProfile();
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("photo", new Photo());

        return "registerstepthree";
    }

    @POST
    @RequestMapping(value = "/registerstepthreeend")
    public String registerStepThreeEnd(UserProfile userProfile, Photo photo, BindingResult result, Model model, Locale locale) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        UserProfile userProfileFreeTime = user.getUserProfile();
        userProfileFreeTime.setFreeTimes(userProfile.getFreeTimes());
        userProfileFreeTime.setDescription(userProfile.getDescription());
        userProfileFreeTime.setFreeTime(userProfile.getFreeTime());
        userProfileFreeTime.setWhoSearch(userProfile.getWhoSearch());
        userProfileFreeTime.setPhysicalActivities(userProfile.getPhysicalActivities());
        userProfileFreeTime.setPhysicalActivity(userProfile.getPhysicalActivity());
        userProfileService.saveUserProfileFreeTimeActivities(userProfileFreeTime);
        profileControllerCalculator.insertEmptyPhoto(photo);
        profileControllerCalculator.builderPhoto(photo);
        try {
            profileControllerCalculator.getProfilePhotoEncoded(user.getId());
        } catch (NullPointerException e) {
            photo.setProfilePhoto(1);
            photo.setName("Default");
            photoService.savePhoto(photo);
        }
        profileControllerCalculator.groupSearch(userProfileFreeTime);
        model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));

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
        photo.setDescription("lolll");
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
        UserProfile userProfile = user.getUserProfile();
        model.addAttribute("email", user.getEmail());
        model.addAttribute("user", userProfile);
        model.addAttribute("image", profileControllerCalculator.getProfilePhotoEncoded(user.getId()));
        LOG.info("**** WYWOŁANO > changePhoto2()");
        return "profil";
    }


}