package ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.Component;

/**
 * interface mixin for affectors that can change or modify affected components' state.
 *
 * @since 0.4.6
 */
public interface AppliableAffectorComponent {
    /**
     * checks if affector has been already applied.
     *
     * @return true if affector has been applied already, false otherwise.
     */
    @NotNull Boolean isApplied();

    /**
     * apply instance's affection to the target {@link Component}.
     *
     * @param target - component to apply affection to.
     */
    void apply(@NotNull Component target);
}
