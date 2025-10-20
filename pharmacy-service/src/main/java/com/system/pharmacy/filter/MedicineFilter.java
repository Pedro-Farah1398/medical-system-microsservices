package com.system.pharmacy.filter;

import com.system.pharmacy.entity.medicine.Administration;
import com.system.pharmacy.entity.medicine.Medicine;
import com.system.pharmacy.entity.medicine.Type;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineFilter {

    private FilterField id;
    private FilterField name;
    private FilterField type;
    private FilterField administration;
    private FilterField amount;

    public List<Predicate> toPredicates(Root<Medicine> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            Predicate p = id.generatePredicate(builder, root.get("id"), String.class);
            if (p != null) {
                predicates.add(p);
            }
        }

        if (name != null) {
            Predicate p = name.generatePredicate(builder, root.get("name"), String.class);
            if (p != null) {
                predicates.add(p);
            }
        }

        if (type != null) {
            Predicate p = type.generatePredicate(builder, root.get("type"), Type.class);
            if (p != null) {
                predicates.add(p);
            }
        }

        if (administration != null) {
            Predicate p = administration.generatePredicate(builder, root.get("administration"), Administration.class);
            if (p != null) {
                predicates.add(p);
            }
        }

        if (amount != null) {
            Predicate p = amount.generatePredicate(builder, root.get("amount"), Integer.class);
            if (p != null) {
                predicates.add(p);
            }
        }

        return predicates;
    }

}
