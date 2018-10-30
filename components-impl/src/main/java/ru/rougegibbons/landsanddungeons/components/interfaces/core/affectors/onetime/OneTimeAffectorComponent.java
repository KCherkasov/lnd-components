package ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.onetime;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectableAffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AppliableAffectorComponent;

/**
 * {@link AffectorComponent} interface extension for affections
 * that shall be implemented only once.
 *
 * @see AffectorComponent
 * @since 0.4.6
 */
public interface OneTimeAffectorComponent extends
        AffectableAffectorComponent, AppliableAffectorComponent {
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
