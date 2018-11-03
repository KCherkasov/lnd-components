package core.longint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint.ModifiablePairLongComponentImpl;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class ModifiablePairLongComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiablePairLongComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final ModifiablePairLongComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final ModifiablePairLongComponentImpl firstComponent = prepareZeroOneHundredComponent();
        getFirstValueCheck(firstComponent, Constants.ZERO_LONG);
        final ModifiablePairLongComponentImpl secondComponent = prepareOneHundredZeroComponent();
        getFirstValueCheck(secondComponent, (long) Constants.PERCENTAGE_CAP_INT);
        assertNotEquals(firstComponent.getFirstValue(), secondComponent.getFirstValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getSecondValueTest() {
        final ModifiablePairLongComponentImpl firstComponent = prepareZeroOneHundredComponent();
        getSecondValueCheck(firstComponent, (long) Constants.PERCENTAGE_CAP_INT);
        final ModifiablePairLongComponentImpl secondComponent = prepareOneHundredZeroComponent();
        getSecondValueCheck(secondComponent, Constants.ZERO_LONG);
        assertNotEquals(firstComponent.getSecondValue(), secondComponent.getSecondValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getBothTest() {
        final ModifiablePairLongComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final List<Long> storedData = firstComponent.getBoth();
        final List<Long> expectedOne = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        expectedOne.add(firstComponent.getFirstValue());
        expectedOne.add(firstComponent.getSecondValue());
        getBothCheck(firstComponent, expectedOne);
        final ModifiablePairLongComponentImpl secondComponent = prepareOneHundredZeroComponent();
        final List<Long> storedDataTwo = secondComponent.getBoth();
        final List<Long> expectedTwo = new ArrayList<>();
        expectedTwo.add(secondComponent.getFirstValue());
        expectedTwo.add(secondComponent.getSecondValue());
        getBothCheck(secondComponent, expectedTwo);
        assertEquals(storedData.get(0), storedDataTwo.get(1),
                "first element in first component list shall be equal" +
                        " to second element in second component's list");
        assertEquals(storedData.get(1), storedDataTwo.get(0),
                "second element in first component list shall be equal" +
                        " to first element in second component's list");
    }

    @Test
    public void packTest() {
        final ModifiablePairLongComponentImpl component = prepareZeroOneHundredComponent();
        final AbstractComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairLongComponentModel.class));
        final PairLongComponentModel model = (PairLongComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        getFirstValueCheck(component, model.getFirstValue());
        getSecondValueCheck(component, model.getSecondValue());
        final ModifiablePairLongComponentImpl deserializedComponent =
                new ModifiablePairLongComponentImpl(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        getBothCheck(component, deserializedComponent);
    }

    @Test
    public void setBothTest() {
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        setBothCheck(component, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setBothCheck(component, Constants.ZERO_LONG, Constants.ZERO_LONG);
        setBothCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT),
                (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void setFirstTest() {
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        setFirstValueCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
        setFirstValueCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void setSecondTest() {
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        setSecondValueCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
        setSecondValueCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void increaseBothSameTest() {
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        increaseBothCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseBothCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
        increaseBothCheck(component, Constants.ZERO_LONG);
    }

    @Test
    public void increaseBothDifferentTest() {
        final ModifiablePairLongComponentImpl component =
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
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        increaseFirstCheck(component, Constants.ZERO_LONG);
        increaseFirstCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseFirstCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void increaseSecondValueTest() {
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        increaseSecondCheck(component, Constants.ZERO_LONG);
        increaseSecondCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseSecondCheck(component, (long) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseBothSameTest() {
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        decreaseBothCheck(component, Constants.ZERO_LONG);
        decreaseBothCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        decreaseBothCheck(component, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBothDifferentTest() {
        final ModifiablePairLongComponentImpl component =
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
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        decreaseFirstCheck(component, Constants.ZERO_LONG);
        decreaseFirstCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        decreaseFirstCheck(component, (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseSecondValueTest() {
        final ModifiablePairLongComponentImpl component =
                prepareComponent(Constants.ZERO_LONG, Constants.ZERO_LONG);
        decreaseSecondCheck(component, Constants.ZERO_LONG);
        decreaseSecondCheck(component, (long) Constants.PERCENTAGE_CAP_INT);
        decreaseSecondCheck(component, (long) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void percentageModificationBothSameTest() {
        final ModifiablePairLongComponentImpl component =
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
        final ModifiablePairLongComponentImpl component =
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
        final ModifiablePairLongComponentImpl component =
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
        final ModifiablePairLongComponentImpl component =
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

    private @NotNull ModifiablePairLongComponentImpl prepareComponent(@NotNull Long firstValue,
                                                                      @NotNull Long secondValue) {
        return new ModifiablePairLongComponentImpl(firstValue, secondValue);
    }

    private @NotNull ModifiablePairLongComponentImpl prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiablePairLongComponentImpl prepareOneHundredZeroComponent() {
        return prepareComponent((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
    }

    private void getFirstValueCheck(@NotNull ModifiablePairLongComponentImpl component,
                                    @NotNull Long expected) {
        assertEquals(expected, component.getFirstValue(),
                "component first value shall be " + expected
                        + " but found " + component.getFirstValue() + '.');
    }

    private void getSecondValueCheck(@NotNull ModifiablePairLongComponentImpl component,
                                     @NotNull Long expected) {
        assertEquals(expected, component.getSecondValue(),
                "component first value shall be " + expected
                        + " but found " + component.getSecondValue() + '.');
    }

    private void getBothCheck(@NotNull ModifiablePairLongComponentImpl component,
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

    private void getBothCheck(@NotNull ModifiablePairLongComponentImpl first,
                              @NotNull ModifiablePairLongComponentImpl second) {
        final List<Long> firstData = first.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, firstData.size(), "" +
                "first component's list size shall be 2.");
        final List<Long> secondData = second.getBoth();
        assertEquals(firstData.size(), secondData.size(),
                "components lists sizes shall be equal.");
        for (int i = Constants.ZERO_INT; i < firstData.size(); ++i) {
            assertEquals(firstData.get(i), secondData.get(i),
                    "element " + i + " shall be "
                            + firstData.get(i) + " but found "
                            + secondData.get(i) + '.');
        }
    }

    private void setBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                              @NotNull Long firstValue,
                              @NotNull Long secondValue) {
        component.setBoth(firstValue, secondValue);
        getFirstValueCheck(component, firstValue);
        getSecondValueCheck(component, secondValue);
    }

    private void setFirstValueCheck(@NotNull ModifiablePairLongComponentImpl component,
                                    @NotNull Long value) {
        component.setFirstValue(value);
        getFirstValueCheck(component, value);
    }

    private void setSecondValueCheck(@NotNull ModifiablePairLongComponentImpl component,
                                     @NotNull Long value) {
        component.setSecondValue(value);
        getSecondValueCheck(component, value);
    }

    private void increaseBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                                   @NotNull Long amount) {
        final Long expectedFirst = component.getFirstValue() + amount;
        final Long expectedSecond = component.getSecondValue() + amount;
        component.increaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                                   @NotNull Long firstIncrease,
                                   @NotNull Long secondIncrease) {
        final Long expectedFirst = component.getFirstValue() + firstIncrease;
        final Long expectedSecond = component.getSecondValue() + secondIncrease;
        component.increaseBoth(firstIncrease, secondIncrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseFirstCheck(@NotNull ModifiablePairLongComponentImpl component,
                                    @NotNull Long amount) {
        final Long expected = component.getFirstValue() + amount;
        component.increaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void increaseSecondCheck(@NotNull ModifiablePairLongComponentImpl component,
                                     @NotNull Long amount) {
        final Long expected = component.getSecondValue() + amount;
        component.increaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void decreaseBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                                   @NotNull Long amount) {
        final Long expectedFirst = component.getFirstValue() - amount;
        final Long expectedSecond = component.getSecondValue() - amount;
        component.decreaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                                   @NotNull Long firstDecrease,
                                   @NotNull Long secondDecrease) {
        final Long expectedFirst = component.getFirstValue() - firstDecrease;
        final Long expectedSecond = component.getSecondValue() - secondDecrease;
        component.decreaseBoth(firstDecrease, secondDecrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseFirstCheck(@NotNull ModifiablePairLongComponentImpl component,
                                    @NotNull Long amount) {
        final Long expected = component.getFirstValue() - amount;
        component.decreaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void decreaseSecondCheck(@NotNull ModifiablePairLongComponentImpl component,
                                     @NotNull Long amount) {
        final Long expected = component.getSecondValue() - amount;
        component.decreaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                                                 @NotNull Integer percent) {
        final Long expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        final Long expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                                                 @NotNull Float percent) {
        final Long expectedFirst = calculateExpectedValue(
                component.getFirstValue(), percent);
        final Long expectedSecond = calculateExpectedValue(
                component.getSecondValue(), percent);
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                                                 @NotNull Integer firstPercent,
                                                 @NotNull Integer secondPercent) {
        final Long expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(firstPercent));
        final Long expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(secondPercent));
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairLongComponentImpl component,
                                                 @NotNull Float firstPercent,
                                                 @NotNull Float secondPercent) {
        final Long expectedFirst = calculateExpectedValue(
                component.getFirstValue(), firstPercent);
        final Long expectedSecond = calculateExpectedValue(
                component.getSecondValue(), secondPercent);
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiablePairLongComponentImpl component,
                                                  @NotNull Integer percent) {
        final Long expected = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiablePairLongComponentImpl component,
                                                  @NotNull Float percent) {
        final Long expected = calculateExpectedValue(
                component.getFirstValue(), percent);
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiablePairLongComponentImpl component,
                                                   @NotNull Integer percent) {
        final Long expected = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiablePairLongComponentImpl component,
                                                   @NotNull Float percent) {
        final Long expected = calculateExpectedValue(
                component.getSecondValue(), percent);
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
