package az.ingress.announcementapp.specification;

import jakarta.persistence.criteria.*;

public enum SearchOperation {
    EQUAL {
        @Override
        public Predicate buildPredicate(Path<?> path, SearchCriteria criteria, CriteriaBuilder builder) {
            if (criteria.getKey().contains(".")) {
                String[] keys = criteria.getKey().split("\\.");
                Path<?> nestedPath = path;
                for (String key : keys) {
                    nestedPath = nestedPath.get(key);
                }
                return builder.equal(nestedPath, criteria.getValue());
            } else {
                return builder.equal(path.get(criteria.getKey()), criteria.getValue());
            }
        }
    },
    GREATER_THAN {
        @Override
        public Predicate buildPredicate(Path<?> path, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.greaterThan(path.get(criteria.getKey()), (Comparable) criteria.getValue());
        }
    },
    LESS_THAN {
        @Override
        public Predicate buildPredicate(Path<?> path, SearchCriteria criteria, CriteriaBuilder builder) {
            return builder.lessThan(path.get(criteria.getKey()), (Comparable) criteria.getValue());
        }
    };

    public abstract Predicate buildPredicate(Path<?> root, SearchCriteria criteria, CriteriaBuilder builder);

}


