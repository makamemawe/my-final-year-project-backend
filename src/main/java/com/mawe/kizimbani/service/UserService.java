package com.mawe.kizimbani.service;

import com.mawe.kizimbani.dao.RoleDao;
import com.mawe.kizimbani.dao.UserDao;
import com.mawe.kizimbani.entity.Role;
import com.mawe.kizimbani.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role subadminRole = new Role();
        subadminRole.setRoleName("SubAdmin");
        subadminRole.setRoleDescription("SubAdmin role with limited admin privileges");
        roleDao.save(subadminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin@1214");
        adminUser.setUserPassword(getEncodedPassword("admin"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User subadminUser = new User();
        subadminUser.setUserName("subadmin");
        subadminUser.setUserPassword(getEncodedPassword("subadmin"));
        subadminUser.setUserFirstName("subadmin");
        subadminUser.setUserLastName("subadmin");
        Set<Role> subadminRoles = new HashSet<>();
        subadminRoles.add(subadminRole);
        subadminUser.setRole(subadminRoles);
        userDao.save(subadminUser);


//        User user = new User();
//        user.setUserName("makamemawe");
//        user.setUserPassword(getEncodedPassword("mawe1214"));
//        user.setUserFirstName("makame");
//        user.setUserLastName("mawe");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
