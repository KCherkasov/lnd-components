package core.integer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer.ModifiablePairIntComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class ModifiablePairIntComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiablePairIntComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final ModifiablePairIntComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final ModifiablePairIntComponentImpl firstComponent = prepareZeroOneHundredComponent();
        getFirstValueCheck(firstComponent, Constants.ZERO_INT);
        final ModifiablePairIntComponentImpl secondComponent = prepareOneHundredZeroComponent();
        getFirstValueCheck(secondComponent, Constants.PERCENTAGE_CAP_INT);
        assertNotEquals(firstComponent.getFirstValue(), secondComponent.getFirstValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getSecondValueTest() {
        final ModifiablePairIntComponentImpl firstComponent = prepareZeroOneHundredComponent();
        getSecondValueCheck(firstComponent, Constants.PERCENTAGE_CAP_INT);
        final ModifiablePairIntComponentImpl secondComponent = prepareOneHundredZeroComponent();
        getSecondValueCheck(secondComponent, Constants.ZERO_INT);
        assertNotEquals(firstComponent.getSecondValue(), secondComponent.getSecondValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getBothTest() {
        final ModifiablePairIntComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final List<Integer> storedData = firstComponent.getBoth();
        final List<Integer> expectedOne = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        expectedOne.add(firstComponent.getFirstValue());
        expectedOne.add(firstComponent.getSecondValue());
        getBothCheck(firstComponent, expectedOne);
        final ModifiablePairIntComponentImpl secondComponent = prepareOneHundredZeroComponent();
        final List<Integer> storedDataTwo = secondComponent.getBoth();
        final List<Integer> expectedTwo = new ArrayList<>();
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
        final ModifiablePairIntComponentImpl component = prepareZeroOneHundredComponent();
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairIntComponentModel.class));
        final PairIntComponentModel model = (PairIntComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        getFirstValueCheck(component, model.getFirstValue());
        getSecondValueCheck(component, model.getSecondValue());
        final ModifiablePairIntComponentImpl deserializedComponent =
                new ModifiablePairIntComponentImpl(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        getBothCheck(component, deserializedComponent);
    }

    @Test
    public void setBothTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        setBothCheck(component, Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        setBothCheck(component, Constants.ZERO_INT, Constants.ZERO_INT);
        setBothCheck(component, -1 * Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void setFirstTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        setFirstValueCheck(component, Constants.PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void setSecondTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        setSecondValueCheck(component, Constants.PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void increaseBothSameTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        increaseBothCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseBothCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
        increaseBothCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void increaseBothDifferentTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        increaseBothCheck(component, Constants.ZERO_INT, Constants.ZERO_INT);
        increaseBothCheck(component, Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        increaseBothCheck(component,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseFirstValueTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        increaseFirstCheck(component, Constants.ZERO_INT);
        increaseFirstCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseFirstCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseSecondValueTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        increaseSecondCheck(component, Constants.ZERO_INT);
        increaseSecondCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBothSameTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseBothCheck(component, Constants.ZERO_INT);
        decreaseBothCheck(component, Constants.PERCENTAGE_CAP_INT);
        decreaseBothCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBothDifferentTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseBothCheck(component, Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseBothCheck(component, Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseBothCheck(component,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseFirstValueTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseFirstCheck(component, Constants.ZERO_INT);
        decreaseFirstCheck(component, Constants.PERCENTAGE_CAP_INT);
        decreaseFirstCheck(component, -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseSecondValueTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseSecondCheck(component, Constants.ZERO_INT);
        decreaseSecondCheck(component, Constants.PERCENTAGE_CAP_INT);
        decreaseSecondCheck(component, -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationBothSameTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.PERCENTAGE_CAP_INT,
                        Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_FLOAT);
        percentageModificationBothCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationBothCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setBoth(Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_INT);
        percentageModificationBothCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationBothCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationBothDifferentTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.PERCENTAGE_CAP_INT,
                        Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        percentageModificationBothCheck(component,
                Constants.PERCENTAGE_CAP_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationBothCheck(component,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setBoth(Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);

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
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.PERCENTAGE_CAP_INT,
                        Constants.PERCENTAGE_CAP_INT);
        percentageModificationFirstCheck(component, Constants.ZERO_FLOAT);
        percentageModificationFirstCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationFirstCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setFirstValue(Constants.PERCENTAGE_CAP_INT);

        percentageModificationFirstCheck(component, Constants.ZERO_INT);
        percentageModificationFirstCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationFirstCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationSecondTest() {
        final ModifiablePairIntComponentImpl component =
                prepareComponent(Constants.PERCENTAGE_CAP_INT,
                        Constants.PERCENTAGE_CAP_INT);
        percentageModificationSecondCheck(component, Constants.ZERO_FLOAT);
        percentageModificationSecondCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setSecondValue(Constants.PERCENTAGE_CAP_INT);

        percentageModificationSecondCheck(component, Constants.ZERO_INT);
        percentageModificationSecondCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiablePairIntComponentImpl prepareComponent(@NotNull Integer firstValue,
                                                                     @NotNull Integer secondValue) {
        return new ModifiablePairIntComponentImpl(firstValue, secondValue);
    }

    private @NotNull ModifiablePairIntComponentImpl prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiablePairIntComponentImpl prepareOneHundredZeroComponent() {
        return prepareComponent(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
    }

    private void getFirstValueCheck(@NotNull ModifiablePairIntComponentImpl component,
                                    @NotNull Integer expected) {
        assertEquals(expected, component.getFirstValue(),
                "component first value shall be " + expected
                        + " but found " + component.getFirstValue() + '.');
    }

    private void getSecondValueCheck(@NotNull ModifiablePairIntComponentImpl component,
                                     @NotNull Integer expected) {
        assertEquals(expected, component.getSecondValue(),
                "component first value shall be " + expected
                        + " but found " + component.getSecondValue() + '.');
    }

    private void getBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                              @NotNull List<Integer> expected) {
        final List<Integer> actual = component.getBoth();
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

    private void getBothCheck(@NotNull ModifiablePairIntComponentImpl first,
                              @NotNull ModifiablePairIntComponentImpl second) {
        final List<Integer> firstData = first.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, firstData.size(), "" +
                "first component's list size shall be 2.");
        final List<Integer> secondData = second.getBoth();
        assertEquals(firstData.size(), secondData.size(),
                "components lists sizes shall be equal.");
        for (int i = Constants.ZERO_INT; i < firstData.size(); ++i) {
            assertEquals(firstData.get(i), secondData.get(i),
                    "element " + i + " shall be "
                            + firstData.get(i) + " but found "
                            + secondData.get(i) + '.');
        }
    }

    private void setBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                              @NotNull Integer firstValue,
                              @NotNull Integer secondValue) {
        component.setBoth(firstValue, secondValue);
        getFirstValueCheck(component, firstValue);
        getSecondValueCheck(component, secondValue);
    }

    private void setFirstValueCheck(@NotNull ModifiablePairIntComponentImpl component,
                                    @NotNull Integer value) {
        component.setFirstValue(value);
        getFirstValueCheck(component, value);
    }

    private void setSecondValueCheck(@NotNull ModifiablePairIntComponentImpl component,
                                     @NotNull Integer value) {
        component.setSecondValue(value);
        getSecondValueCheck(component, value);
    }

    private void increaseBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                                   @NotNull Integer amount) {
        final Integer expectedFirst = component.getFirstValue() + amount;
        final Integer expectedSecond = component.getSecondValue() + amount;
        component.increaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                                   @NotNull Integer firstIncrease,
                                   @NotNull Integer secondIncrease) {
        final Integer expectedFirst = component.getFirstValue() + firstIncrease;
        final Integer expectedSecond = component.getSecondValue() + secondIncrease;
        component.increaseBoth(firstIncrease, secondIncrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseFirstCheck(@NotNull ModifiablePairIntComponentImpl component,
                                    @NotNull Integer amount) {
        final Integer expected = component.getFirstValue() + amount;
        component.increaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void increaseSecondCheck(@NotNull ModifiablePairIntComponentImpl component,
                                     @NotNull Integer amount) {
        final Integer expected = component.getSecondValue() + amount;
        component.increaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void decreaseBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                                   @NotNull Integer amount) {
        final Integer expectedFirst = component.getFirstValue() - amount;
        final Integer expectedSecond = component.getSecondValue() - amount;
        component.decreaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                                   @NotNull Integer firstDecrease,
                                   @NotNull Integer secondDecrease) {
        final Integer expectedFirst = component.getFirstValue() - firstDecrease;
        final Integer expectedSecond = component.getSecondValue() - secondDecrease;
        component.decreaseBoth(firstDecrease, secondDecrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseFirstCheck(@NotNull ModifiablePairIntComponentImpl component,
                                    @NotNull Integer amount) {
        final Integer expected = component.getFirstValue() - amount;
        component.decreaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void decreaseSecondCheck(@NotNull ModifiablePairIntComponentImpl component,
                                     @NotNull Integer amount) {
        final Integer expected = component.getSecondValue() - amount;
        component.decreaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                                                 @NotNull Integer percent) {
        final Integer expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        final Integer expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                                                 @NotNull Float percent) {
        final Integer expectedFirst = calculateExpectedValue(
                component.getFirstValue(), percent);
        final Integer expectedSecond = calculateExpectedValue(
                component.getSecondValue(), percent);
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                                                 @NotNull Integer firstPercent,
                                                 @NotNull Integer secondPercent) {
        final Integer expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(firstPercent));
        final Integer expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(secondPercent));
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairIntComponentImpl component,
                                                 @NotNull Float firstPercent,
                                                 @NotNull Float secondPercent) {
        final Integer expectedFirst = calculateExpectedValue(
                component.getFirstValue(), firstPercent);
        final Integer expectedSecond = calculateExpectedValue(
                component.getSecondValue(), secondPercent);
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiablePairIntComponentImpl component,
                                                  @NotNull Integer percent) {
        final Integer expected = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiablePairIntComponentImpl component,
                                                  @NotNull Float percent) {
        final Integer expected = calculateExpectedValue(
                component.getFirstValue(), percent);
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiablePairIntComponentImpl component,
                                                   @NotNull Integer percent) {
        final Integer expected = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiablePairIntComponentImpl component,
                                                   @NotNull Float percent) {
        final Integer expected = calculateExpectedValue(
                component.getSecondValue(), percent);
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    private @NotNull Float toPercent(@NotNull Integer percent) {
        return FloatMath.divide(percent.floatValue(), (float) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull Integer calculateExpectedValue(@NotNull Integer initial,
                                                    @NotNull Float percentage) {
        return FloatMath.multiply(initial.floatValue(),
                Constants.PERCENTAGE_CAP_FLOAT + percentage).intValue();
    }
}
