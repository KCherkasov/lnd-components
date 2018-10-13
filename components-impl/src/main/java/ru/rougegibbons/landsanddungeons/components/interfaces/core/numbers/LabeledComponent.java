package ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers;

import org.jetbrains.annotations.NotNull;

/**
 * interface mixin for components with various kind labels.
 *
 * @since 0.4.6
 */
public interface LabeledComponent {
    /**
     * get digital label.
     *
     * @return component label.
     */
    @NotNull Long getLabel();
}
