package ru.rougegibbons.landsanddungeons.components.core.affectors.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.Component;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AppliableAffectorComponent;

import java.util.Set;

/**
 * {@link AbstractAffectorComponent} extension implementing
 * {@link AppliableAffectorComponent} interface mixin.
 *
 * @see AbstractAffectorComponent
 * @see AppliableAffectorComponent
 * @since 0.4.6
 */
public abstract class AbstractAppliableAffectorComponent
        extends AbstractAffectorComponent implements AppliableAffectorComponent {
    private Boolean isApplied;

    /**
     * default constructor for creating new components.
     *
     * @param affection   - affection value.
     * @param targetLabel - target component label.
     * @param labels      - {@link Set} of labels describing the component.
     */
    public AbstractAppliableAffectorComponent(@NotNull Integer affection,
                                              @NotNull Long targetLabel,
                                              @NotNull Set<Long> labels) {
        super(affection, targetLabel, labels);
        isApplied = false;
    }

    /**
     * constructor for deserializing components from some data model.
     *
     * @param id - instance id.
     * @param affection - affection value.
     * @param targetLabel - target component label.
     * @param labels - {@link Set} of labels describing the component.
     * @param isApplied - was component applied flag.
     */
    public AbstractAppliableAffectorComponent(@NotNull Long id,
                                              @NotNull Integer affection,
                                              @NotNull Long targetLabel,
                                              @NotNull Set<Long> labels,
                                              @NotNull Boolean isApplied) {
        super(id, affection, targetLabel, labels);
        this.isApplied = isApplied;
    }

    /**
     * see {@link AppliableAffectorComponent} description.
     *
     * @return true if the component was applied, false otherwise.
     */
    @Override
    public @NotNull Boolean isApplied() {
        return isApplied;
    }

    /**
     * see {@link AppliableAffectorComponent} description.
     *
     * @param target - component to apply affection to.
     */
    @Override
    public void apply(@NotNull Component target) {
        if (!isApplied) {
            interact(target);
            setIsApplied();
        }
    }

    /**
     * abstract method containing the interaction routine.
     *
     * @param target - component to apply affection to.
     */
    protected abstract void interact(@NotNull Component target);

    /**
     * sets component's flag in true.
     */
    protected void setIsApplied() {
        isApplied = true;
    }

    /**
     * sets component's flag in false.
     */
    protected void resetIsApplied() {
        isApplied = false;
    }
}
