//package com.merga.linkSave.repositories;
//
//import com.merga.linkSave.models.Link;
//import com.merga.linkSave.models.User;
//import com.merga.linkSave.models.UserPage;
//import com.merga.linkSave.models.UserSearchCriteria;
//import org.springframework.data.domain.*;
//import org.springframework.stereotype.Repository;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Repository
//public class UserCriteriaRepository {
//
//    private final EntityManager entityManager;
//    private final CriteriaBuilder criteriaBuilder;
//
//    public UserCriteriaRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//        this.criteriaBuilder = entityManager.getCriteriaBuilder();
//    }
//
//    public Page<Link> findAllWithFilters(UserPage userPage, UserSearchCriteria userSearchCriteria) {
//
//        CriteriaQuery<Link> criteriaQuery = criteriaBuilder.createQuery(Link.class);
//        Root<Link> userRoot = criteriaQuery.from(Link.class);
//       // Predicate predicate = getPredicate(userSearchCriteria, userRoot);
//       // criteriaQuery.where(predicate);
//        setOrder(userPage, criteriaQuery, userRoot);
//
//        TypedQuery<Link> typeQuery = entityManager.createQuery(criteriaQuery);
//        typeQuery.setFirstResult(userPage.getPageNumber() * userPage.getPageSize());
//        typeQuery.setMaxResults(userPage.getPageSize());
//
//        Pageable pageable = getPageable(userPage);
//
//       // Long userCount = getUserCount(predicate);
//        return new PageImpl<>(typeQuery.getResultList(), pageable, 2l);
//    }
//
//    private Long getUserCount(Predicate predicate) {
//        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//        Root<Link> countRoot = countQuery.from(Link.class);
//        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
//        return entityManager.createQuery(countQuery).getSingleResult();
//    }
//
//    private Pageable getPageable(UserPage userPage) {
//        Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy());
//        return PageRequest.of(userPage.getPageNumber(), userPage.getPageSize(), sort);
//    }
//
////    private Predicate getPredicate(UserSearchCriteria userSearchCriteria, Root<Link> userRoot) {
////        List<Predicate> predicates = new ArrayList<>();
////        if (Objects.nonNull("A")) {
////            predicates.add(
////                    //      criteriaBuilder.like(userRoot.get("username"), "%" + "A" + "%")
////                    criteriaBuilder.asc(userRoot.get("username"),)
////            );
////        }
////
//////        if (Objects.nonNull(userSearchCriteria.getPhone())) {
//////            predicates.add(
//////                    criteriaBuilder.like(userRoot.get("phone"), "%" + userSearchCriteria.getPhone() + "%")
//////            );
//////        }
////        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
////    }
//
//    private void setOrder(UserPage userPage, CriteriaQuery<Link> criteriaQuery, Root<Link> userRoot) {
//        if (userPage.getSortDirection().equals(Sort.Direction.ASC)) {
//            criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get(userPage.getSortBy())));
//        } else {
//            criteriaQuery.orderBy(criteriaBuilder.desc(userRoot.get(userPage.getSortBy())));
//        }
//    }
//}
