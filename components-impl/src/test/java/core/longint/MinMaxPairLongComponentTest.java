package core.longint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.longint.MinMaxPairLongComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class MinMaxPairLongComponentTest {
    @Test
    public void idGeneratorTest() {
        final MinMaxPairLongComponent componentOne = prepareOneZeroComponent();
        final MinMaxPairLongComponent componentTwo = prepareOneZeroComponent();
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent component ids shall be one");
    }

    @Test
    public void getFirstValueTest() {
        final MinMaxPairLongComponent componentOne = prepareComponent(
                Constants.ZERO_LONG, Constants.ZERO_LONG);
        assertEquals(Constants.ZERO_LONG, componentOne.getFirstValue().longValue(),
                "first value must be zero");
        final MinMaxPairLongComponent componentTwo = prepareOneZeroComponent();
        assertEquals(componentOne.getFirstValue(), componentTwo.getFirstValue(),
                "first values shall be equal.");
        final MinMaxPairLongComponent componentThree = prepareComponent(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        assertEquals((long) Constants.PERCENTAGE_CAP_INT,
                componentThree.getFirstValue().longValue(),
                "first value shall be 100.");
    }

    @Test
    public void getSecondValueTest() {
        final MinMaxPairLongComponent componentOne = prepareComponent(
                Constants.ZERO_LONG, Constants.ZERO_LONG);
        assertEquals(Constants.ZERO_LONG, componentOne.getSecondValue().longValue(),
                "first value must be zero");
        final MinMaxPairLongComponent componentTwo = prepareOneZeroComponent();
        assertNotEquals(componentOne.getSecondValue(), componentTwo.getSecondValue(),
                "second values shall be equal.");
        final MinMaxPairLongComponent componentThree = prepareComponent(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        assertEquals((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                componentThree.getSecondValue().longValue(),
                "second value shall be 10000.");
    }

    @Test
    public void getBothTest() {
        checkBoth(Constants.ZERO_LONG, Constants.ZERO_LONG);
        checkBoth(Constants.ZERO_LONG, 1L);
        checkBoth((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void packTest() {
        packCheck(Constants.ZERO_LONG, Constants.ZERO_LONG);
        packCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        packCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void orderingTest() {
        final MinMaxPairLongComponent componentOne = prepareComponent(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        final MinMaxPairLongComponent componentTwo = prepareComponent(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        checkBoth(componentOne, componentTwo);
    }

    private @NotNull MinMaxPairLongComponent prepareComponent(@NotNull Long firstValue,
                                                              @NotNull Long secondValue) {
        return new MinMaxPairLongComponent(firstValue, secondValue);
    }

    private @NotNull MinMaxPairLongComponent prepareOneZeroComponent() {
        return prepareComponent(Constants.ZERO_LONG, 1L);
    }

    private void checkBoth(@NotNull Long firstValue,
                           @NotNull Long secondValue) {
        final MinMaxPairLongComponent component = prepareComponent(firstValue, secondValue);
        final List<Long> expected = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        orderedFilling(expected, firstValue, secondValue);
        final List<Long> actual = component.getBoth();
        assertEquals(expected.size(), actual.size(),
                "component values list size shall be 2.");
        for (int i = 0; i < expected.size(); ++i) {
            assertEquals(expected.get(i), actual.get(i),
                    "value " + i + " shall be "
                            + expected.get(i) +
                            " but found " + actual.get(i) + '.');
        }
    }

    private void checkBoth(@NotNull MinMaxPairLongComponent expected,
                           @NotNull MinMaxPairLongComponent actual) {
        final List<Long> expectedData = expected.getBoth();
        final List<Long> actualData = actual.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, expectedData.size(),
                "expected list size shall be 2.");
        assertEquals(expectedData.size(), actualData.size(),
                "components lists sizes shall be equal.");
        for (int i = 0; i < expectedData.size(); ++i) {
            assertEquals(expectedData.get(i), actualData.get(i),
                    "value " + i + " shall be "
                            + expectedData.get(i) +
                            " but found " + actualData.get(i) + '.');
        }
    }

    private void orderedFilling(@NotNull List<Long> list,
                                @NotNull Long first,
                                @NotNull Long second) {
        final Long min = first <= second ? first : second;
        list.add(min);
        if (min.equals(first)) {
            list.add(second);
        } else {
            list.add(first);
        }
    }

    private void packCheck(@NotNull Long firstValue,
                           @NotNull Long secondValue) {
        final MinMaxPairLongComponent component = prepareComponent(firstValue, secondValue);
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairLongComponentModel.class));
        final PairLongComponentModel model = (PairLongComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "model id shall be equal to component id.");
        assertEquals(component.getFirstValue(), model.getFirstValue(),
                "model first value shall be equal to component's first value.");
        assertEquals(component.getSecondValue(), model.getSecondValue(),
                "model second value shall be equal to component second value.");
        final MinMaxPairLongComponent restored = new MinMaxPairLongComponent(model);
        assertEquals(component.getId(), restored.getId(),
                "restored component id shall be equal to original component id.");
        assertEquals(component.getFirstValue(), restored.getFirstValue(),
                "restored component first value " +
                        "shall be equal to original component first value.");
        assertEquals(component.getSecondValue(), restored.getSecondValue(),
                "restored component second value " +
                        "shall be equal to original component second value.");
        checkBoth(component, restored);
    }
}
