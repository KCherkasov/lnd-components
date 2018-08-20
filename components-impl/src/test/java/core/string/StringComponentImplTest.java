package core.string;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.components.core.string.StringComponentImpl;
import ru.rougegibbons.components.models.ComponentModel;
import ru.rougegibbons.components.models.core.StringComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class StringComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final StringComponentImpl componentOne = prepareEmptyComponent();
        assertEquals(Constants.ZERO_LONG, componentOne.getId().longValue(),
                "first component's id shall be zero");
        final StringComponentImpl componentTwo = prepareEmptyComponent();
        assertEquals(1, componentTwo.getId().longValue(), "second component's id shall be one");
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent components ids shall be always one");
    }

    @Test
    public void getTextTest() {
        final String textOne = "text one";
        final StringComponentImpl componentOne = new StringComponentImpl(textOne);
        assertEquals(textOne, componentOne.getText(),
                "stored text shall be equal to the one passed in constructor");
        final String textTwo = "text two";
        final StringComponentImpl componentTwo = new StringComponentImpl(textTwo);
        assertEquals(textTwo, componentTwo.getText(),
                "stored text shall be equal to the one passed in constructor");
        assertNotEquals(componentOne.getText(), componentTwo.getText(),
                "different data passed at creation, so tored data shall be different as well.");
    }

    @Test
    public void packTest() {
        final String testString = "test string.";
        final StringComponentImpl component = new StringComponentImpl(testString);
        assertEquals(testString, component.getText(),
                "stored string shall be equal to the source one.");
        final ComponentModel model = component.pack();
        assertThat(model, instanceOf(StringComponentModel.class));
        final StringComponentModel stringComponentModel = (StringComponentModel)model;
        final StringComponentImpl unpackedComponent = new StringComponentImpl(stringComponentModel);
        assertEquals(component.getId(), unpackedComponent.getId(),
                "ids shall be equal");
        assertEquals(component.getText(), unpackedComponent.getText(),
                "stored texts shall be equal");
    }

    private @NotNull StringComponentImpl prepareComponent(@NotNull String text) {
        return new StringComponentImpl(text);
    }

    private @NotNull StringComponentImpl prepareEmptyComponent() {
        return prepareComponent(Constants.EMPTY_LINE);
    }
}
