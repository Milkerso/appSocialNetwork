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
	private MessageSource messageSource;
	
	private String encodedImage;
	
	@GET
	@RequestMapping(value = "/profil")
	public String showUserProfilePage(Model model) {	
		User user=onlineUser();
		byte[] encoded=Base64.encodeBase64(user.getData());
		String encodedString = new String(encoded);
		model.addAttribute("user", user);
		model.addAttribute("image", encodedString);
		
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
		String returnPage = null;
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
		String returnPage = null;
		LOG.info(user.getName().toString());
		new EditUserProfileValidator().validate(user, result);
		if (result.hasErrors()) {
			returnPage = "editprofil";
		} else {
			userService.updateUserProfile(user.getName(), user.getLastName(), user.getEmail(), user.getId());
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
		LOG.info(user.getCharacter().toString());
	//	Date date =new Date();
	//	user.setBirthDate(date);
	//	LOG.info(user.getBirthDate().toString());
		
	
		
			userService.updateRegisterStepTwo(user.getLanguage(), user.getNumber(), user.getCharacter(), user.getBirthDate(),user.getId());
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
	public String registerStepThreeEnd(User user, BindingResult result, Model model, Locale locale) {
		LOG.info("**** WYWOŁANO > endthree()");
		
		Path path = Paths.get(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\photo.jpg");
		String name = "photo.jpg";
		String originalFileName = "photo.jpg";
		String contentType = "image/png";
		byte[] content = null;
		try {
		    content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MultipartFile multipartFile = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		user.setPhoto(multipartFile);
		LOG.info("**** WYWOŁANO > endfourth()asa");
		changeAvatar(user,result,model,locale);
		byte[] encoded=Base64.encodeBase64(user.getData());
		String encodedString = new String(encoded);
		
			userService.updateRegisterStepThree(user.getFreeTime(), user.getPhysicalActivity(), user.getWhoSearch(), user.getDescription(),user.getId());
			model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
			model.addAttribute("image", encodedString);

		return "registerstepfourth";
	}
	
	@POST
	@RequestMapping(value = "/registerstepfourth")
	public String registerstepfourth(Model model) {
		
		String username = UserUtilities.getLoggedUser();
		User user = userService.findUserByEmail(username);
		user=onlineUser();
		byte[] encoded=Base64.encodeBase64(user.getData());
		String encodedString = new String(encoded);
		model.addAttribute("user", user);
		model.addAttribute("image", encodedString);
		
		return "registerstepfourth";
	}
	
	@POST
	@RequestMapping(value = "/registerstepfourthend")
	public String registerStepFourthEnds(User user, BindingResult result, Model model, Locale locale) {
		LOG.info("**** WYWOŁANO > endfourth()");
		changeAvatar(user,result,model,locale);
		user=onlineUser();
		byte[] encoded=Base64.encodeBase64(user.getData());
		String encodedString = new String(encoded);
		model.addAttribute("user", user);
		model.addAttribute("image", encodedString);
		return "registerstepfourth";
	}
	@POST
	@RequestMapping(value = "/changephoto")
	public String changePhoto(User user, BindingResult result, Model model, Locale locale) {
		LOG.info("**** WYWOŁANO > changePhoto()");
		changeAvatar(user,result,model,locale);
		user=onlineUser();
		byte[] encoded=Base64.encodeBase64(user.getData());
		String encodedString = new String(encoded);
		model.addAttribute("user", user);
		model.addAttribute("image", encodedString);
		LOG.info("**** WYWOŁANO > changePhoto2()");
		return "profil";
	}
	
	public void changeAvatar(User user, BindingResult result, Model model, Locale locale)
	{
		user.setFileName(user.getPhoto().getOriginalFilename());
		try {
			user.setData(user.getPhoto().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setFileType(user.getPhoto().getContentType());

		
			userService.updateRegisterStepFourth(user.getFileName(),user.getFileType(),user.getData(),user.getId());
			model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
	}
	public User onlineUser()
	{
		String username = UserUtilities.getLoggedUser();
		User user = userService.findUserByEmail(username);
		int nrRoli = user.getRoles().iterator().next().getId();
		user.setNrRoli(nrRoli);
		return user;
	}
	
	public String getEncodedImage() {
		return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}
	
	
}