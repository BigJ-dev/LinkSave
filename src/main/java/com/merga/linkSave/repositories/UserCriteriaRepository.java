package com.merga.linkSave.repositories;

import com.merga.linkSave.models.User;
import com.merga.linkSave.models.UserPage;
import com.merga.linkSave.models.UserSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public UserCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<User> findAllWithFilters(UserPage userPage, UserSearchCriteria userSearchCriteria) {

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = getPredicate(userSearchCriteria, userRoot);
        criteriaQuery.where(predicate);
        setOrder(userPage, criteriaQuery, userRoot);

        TypedQuery<User> typeQuery = entityManager.createQuery(criteriaQuery);
        typeQuery.setFirstResult(userPage.getPageNumber() * userPage.getPageSize());
        typeQuery.setMaxResults(userPage.getPageSize());

        Pageable pageable = getPageable(userPage);

        Long userCount = getUserCount(predicate);
        return new PageImpl<>(typeQuery.getResultList(), pageable, userCount);
    }

    private Long getUserCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> countRoot = countQuery.from(User.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(UserPage userPage) {
        Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy());
        return PageRequest.of(userPage.getPageNumber(), userPage.getPageSize(), sort);
    }

    private Predicate getPredicate(UserSearchCriteria userSearchCriteria, Root<User> userRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(userSearchCriteria.getUsername())) {
            predicates.add(
                    criteriaBuilder.like(userRoot.get("username"), "%" + userSearchCriteria.getUsername() + "%")
            );
        }

        if (Objects.nonNull(userSearchCriteria.getPhone())) {
            predicates.add(
                    criteriaBuilder.like(userRoot.get("phone"), "%" + userSearchCriteria.getPhone() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(UserPage userPage, CriteriaQuery<User> criteriaQuery, Root<User> userRoot) {
        if (userPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get(userPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(userRoot.get(userPage.getSortBy())));
        }
    }
}
