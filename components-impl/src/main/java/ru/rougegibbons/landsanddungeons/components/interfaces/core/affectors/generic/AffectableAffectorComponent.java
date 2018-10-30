package ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AffectableComponent;

/**
 * extension of {@link AffectorComponent} interface for affectors
 * that can be affected by other affectors.
 *
 * @see AffectorComponent
 * @see AffectableComponent
 * @since 0.4.6
 */
public interface AffectableAffectorComponent extends
        UpdateableAffectorComponent, AffectableComponent {
    /**
     * see {@link AffectorComponent} description.
     *
     * @return true.
     */
    @Override
    default @NotNull Boolean isAffectable() {
        return true;
    }
}
