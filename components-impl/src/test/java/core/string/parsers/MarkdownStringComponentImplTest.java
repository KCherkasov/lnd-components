package core.string.parsers;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.string.parsers.MarkdownStringComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.StringComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.MarkdownStringComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MarkdownStringComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final MarkdownStringComponentImpl componentOne = prepareEmptyComponent();
        final MarkdownStringComponentImpl componentTwo = prepareEmptyComponent();
        assertEquals(1L, componentTwo.getId() - componentOne.getId(),
                "adjacent components ids difference shall be one");
    }

    @Test
    public void markdownStringComponentModelCreationTest() {
        final Set<String> emptyTagsSet = new HashSet<>();
        final MarkdownStringComponentModel emptyStringModel = prepareMDStringComponentModel(
                Constants.ZERO_LONG, Constants.EMPTY_LINE, emptyTagsSet);
        final MarkdownStringComponentImpl emptyStringComponent =
                new MarkdownStringComponentImpl(emptyStringModel);
        checkDeserializedComponent(emptyStringComponent, emptyStringModel);

        final MarkdownStringComponentModel noTagsStringModel = prepareMDStringComponentModel(
                1L, MarkdownTestStrings.NO_TAGS_STRING, emptyTagsSet);
        final MarkdownStringComponentImpl noTagsComponent =
                new MarkdownStringComponentImpl(noTagsStringModel);
        checkDeserializedComponent(noTagsComponent, noTagsStringModel);


        final Set<String> onlyTagSet = new HashSet<>();
        onlyTagSet.add(MarkdownTestStrings.ONLY_TAG_STRING);
        final MarkdownStringComponentModel onlyTagStringModel = prepareMDStringComponentModel(
                2L, MarkdownTestStrings.ONLY_TAG_STRING, onlyTagSet);
        final MarkdownStringComponentImpl onlyTagComponent =
                new MarkdownStringComponentImpl(onlyTagStringModel);
        checkDeserializedComponent(onlyTagComponent, onlyTagStringModel);


        final Set<String> oneTagSet = new HashSet<>();
        oneTagSet.add(MarkdownTestStrings.ONE_TAG);
        final StringComponentModel oneTagStringModel = prepareMDStringComponentModel(
                3L, MarkdownTestStrings.ONE_TAG_STRING, oneTagSet);
        final MarkdownStringComponentImpl oneTagComponent =
                new MarkdownStringComponentImpl(oneTagStringModel);
        checkDeserializedComponent(oneTagComponent, oneTagStringModel);

        final Set<String> multiTagsSet = new HashSet<>();
        multiTagsSet.add(MarkdownTestStrings.STRING_TAG);
        multiTagsSet.add(MarkdownTestStrings.TAGS_TAG);
        final StringComponentModel multiTagsStringModel = prepareMDStringComponentModel(
                4L, MarkdownTestStrings.MULTIPLE_TAGS_STRING, multiTagsSet);
        final MarkdownStringComponentImpl multiTagsComponent =
                new MarkdownStringComponentImpl(multiTagsStringModel);
        checkDeserializedComponent(multiTagsComponent, multiTagsStringModel);
    }

    @Test
    public void stringComponentModelCreationTest() {
        final StringComponentModel emptyStringModel = prepareStringComponentModel(
                Constants.ZERO_LONG, Constants.EMPTY_LINE);
        final MarkdownStringComponentImpl emptyStringComponent =
                new MarkdownStringComponentImpl(emptyStringModel);
        checkDeserializedComponent(emptyStringComponent, emptyStringModel);
        checkParsedTags(emptyStringComponent, true, new HashSet<>());

        final StringComponentModel noTagsStringModel = prepareStringComponentModel(1L,
                MarkdownTestStrings.NO_TAGS_STRING);
        final MarkdownStringComponentImpl noTagsComponent =
                new MarkdownStringComponentImpl(noTagsStringModel);
        checkDeserializedComponent(noTagsComponent, noTagsStringModel);
        checkParsedTags(noTagsComponent, true, new HashSet<>());

        final StringComponentModel onlyTagStringModel = prepareStringComponentModel(
                2L, MarkdownTestStrings.ONLY_TAG_STRING);
        final MarkdownStringComponentImpl onlyTagComponent =
                new MarkdownStringComponentImpl(onlyTagStringModel);
        final Set<String> onlyTagSet = new HashSet<>();
        onlyTagSet.add(MarkdownTestStrings.ONLY_TAG_STRING);
        checkDeserializedComponent(onlyTagComponent, onlyTagStringModel);
        checkParsedTags(onlyTagComponent, false, onlyTagSet);

        final StringComponentModel oneTagStringModel = new StringComponentModel(
                3L, MarkdownTestStrings.ONE_TAG_STRING);
        final MarkdownStringComponentImpl oneTagComponent =
                new MarkdownStringComponentImpl(oneTagStringModel);
        final Set<String> oneTagSet = new HashSet<>();
        oneTagSet.add(MarkdownTestStrings.ONE_TAG);
        checkDeserializedComponent(oneTagComponent, oneTagStringModel);
        checkParsedTags(oneTagComponent, false, oneTagSet);

        final StringComponentModel multiTagsStringModel = new StringComponentModel(
                4L, MarkdownTestStrings.MULTIPLE_TAGS_STRING);
        final MarkdownStringComponentImpl multiTagsComponent =
                new MarkdownStringComponentImpl(multiTagsStringModel);
        final Set<String> multiTagsSet = new HashSet<>();
        multiTagsSet.add(MarkdownTestStrings.STRING_TAG);
        multiTagsSet.add(MarkdownTestStrings.TAGS_TAG);
        checkDeserializedComponent(multiTagsComponent, multiTagsStringModel);
        checkParsedTags(multiTagsComponent, false, multiTagsSet);
    }

    @Test
    public void getTagsTest() {
        final Set<String> multiTagsSet = new HashSet<>();
        multiTagsSet.add(MarkdownTestStrings.TAGS_TAG);
        multiTagsSet.add(MarkdownTestStrings.STRING_TAG);

        final MarkdownStringComponentImpl componentOne = new MarkdownStringComponentImpl(
                MarkdownTestStrings.MULTIPLE_TAGS_STRING);
        checkParsedTags(componentOne, false, multiTagsSet);

        final MarkdownStringComponentImpl componentTwo = new MarkdownStringComponentImpl(
                MarkdownTestStrings.MULTIPLE_TAGS_STRING, multiTagsSet);
        checkParsedTags(componentTwo, false, multiTagsSet);

        assertEquals(componentOne.getTags(), componentTwo.getTags(),
                "components' tags keysets shall be equal.");
    }

    @Test
    public void packTest() {
        final Set<String> testTags = new HashSet<>();
        testTags.add(MarkdownTestStrings.STRING_TAG);
        testTags.add(MarkdownTestStrings.TAGS_TAG);

        final MarkdownStringComponentImpl componentOne = new MarkdownStringComponentImpl(
                MarkdownTestStrings.MULTIPLE_TAGS_STRING);
        checkParsedTags(componentOne, false, testTags);
        packUnpackCheck(componentOne);

        final MarkdownStringComponentImpl componentTwo = new MarkdownStringComponentImpl(
                MarkdownTestStrings.MULTIPLE_TAGS_STRING, testTags);
        packUnpackCheck(componentTwo);
    }

    private @NotNull MarkdownStringComponentImpl prepareComponent(@NotNull String sourceString) {
        return new MarkdownStringComponentImpl(sourceString);
    }

    private @NotNull MarkdownStringComponentImpl prepareEmptyComponent() {
        return prepareComponent(Constants.EMPTY_LINE);
    }

    private @NotNull StringComponentModel prepareStringComponentModel(@NotNull Long id,
                                                                      @NotNull String source) {
        return new StringComponentModel(id, source);
    }

    private void checkDeserializedComponent(@NotNull MarkdownStringComponentImpl component,
                                            @NotNull StringComponentModel model) {
        assertEquals(model.getId(), component.getId(),
                "component id shall be equal to the one passed into the model.");
        assertEquals(model.getText(), component.getText(),
                "component's text shall be equal to one passed into the model.");

    }

    private void checkDeserializedComponent(@NotNull MarkdownStringComponentImpl component,
                                            @NotNull MarkdownStringComponentModel model) {
        assertEquals(model.getId(), component.getId(),
                "component id shall be equal to the one passed into the model");
        assertEquals(model.getText(), component.getText(),
                "component text shall be equal to one passed into the model.");
        assertEquals(model.getTags(), component.getTags(),
                "component tags keyset shall be equal to the one passed into the model.");
    }

    private void checkParsedTags(@NotNull MarkdownStringComponentImpl component,
                                 @NotNull Boolean isEmpty,
                                 @NotNull Set<String> tags) {
        assertEquals(isEmpty, component.getTags().isEmpty(),
                "component tags keyset state shall be equal to the flag");
        assertEquals(tags.size(), component.getTags().size(),
                "component's tags keyset size shall be " + tags.size());
        for (String tag : tags) {
            assertTrue(component.getTags().contains(tag),
                    "component tags keyset shall contain tag " + tag);
        }
    }

    private void checkParsedTags(@NotNull MarkdownStringComponentImpl component,
                                 @NotNull MarkdownStringComponentModel model) {
        checkParsedTags(component, model.getTags().isEmpty(), model.getTags());
    }

    private @NotNull MarkdownStringComponentModel prepareMDStringComponentModel(
            @NotNull Long id,
            @NotNull String source,
            @NotNull Set<String> tags) {
        return new MarkdownStringComponentModel(id, source, tags);
    }

    private void packUnpackCheck(@NotNull MarkdownStringComponentImpl component) {
        final AbstractComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(MarkdownStringComponentModel.class));
        final MarkdownStringComponentModel model = (MarkdownStringComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(), "model shall have the same id " +
                "as the component it was serialized from.");
        assertEquals(component.getText(), model.getText(), "model shall have the same tags keyset " +
                "as the component it was serialized from.");
        checkParsedTags(component, model);

        final MarkdownStringComponentImpl deserializedComponent =
                new MarkdownStringComponentImpl(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "deserialized component shall have the same id as " +
                        "initial component.");
        assertEquals(component.getText(), deserializedComponent.getText(),
                "deserialized component's text shall be equal " +
                        "to the one stored in initial component.");
        checkParsedTags(deserializedComponent, model);
    }
}
