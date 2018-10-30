package ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;

import java.util.List;

/**
 * interface mixin for components that
 * can be affected by {@link AffectorComponent} implementations.
 *
 * @see AffectorComponent
 * @since 0.4.6
 */
public interface AffectableComponent {
    /**
     * get list of affectors applied to that property.
     *
     * @return list of affectors applied to that property.
     */
    @NotNull List<AffectorComponent> getAffectors();

    /**
     * adds given affector to the affectors list.
     *
     * @param affector - affector to add.
     */
    void attachAffector(@NotNull AffectorComponent affector);

    /**
     * removes given affectors from the affectors list.
     *
     * @param affector - affector to remove.
     */
    void detachAffector(@NotNull AffectorComponent affector);

    /**
     * removes all affectors from the affectors list.
     */
    void detachAll();
}
