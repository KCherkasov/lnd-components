package ru.rougegibbons.landsanddungeons.components.interfaces.core.properties;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.UpdateableComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.mixins.affectors.AffectableComponent;

public interface UpdateablePropertyComponent
        extends PropertyComponent, UpdateableComponent, AffectableComponent {
    @Override
    default @NotNull Boolean isUpdateable() {
        return true;
    }

}
