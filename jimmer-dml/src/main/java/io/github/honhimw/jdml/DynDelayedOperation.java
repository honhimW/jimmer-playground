package io.github.honhimw.jdml;

import org.babyfish.jimmer.meta.ImmutableProp;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.RootTableResolver;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.impl.table.WeakJoinHandle;
import org.babyfish.jimmer.sql.ast.table.Table;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

/// @author honhimW
/// @since 2025-10-31
public class DynDelayedOperation implements AbstractTypedTable.DelayedOperation<Object> {

    private final AbstractTypedTable<?> parent;
    private final ImmutableProp prop;
    private final JoinType joinType;

    public DynDelayedOperation(AbstractTypedTable<?> parent, ImmutableProp prop, JoinType joinType) {
        this.parent = parent;
        this.prop = prop;
        this.joinType = joinType;
    }

    public DynDelayedOperation(DynDelayedOperation base, BaseTableOwner baseTableOwner) {
        this.parent = (AbstractTypedTable<?>) base.parent.__baseTableOwner(baseTableOwner);
        this.prop = base.prop;
        this.joinType = base.joinType;
    }

    @Override
    public Table<?> parent() {
        return parent;
    }

    @Override
    public ImmutableProp prop() {
        return prop;
    }

    @Override
    public WeakJoinHandle weakJoinHandle() {
        return null;
    }

    @Override
    public ImmutableType targetType() {
        if (prop != null) {
            return prop.getTargetType();
        }
        return null;
    }

    @Override
    public TableImplementor<Object> resolve(RootTableResolver ctx) {
        TableImplementor<Object> tableImplementor;
        if (prop != null) {
            tableImplementor = parent.__resolve(ctx).joinImplementor(prop.getName(), joinType, -1);
            return tableImplementor.baseTableOwner(parent.__baseTableOwner());
        }
        return null;
    }

    @Override
    public AbstractTypedTable.DelayedOperation<Object> baseTableOwner(BaseTableOwner baseTableOwner) {
        return new DynDelayedOperation(
            this,
            baseTableOwner
        );
    }

    @Override
    public String toString() {
        return "AnyDelayedOperation{" +
               "parent=" + parent +
               ", prop=" + prop +
               ", joinType=" + joinType +
               '}';
    }
}
