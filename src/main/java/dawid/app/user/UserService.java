package dawid.app.user;


import java.sql.Date;


public interface UserService {
	
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void updateUserPassword(String newPassword, String email);
	public void updateUserProfile(String newName, String newLastName, String newEmail, int id);
	public void updateUserActivation(int activeCode, String activationCode);
	public void updateRegisterStepTwo(String newlanguage, int newnumber, String newcharacter, Date newbirthDate, int id);
	public void updateRegisterStepThree(String newFreeTime, String newPhysicalActivity, String newWhoSearch, String newDescription, int id);
	public void updateRegisterStepFourth(String newFileName, String newFileType, byte[] newData,int id);

	
}
