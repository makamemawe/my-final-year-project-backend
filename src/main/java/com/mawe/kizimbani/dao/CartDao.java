package com.mawe.kizimbani.dao;

import com.mawe.kizimbani.entity.Cart;
import com.mawe.kizimbani.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer > {
    public List<Cart> findByUser(User user);
}
