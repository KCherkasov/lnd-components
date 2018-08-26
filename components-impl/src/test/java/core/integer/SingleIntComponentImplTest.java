package core.integer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.integer.SingleIntComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.SingleIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class SingleIntComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final SingleIntComponentImpl componentOne = prepareZeroComponent();
        final SingleIntComponentImpl componentTwo = prepareZeroComponent();
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent components ids shall always be one.");
    }

    @Test
    public void getValueTest() {
        final SingleIntComponentImpl componentOne = prepareZeroComponent();
        assertEquals(Constants.ZERO_INT, componentOne.getValue().intValue(),
                "first component's value shall be zero.");
        final SingleIntComponentImpl componentTwo = prepareComponent(Constants.PERCENTAGE_CAP_INT);
        assertEquals(Constants.PERCENTAGE_CAP_INT, componentTwo.getValue().intValue(),
                "second component's value shall be 100.");
        assertNotEquals(componentOne.getValue(), componentTwo.getValue(),
                "first component's value shall not be equal to second's.");
    }

    @Test
    public void packTest() {
        final SingleIntComponentImpl componentOne = prepareComponent(Constants.WIDE_PERCENTAGE_CAP_INT);
        assertEquals(Constants.WIDE_PERCENTAGE_CAP_INT, componentOne.getValue().intValue(),
                "first component's value shall be equal to the one passed to it on creation.");
        final ComponentModel genericModel = componentOne.pack();
        assertThat(genericModel, instanceOf(SingleIntComponentModel.class));
        final SingleIntComponentModel model = (SingleIntComponentModel)genericModel;
        final SingleIntComponentImpl componentTwo = new SingleIntComponentImpl(model);
        assertEquals(Constants.WIDE_PERCENTAGE_CAP_INT, componentTwo.getValue().intValue(),
                "deserialized component value shall be equal to the one initially passed.");
        assertEquals(componentOne.getId(), componentTwo.getId(), "components ids shall be equal.");
        assertEquals(componentOne.getValue(), componentTwo.getValue(),
                "components values shall be equal.");
    }

    private @NotNull SingleIntComponentImpl prepareComponent(@NotNull Integer value) {
        return new SingleIntComponentImpl(value);
    }

    private @NotNull SingleIntComponentImpl prepareZeroComponent() {
        return prepareComponent(Constants.ZERO_INT);
    }
}
