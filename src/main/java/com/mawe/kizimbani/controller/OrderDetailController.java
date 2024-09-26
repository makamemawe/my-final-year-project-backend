package com.mawe.kizimbani.controller;

import com.mawe.kizimbani.dao.OrderDetailDao;
import com.mawe.kizimbani.entity.OrderDetail;
import com.mawe.kizimbani.entity.OrderInput;
import com.mawe.kizimbani.entity.SalesReport;
import com.mawe.kizimbani.entity.TransactionDetails;
import com.mawe.kizimbani.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderDetailDao orderDetailDao;

//    @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder/{isSingleProductCheckout}"})
    public void placeOrder(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
            @RequestBody OrderInput orderInput) {
        orderDetailService.placeOrder(orderInput, isSingleProductCheckout);
    }

//    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOrderDetails"})
    public List<OrderDetail> getOrderDetails() {
        return orderDetailService.getOrderDetails();
    }

//    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllOrderDetails/{status}"})
    public List<OrderDetail> getAllOrderDetails(@PathVariable(name = "status") String status) {
        return orderDetailService.getAllOrderDetails(status);
    }

    @GetMapping("/delivery")
    public ResponseEntity<List<OrderDetail>> getDeliveryOrders() {
        List<OrderDetail> deliveryOrders = orderDetailDao.findByOrderStatus("delivery");
        if (deliveryOrders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(deliveryOrders);
    }

    @DeleteMapping("deleteOrder/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderDetailService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping({"/updateDelivery/{orderId}"})
    public void updateDelivered(@PathVariable(name = "orderId") Integer orderId) {
        orderDetailService.updateDelivery(orderId);
    }

//    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    public void markOrderAsDelivered(@PathVariable(name = "orderId") Integer orderId) {
        orderDetailService.markOrderAsDelivered(orderId);
    }

//    @PreAuthorize("hasRole('User')")
    @GetMapping({"/createTransaction/{amount}"})
    public TransactionDetails createTransaction(@PathVariable(name = "amount") Double amount) {
        return orderDetailService.createTransaction(amount);
    }

    @GetMapping("/sales-report")
    public List<SalesReport> getSalesReport() {
        return orderDetailService.generateSalesReport();
    }
}
