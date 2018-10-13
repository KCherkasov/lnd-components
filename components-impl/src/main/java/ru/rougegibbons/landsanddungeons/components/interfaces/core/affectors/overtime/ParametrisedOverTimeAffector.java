package ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.overtime;

import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.ParameterBasedAffectorComponent;

/**
 * {@link OverTimeAffectorComponent} interface extension
 * with {@link ParameterBasedAffectorComponent} mixin.
 *
 * @see OverTimeAffectorComponent
 * @see ParameterBasedAffectorComponent
 * @since 0.4.6
 */
public interface ParametrisedOverTimeAffector extends
        OverTimeAffectorComponent, ParameterBasedAffectorComponent {
}
