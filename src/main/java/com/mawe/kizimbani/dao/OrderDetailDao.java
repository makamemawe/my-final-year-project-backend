package com.mawe.kizimbani.dao;

import com.mawe.kizimbani.entity.OrderDetail;
import com.mawe.kizimbani.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
    public List<OrderDetail> findByUser(User user);

    public List<OrderDetail> findByOrderStatus(String status);
}
