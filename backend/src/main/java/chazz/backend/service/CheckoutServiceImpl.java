package chazz.backend.service;

import chazz.backend.dao.CartRepository;
import chazz.backend.dao.CustomerRepository;
import chazz.backend.dto.Purchase;
import chazz.backend.dto.PurchaseResponse;
import chazz.backend.entities.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository){
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();

        if (cart.getId() != null && cart.getId() == 0) {
            cart.setId(null);
        }

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // populate cart with cart items
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(cart::add);

        // populate cart with customer
        cart.setCustomer(purchase.getCustomer());

        // populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);


        customerRepository.save(customer);
        cartRepository.save(cart);

        cart.setStatus(StatusType.ordered);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // generate a random uuid number
        return UUID.randomUUID().toString();
    }
}
