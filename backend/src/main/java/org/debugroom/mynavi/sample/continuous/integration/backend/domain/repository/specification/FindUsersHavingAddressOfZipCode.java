package org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
public class FindUsersHavingAddressOfZipCode implements Specification<User> {

    private String zipCd;

    @Override
    public Predicate toPredicate(Root<User> root,
                                 CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<User, Address> joinAddress = root.join(User_.addressByUserId);
        predicates.add(criteriaBuilder.equal(joinAddress.get(Address_.zipCode), zipCd));

        return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));

    }

}
