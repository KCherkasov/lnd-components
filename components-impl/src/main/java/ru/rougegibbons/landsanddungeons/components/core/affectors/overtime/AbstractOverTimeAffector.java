package ru.rougegibbons.landsanddungeons.components.core.affectors.overtime;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.core.affectors.generic.AbstractAffectableAffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.Component;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.overtime.OverTimeAffectorComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.MathUtils;

import java.util.List;
import java.util.Set;

/**
 * {@link AbstractAffectableAffectorComponent} class extension,
 * abstract implementation of {@link OverTimeAffectorComponent} interface.
 * Represents various effects that are active during some time.
 *
 * @see AbstractAffectableAffectorComponent
 * @see OverTimeAffectorComponent
 * @since 0.4.6
 */
public abstract class AbstractOverTimeAffector
        extends AbstractAffectableAffectorComponent
        implements OverTimeAffectorComponent {
    private Boolean isApplied;
    private final Integer duration;
    private Integer remaining;

    /**
     * default constructor for creating new components.
     *
     * @param affection   - affection value.
     * @param targetLabel - target component label.
     * @param labels      - component labels.
     * @param duration    - affection active duration.
     */
    public AbstractOverTimeAffector(@NotNull Integer affection,
                                    @NotNull Long targetLabel,
                                    @NotNull Set<Long> labels,
                                    @NotNull Integer duration) {
        super(affection, targetLabel, labels);
        isApplied = false;
        this.duration = duration;
        remaining = this.duration;
    }

    /**
     * constructor for component deserialization from some data model.
     *
     * @param id          - instance id.
     * @param affection   - component affection value.
     * @param targetLabel - target component label.
     * @param labels      - component labels.
     * @param affectors   - attached affectors.
     * @param isApplied   - was component applied during the current tick flag.
     * @param duration    - affection full duration.
     * @param remaining   - affection remaining time.
     */
    public AbstractOverTimeAffector(@NotNull Long id,
                                    @NotNull Integer affection,
                                    @NotNull Long targetLabel,
                                    @NotNull Set<Long> labels,
                                    @NotNull List<AffectorComponent> affectors,
                                    @NotNull Boolean isApplied,
                                    @NotNull Integer duration,
                                    @NotNull Integer remaining) {
        super(id, affection, targetLabel, labels, affectors);
        this.isApplied = isApplied;
        this.duration = duration;
        this.remaining = MathUtils.clamp(remaining, Constants.ZERO_INT, this.duration);
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AppliableAffectorComponent}.
     *
     * @return true if affection was applied duriing current simulation tick, false otherwise.
     */
    @Override
    public @NotNull Boolean isApplied() {
        return isApplied;
    }

    /**
     * see {@link OverTimeAffectorComponent} description.
     *
     * @return true if remaining time is more than zero, false otherwise.
     */
    @Override
    public @NotNull Boolean isActive() {
        return remaining > Constants.ZERO_INT;
    }

    /**
     * see {@link OverTimeAffectorComponent} description.
     *
     * @return full affector duration.
     */
    @Override
    public @NotNull Integer getDuration() {
        return duration;
    }

    /**
     * see {@link OverTimeAffectorComponent} description.
     *
     * @return remaining affector duration.
     */
    @Override
    public @NotNull Integer getRemaining() {
        return remaining;
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.UpdateableComponent}.
     */
    @Override
    public void update() {
        if (!isToRemove()) {
            if (isActive()) {
                super.update();
                resetIsApplied();
            } else {
                markToRemove();
            }
        }
    }

    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AppliableAffectorComponent}.
     *
     * @param target - component to apply affection to.
     */
    @Override
    public void apply(@NotNull Component target) {
        if (isActive() && !isApplied) {
            interact(target);
            --remaining;
            setIsApplied();
        }
    }

    /**
     * abstract method. Implementations shall contain the description of affector interaction with its target.
     *
     * @param target - component to apply affection to.
     */
    protected abstract void interact(@NotNull Component target);

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
