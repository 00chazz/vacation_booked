package chazz.backend.dto;

import chazz.backend.entities.Cart;
import chazz.backend.entities.CartItem;
import chazz.backend.entities.Customer;
import lombok.Data;
import org.hibernate.query.Order;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;
}
