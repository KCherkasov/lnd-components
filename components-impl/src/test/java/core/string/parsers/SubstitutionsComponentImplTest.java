package core.string.parsers;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.string.parsers.SubstitutionsComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.string.parsers.SubstitutionsComponentModel;

import java.util.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class SubstitutionsComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final SubstitutionsComponentImpl componentOne = prepareEmptyComponent();
        final SubstitutionsComponentImpl componentTwo = prepareEmptyComponent();
        assertEquals(1L, componentTwo.getId() - componentOne.getId(),
                "adjacent components ids difference shall be one.");
    }

    @Test
    public void getSubstitutionsMapTest() {
        final SubstitutionsComponentImpl emptyComponent = prepareEmptyComponent();
        assertTrue(emptyComponent.getSubstitutionsMap().isEmpty(),
                "empty component's substitutions map shall be empty.");

        final Map<String, String> oneTagMap = new HashMap<>();
        oneTagMap.put(MarkdownTestStrings.ONE_TAG, "one_sub");
        final SubstitutionsComponentImpl oneTagComponent = prepareComponent(oneTagMap);
        checkSubstitutionsMap(oneTagComponent, oneTagMap);

        final Map<String, String> multiTagsMap = new HashMap<>();
        multiTagsMap.put(MarkdownTestStrings.ONE_TAG, "one_sub");
        multiTagsMap.put(MarkdownTestStrings.STRING_TAG, "string_sub");
        multiTagsMap.put(MarkdownTestStrings.ONLY_TAG_STRING, "only_tag_string_sub");
        final SubstitutionsComponentImpl multiTagsComponent = prepareComponent(multiTagsMap);
        checkSubstitutionsMap(multiTagsComponent, multiTagsMap);
    }

    @Test
    public void getSubstitutionsTest() {
        final SubstitutionsComponentImpl emptyComponent = prepareEmptyComponent();
        assertTrue(emptyComponent.getSubstitutions().isEmpty(),
                "empty component's substitutions list shall be empty.");

        final Map<String, String> oneTagMap = new HashMap<>();
        oneTagMap.put(MarkdownTestStrings.ONE_TAG, "one_sub");
        final List<String> oneTagList = new ArrayList<>();
        oneTagList.add("one_sub");
        final SubstitutionsComponentImpl oneTagComponent = prepareComponent(oneTagMap);
        checkSubstitutions(oneTagComponent, oneTagList);

        final Map<String, String> multiTagsMap = new HashMap<>();
        multiTagsMap.put(MarkdownTestStrings.ONE_TAG, "one_sub");
        multiTagsMap.put(MarkdownTestStrings.STRING_TAG, "string_sub");
        multiTagsMap.put(MarkdownTestStrings.ONLY_TAG_STRING, "only_tag_string_sub");
        final List<String> multiTagsList = new ArrayList<>();
        for (String key : multiTagsMap.keySet()) {
            multiTagsList.add(multiTagsMap.get(key));
        }
        final SubstitutionsComponentImpl multiTagsComponent = prepareComponent(multiTagsMap);
        checkSubstitutions(multiTagsComponent, multiTagsList);
    }

    @Test
    public void getTagsTest() {
        final SubstitutionsComponentImpl emptyComponent = prepareEmptyComponent();
        assertTrue(emptyComponent.getTags().isEmpty(),
                "empty component's tags keyset shall be empty.");

        final Map<String, String> oneTagMap = new HashMap<>();
        oneTagMap.put(MarkdownTestStrings.ONE_TAG, "one_sub");
        final SubstitutionsComponentImpl oneTagComponent = prepareComponent(oneTagMap);
        checkTags(oneTagComponent, oneTagMap.keySet());

        final Map<String, String> multiTagsMap = new HashMap<>();
        multiTagsMap.put(MarkdownTestStrings.ONE_TAG, "one_sub");
        multiTagsMap.put(MarkdownTestStrings.STRING_TAG, "string_sub");
        multiTagsMap.put(MarkdownTestStrings.ONLY_TAG_STRING, "only_tag_string_sub");
        final SubstitutionsComponentImpl multiTagsComponent = prepareComponent(multiTagsMap);
        checkTags(multiTagsComponent, multiTagsMap.keySet());
    }

    @Test
    public void packTest() {
        final SubstitutionsComponentImpl emptyComponent = prepareEmptyComponent();
        packUnpackCheck(emptyComponent);

        final Map<String, String> oneTagMap = new HashMap<>();
        oneTagMap.put(MarkdownTestStrings.ONE_TAG, "one_sub");
        final SubstitutionsComponentImpl oneTagComponent = prepareComponent(oneTagMap);
        packUnpackCheck(oneTagComponent);

        final Map<String, String> multiTagsMap = new HashMap<>();
        multiTagsMap.put(MarkdownTestStrings.ONE_TAG, "one_sub");
        multiTagsMap.put(MarkdownTestStrings.STRING_TAG, "string_sub");
        multiTagsMap.put(MarkdownTestStrings.ONLY_TAG_STRING, "only_tag_string_sub");
        final SubstitutionsComponentImpl multiTagsComponent = prepareComponent(multiTagsMap);
        packUnpackCheck(multiTagsComponent);
    }

    private @NotNull SubstitutionsComponentImpl prepareComponent(@NotNull Map<String, String> tags) {
        return new SubstitutionsComponentImpl(tags);
    }

    private @NotNull SubstitutionsComponentImpl prepareEmptyComponent() {
        return prepareComponent(new HashMap<>());
    }

    private void checkSubstitutionsMap(@NotNull SubstitutionsComponentImpl component,
                                       @NotNull Map<String, String> substitutionsMap) {
        for (String key : substitutionsMap.keySet()) {
            assertEquals(substitutionsMap.get(key), component.getSubstitutionsMap().get(key),
                    "component's subsitution for key " + key
                            + " shall be equal to one in the original map.");
        }
    }

    private void checkSubstitutions(@NotNull SubstitutionsComponentImpl component,
                                    @NotNull List<String> substitutions) {
        final List<String> componentList = component.getSubstitutions();
        assertEquals(substitutions.size(), componentList.size(),
                "number of elements in expected list and in component's one shall be equal.");
        for (int i = 0; i < substitutions.size(); ++i) {
            assertEquals(substitutions.get(i), componentList.get(i),
                    "element number " + i + " in component list shall be equal to the original list one.");
        }
    }

    private void checkTags(@NotNull SubstitutionsComponentImpl component,
                           @NotNull Set<String> tagsKeyset) {
        final Set<String> componentsKeyset = component.getTags();
        for (String key : tagsKeyset) {
            assertTrue(componentsKeyset.contains(key), "key "
                    + key + " shall be present in components keyset");
        }
    }

    private void packUnpackCheck(@NotNull SubstitutionsComponentImpl component) {
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(SubstitutionsComponentModel.class));
        final SubstitutionsComponentModel model = (SubstitutionsComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "model id shall be equal to the component id.");
        checkSubstitutionsMap(component, model.getSubstitutions());

        final SubstitutionsComponentImpl deserializeComponent =
                new SubstitutionsComponentImpl(model);
        assertEquals(model.getId(), deserializeComponent.getId(),
                "deserialized component's id shall be equal to the model id.");
        checkSubstitutionsMap(deserializeComponent, model.getSubstitutions());
        checkSubstitutionsMap(deserializeComponent, component.getSubstitutionsMap());
    }
}
