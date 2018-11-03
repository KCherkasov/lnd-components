package core.integer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.integer.MinMaxPairIntComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class MinMaxPairIntComponentTest {
    @Test
    public void idGeneratorTest() {
        final MinMaxPairIntComponent componentOne = prepareOneZeroComponent();
        final MinMaxPairIntComponent componentTwo = prepareOneZeroComponent();
        assertEquals(1, componentTwo.getId() - componentOne.getId(),
                "difference between adjacent component ids shall be one");
    }

    @Test
    public void getFirstValueTest() {
        final MinMaxPairIntComponent componentOne = prepareComponent(
                Constants.ZERO_INT, Constants.ZERO_INT);
        assertEquals(Constants.ZERO_INT, componentOne.getFirstValue().intValue(),
                "first value must be zero");
        final MinMaxPairIntComponent componentTwo = prepareOneZeroComponent();
        assertEquals(componentOne.getFirstValue(), componentTwo.getFirstValue(),
                "first values shall be equal.");
        final MinMaxPairIntComponent componentThree = prepareComponent(
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        assertEquals(Constants.PERCENTAGE_CAP_INT, componentThree.getFirstValue().intValue(),
                "first value shall be 100.");
    }

    @Test
    public void getSecondValueTest() {
        final MinMaxPairIntComponent componentOne = prepareComponent(
                Constants.ZERO_INT, Constants.ZERO_INT);
        assertEquals(Constants.ZERO_INT, componentOne.getSecondValue().intValue(),
                "first value must be zero");
        final MinMaxPairIntComponent componentTwo = prepareOneZeroComponent();
        assertNotEquals(componentOne.getSecondValue(), componentTwo.getSecondValue(),
                "second values shall be equal.");
        final MinMaxPairIntComponent componentThree = prepareComponent(
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        assertEquals(Constants.WIDE_PERCENTAGE_CAP_INT, componentThree.getSecondValue().intValue(),
                "second value shall be 10000.");
    }

    @Test
    public void getBothTest() {
        checkBoth(Constants.ZERO_INT, Constants.ZERO_INT);
        checkBoth(Constants.ZERO_INT, 1);
        checkBoth(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void packTest() {
        packCheck(Constants.ZERO_INT, Constants.ZERO_INT);
        packCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        packCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void orderingTest() {
        final MinMaxPairIntComponent componentOne = prepareComponent(
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        final MinMaxPairIntComponent componentTwo = prepareComponent(
                Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        checkBoth(componentOne, componentTwo);
    }

    private @NotNull MinMaxPairIntComponent prepareComponent(@NotNull Integer firstValue,
                                                             @NotNull Integer secondValue) {
        return new MinMaxPairIntComponent(firstValue, secondValue);
    }

    private @NotNull MinMaxPairIntComponent prepareOneZeroComponent() {
        return prepareComponent(Constants.ZERO_INT, 1);
    }

    private void checkBoth(@NotNull Integer firstValue,
                           @NotNull Integer secondValue) {
        final MinMaxPairIntComponent component = prepareComponent(firstValue, secondValue);
        final List<Integer> expected = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        orderedFilling(expected, firstValue, secondValue);
        final List<Integer> actual = component.getBoth();
        assertEquals(expected.size(), actual.size(),
                "component values list size shall be 2.");
        for (int i = 0; i < expected.size(); ++i) {
            assertEquals(expected.get(i), actual.get(i),
                    "value " + i + " shall be "
                            + expected.get(i) +
                            " but found " + actual.get(i) + '.');
        }
    }

    private void checkBoth(@NotNull MinMaxPairIntComponent expected,
                           @NotNull MinMaxPairIntComponent actual) {
        final List<Integer> expectedData = expected.getBoth();
        final List<Integer> actualData = actual.getBoth();
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

    private void orderedFilling(@NotNull List<Integer> list,
                                @NotNull Integer first,
                                @NotNull Integer second) {
        final Integer min = first <= second ? first : second;
        list.add(min);
        if (min.equals(first)) {
            list.add(second);
        } else {
            list.add(first);
        }
    }

    private void packCheck(@NotNull Integer firstValue,
                           @NotNull Integer secondValue) {
        final MinMaxPairIntComponent component = prepareComponent(firstValue, secondValue);
        final AbstractComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairIntComponentModel.class));
        final PairIntComponentModel model = (PairIntComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "model id shall be equal to component id.");
        assertEquals(component.getFirstValue(), model.getFirstValue(),
                "model first value shall be equal to component's first value.");
        assertEquals(component.getSecondValue(), model.getSecondValue(),
                "model second value shall be equal to component second value.");
        final MinMaxPairIntComponent restored = new MinMaxPairIntComponent(model);
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
