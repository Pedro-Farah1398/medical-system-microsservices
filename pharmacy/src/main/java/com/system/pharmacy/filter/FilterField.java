package com.system.pharmacy.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterField {
    private String operator;
    private String value;

    public Predicate generatePredicate(CriteriaBuilder builder, Path<?> field, Class<?> fieldType) {

        if (fieldType.equals(String.class)) {
            return handleTextOperator(builder, (Path<String>) field);
        }

        if (fieldType.equals(Integer.class)) {
            return handleNumberOperator(builder, (Path<Integer>) field);
        }

        if (fieldType.equals(LocalDate.class)) {
            return handleDateOperator(builder, (Path<LocalDate>) field);
        }

        if (Enum.class.isAssignableFrom(fieldType)) {
            return handleEnumOperator(builder, (Path<Enum>) field, (Class<? extends Enum>) fieldType);
        }

        throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
    }

    private Predicate handleTextOperator(CriteriaBuilder builder, Path<String> field) {
        switch (operator) {
            case "contains" -> {
                return builder.like(field, "%" + value + "%");
            }
            case "startsWith" -> {
                return builder.like(field, value + "%");
            }
            case "endsWith" -> {
                return builder.like(field, "%" + value);
            }
            case "eq" -> {
                return builder.equal(field, value);
            }
            default -> {
                throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
    }

    private Predicate handleNumberOperator(CriteriaBuilder builder, Path<Integer> field) {
        switch (operator) {
            case "lt" -> {
                return builder.lt(field, Integer.parseInt(value));
            }
            case "le" -> {
                return builder.le(field, Integer.parseInt(value));
            }
            case "gt" -> {
                return builder.gt(field, Integer.parseInt(value));
            }
            case "ge" -> {
                return builder.ge(field, Integer.parseInt(value));
            }
            case "eq" -> {
                return builder.equal(field, Integer.parseInt(value));
            }
            default -> {
                throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
    }

    private Predicate handleEnumOperator(CriteriaBuilder builder, Path<Enum> field, Class<? extends Enum> enumType) {
        Enum<?> enumValue = Enum.valueOf(enumType, value.toUpperCase());
        return switch (operator) {
            case "eq" -> builder.equal(field, enumValue);
            case "ne" -> builder.notEqual(field, enumValue);
            default -> throw new IllegalArgumentException("Invalid enum operator: " + operator);
        };
    }

    private Predicate handleDateOperator(CriteriaBuilder builder, Path<LocalDate> field) {
        LocalDate dateValue = LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
        return switch (operator) {
            case "lt" -> builder.lessThan(field, dateValue);
            case "le" -> builder.lessThanOrEqualTo(field, dateValue);
            case "gt" -> builder.greaterThan(field, dateValue);
            case "ge" -> builder.greaterThanOrEqualTo(field, dateValue);
            case "eq" -> builder.equal(field, dateValue);
            default -> throw new IllegalArgumentException("Invalid date operator: " + operator);
        };
    }


}
