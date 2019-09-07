package dawid.app.service;

import dawid.app.model.AllGroup;
import dawid.app.model.Role;
import dawid.app.model.User;
import dawid.app.repository.RoleRepository;
import dawid.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        userRepository.save(user);
    }

    @Override
    public void updateUserPassword(String newPassword, String email) {
        userRepository.updateUserPassword(bCryptPasswordEncoder.encode(newPassword), email);
    }

    @Override
    public void updateUserProfile(String newEmail, int id) {
        userRepository.updateUserProfile(newEmail, id);
    }

    @Override
    public List<User> findAllByGroups(AllGroup allGroup)
    {
        return userRepository.findAllByGroups(allGroup);
    }


    @Override
    public void updateUserActivation(int activeCode, String activationCode) {
        userRepository.updateActivation(activeCode, activationCode);
    }


}
