package dawid.app.controller;

import dawid.app.repository.AdminRepository;
import dawid.app.repository.PostRepository;
import dawid.app.service.AdminService;
import dawid.app.utilities.SearchSpecification;
import dawid.app.model.Place;
import dawid.app.repository.PlaceRepository;
import dawid.app.utilities.ProfileControllerCalculator;
import dawid.app.model.User;
import dawid.app.service.UserService;
import dawid.app.model.Photo;
import dawid.app.model.FreeTime;
import dawid.app.repository.FreeTimeRepository;
import dawid.app.model.PhysicalActivity;
import dawid.app.repository.PhysicalActivityRepository;
import dawid.app.utilities.UserUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.springframework.data.jpa.domain.Specification.where;


@Controller
public class AdminPageController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminPageController.class);
	
	private static int ELEMENTS = 15;

	@Autowired
	private AdminService adminService;

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserService userService;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private FreeTimeRepository freeTimeRepository;

	@Autowired
	private PhysicalActivityRepository physicalActivityRepository;

	@Autowired
	private ProfileControllerCalculator profileControllerCalculator;

	@Autowired
	private PlaceRepository placeRepository;

	private PostRepository.PhotoService photoService;


	@GET
	@RequestMapping(value = "/admin")
	@Secured(value = { "ROLE_ADMIN" })
	public String openAdminMainPage() {
		LOG.info("Administracja strona główna");
		return "admin/admin";
	}

	@GET
	@RequestMapping(value = "/admin/users/{page}")
	@Secured(value = { "ROLE_ADMIN" })
	public String openAdminAllUsersPage(@PathVariable("page") int page, Model model) {
		Page<User> pages = getAllUsersPageable(page - 1, false, null);

		int totalPages = pages.getTotalPages();
		int currentPage = pages.getNumber();
		List<User> userList = pages.getContent();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage + 1);
		model.addAttribute("userList", userList);
		model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
		return "admin/users";
	}

	@GET
	@RequestMapping(value = "/admin/users/search/{searchName}/{searchLastName}/{searchEmail}/{page}")
	@Secured(value = { "ROLE_ADMIN" })
	public String openAdminAllUsersPageFilter (@PathVariable("searchName") String searchName,
											   @PathVariable("searchLastName") String searchLastName,
											   @PathVariable("searchEmail") String searchEmail,
											   @PathVariable("page") int page, Model model) {
		SearchSpecification searchSpecification=new SearchSpecification();
		PageRequest pageRequest=new PageRequest(page-1,15);
		Page<User> pages = adminRepository.findAll(where(searchSpecification.hasName(searchName))
		.and(searchSpecification.hasLastName(searchLastName))
		.and(searchSpecification.hasEmail(searchEmail)), pageRequest);

		for (User users : pages) {
			int numerRoli = users.getRoles().iterator().next().getId();
			System.out.println("jaja");
			users.setNrRoli(numerRoli);
		}

		int totalPages = pages.getTotalPages();
		System.out.println(searchEmail);
		int currentPage = pages.getNumber();
		List<User> userList = pages.getContent();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage+1);
		model.addAttribute("userList", userList);
		model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
		return "admin/users";
	}

	@GET
	@RequestMapping(value = "/admin/users/edit/{id}")
	@Secured(value = { "ROLE_ADMIN" })
	public String getUserToEdit(@PathVariable("id") int id, Model model) {
		User user = new User();
		user = adminService.findUserById(id);
		Map<Integer, String> roleMap = new HashMap<Integer, String>();
		roleMap = prepareRoleMap();
		Map<Integer, String> activityMap = new HashMap<Integer, String>();
		activityMap = prepareActivityMap();
		int rola = user.getRoles().iterator().next().getId();
		user.setNrRoli(rola);
		model.addAttribute("roleMap", roleMap);
		model.addAttribute("activityMap", activityMap);
		model.addAttribute("user", user);
		return "admin/useredit";
	}

	@POST
	@RequestMapping(value = "/admin/updateuser/{id}")
	@Secured(value = "ROLE_ADMIN")
	public String updateUser(@PathVariable("id") int id, User user) {
		int nrRoli = user.getNrRoli();
		int czyActive = user.getActive();
		adminService.updateUser(id, nrRoli, czyActive);
		return "redirect:/admin/users/1";
	}

	@POST
	@RequestMapping(value = "/admin/addplace")
	@Secured(value = "ROLE_ADMIN")
	public String addPlace(Model model) {
		Place place=new Place();
		model.addAttribute("place",place);
		return "addplace";
	}

	@GET
	@RequestMapping(value = "/admin/users/search/{searchWord}/{page}")
	@Secured(value = "ROLE_ADMIN")
	public String openSearchUsersPage(@PathVariable("searchWord") String searchWord, 
			@PathVariable("page") int page, Model model) {
		Page<User> pages = getAllUsersPageable(page - 1, true, searchWord);
		int totalPages = pages.getTotalPages();
		int currentPage = pages.getNumber();
		List<User> userList = pages.getContent();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage + 1);
		model.addAttribute("userList", userList);
		model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("userList", userList);
		return "admin/usersearch";
	}

	@GET
	@RequestMapping(value = "/admin/users/importusers")
	@Secured(value = "ROLE_ADMIN")
	public String showUploadPageFromXML(Model model) {
		return "admin/importusers";
	}
	
	@POST
	@RequestMapping(value = "/admin/users/upload")
	@Secured(value = "ROLE_ADMIN")
	public String importUsersFromXML(@RequestParam("filename") MultipartFile mFile) {

		String uploadDir = System.getProperty("user.dir") + "/uploads";
		File file;
		try {
			file = new File(uploadDir);
			if (!file.exists()) {
				file.mkdir();
			}
			Path fileAndPath = Paths.get(uploadDir, mFile.getOriginalFilename());
			Files.write(fileAndPath, mFile.getBytes());
			file = new File(fileAndPath.toString());
			List<User> userList = UserUtilities.usersDataLoader(file);
			adminService.saveAll(userList);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/users/1";
	}
	@POST
	@RequestMapping(value = "/admin/addnewplace")
	@Secured(value = "ROLE_ADMIN")
	public String addPlaceEnd(Model model, Place place) {
		String username = UserUtilities.getLoggedUser();
		User user=userService.findUserByEmail(username);

		Photo photo = new Photo();

		photo.setName("BuildPhoto");
		photo.setDescription("DescriptionPhoto");
		photo.setProfilePhoto(2);
		photo.setUserId(user.getId());
		try {
			photo.setData(place.getPhoto().getBytes());
		} catch (IOException e) {

		}
		if(place.getPhoto().isEmpty())
		{
			byte[] data =profileControllerCalculator.insertEmptyPhotoPlace(photo);
			photo.setData(data);
		}
		List<Photo> photos = new ArrayList<>();
		photos.add(photo);
		place.setPhotos(photos);
		List<FreeTime> freeTimesList = new ArrayList<>();
		List<PhysicalActivity> activitiesList = new ArrayList<>();
		List<Integer> freeTimeList = place.getFreeTime();
		List<Integer> activityList = place.getPhysicalActivity();
		for (int i = 0; i < freeTimeList.size(); i++) {
			freeTimesList.add(freeTimeRepository.findById(place.getFreeTime().get(i).intValue()));
		}
		for (int i = 0; i < activityList.size(); i++) {
			activitiesList.add(physicalActivityRepository.findById(place.getPhysicalActivity().get(i).intValue()));
		}
		place.setFreeTimes(new ArrayList<>(freeTimesList));
		place.setPhysicalActivities(new ArrayList<>(activitiesList));
		placeRepository.save(place);


		return "index";
	}

	@DELETE
	@RequestMapping(value = "/admin/users/delete/{id}")
	@Secured(value = "ROLE_ADMIN")
	public String deleteUser(@PathVariable("id") int id) {
		LOG.debug("[WYWOŁANIE >>> AdminPageController.deleteUser > PARAMETR: " + id);
		adminService.deleteUserById(id);
		return "redirect:/admin/users/1";
	}
	
	// Metody uzytkowe
	// Pobranie listy userów
	private Page<User> getAllUsersPageable(int page, boolean search, String param) {
		Page<User> pages;
		if (!search) {
			pages = adminService.findAll(PageRequest.of(page, ELEMENTS));
		} else {
			pages = adminService.findAllSearch(param, PageRequest.of(page, ELEMENTS));
		}
		for (User users : pages) {
			int numerRoli = users.getRoles().iterator().next().getId();
			System.out.println("jaja");
			users.setNrRoli(numerRoli);
		}
		return pages;
	}

	// przygotowanie mapy ról
	public Map<Integer, String> prepareRoleMap() {
		Locale locale = Locale.getDefault();
		Map<Integer, String> roleMap = new HashMap<Integer, String>();
		roleMap.put(1, messageSource.getMessage("word.admin", null, locale));
		roleMap.put(2, messageSource.getMessage("word.user", null, locale));
		return roleMap;
	}

	// przygotowanie may aktywny/nieaktywny
	public Map<Integer, String> prepareActivityMap() {
		Locale locale = Locale.getDefault();
		Map<Integer, String> activityMap = new HashMap<Integer, String>();
		activityMap.put(0, messageSource.getMessage("word.nie", null, locale));
		activityMap.put(1, messageSource.getMessage("word.tak", null, locale));
		return activityMap;
	}
}
