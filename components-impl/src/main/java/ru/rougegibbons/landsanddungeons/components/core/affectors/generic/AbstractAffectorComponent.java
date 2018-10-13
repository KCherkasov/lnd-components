package ru.rougegibbons.landsanddungeons.components.core.affectors.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.AbstractComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;

public abstract class AbstractAffectorComponent extends AbstractComponent
        implements AffectorComponent {
    private final Integer affection;
    private final Long targetLabel;

    public AbstractAffectorComponent(@NotNull Integer affection,
                                     @NotNull Long targetLabel) {
        super();
        this.affection = affection;
        this.targetLabel = targetLabel;
    }

    public AbstractAffectorComponent(@NotNull Long id,
                                     @NotNull Integer affection,
                                     @NotNull Long targetLabel) {
        super(id);
        this.affection = affection;
        this.targetLabel = targetLabel;
    }

    @Override
    public @NotNull Integer getAffection() {
        return affection;
    }

    @Override
    public @NotNull Long getTargetLabel() {
        return targetLabel;
    }
}
