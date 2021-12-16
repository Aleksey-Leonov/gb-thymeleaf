package ru.gb.gbthymeleaf.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.enums.Status;

import java.util.List;
import java.util.Optional;

public interface CartDao extends JpaRepository<Cart, Long> {

    Cart findCartsById(Long id);

}
