package dawid.app.service;


import dawid.app.model.AllGroup;
import dawid.app.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void updateUserPassword(String newPassword, String email);

    void updateUserActivation(int activeCode, String activationCode);

    void updateUserProfile(String newEmail, int id);

    List<User> findAllByGroups(AllGroup allGroup);



}
