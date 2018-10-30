package ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.overtime;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectableAffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AppliableAffectorComponent;

/**
 * {@link AffectorComponent} interface extension for affections that act over some time.
 *
 * @see AffectorComponent
 * @since 0.4.6
 */
public interface OverTimeAffectorComponent extends
        AffectableAffectorComponent, AppliableAffectorComponent {
    /**
     * get affection full duration.
     *
     * @return component duration.
     */
    @NotNull Integer getDuration();

    /**
     * get affection remaining time.
     *
     * @return component remaining time.
     */
    @NotNull Integer getRemaining();

    /**
     * check if affector is active.
     *
     * @return true if remaining time is greater than 0, false otherwise.
     */
    @NotNull Boolean isActive();

    /**
     * see {@link AffectorComponent} description.
     *
     * @return always true.
     */
    @Override
    default @NotNull Boolean isAppliable() {
        return true;
    }
}
