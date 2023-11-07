package ee.spacexy.whatwhere.service.service.utils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class QueryUtils {

    public static <V extends Comparable<?>> void isIn(List<V> value, Supplier<SimpleExpression<V>> column, BooleanBuilder where) {
        if (value == null || value.isEmpty()) {
            return;
        }

        if (value.size() == 1) {
            where.and(column.get().eq(value.get(0)));
        } else {
            where.and(column.get().in(value));
        }
    }

    public static void isLike(String value, Supplier<StringExpression> column, BooleanBuilder where) {
        if (value == null || value.isEmpty()) {
            return;
        }

        where.and(column.get().likeIgnoreCase("%" + value + "%"));
    }

    public static <V extends Comparable<?>> void isIn(List<V> value, SimpleExpression<V> column, BooleanBuilder where) {
        isIn(value, () -> column, where);
    }

    public static void isLike(String value, StringExpression column, BooleanBuilder where) {
        isLike(value, () -> column, where);
    }

    public static <V> void is(V value, Function<V, Predicate> function, BooleanBuilder where) {
        if (value != null) {
            where.and(function.apply(value));
        }
    }

}
