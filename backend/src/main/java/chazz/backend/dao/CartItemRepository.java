package chazz.backend.dao;

import chazz.backend.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "cartitem", path = "cartitem")
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
