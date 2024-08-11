package com.example.Tunehub.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Tunehub.entities.Users;
import com.example.Tunehub.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Controller
public class PaymentController {

    private static final Logger LOGGER = Logger.getLogger(PaymentController.class.getName());

    @Autowired
    UserService userserv;

    @PostMapping("/createOrder")
    @ResponseBody
    public String createOrder() {
        Order order = null;
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_MWaaIMHrDpKute", "lPUIxAUXZprV52x95J7MtNcW");

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", 50000);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt#1");
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Tea, Earl Grey, Hot");
            orderRequest.put("notes", notes);

            order = razorpay.orders.create(orderRequest);
        } catch (RazorpayException e) {
            LOGGER.severe("RazorpayException while creating order: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Exception while creating order: " + e.getMessage());
        }
        return (order != null) ? order.toString() : "{}";
    }

    @PostMapping("/verify")
    @ResponseBody
    public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId,
                                 @RequestParam String signature) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_MWaaIMHrDpKute", "lPUIxAUXZprV52x95J7MtNcW");
            String verificationData = orderId + "|" + paymentId;

            return Utils.verifySignature(verificationData, signature, "lPUIxAUXZprV52x95J7MtNcW");
        } catch (RazorpayException e) {
            LOGGER.severe("RazorpayException while verifying payment: " + e.getMessage());
            return false;
        } catch (Exception e) {
            LOGGER.severe("Exception while verifying payment: " + e.getMessage());
            return false;
        }
    }

    @GetMapping("payment-success")
    public String paymentSuccess(HttpSession session) {
        try {
            String email = (String) session.getAttribute("email");
            if (email != null) {
                Users user = userserv.getUser(email);
                if (user != null) {
                    user.setPremium(true);
                    userserv.updateUser(user);
                } else {
                    LOGGER.warning("User not found: " + email);
                }
            } else {
                LOGGER.warning("No email found in session.");
            }
        } catch (Exception e) {
            LOGGER.severe("Exception while processing payment success: " + e.getMessage());
        }
        return "Login";
    }

    @GetMapping("payment-failure")
    public String paymentFailure() {
        return "Login";
    }
}
