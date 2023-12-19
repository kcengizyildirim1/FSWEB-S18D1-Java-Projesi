package com.workintech.jpa.fswebs18d1.repository;

import com.workintech.jpa.fswebs18d1.entity.BreadType;
import com.workintech.jpa.fswebs18d1.entity.Burger;
import com.workintech.jpa.fswebs18d1.exceptions.BurgerErrorException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BurgerDaoImp implements BurgerDao {

    private EntityManager entityManager;

    @Autowired
    public BurgerDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
         entityManager.persist(burger);
         return burger;
    }

    @Override
    public List<Burger> getAllBurger() {
       TypedQuery<Burger> query = entityManager.createQuery("SELECT e FROM Burger e", Burger.class);
       return query.getResultList();
    }

    @Override
    public Burger findById(Integer id) {
        Burger burger = entityManager.find(Burger.class, id);
        if(burger == null){
            throw new BurgerErrorException("Burger with given id is not exist:"+ id, HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    @Transactional
    @Override
    public Burger removeBurger(Integer id) {
        Burger foundBurger = findById(id);
        entityManager.remove(foundBurger);
        return foundBurger;
    }

    @Transactional
    @Override
    public Burger updateBurger(Burger burger) {
        return entityManager.merge(burger);
    }

    @Override
    public List<Burger> findByPrice(double price) {
        TypedQuery<Burger> query =
                entityManager.createQuery("SELECT e FROM Burger e WHERE e.price <= :price ORDER BY e.price desc",
                        Burger.class);
        query.setParameter("price", price);
        return query.getResultList();
    }



    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query =
                entityManager.createQuery("SELECT e FROM Burger e WHERE e.breadType = :breadType " +
                        "ORDER BY e" +
                        ".breadType",
                Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> query = entityManager.createQuery(
                "SELECT e FROM Burger e WHERE e.contents ilike %:content% ORDER BY e.name",
                Burger.class
        );
        query.setParameter("contents", content);
        return query.getResultList();
    }
}
