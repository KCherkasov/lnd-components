package ru.rougegibbons.landsanddungeons.components.interfaces;

import org.jetbrains.annotations.NotNull;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;

/**
 * {@link Component} interface extension for the components that can be (un)packed to/from some {@link ComponentModel}.
 */
public interface PackableComponent extends Component {
    /**
     * packs instance data into JSON model, suitable for sending as packet or write into a file.
     * @return packed instance data.
     */
    @NotNull ComponentModel pack();
}
