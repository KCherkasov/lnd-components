package ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors;

import org.jetbrains.annotations.NotNull;

/**
 * interface mixin for components which actual
 * affection value depends on some parameter.
 *
 * @since 0.4.6
 */
public interface ParameterBasedAffectorComponent {
    /**
     * get parameter-based affection value.
     *
     * @param parameter - parameter value.
     * @return actual affection value.
     */
    @NotNull Integer getAffection(@NotNull Integer parameter);
}
