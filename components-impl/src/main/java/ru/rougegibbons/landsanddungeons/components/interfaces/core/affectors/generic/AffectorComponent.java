package ru.rougegibbons.landsanddungeons.components.interfaces.core.affectors.generic;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.PackableComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.RemovableComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.UpdateableComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.MultiLabeledComponent;

/**
 * interface for components representing affections on other components
 * (e.g. damage/healing, buffs/debuffs, etc.).
 *
 * @see PackableComponent
 * @see UpdateableComponent
 * @since 0.4.6
 */
public interface AffectorComponent extends PackableComponent,
        RemovableComponent, MultiLabeledComponent {
    /**
     * get affection value.
     *
     * @return affection value.
     */
    @NotNull Integer getAffection();

    /**
     * get target property digital label;
     *
     * @return target property label.
     */
    @NotNull Long getTargetLabel();

    /**
     * checks if affector instance has update() method.
     *
     * @return true if instance has update() method, false otherwise.
     */
    default @NotNull Boolean isUpdateable() {
        return false;
    }
}
