package com.mawe.kizimbani.service;

import com.mawe.kizimbani.dao.RoleDao;
import com.mawe.kizimbani.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
