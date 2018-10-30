package ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.onetime;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.ParameterBasedAffectorComponent;

/**
 * {@link OneTimeAffectorComponent} interface extension
 * with {@link ParameterBasedAffectorComponent} mixin.
 *
 * @see OneTimeAffectorComponent
 * @see ParameterBasedAffectorComponent
 * @since 0.4.6
 */
public interface ParametrisedOneTimeAffector extends
        OneTimeAffectorComponent, ParameterBasedAffectorComponent {
    /**
     * see {@link ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic.AffectorComponent}.
     *
     * @return true.
     */
    @Override
    default @NotNull Boolean isParameterBased() {
        return true;
    }
}
