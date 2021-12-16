package ru.gb.gbthymeleaf.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleaf.dao.CartDao;
import ru.gb.gbthymeleaf.dao.ProductDao;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.Product;
import ru.gb.gbthymeleaf.entity.enums.Status;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;


    public Cart findCartsById(Long id) {
        return cartDao.findCartsById(id);
    }

    public void deleteById(Long id) {
        try {
            cartDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("No such id in DB: " + e.getMessage());
        }
    }


    public Cart save(Cart cart) {
        if (cart.getId() != null) {
            Optional<Cart> cartOptional = cartDao.findById(cart.getId());
            if (cartOptional.isPresent()) {
                Cart cartFromDB = cartOptional.get();
                cartFromDB.setProducts(cart.getProducts());
                return cartDao.save(cartFromDB);
            }
        }
        return cartDao.save(cart);

    }
}
