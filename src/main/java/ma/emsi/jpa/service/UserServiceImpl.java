package ma.emsi.jpa.service;

import lombok.AllArgsConstructor;
import ma.emsi.jpa.entities.Role;
import ma.emsi.jpa.entities.User;
import ma.emsi.jpa.repositories.RoleRepository;
import ma.emsi.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(roleName);
        if (user.getRoles() !=null) {

            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        // userRepository.save(user);

    }

    @Override
    public User authenticate(String userName, String password) {
        User user= userRepository.findByUsername(userName);
        if (user==null)throw new RuntimeException("Bad Credentials");
        if (user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad Credentials");
    }
}
