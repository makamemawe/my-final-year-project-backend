package com.mawe.kizimbani.service;

import com.mawe.kizimbani.entity.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.mawe.kizimbani.configuration.JwtRequestFilter;
import com.mawe.kizimbani.dao.CartDao;
import com.mawe.kizimbani.dao.OrderDetailDao;
import com.mawe.kizimbani.dao.ProductDao;
import com.mawe.kizimbani.dao.UserDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Order Placed";

    private static final String KEY = "rzp_test_hfjaRz82yPJU9s";
    private static final String KEY_SECRET = "8bMKHNd2ak9EnLdZGG3flsoO";
    private static final String CURRENCY = "INR";

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    public List<OrderDetail> getAllOrderDetails(String status) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        if(status.equals("All")) {
            orderDetailDao.findAll().forEach(
                    x -> orderDetails.add(x)
            );
        } else {
            orderDetailDao.findByOrderStatus(status).forEach(
                    x -> orderDetails.add(x)
            );
        }


         return orderDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();

        return orderDetailDao.findByUser(user);
    }
    public void deleteOrder(Integer orderId) {
        orderDetailDao.deleteById(orderId);
    }

    public Iterable<OrderDetail> deliveryOrder(){
        return orderDetailDao.findAll();
    }

    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o: productQuantityList) {
            Product product = productDao.findById(o.getProductId()).get();

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                  orderInput.getFullName(),
                  orderInput.getFullAddress(),
                  orderInput.getOrderDate(),
                  orderInput.getContactNumber(),
                  orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user,
                    orderInput.getTransactionId()
            );

            // empty the cart.
            if(!isSingleProductCheckout) {
                List<Cart> carts = cartDao.findByUser(user);
                carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));
            }

            orderDetailDao.save(orderDetail);
        }
    }


    public void markOrderAsDelivered(Integer orderId) {
        OrderDetail orderDetail = orderDetailDao.findById(orderId).get();

        if(orderDetail != null) {
            orderDetail.setOrderStatus("Delivered");
            orderDetailDao.save(orderDetail);
        }
    }

    public void updateDelivery(Integer orderId) {
        OrderDetail orderDetail = orderDetailDao.findById(orderId).get();

        if(orderDetail != null) {
            orderDetail.setOrderStatus("Receive");
            orderDetailDao.save(orderDetail);
        }
    }

    public TransactionDetails createTransaction(Double amount) {
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", (amount * 100) );
            jsonObject.put("currency", CURRENCY);

            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);

            Order order = razorpayClient.orders.create(jsonObject);

            TransactionDetails transactionDetails =  prepareTransactionDetails(order);
            return transactionDetails;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private TransactionDetails prepareTransactionDetails(Order order) {
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");

        TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, KEY);
        return transactionDetails;
    }

    public List<SalesReport> generateSalesReport() {
        List<OrderDetail> orderDetails = (List<OrderDetail>) orderDetailDao.findAll();

        // Convert OrderDetail to SalesReportDTO
        return orderDetails.stream().map(order -> {
            SalesReport report = new SalesReport();
            report.setOrderId(order.getOrderId());
            report.setOrderFullName(order.getOrderFullName());
            report.setOrederDate(order.getOrderDate());
            report.setOrderFullOrder(order.getOrderFullOrder());
            report.setOrderContactNumber(order.getOrderContactNumber());
            report.setOrderStatus(order.getOrderStatus());
            report.setOrderAmount(order.getOrderAmount());
            report.setTransactionId(order.getTransactionId());
            // Set other fields as needed
            return report;
        }).collect(Collectors.toList());
    }
}
