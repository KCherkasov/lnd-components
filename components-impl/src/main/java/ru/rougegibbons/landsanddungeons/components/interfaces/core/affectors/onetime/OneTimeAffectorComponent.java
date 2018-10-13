package ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.onetime;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectableAffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;

/**
 * {@link AffectorComponent} interface extension for affections
 * that shall be implemented only once.
 *
 * @see AffectorComponent
 * @since 0.4.6
 */
public interface OneTimeAffectorComponent extends AffectableAffectorComponent {
    /**
     * checks if affection was applied or not.
     *
     * @return true if affector was already handled, false otherwise and by default.
     */
    @NotNull Boolean isApplied();
}
