package com.workintech.jpa.fswebs18d1.repository;

import com.workintech.jpa.fswebs18d1.entity.BreadType;
import com.workintech.jpa.fswebs18d1.entity.Burger;

import java.util.List;

public interface BurgerDao {
    Burger save(Burger burger);
    List<Burger> getAllBurger();
    Burger removeBurger(Integer id);

    Burger updateBurger(Burger burger);

    Burger findById(Integer id);
    List<Burger> findByPrice(double price);
    List<Burger> findByBreadType(BreadType breadType);

    List<Burger> findByContent(String contents);
}
