package dawid.app.user;

import java.util.Arrays;
import java.sql.Date;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(0);
		Role role = roleRepository.findByRole("ROLE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		userRepository.save(user);
	}

	@Override
	public void updateUserPassword(String newPassword, String email) {
		userRepository.updateUserPassword(bCryptPasswordEncoder.encode(newPassword), email);
	}

	@Override
	public void updateUserProfile(String newName, String newLastName, String newEmail, int id) {
		userRepository.updateUserProfile(newName, newLastName, newEmail, id);
	}
	
	@Override
	public void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id){
		userRepository.updateRegisterStepTwo(newlanguage, newnumber, newcharacter, newbirthDate, id);
	}

	@Override
	public void updateRegisterStepThree(String newFreeTime, String newPhysicalActivity, String newWhoSearch, String newDescription, int id){
		userRepository.updateRegisterStepThree(newFreeTime, newPhysicalActivity, newWhoSearch, newDescription, id);
	}
	
	@Override
	public void updateRegisterStepFourth(String newFileName, String newFileType, byte[] newData,int id)
	{
		userRepository.updateRegisterStepFourth(newFileName, newFileType, newData, id);
	}
	@Override
	public void updateUserActivation(int activeCode, String activationCode) {
		userRepository.updateActivation(activeCode, activationCode);
	}

	
}
