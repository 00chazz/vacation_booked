package chazz.backend.dao;

import chazz.backend.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "cart", path = "cart")
public interface CartRepository extends JpaRepository<Cart,Long> {
}
