package ru.rougegibbons.landsanddungeons.components.core.affectors.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectableAffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AppliableAffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.ParameterBasedAffectorComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * {@link AbstractAffectorComponent} extension implementing
 * {@link AffectableAffectorComponent} interface.
 *
 * @see AbstractAffectorComponent
 * @see AffectableAffectorComponent
 * @since 0.4.6
 */
public abstract class AbstractAffectableAffectorComponent
        extends AbstractAffectorComponent implements AffectableAffectorComponent {
    private final List<AffectorComponent> affectors;

    /**
     * default constructor for creating new components.
     *
     * @param affection   - affection value.
     * @param targetLabel - target property/affector label.
     * @param labels      - affector labels.
     */
    public AbstractAffectableAffectorComponent(@NotNull Integer affection,
                                               @NotNull Long targetLabel,
                                               @NotNull Set<Long> labels) {
        super(affection, targetLabel, labels);
        affectors = new ArrayList<>();
    }

    /**
     * constructor for component deserialization from some data model instance.
     *
     * @param id          - instance id.
     * @param affection   - affection value.
     * @param targetLabel - target component label.
     * @param labels      - component labels.
     * @param affectors   - affectors applied to the component.
     */
    public AbstractAffectableAffectorComponent(@NotNull Long id,
                                               @NotNull Integer affection,
                                               @NotNull Long targetLabel,
                                               @NotNull Set<Long> labels,
                                               @NotNull List<AffectorComponent> affectors) {
        super(id, affection, targetLabel, labels);
        this.affectors = affectors;
    }

    /**
     * see {@link AffectableAffectorComponent} description.
     *
     * @return applied affectors list.
     */
    @Override
    public @NotNull List<AffectorComponent> getAffectors() {
        return affectors;
    }

    /**
     * see {@link AffectableAffectorComponent} description.
     *
     * @param affector - affector to add.
     */
    @Override
    public void attachAffector(@NotNull AffectorComponent affector) {
        if (!affectors.contains(affector)) {
            affectors.add(affector);
        }
    }

    /**
     * see {@link AffectableAffectorComponent} description.
     *
     * @param affector - affector to remove.
     */
    @Override
    public void detachAffector(@NotNull AffectorComponent affector) {
        affectors.remove(affector);
    }

    /**
     * see {@link AffectableAffectorComponent} description.
     */
    @Override
    public void detachAll() {
        affectors.clear();
    }

    /**
     * see {@link AbstractAffectorComponent} description.
     *
     * @return affection modified by attached affectors.
     */
    @Override
    public @NotNull Integer getAffection() {
        Integer totalAffection = 0;
        for (AffectorComponent affector : affectors) {
            totalAffection += getAffectorAffection(affector);
        }
        return getRawAffection() + totalAffection;
    }

    /**
     * see {@link AbstractAffectorComponent} description.
     */
    @Override
    public void update() {
        detachMarked();
        applyAffectors();
    }

    /**
     * detaches all affectors marked for removalv from the component.
     */
    private void detachMarked() {
        for (AffectorComponent affector : affectors) {
            if (affector.isToRemove()) {
                affectors.remove(affector);
            }
        }
    }

    /**
     * applies attached affector (if they were not applied earlier) to current affection value.
     */
    private void applyAffectors() {
        for (AffectorComponent affector : affectors) {
            if (affector.isAppliable()) {
                final AppliableAffectorComponent appliable =
                        (AppliableAffectorComponent) affector;
                if (!affector.isToRemove() && !appliable.isApplied()) {
                    appliable.apply(this);
                }
            }
        }
    }

    /**
     * gets nominal affection value, without attached affectors' influence.
     *
     * @return nominal affection value.
     */
    private @NotNull Integer getRawAffection() {
        return super.getAffection();
    }

    /**
     * gets the attached affector's affection value.
     *
     * @param affector - affector to get affection value from.
     * @return attached affector's affection.
     */
    private @NotNull Integer getAffectorAffection(@NotNull AffectorComponent affector) {
        return affector.isAppliable() ? Constants.ZERO_INT
                : affector.isParameterBased()
                ? ((ParameterBasedAffectorComponent) affector)
                .getAffection(getRawAffection()) : affector.getAffection();
    }
}
