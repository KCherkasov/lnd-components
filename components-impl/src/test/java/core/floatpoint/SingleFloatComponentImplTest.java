package core.floatpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.components.core.numbers.floatpoint.SingleFloatComponentImpl;
import ru.rougegibbons.components.models.ComponentModel;
import ru.rougegibbons.components.models.core.SingleFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatComparator;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public final class SingleFloatComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final SingleFloatComponentImpl componentOne = prepareZeroComponent();
        assertEquals(Constants.ZERO_LONG, componentOne.getId().longValue(),
                "first component's id shall be zero.");
        final SingleFloatComponentImpl componentTwo = prepareZeroComponent();
        assertEquals(1, componentTwo.getId().longValue(),
                "second component's id shall be one.");
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent components ids shall always be one.");
    }

    @Test
    public void getValueTest() {
        final SingleFloatComponentImpl componentOne = prepareZeroComponent();
        assertTrue(FloatComparator.areEqual(Constants.ZERO_FLOAT, componentOne.getValue()),
                "first component's value shall be zero.");
        final SingleFloatComponentImpl componentTwo = prepareComponent(Constants.PERCENTAGE_CAP_FLOAT);
        assertTrue(FloatComparator.areEqual(Constants.PERCENTAGE_CAP_FLOAT, componentTwo.getValue()),
                "second component's value shall be 1.0.");
        assertFalse(FloatComparator.areEqual(componentOne.getValue(), componentTwo.getValue()),
                "first component's value shall not be equal to second's.");
    }

    @Test
    public void packTest() {
        final SingleFloatComponentImpl componentOne = prepareComponent(Constants.PERCENTAGE_CAP_FLOAT);
        assertTrue(FloatComparator.areEqual(Constants.PERCENTAGE_CAP_FLOAT, componentOne.getValue()),
                "first component's value shall be equal to the one passed to it on creation.");
        final ComponentModel genericModel = componentOne.pack();
        assertThat(genericModel, instanceOf(SingleFloatComponentModel.class));
        final SingleFloatComponentModel model = (SingleFloatComponentModel)genericModel;
        final SingleFloatComponentImpl componentTwo = new SingleFloatComponentImpl(model);
        assertTrue(FloatComparator.areEqual(Constants.PERCENTAGE_CAP_FLOAT, componentTwo.getValue()),
                "deserialized component value shall be equal to the one initially passed.");
        assertEquals(componentOne.getId(), componentTwo.getId(), "components ids shall be equal.");
        assertTrue(FloatComparator.areEqual(componentOne.getValue(), componentTwo.getValue()),
                "components values shall be equal.");
    }

    private @NotNull SingleFloatComponentImpl prepareComponent(@NotNull Float value) {
        return new SingleFloatComponentImpl(value);
    }

    private @NotNull SingleFloatComponentImpl prepareZeroComponent() {
        return prepareComponent(Constants.ZERO_FLOAT);
    }
}
