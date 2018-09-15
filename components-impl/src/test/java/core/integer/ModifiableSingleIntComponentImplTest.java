package core.integer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer.ModifiableSingleIntComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.SingleIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class ModifiableSingleIntComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableSingleIntComponentImpl componentOne = prepareZeroComponent();
        final ModifiableSingleIntComponentImpl componentTwo = prepareZeroComponent();
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent components ids shall always be one.");
    }

    @Test
    public void getValueTest() {
        final ModifiableSingleIntComponentImpl componentOne = prepareZeroComponent();
        getValueCheck(componentOne, Constants.ZERO_INT);
        final ModifiableSingleIntComponentImpl componentTwo =
                prepareComponent(Constants.PERCENTAGE_CAP_INT);
        getValueCheck(componentTwo, Constants.PERCENTAGE_CAP_INT);
        getValueDifferenceCheck(componentOne, componentTwo);
    }

    @Test
    public void packTest() {
        final ModifiableSingleIntComponentImpl componentOne =
                prepareComponent(Constants.WIDE_PERCENTAGE_CAP_INT);
        getValueCheck(componentOne, Constants.WIDE_PERCENTAGE_CAP_INT);
        final ComponentModel genericModel = componentOne.pack();
        assertThat(genericModel, instanceOf(SingleIntComponentModel.class));
        final SingleIntComponentModel model = (SingleIntComponentModel) genericModel;
        final ModifiableSingleIntComponentImpl componentTwo =
                new ModifiableSingleIntComponentImpl(model);
        getValueCheck(componentTwo, Constants.WIDE_PERCENTAGE_CAP_INT);
        assertEquals(componentOne.getId(), componentTwo.getId(),
                "components ids shall be equal.");
        getValueCheck(componentTwo, componentOne.getValue());
    }

    @Test
    public void setValueTest() {
        final ModifiableSingleIntComponentImpl component = prepareZeroComponent();
        getValueCheck(component, Constants.ZERO_INT);
        setValueCheck(component, Constants.PERCENTAGE_CAP_INT);
        setValueCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        setValueCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void increaseTest() {
        final ModifiableSingleIntComponentImpl component = prepareZeroComponent();
        increaseCheck(component, Constants.PERCENTAGE_CAP_INT);
        increaseCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
        increaseCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void decreaseTest() {
        final ModifiableSingleIntComponentImpl component = prepareZeroComponent();
        decreaseCheck(component, Constants.PERCENTAGE_CAP_INT);
        decreaseCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void percentageModificationTest() {
        final ModifiableSingleIntComponentImpl component = prepareComponent(1);
        percentageModificationCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCheck(component, Constants.ZERO_INT);
        percentageModificationCheck(component, Constants.ZERO_FLOAT);
        percentageModificationCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

    }

    private @NotNull ModifiableSingleIntComponentImpl prepareComponent(@NotNull Integer value) {
        return new ModifiableSingleIntComponentImpl(value);
    }

    private @NotNull ModifiableSingleIntComponentImpl prepareZeroComponent() {
        return prepareComponent(Constants.ZERO_INT);
    }

    private void getValueCheck(@NotNull ModifiableSingleIntComponentImpl component,
                               @NotNull Integer expected) {
        assertEquals(expected, component.getValue(),
                "component's value shall be " + expected
                        + " but found " + component.getValue());
    }

    private void getValueDifferenceCheck(@NotNull ModifiableSingleIntComponentImpl first,
                                         @NotNull ModifiableSingleIntComponentImpl second) {
        assertNotEquals(first.getValue(), second.getValue(),
                "components values shall be different.");
    }

    private void setValueCheck(@NotNull ModifiableSingleIntComponentImpl component,
                               @NotNull Integer toSet) {
        component.setValue(toSet);
        getValueCheck(component, toSet);
    }

    private void increaseCheck(@NotNull ModifiableSingleIntComponentImpl component,
                               @NotNull Integer amount) {
        final Integer expected = component.getValue() + amount;
        component.increase(amount);
        getValueCheck(component, expected);
    }

    private void decreaseCheck(@NotNull ModifiableSingleIntComponentImpl component,
                               @NotNull Integer amount) {
        final Integer expected = component.getValue() - amount;
        component.decrease(amount);
        getValueCheck(component, expected);
    }

    private void percentageModificationCheck(@NotNull ModifiableSingleIntComponentImpl component,
                                             @NotNull Integer percent) {
        final Integer expected = calculateExpectedValue(
                component.getValue(), toPercent(percent));
        component.modifyByPercentage(percent);
        getValueCheck(component, expected);
    }

    private void percentageModificationCheck(@NotNull ModifiableSingleIntComponentImpl component,
                                             @NotNull Float percent) {
        final Integer expected = calculateExpectedValue(component.getValue(), percent);
        component.modifyByPercentage(percent);
        getValueCheck(component, expected);
    }

    private @NotNull Integer calculateExpectedValue(@NotNull Integer initial,
                                                    @NotNull Float percentage) {
        return FloatMath.multiply(initial.floatValue(),
                Constants.PERCENTAGE_CAP_FLOAT + percentage).intValue();
    }

    private @NotNull Float toPercent(@NotNull Integer percentage) {
        return FloatMath.divide(percentage.floatValue(), (float) Constants.PERCENTAGE_CAP_INT);
    }
}
