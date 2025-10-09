package com.system.pharmacy.filter;

import com.system.pharmacy.entity.medicine.Medicine;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class MedicineSpecification {

    // Specification receives root (root entity of the query, in this case, Medicine), query (query being built),
    // builder (which is the builder of the conditions for SQL).

    //
    public static Specification<Medicine> withFilter(MedicineFilter filterField){
        return(Root<Medicine> root,CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            var predicates = filterField.toPredicates(root, builder);
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
