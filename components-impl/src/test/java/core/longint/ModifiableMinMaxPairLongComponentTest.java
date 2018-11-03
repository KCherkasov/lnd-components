package core.longint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint.ModifiableMinMaxPairLongComponent;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ModifiableMinMaxPairLongComponentTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableMinMaxPairLongComponent firstComponent = prepareZeroOneHundredComponent();
        final ModifiableMinMaxPairLongComponent secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final ModifiableMinMaxPairLongComponent firstComponent =
                prepareZeroOneHundredComponent();
        getFirstValueCheck(firstComponent, Constants.ZERO_LONG);
        final ModifiableMinMaxPairLongComponent secondComponent =
                prepareOneHundredZeroComponent();
        getFirstValueCheck(secondComponent, firstComponent.getFirstValue());
    }

    @Test
    public void getSecondValueTest() {
        final ModifiableMinMaxPairLongComponent firstComponent = prepareZeroOneHundredComponent();
        getSecondValueCheck(firstComponent, (long) Constants.PERCENTAGE_CAP_INT);
        final ModifiableMinMaxPairLongComponent secondComponent = prepareOneHundredZeroComponent();
        getSecondValueCheck(secondComponent, firstComponent.getSecondValue());
    }

    @Test
    public void getBothTest() {
        final ModifiableMinMaxPairLongComponent firstComponent =
                prepareZeroOneHundredComponent();
        final List<Long> expectedOne = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        expectedOne.add(firstComponent.getFirstValue());
        expectedOne.add(firstComponent.getSecondValue());
        getBothCheck(firstComponent, expectedOne);
        final ModifiableMinMaxPairLongComponent secondComponent =
                prepareOneHundredZeroComponent();
        final List<Long> expectedTwo = new ArrayList<>();
        expectedTwo.add(secondComponent.getFirstValue());
        expectedTwo.add(secondComponent.getSecondValue());
        getBothCheck(secondComponent, expectedTwo);
    }

    @Test
    public void packTest() {
        final ModifiableMinMaxPairLongComponent component = prepareZeroOneHundredComponent();
        final AbstractComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairLongComponentModel.class));
        final PairLongComponentModel model = (PairLongComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        getFirstValueCheck(component, model.getFirstValue());
        getSecondValueCheck(component, model.getSecondValue());
        final ModifiableMinMaxPairLongComponent deserializedComponent =
                new ModifiableMinMaxPairLongComponent(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        getBothCheck(component, deserializedComponent);
    }

    @Test
    public void setBothTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        setBothCheck(component, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setBothCheck(component, Constants.ZERO_LONG, Constants.ZERO_LONG);
        setBothCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT),
                (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void setFirstTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        setFirstValueCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
        setFirstValueCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void setSecondTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        setSecondValueCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
        setSecondValueCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void increaseBothSameTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        increaseBothCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseBothCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
        increaseBothCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void increaseBothDifferentTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        increaseBothCheck(component, Constants.ZERO_LONG, Constants.ZERO_LONG);
        increaseBothCheck(component, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        increaseBothCheck(component,
                (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT),
                (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void increaseFirstValueTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        increaseFirstCheck(component, Constants.ZERO_LONG);
        increaseFirstCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseFirstCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void increaseSecondValueTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        increaseSecondCheck(component, Constants.ZERO_LONG);
        increaseSecondCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseSecondCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseBothSameTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        decreaseBothCheck(component, Constants.ZERO_LONG);
        decreaseBothCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        decreaseBothCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBothDifferentTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        decreaseBothCheck(component, Constants.ZERO_LONG, Constants.ZERO_LONG);
        decreaseBothCheck(component, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseBothCheck(component,
                (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT),
                (long) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseFirstValueTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        decreaseFirstCheck(component, Constants.ZERO_LONG);
        decreaseFirstCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        decreaseFirstCheck(component, (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseSecondValueTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        decreaseSecondCheck(component, Constants.ZERO_LONG);
        decreaseSecondCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        decreaseSecondCheck(component, (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void percentageModificationBothSameTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent((long) Constants.PERCENTAGE_CAP_INT,
                        (long) Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_FLOAT);
        percentageModificationBothCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationBothCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setBoth((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_INT);
        percentageModificationBothCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationBothCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationBothDifferentTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent((long) Constants.PERCENTAGE_CAP_INT,
                        (long) Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        percentageModificationBothCheck(component,
                Constants.PERCENTAGE_CAP_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationBothCheck(component,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setBoth((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_INT, Constants.ZERO_INT);
        percentageModificationBothCheck(component,
                Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentageModificationBothCheck(component,
                -1 * Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationFirstTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent((long) Constants.PERCENTAGE_CAP_INT,
                        (long) Constants.PERCENTAGE_CAP_INT);
        percentageModificationFirstCheck(component, Constants.ZERO_FLOAT);
        percentageModificationFirstCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationFirstCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setFirstValue((long) Constants.PERCENTAGE_CAP_INT);

        percentageModificationFirstCheck(component, Constants.ZERO_INT);
        percentageModificationFirstCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationFirstCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationSecondTest() {
        final ModifiableMinMaxPairLongComponent component =
                prepareComponent((long) Constants.PERCENTAGE_CAP_INT,
                        (long) Constants.PERCENTAGE_CAP_INT);
        percentageModificationSecondCheck(component, Constants.ZERO_FLOAT);
        percentageModificationSecondCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setSecondValue((long) Constants.PERCENTAGE_CAP_INT);

        percentageModificationSecondCheck(component, Constants.ZERO_INT);
        percentageModificationSecondCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiableMinMaxPairLongComponent prepareComponent(@NotNull Long firstValue,
                                                                        @NotNull Long secondValue) {
        return new ModifiableMinMaxPairLongComponent(firstValue, secondValue);
    }

    private @NotNull ModifiableMinMaxPairLongComponent prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiableMinMaxPairLongComponent prepareOneHundredZeroComponent() {
        return prepareComponent((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
    }

    private void getFirstValueCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                    @NotNull Long expected) {
        assertEquals(expected, component.getFirstValue(),
                "component first value shall be " + expected
                        + " but found " + component.getFirstValue() + '.');
    }

    private void getSecondValueCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                     @NotNull Long expected) {
        assertEquals(expected, component.getSecondValue(),
                "component first value shall be " + expected
                        + " but found " + component.getSecondValue() + '.');
    }

    private void getBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                              @NotNull List<Long> expected) {
        final List<Long> actual = component.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, actual.size(),
                "pair component list size shall be 2.");
        assertEquals(expected.size(), actual.size(),
                "component list size shall be equal to expected list size.");
        for (int i = Constants.ZERO_INT; i < expected.size(); ++i) {
            assertEquals(expected.get(i), actual.get(i),
                    "element " + i + " shall be "
                            + expected.get(i) + " but found "
                            + actual.get(i) + '.');
        }
    }

    private void getBothCheck(@NotNull ModifiableMinMaxPairLongComponent first,
                              @NotNull ModifiableMinMaxPairLongComponent second) {
        final List<Long> firstData = first.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, firstData.size(), "" +
                "first component's list size shall be 2.");
        final List<Long> secondData = second.getBoth();
        assertEquals(firstData.size(), secondData.size(),
                "lists sizes shall be equal.");
        for (int i = Constants.ZERO_INT; i < firstData.size(); ++i) {
            assertEquals(firstData.get(i), secondData.get(i),
                    "element " + i + " shall be "
                            + firstData.get(i) + " but found "
                            + secondData.get(i) + '.');
        }
    }

    private void setBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                              @NotNull Long firstValue,
                              @NotNull Long secondValue) {
        component.setBoth(firstValue, secondValue);
        if (firstValue > secondValue) {
            getFirstValueCheck(component, secondValue);
            getSecondValueCheck(component, firstValue);
        } else {
            getFirstValueCheck(component, firstValue);
            getSecondValueCheck(component, secondValue);
        }
    }

    private void setFirstValueCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                    @NotNull Long value) {
        final Long second = component.getSecondValue();
        component.setFirstValue(value);
        if (value > second) {
            getFirstValueCheck(component, second);
        } else {
            getFirstValueCheck(component, value);
        }
    }

    private void setSecondValueCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                     @NotNull Long value) {
        final Long first = component.getFirstValue();
        component.setSecondValue(value);
        if (value < first) {
            getSecondValueCheck(component, first);
        } else {
            getSecondValueCheck(component, value);
        }
    }

    private void increaseBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                   @NotNull Long amount) {
        Long expectedFirst = component.getFirstValue() + amount;
        Long expectedSecond = component.getSecondValue() + amount;
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.increaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                   @NotNull Long firstIncrease,
                                   @NotNull Long secondIncrease) {
        Long expectedFirst = component.getFirstValue() + firstIncrease;
        Long expectedSecond = component.getSecondValue() + secondIncrease;
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.increaseBoth(firstIncrease, secondIncrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseFirstCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                    @NotNull Long amount) {
        Long expected = component.getFirstValue() + amount;
        if (expected > component.getSecondValue()) {
            expected = component.getSecondValue();
        }
        component.increaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void increaseSecondCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                     @NotNull Long amount) {
        Long expected = component.getSecondValue() + amount;
        if (expected < component.getFirstValue()) {
            expected = component.getFirstValue();
        }
        component.increaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void decreaseBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                   @NotNull Long amount) {
        Long expectedFirst = component.getFirstValue() - amount;
        Long expectedSecond = component.getSecondValue() - amount;
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.decreaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                   @NotNull Long firstDecrease,
                                   @NotNull Long secondDecrease) {
        Long expectedFirst = component.getFirstValue() - firstDecrease;
        Long expectedSecond = component.getSecondValue() - secondDecrease;
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.decreaseBoth(firstDecrease, secondDecrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseFirstCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                    @NotNull Long amount) {
        Long expected = component.getFirstValue() - amount;
        if (expected > component.getSecondValue()) {
            expected = component.getSecondValue();
        }
        component.decreaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void decreaseSecondCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                     @NotNull Long amount) {
        Long expected = component.getSecondValue() - amount;
        if (expected < component.getFirstValue()) {
            expected = component.getFirstValue();
        }
        component.decreaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                                 @NotNull Integer percent) {
        Long expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        Long expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                                 @NotNull Float percent) {
        Long expectedFirst = calculateExpectedValue(
                component.getFirstValue(), percent);
        Long expectedSecond = calculateExpectedValue(
                component.getSecondValue(), percent);
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                                 @NotNull Integer firstPercent,
                                                 @NotNull Integer secondPercent) {
        Long expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(firstPercent));
        Long expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(secondPercent));
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                                 @NotNull Float firstPercent,
                                                 @NotNull Float secondPercent) {
        Long expectedFirst = calculateExpectedValue(
                component.getFirstValue(), firstPercent);
        Long expectedSecond = calculateExpectedValue(
                component.getSecondValue(), secondPercent);
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                                  @NotNull Integer percent) {
        Long expected = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        if (expected > component.getSecondValue()) {
            expected = component.getSecondValue();
        }
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                                  @NotNull Float percent) {
        Long expected = calculateExpectedValue(
                component.getFirstValue(), percent);
        if (expected > component.getSecondValue()) {
            expected = component.getSecondValue();
        }
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                                   @NotNull Integer percent) {
        Long expected = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        if (expected < component.getFirstValue()) {
            expected = component.getFirstValue();
        }
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiableMinMaxPairLongComponent component,
                                                   @NotNull Float percent) {
        Long expected = calculateExpectedValue(
                component.getSecondValue(), percent);
        if (expected < component.getFirstValue()) {
            expected = component.getFirstValue();
        }
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    private @NotNull Float toPercent(@NotNull Integer percent) {
        return FloatMath.divide(percent.floatValue(), (float) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull Long calculateExpectedValue(@NotNull Long initial,
                                                 @NotNull Float percentage) {
        return FloatMath.multiply(initial.floatValue(),
                Constants.PERCENTAGE_CAP_FLOAT + percentage).longValue();
    }
}
