package ru.rougegibbons.landsanddungeons.components.core.affectors.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.Component;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AppliableAffectorComponent;

public abstract class AbstractAppliableAffectorComponent
        extends AbstractAffectorComponent implements AppliableAffectorComponent {
    private Boolean isApplied;

    public AbstractAppliableAffectorComponent(@NotNull Integer affection,
                                              @NotNull Long targetLabel) {
        super(affection, targetLabel);
        isApplied = false;
    }

    public AbstractAppliableAffectorComponent(@NotNull Long id,
                                              @NotNull Integer affection,
                                              @NotNull Long targetLabel,
                                              @NotNull Boolean isApplied) {
        super(id, affection, targetLabel);
        this.isApplied = isApplied;
    }

    @Override
    public @NotNull Boolean isApplied() {
        return isApplied;
    }

    protected abstract @NotNull Integer interact(@NotNull Component target);
}
