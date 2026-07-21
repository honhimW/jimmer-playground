package org.babyfish.jimmer.sql.ast.impl.mutation;

import org.babyfish.jimmer.meta.ImmutableProp;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.MappedId;
import org.babyfish.jimmer.sql.runtime.JSqlClientImplementor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/// @author honhimW
/// @since 2026-07-21

public class IShape {

    static Shape fullOf(JSqlClientImplementor sqlClient, ImmutableType type) {
        return Shape.of(
            sqlClient,
            type,
            null,
            null
        );
    }

    public static Predicate<ImmutableProp> withoutMappedIdProps(
        ImmutableType type,
        Predicate<ImmutableProp> propFilter
    ) {
        List<MappedId> mappedIds = type.getMappedIds();
        if (mappedIds.isEmpty()) {
            return propFilter;
        }
        Set<ImmutableProp> mappedIdProps = new HashSet<>(mappedIds.size());
        for (MappedId mappedId : mappedIds) {
            mappedIdProps.add(mappedId.getProp());
        }
        if (propFilter == null) {
            return prop -> !mappedIdProps.contains(prop);
        }
        return prop -> !mappedIdProps.contains(prop) && propFilter.test(prop);
    }

}
