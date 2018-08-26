package core.string.parsers;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.string.parsers.MarkdownStringComponentImpl;
import ru.rougegibbons.landsanddungeons.components.core.string.parsers.MarkdownTextParserComponentImpl;
import ru.rougegibbons.landsanddungeons.components.core.string.parsers.SubstitutionsComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers.MarkdownStringComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers.MarkdownTextParserComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.string.parsers.SubstitutionsComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public final class MarkdownTextParserComponentImplTest {
    @Test
    public void emptyStringProcessingTest() {
        final MarkdownTextParserComponent parser = initParser();
        final MarkdownStringComponent emptySource = initSource(Constants.EMPTY_LINE);
        final SubstitutionsComponent emptySubstitutions =
                initSubstitutions(makeEmptySubstitutionsMap());
        final String processedEmptyString = parser.process(emptySource, emptySubstitutions);
        assertEquals(Constants.EMPTY_LINE, processedEmptyString,
                "empty line processing shall return unchanged empty line.");

        final SubstitutionsComponent notEmptySubstitutions =
                initSubstitutions(makeFullSubstitutionsMap());
        final String emptyNotEmptyProcessedString = parser.process(
                emptySource, notEmptySubstitutions);
        assertEquals(Constants.EMPTY_LINE, emptyNotEmptyProcessedString,
                "empty line processing shall return unchanged empty line.");
    }

    @Test
    public void noTagsStringProcessingTest() {
        final MarkdownTextParserComponent parser = initParser();
        final MarkdownStringComponent source = initSource(
                MarkdownTestStrings.NO_TAGS_STRING);

        final SubstitutionsComponent emptySubstitutions = initSubstitutions(
                makeEmptySubstitutionsMap());
        final String emptySubstitutionsProcessed = parser.process(
                source, emptySubstitutions);
        assertEquals(MarkdownTestStrings.NO_TAGS_STRING, emptySubstitutionsProcessed,
                "string with no tags shall not be modified.");

        final SubstitutionsComponent partialSubstitutions = initSubstitutions(
                makePartialSubstitutionsMap());
        final String partialSubstitutionsProcessed = parser.process(
                source, partialSubstitutions);
        assertEquals(MarkdownTestStrings.NO_TAGS_STRING, partialSubstitutionsProcessed,
                "string with no tags shall not be modified.");

        final SubstitutionsComponent fullSubstitutions = initSubstitutions(
                makeFullSubstitutionsMap());
        final String fullSubstitutionsProcessed = parser.process(
                source, fullSubstitutions);
        assertEquals(MarkdownTestStrings.NO_TAGS_STRING, fullSubstitutionsProcessed,
                "string with no tags shall not be modified.");
    }

    @Test
    public void onlyTagStringProcessingTest() {
        final MarkdownTextParserComponent parser = initParser();
        final MarkdownStringComponent source = initSource(
                MarkdownTestStrings.ONLY_TAG_STRING);

        final SubstitutionsComponent emptySubstitutions =
                initSubstitutions(makeEmptySubstitutionsMap());
        final String emptySubsProcessingResult = parser.process(
                source, emptySubstitutions);
        assertEquals(MarkdownTestStrings.ONLY_TAG_STRING, emptySubsProcessingResult,
                "processing with empty substitutions " +
                        "map shall not modify source string.");

        final SubstitutionsComponent partialSubstitutions =
                initSubstitutions(makePartialSubstitutionsMap());
        final String partialSubsProcessingResult = parser.process(
                source, partialSubstitutions);
        assertEquals(MarkdownTestStrings.ONLY_TAG_STRING, partialSubsProcessingResult,
                "processing with partially filled substitutions map " +
                        "shall not remove markdown tag.");

        final SubstitutionsComponent fullSubstitutions =
                initSubstitutions(makeFullSubstitutionsMap());
        final String fullSubsProcessingResult = parser.process(
                source, fullSubstitutions);
        assertEquals(MarkdownTestStrings.PROCESSED_ONLY_TAG_STRING, fullSubsProcessingResult,
                "processing with partially filled substitutions map " +
                        "shall remove markdown tag and put a substitution instead.");
    }

    @Test
    public void multiTagStringProcessingTest() {
        final MarkdownTextParserComponent parser = initParser();
        final MarkdownStringComponent source = initSource(
                MarkdownTestStrings.MULTIPLE_TAGS_STRING);

        final SubstitutionsComponent emptySubstitutions =
                initSubstitutions(makeEmptySubstitutionsMap());
        final String emptySubsProcessingResult = parser.process(
                source, emptySubstitutions);
        assertEquals(MarkdownTestStrings.MULTIPLE_TAGS_STRING, emptySubsProcessingResult,
                "processing with empty substitutions map " +
                        "shall not modify source string.");

        final SubstitutionsComponent partialSubstitutions =
                initSubstitutions(makePartialSubstitutionsMap());
        final String partialSubsProcessingResult = parser.process(
                source, partialSubstitutions);
        assertNotEquals(MarkdownTestStrings.MULTIPLE_TAGS_STRING, partialSubsProcessingResult,
                "partial substitutions processing" +
                        "shall modify source string.");
        assertNotEquals(MarkdownTestStrings.PROCESSED_MULTI_TAGS_STRING, partialSubsProcessingResult,
                "processing with partial substitutions " +
                        "shall not give an entirely processed string.");
        final MarkdownStringComponent partiallyProcessed =
                initSource(partialSubsProcessingResult);
        assertTrue(partiallyProcessed.getTags().contains(MarkdownTestStrings.STRING_TAG),
                MarkdownTestStrings.STRING_TAG + " shall not be removed after partial processing.");

        final SubstitutionsComponent fullSubstitutions =
                initSubstitutions(makeFullSubstitutionsMap());
        final String fullSubsProcessingResult = parser.process(
                source, fullSubstitutions);
        assertEquals(MarkdownTestStrings.PROCESSED_MULTI_TAGS_STRING, fullSubsProcessingResult,
                "full subtitutions processing shall remove markdown" +
                        "entirely and put tag subs instead.");
    }

    private @NotNull MarkdownTextParserComponent initParser() {
        return new MarkdownTextParserComponentImpl();
    }

    private @NotNull MarkdownStringComponent initSource(@NotNull String text) {
        return new MarkdownStringComponentImpl(text);
    }

    private @NotNull MarkdownStringComponent initSource(@NotNull String text,
                                                        @NotNull Set<String> tags) {
        return new MarkdownStringComponentImpl(text, tags);
    }

    private @NotNull SubstitutionsComponent initSubstitutions(
            @NotNull Map<String, String> substitutions) {
        return new SubstitutionsComponentImpl(substitutions);
    }

    private @NotNull Map<String, String> makeFullSubstitutionsMap() {
        final Map<String, String> map =
                makePartialSubstitutionsMap();

        map.put(MarkdownTestStrings.STRING_TAG,
                MarkdownTestStrings.STRING_TAG_SUB);
        map.put(MarkdownTestStrings.ONLY_TAG_STRING,
                MarkdownTestStrings.ONLY_TAG_SUB);

        return map;
    }

    private @NotNull Map<String, String> makePartialSubstitutionsMap() {
        final Map<String, String> map = new HashMap<>();

        map.put(MarkdownTestStrings.TAGS_TAG,
                MarkdownTestStrings.TAGS_TAG_SUB);
        map.put(MarkdownTestStrings.ONE_TAG,
                MarkdownTestStrings.ONE_TAG_SUB);

        return map;
    }

    private @NotNull Map<String, String> makeEmptySubstitutionsMap() {
        return new HashMap<>();
    }
}
