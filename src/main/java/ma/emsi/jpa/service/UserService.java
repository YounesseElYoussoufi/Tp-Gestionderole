package ma.emsi.jpa.service;

import ma.emsi.jpa.entities.Role;
import ma.emsi.jpa.entities.User;

public interface  UserService {
    User addNewUser(User user) ;
    Role addNewRole(Role role);

    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username , String roleName);

    User authenticate(String userName,  String password);

}
