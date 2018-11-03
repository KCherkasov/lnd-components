package core.floatpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.floatpoint.ModifiableSingleFloatComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.SingleFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatComparator;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ModifiableSingleFloatComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableSingleFloatComponentImpl componentOne = prepareZeroComponent();
        final ModifiableSingleFloatComponentImpl componentTwo = prepareZeroComponent();
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent components ids shall always be one.");
    }

    @Test
    public void getValueTest() {
        final ModifiableSingleFloatComponentImpl componentOne = prepareZeroComponent();
        getValueCheck(componentOne, Constants.ZERO_FLOAT);
        final ModifiableSingleFloatComponentImpl componentTwo =
                prepareComponent((float) Constants.PERCENTAGE_CAP_INT);
        getValueCheck(componentTwo, (float) Constants.PERCENTAGE_CAP_INT);
        getValueDifferenceCheck(componentOne, componentTwo);
    }

    @Test
    public void packTest() {
        final ModifiableSingleFloatComponentImpl componentOne =
                prepareComponent((float) Constants.WIDE_PERCENTAGE_CAP_INT);
        getValueCheck(componentOne, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        final AbstractComponentModel genericModel = componentOne.pack();
        assertThat(genericModel, instanceOf(SingleFloatComponentModel.class));
        final SingleFloatComponentModel model = (SingleFloatComponentModel) genericModel;
        final ModifiableSingleFloatComponentImpl componentTwo =
                new ModifiableSingleFloatComponentImpl(model);
        getValueCheck(componentTwo, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        assertEquals(componentOne.getId(), componentTwo.getId(),
                "components ids shall be equal.");
        getValueCheck(componentTwo, componentOne.getValue());
    }

    @Test
    public void setValueTest() {
        final ModifiableSingleFloatComponentImpl component = prepareZeroComponent();
        getValueCheck(component, Constants.ZERO_FLOAT);
        setValueCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        setValueCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setValueCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void increaseTest() {
        final ModifiableSingleFloatComponentImpl component = prepareZeroComponent();
        increaseCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        increaseCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
        increaseCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void decreaseTest() {
        final ModifiableSingleFloatComponentImpl component = prepareZeroComponent();
        decreaseCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        decreaseCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
        decreaseCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void percentageModificationTest() {
        final ModifiableSingleFloatComponentImpl component =
                prepareComponent(Constants.ONE_PERCENT_FLOAT);
        percentageModificationCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCheck(component, Constants.ZERO_INT);
        percentageModificationCheck(component, Constants.ZERO_FLOAT);
        percentageModificationCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

    }

    private @NotNull ModifiableSingleFloatComponentImpl prepareComponent(@NotNull Float value) {
        return new ModifiableSingleFloatComponentImpl(value);
    }

    private @NotNull ModifiableSingleFloatComponentImpl prepareZeroComponent() {
        return prepareComponent(Constants.ZERO_FLOAT);
    }

    private void getValueCheck(@NotNull ModifiableSingleFloatComponentImpl component,
                               @NotNull Float expected) {
        assertTrue(FloatComparator.areEqual(expected, component.getValue()),
                "component's value shall be " + expected
                        + " but found " + component.getValue());
    }

    private void getValueDifferenceCheck(@NotNull ModifiableSingleFloatComponentImpl first,
                                         @NotNull ModifiableSingleFloatComponentImpl second) {
        assertFalse(FloatComparator.areEqual(first.getValue(), second.getValue()),
                "components values shall be different.");
    }

    private void setValueCheck(@NotNull ModifiableSingleFloatComponentImpl component,
                               @NotNull Float toSet) {
        component.setValue(toSet);
        getValueCheck(component, toSet);
    }

    private void increaseCheck(@NotNull ModifiableSingleFloatComponentImpl component,
                               @NotNull Float amount) {
        final Float expected = component.getValue() + amount;
        component.increase(amount);
        getValueCheck(component, expected);
    }

    private void decreaseCheck(@NotNull ModifiableSingleFloatComponentImpl component,
                               @NotNull Float amount) {
        final Float expected = component.getValue() - amount;
        component.decrease(amount);
        getValueCheck(component, expected);
    }

    private void percentageModificationCheck(@NotNull ModifiableSingleFloatComponentImpl component,
                                             @NotNull Integer percent) {
        final Float expected = calculateExpectedValue(
                component.getValue(), toPercent(percent));
        component.modifyByPercentage(percent);
        getValueCheck(component, expected);
    }

    private void percentageModificationCheck(@NotNull ModifiableSingleFloatComponentImpl component,
                                             @NotNull Float percent) {
        final Float expected = calculateExpectedValue(component.getValue(), percent);
        component.modifyByPercentage(percent);
        getValueCheck(component, expected);
    }

    private @NotNull Float calculateExpectedValue(@NotNull Float initial,
                                                  @NotNull Float percentage) {
        return FloatMath.multiply(initial, Constants.PERCENTAGE_CAP_FLOAT + percentage);
    }

    private @NotNull Float toPercent(@NotNull Integer percentage) {
        return FloatMath.divide(percentage.floatValue(), (float) Constants.PERCENTAGE_CAP_INT);
    }
}
