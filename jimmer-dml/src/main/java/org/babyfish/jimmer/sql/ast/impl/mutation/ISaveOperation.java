package org.babyfish.jimmer.sql.ast.impl.mutation;

import org.babyfish.jimmer.runtime.DraftSpi;

import java.util.List;

/// @author honhimW
/// @since 2026-07-21

class ISaveOperation {

    final ISaver saver;

    final List<DraftSpi> drafts;

    final PreHandler preHandler;

    final boolean ownerAcceptanceRequired;

    ISaveOperation(
        ISaver saver,
        List<DraftSpi> drafts,
        PreHandler preHandler,
        boolean ownerAcceptanceRequired
    ) {
        this.saver = saver;
        this.drafts = drafts;
        this.preHandler = preHandler;
        this.ownerAcceptanceRequired = ownerAcceptanceRequired;
    }
}
