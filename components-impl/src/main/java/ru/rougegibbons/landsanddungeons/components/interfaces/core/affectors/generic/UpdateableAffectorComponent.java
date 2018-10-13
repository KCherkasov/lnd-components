package ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.UpdateableComponent;

/**
 * extension of {@link AffectorComponent} interface
 * for affectors that have update() method.
 *
 * @see AffectorComponent
 * @see UpdateableComponent
 * @since 0.4.6
 */
public interface UpdateableAffectorComponent
        extends AffectorComponent, UpdateableComponent {
    /**
     * see {@link AffectorComponent}.
     *
     * @return always true.
     */
    @Override
    default @NotNull Boolean isUpdateable() {
        return true;
    }
}
