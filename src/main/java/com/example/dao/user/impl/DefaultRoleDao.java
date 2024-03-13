package com.example.dao.user.impl;

import com.example.dao.user.RoleDao;
import com.example.model.user.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class DefaultRoleDao implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getByName(String roleName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> roleCriteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = roleCriteriaQuery.from(Role.class);

        roleCriteriaQuery.where(criteriaBuilder.equal(roleRoot.get("name"), roleName));

        return entityManager.createQuery(roleCriteriaQuery).getSingleResult();
    }
}
