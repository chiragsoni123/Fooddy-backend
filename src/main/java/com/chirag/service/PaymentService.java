package com.chirag.service;

import com.chirag.model.Order;
import com.chirag.response.PaymentResponse;
import com.stripe.exception.StripeException;


public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
