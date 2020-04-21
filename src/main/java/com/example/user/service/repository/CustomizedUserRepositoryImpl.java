package com.example.user.service.repository;

import com.example.user.service.dto.FullNameFilter;
import com.example.user.service.model.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findByFullName(FullNameFilter filter, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> user = cq.from(User.class);
        Predicate currentPredicate = cb.equal(user.get("deleted"), filter.isDeleted());
        String firstname = filter.getFirstname();
        if (Strings.isNotBlank(firstname)) {
            Predicate firstNamePredicate = cb.equal(user.get("firstname"), firstname);
            currentPredicate = cb.and(currentPredicate, firstNamePredicate);
        }
        String middlename = filter.getMiddlename();
        if (Strings.isNotBlank(middlename)) {
            Predicate middleNamePredicate = cb.equal(user.get("middlename"), middlename);
            currentPredicate = cb.and(currentPredicate, middleNamePredicate);
        }
        String lastname = filter.getLastname();
        if (Strings.isNotBlank(lastname)) {
            Predicate lastNamePredicate = cb.equal(user.get("lastname"), lastname);
            currentPredicate = cb.and(currentPredicate, lastNamePredicate);
        }
        cq.where(currentPredicate);

        TypedQuery<User> query = em.createQuery(cq);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }
}
