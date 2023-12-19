package com.workintech.jpa.fswebs18d1.controller;




import com.workintech.jpa.fswebs18d1.entity.BreadType;
import com.workintech.jpa.fswebs18d1.entity.Burger;
import com.workintech.jpa.fswebs18d1.repository.BurgerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "${my.allowed.origins}")
@RestController
@RequestMapping("/workintech/burgers")
public class BurgerController {
    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping
    public Burger createBurger(@RequestBody Burger burger){
        return burgerDao.save(burger);
    }

    @GetMapping("/")
    public List<Burger> getAllBurger(){
        return burgerDao.getAllBurger();
    }
    @GetMapping
    public Burger getByIdBurger(@RequestParam Integer id){
        return burgerDao.findById(id);
    }
    @PutMapping("/")
    public Burger updateBurger(@RequestBody Burger newBurger){
         return burgerDao.updateBurger(newBurger);
    }

    @DeleteMapping("/{id}")
    public Burger deleteBurger(@PathVariable Integer id){
        return burgerDao.removeBurger(id);
    }


    @GetMapping("/price/{price}")
    public List<Burger> getByPrice(@PathVariable double price) {
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/breadtype/{breadType}")
    public List<Burger> getByBreadType(@PathVariable BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }

}
