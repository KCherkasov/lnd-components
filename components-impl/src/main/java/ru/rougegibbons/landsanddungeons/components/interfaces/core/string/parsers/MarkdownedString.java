package ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * String containing some markdown data, which shall be replaced before sending that string to the user.
 */
public interface MarkdownedString {
    /**
     * get source text (contains markdown).
     * @return source text stored in instance.
     */
    @NotNull String getSource();

    /**
     * get all tags contained in source string.
     * @return tags keyset.
     */
    @NotNull Set<String> getTags();
}
