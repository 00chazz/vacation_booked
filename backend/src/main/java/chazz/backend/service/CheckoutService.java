package chazz.backend.service;

import chazz.backend.dto.Purchase;
import chazz.backend.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase Purchase);
}
