package core.longint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.longint.SingleLongComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.SingleLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class SingleLongComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final SingleLongComponentImpl componentOne = prepareZeroComponent();
        final SingleLongComponentImpl componentTwo = prepareZeroComponent();
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent components ids shall always be one.");
    }

    @Test
    public void getValueTest() {
        final SingleLongComponentImpl componentOne = prepareZeroComponent();
        assertEquals(Constants.ZERO_LONG, componentOne.getValue().longValue(),
                "first component's value shall be zero.");
        final SingleLongComponentImpl componentTwo = prepareComponent(Constants.ZERO_LONG + 1);
        assertEquals(Constants.ZERO_LONG + 1, componentTwo.getValue().longValue(),
                "second component's value shall be 100.");
        assertNotEquals(componentOne.getValue(), componentTwo.getValue(),
                "first component's value shall not be equal to second's.");
    }

    @Test
    public void packTest() {
        final SingleLongComponentImpl componentOne = prepareComponent(Constants.ZERO_LONG);
        assertEquals(Constants.ZERO_LONG, componentOne.getValue().intValue(),
                "first component's value shall be equal to the one passed to it on creation.");
        final ComponentModel genericModel = componentOne.pack();
        assertThat(genericModel, instanceOf(SingleLongComponentModel.class));
        final SingleLongComponentModel model = (SingleLongComponentModel)genericModel;
        final SingleLongComponentImpl componentTwo = new SingleLongComponentImpl(model);
        assertEquals(Constants.ZERO_LONG, componentTwo.getValue().intValue(),
                "deserialized component value shall be equal to the one initially passed.");
        assertEquals(componentOne.getId(), componentTwo.getId(), "components ids shall be equal.");
        assertEquals(componentOne.getValue(), componentTwo.getValue(),
                "components values shall be equal.");
    }

    private @NotNull SingleLongComponentImpl prepareComponent(@NotNull Long value) {
        return new SingleLongComponentImpl(value);
    }

    private @NotNull SingleLongComponentImpl prepareZeroComponent() {
        return prepareComponent(Constants.ZERO_LONG);
    }
}
