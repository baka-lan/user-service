package com.example.user.service.specification;

import com.example.user.service.dto.FullNameFilter;
import com.example.user.service.model.User;
import com.example.user.service.model.User_;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class UserSpecification {
    public static Specification<User> userByFullName(final FullNameFilter filter) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate currentPredicate = criteriaBuilder.equal(root.get(User_.deleted), filter.isDeleted());
            String firstname = filter.getFirstname();
            if (Strings.isNotBlank(firstname)) {
                Predicate firstNamePredicate = criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get(User_.firstname)), firstname.toLowerCase());
                currentPredicate = criteriaBuilder.and(currentPredicate, firstNamePredicate);
            }
            String middlename = filter.getMiddlename();
            if (Strings.isNotBlank(middlename)) {
                Predicate middleNamePredicate = criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get(User_.middlename)), middlename.toLowerCase());
                currentPredicate = criteriaBuilder.and(currentPredicate, middleNamePredicate);
            }
            String lastname = filter.getLastname();
            if (Strings.isNotBlank(lastname)) {
                Predicate lastNamePredicate = criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get(User_.lastname)), lastname.toLowerCase());
                currentPredicate = criteriaBuilder.and(currentPredicate, lastNamePredicate);
            }
            return currentPredicate;
        };
    }
}
