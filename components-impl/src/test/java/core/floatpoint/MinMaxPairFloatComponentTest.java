package core.floatpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.floatpoint.MinMaxPairFloatComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatComparator;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public final class MinMaxPairFloatComponentTest {
    @Test
    public void idGeneratorTest() {
        final MinMaxPairFloatComponent componentOne = prepareOneZeroComponent();
        final MinMaxPairFloatComponent componentTwo = prepareOneZeroComponent();
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent component ids shall be one");
    }

    @Test
    public void getFirstValueTest() {
        final MinMaxPairFloatComponent componentOne = prepareComponent(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        assertTrue(FloatComparator.areEqual(Constants.ZERO_FLOAT, componentOne.getFirstValue()),
                "first value must be zero");
        final MinMaxPairFloatComponent componentTwo = prepareOneZeroComponent();
        assertTrue(FloatComparator.areEqual(componentOne.getFirstValue(),
                componentTwo.getFirstValue()),
                "first values shall be equal.");
        final MinMaxPairFloatComponent componentThree = prepareComponent(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        assertTrue(FloatComparator.areEqual(
                (float) Constants.PERCENTAGE_CAP_INT,
                componentThree.getFirstValue()),
                "first value shall be 100.");
    }

    @Test
    public void getSecondValueTest() {
        final MinMaxPairFloatComponent componentOne = prepareComponent(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        assertTrue(FloatComparator.areEqual(Constants.ZERO_FLOAT, componentOne.getSecondValue()),
                "first value must be zero");
        final MinMaxPairFloatComponent componentTwo = prepareOneZeroComponent();
        assertFalse(FloatComparator.areEqual(componentOne.getSecondValue(),
                componentTwo.getSecondValue()),
                "second values shall be equal.");
        final MinMaxPairFloatComponent componentThree = prepareComponent(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        assertTrue(FloatComparator.areEqual((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                componentThree.getSecondValue()),
                "second value shall be 10000.");
    }

    @Test
    public void getBothTest() {
        checkBoth(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        checkBoth(Constants.ZERO_FLOAT, Constants.ONE_PERCENT_FLOAT);
        checkBoth((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void packTest() {
        packCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        packCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        packCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void orderingTest() {
        final MinMaxPairFloatComponent componentOne = prepareComponent(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        final MinMaxPairFloatComponent componentTwo = prepareComponent(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        checkBoth(componentOne, componentTwo);
    }

    private @NotNull MinMaxPairFloatComponent prepareComponent(@NotNull Float firstValue,
                                                               @NotNull Float secondValue) {
        return new MinMaxPairFloatComponent(firstValue, secondValue);
    }

    private @NotNull MinMaxPairFloatComponent prepareOneZeroComponent() {
        return prepareComponent(Constants.ZERO_FLOAT, Constants.ONE_PERCENT_FLOAT);
    }

    private void checkBoth(@NotNull Float firstValue,
                           @NotNull Float secondValue) {
        final MinMaxPairFloatComponent component = prepareComponent(firstValue, secondValue);
        final List<Float> expected = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        orderedFilling(expected, firstValue, secondValue);
        final List<Float> actual = component.getBoth();
        assertEquals(expected.size(), actual.size(),
                "component values list size shall be 2.");
        for (int i = 0; i < expected.size(); ++i) {
            assertTrue(FloatComparator.areEqual(
                    expected.get(i), actual.get(i)),
                    "value " + i + " shall be "
                            + expected.get(i) +
                            " but found " + actual.get(i) + '.');
        }
    }

    private void checkBoth(@NotNull MinMaxPairFloatComponent expected,
                           @NotNull MinMaxPairFloatComponent actual) {
        final List<Float> expectedData = expected.getBoth();
        final List<Float> actualData = actual.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, expectedData.size(),
                "expected list size shall be 2.");
        assertEquals(expectedData.size(), actualData.size(),
                "components lists sizes shall be equal.");
        for (int i = 0; i < expectedData.size(); ++i) {
            assertTrue(FloatComparator.areEqual(
                    expectedData.get(i), actualData.get(i)),
                    "value " + i + " shall be "
                            + expectedData.get(i) +
                            " but found " + actualData.get(i) + '.');
        }
    }

    private void orderedFilling(@NotNull List<Float> list,
                                @NotNull Float first,
                                @NotNull Float second) {
        final Float min = FloatComparator.areEqual(first, second)
                || FloatComparator.isLess(first, second) ? first : second;
        list.add(min);
        if (FloatComparator.areEqual(min, first)) {
            list.add(second);
        } else {
            list.add(first);
        }
    }

    private void packCheck(@NotNull Float firstValue,
                           @NotNull Float secondValue) {
        final MinMaxPairFloatComponent component = prepareComponent(firstValue, secondValue);
        final AbstractComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairFloatComponentModel.class));
        final PairFloatComponentModel model = (PairFloatComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "model id shall be equal to component id.");
        assertTrue(FloatComparator.areEqual(component.getFirstValue(), model.getFirstValue()),
                "model first value shall be equal to component's first value.");
        assertTrue(FloatComparator.areEqual(component.getSecondValue(), model.getSecondValue()),
                "model second value shall be equal to component second value.");
        final MinMaxPairFloatComponent restored = new MinMaxPairFloatComponent(model);
        assertEquals(component.getId(), restored.getId(),
                "restored component id shall be equal to original component id.");
        assertTrue(FloatComparator.areEqual(component.getFirstValue(), restored.getFirstValue()),
                "restored component first value " +
                        "shall be equal to original component first value.");
        assertTrue(FloatComparator.areEqual(component.getSecondValue(), restored.getSecondValue()),
                "restored component second value " +
                        "shall be equal to original component second value.");
        checkBoth(component, restored);
    }
}
