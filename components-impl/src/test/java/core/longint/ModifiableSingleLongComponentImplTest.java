package core.longint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint.ModifiableSingleLongComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.SingleLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class ModifiableSingleLongComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableSingleLongComponentImpl componentOne = prepareZeroComponent();
        final ModifiableSingleLongComponentImpl componentTwo = prepareZeroComponent();
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent components ids shall always be one.");
    }

    @Test
    public void getValueTest() {
        final ModifiableSingleLongComponentImpl componentOne = prepareZeroComponent();
        getValueCheck(componentOne, Constants.ZERO_LONG);
        final ModifiableSingleLongComponentImpl componentTwo =
                prepareComponent((long) Constants.PERCENTAGE_CAP_INT);
        getValueCheck(componentTwo, (long) Constants.PERCENTAGE_CAP_INT);
        getValueDifferenceCheck(componentOne, componentTwo);
    }

    @Test
    public void packTest() {
        final ModifiableSingleLongComponentImpl componentOne =
                prepareComponent((long) Constants.WIDE_PERCENTAGE_CAP_INT);
        getValueCheck(componentOne, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        final AbstractComponentModel genericModel = componentOne.pack();
        assertThat(genericModel, instanceOf(SingleLongComponentModel.class));
        final SingleLongComponentModel model = (SingleLongComponentModel) genericModel;
        final ModifiableSingleLongComponentImpl componentTwo =
                new ModifiableSingleLongComponentImpl(model);
        getValueCheck(componentTwo, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        assertEquals(componentOne.getId(), componentTwo.getId(),
                "components ids shall be equal.");
        getValueCheck(componentTwo, componentOne.getValue());
    }

    @Test
    public void setValueTest() {
        final ModifiableSingleLongComponentImpl component = prepareZeroComponent();
        getValueCheck(component, Constants.ZERO_LONG);
        setValueCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        setValueCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setValueCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void increaseTest() {
        final ModifiableSingleLongComponentImpl component = prepareZeroComponent();
        increaseCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        increaseCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
        increaseCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void decreaseTest() {
        final ModifiableSingleLongComponentImpl component = prepareZeroComponent();
        decreaseCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        decreaseCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
        decreaseCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void percentageModificationTest() {
        final ModifiableSingleLongComponentImpl component = prepareComponent(1L);
        percentageModificationCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCheck(component, Constants.ZERO_INT);
        percentageModificationCheck(component, Constants.ZERO_FLOAT);
        percentageModificationCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

    }

    private @NotNull ModifiableSingleLongComponentImpl prepareComponent(@NotNull Long value) {
        return new ModifiableSingleLongComponentImpl(value);
    }

    private @NotNull ModifiableSingleLongComponentImpl prepareZeroComponent() {
        return prepareComponent(Constants.ZERO_LONG);
    }

    private void getValueCheck(@NotNull ModifiableSingleLongComponentImpl component,
                               @NotNull Long expected) {
        assertEquals(expected, component.getValue(),
                "component's value shall be " + expected
                        + " but found " + component.getValue());
    }

    private void getValueDifferenceCheck(@NotNull ModifiableSingleLongComponentImpl first,
                                         @NotNull ModifiableSingleLongComponentImpl second) {
        assertNotEquals(first.getValue(), second.getValue(),
                "components values shall be different.");
    }

    private void setValueCheck(@NotNull ModifiableSingleLongComponentImpl component,
                               @NotNull Long toSet) {
        component.setValue(toSet);
        getValueCheck(component, toSet);
    }

    private void increaseCheck(@NotNull ModifiableSingleLongComponentImpl component,
                               @NotNull Long amount) {
        final Long expected = component.getValue() + amount;
        component.increase(amount);
        getValueCheck(component, expected);
    }

    private void decreaseCheck(@NotNull ModifiableSingleLongComponentImpl component,
                               @NotNull Long amount) {
        final Long expected = component.getValue() - amount;
        component.decrease(amount);
        getValueCheck(component, expected);
    }

    private void percentageModificationCheck(@NotNull ModifiableSingleLongComponentImpl component,
                                             @NotNull Integer percent) {
        final Long expected = calculateExpectedValue(
                component.getValue(), toPercent(percent));
        component.modifyByPercentage(percent);
        getValueCheck(component, expected);
    }

    private void percentageModificationCheck(@NotNull ModifiableSingleLongComponentImpl component,
                                             @NotNull Float percent) {
        final Long expected = calculateExpectedValue(component.getValue(), percent);
        component.modifyByPercentage(percent);
        getValueCheck(component, expected);
    }

    private @NotNull Long calculateExpectedValue(@NotNull Long initial,
                                                 @NotNull Float percentage) {
        return FloatMath.multiply(initial.floatValue(),
                Constants.PERCENTAGE_CAP_FLOAT + percentage).longValue();
    }

    private @NotNull Float toPercent(@NotNull Integer percentage) {
        return FloatMath.divide(percentage.floatValue(), (float) Constants.PERCENTAGE_CAP_INT);
    }
}
