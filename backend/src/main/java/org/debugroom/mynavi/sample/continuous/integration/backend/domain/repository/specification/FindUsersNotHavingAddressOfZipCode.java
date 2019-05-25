package org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Address;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Address_;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.User_;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FindUsersNotHavingAddressOfZipCode implements Specification<User> {

    private String zipCd;

    @Override
    public Predicate toPredicate(Root<User> root,
                                 CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Path<Object> path = root.get("userId");
        Subquery<User> subQuery = criteriaBuilder.createQuery().subquery(User.class);
        Root<User> subQueryRoot = subQuery.from(User.class);
        Join<User, Address> subQueryJoinAddress = subQueryRoot.join(User_.addressByUserId);
        Predicate subQueryPredicate = criteriaBuilder.equal(
                subQueryJoinAddress.get(Address_.zipCode), zipCd);
        subQuery.select(subQueryRoot.get("userId"));
        subQuery.where(subQueryPredicate);

        return criteriaBuilder.not(criteriaBuilder.in(path).value(subQuery));

    }
}
