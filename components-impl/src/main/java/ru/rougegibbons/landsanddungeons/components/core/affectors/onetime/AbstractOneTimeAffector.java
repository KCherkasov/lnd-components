package ru.rougegibbons.landsanddungeons.components.core.affectors.onetime;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.affectors.generic.AbstractAffectableAffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.Component;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.onetime.OneTimeAffectorComponent;

import java.util.List;
import java.util.Set;

/**
 * {@link AbstractAffectableAffectorComponent} class extension
 * implementing {@link OneTimeAffectorComponent} interface.
 * Represents abstract affectors with one-time direct action on their targets.
 *
 * @see AbstractAffectableAffectorComponent
 * @see OneTimeAffectorComponent
 * @since 0.4.6
 */
public abstract class AbstractOneTimeAffector
        extends AbstractAffectableAffectorComponent
        implements OneTimeAffectorComponent {
    private Boolean isApplied;

    /**
     * default constructor for creating new component.
     *
     * @param affection   - component affection value.
     * @param targetLabel - target component label.
     * @param labels      - component labels.
     */
    public AbstractOneTimeAffector(@NotNull Integer affection,
                                   @NotNull Long targetLabel,
                                   @NotNull Set<Long> labels) {
        super(affection, targetLabel, labels);
        isApplied = false;
    }

    /**
     * constructor for component deserialization from some data model.
     *
     * @param id          - instance id.
     * @param affection   - component affection value.
     * @param targetLabel - target component label.
     * @param labels      - component labels.
     * @param affectors   - attached affectors.
     * @param isApplied   - is applied flag.
     */
    public AbstractOneTimeAffector(@NotNull Long id,
                                   @NotNull Integer affection,
                                   @NotNull Long targetLabel,
                                   @NotNull Set<Long> labels,
                                   @NotNull List<AffectorComponent> affectors,
                                   @NotNull Boolean isApplied) {
        super(id, affection, targetLabel, labels, affectors);
        this.isApplied = isApplied;
    }

    /**
     * checks if the affector is applied already.
     *
     * @return true if affector is already applied to its target, false otherwise.
     */
    @Override
    public @NotNull Boolean isApplied() {
        return isApplied;
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AppliableAffectorComponent}.
     *
     * @param target - component to apply affection to.
     */
    @Override
    public void apply(@NotNull Component target) {
        interact(target);
        setIsApplied();
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.UpdateableComponent}.
     */
    @Override
    public void update() {
        if (isApplied) {
            markToRemove();
        } else {
            super.update();
            updateRoutine();
        }
    }

    /**
     * abstract method. Implementations shall contain logic of applying the affection to the target component.
     *
     * @param target - target component to apply affection to.
     */
    protected abstract void interact(@NotNull Component target);

    /**
     * abstract part of the update() method, to be implemented in derived classes.
     */
    protected abstract void updateRoutine();

    /**
     * sets is applied flag as true.
     */
    protected void setIsApplied() {
        isApplied = true;
    }

    /**
     * sets is applied flag as false.
     */
    protected void resetIsApplied() {
        isApplied = false;
    }
}
