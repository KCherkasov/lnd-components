package ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers;

import org.jetbrains.annotations.NotNull;

/**
 * component containing several digital labels.
 *
 * @since 0.4.6
 */
public interface MultiLabeledComponent {
    @NotNull Boolean hasLabel(@NotNull Long label);

    void addLabel(@NotNull Long label);

    void removeLabel(@NotNull Long label);
}
