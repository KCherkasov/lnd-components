package core.integer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer.ModifiableMinMaxPairIntComponent;
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

public final class ModifiableMinMaxPairIntComponentTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableMinMaxPairIntComponent firstComponent = prepareZeroOneHundredComponent();
        final ModifiableMinMaxPairIntComponent secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final ModifiableMinMaxPairIntComponent firstComponent = prepareZeroOneHundredComponent();
        getFirstValueCheck(firstComponent, Constants.ZERO_INT);
        final ModifiableMinMaxPairIntComponent secondComponent = prepareOneHundredZeroComponent();
        getFirstValueCheck(secondComponent, firstComponent.getFirstValue());
    }

    @Test
    public void getSecondValueTest() {
        final ModifiableMinMaxPairIntComponent firstComponent = prepareZeroOneHundredComponent();
        getSecondValueCheck(firstComponent, Constants.PERCENTAGE_CAP_INT);
        final ModifiableMinMaxPairIntComponent secondComponent = prepareOneHundredZeroComponent();
        getSecondValueCheck(secondComponent, firstComponent.getSecondValue());
    }

    @Test
    public void getBothTest() {
        final ModifiableMinMaxPairIntComponent firstComponent = prepareZeroOneHundredComponent();
        final List<Integer> storedData = firstComponent.getBoth();
        final List<Integer> expectedOne = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        expectedOne.add(firstComponent.getFirstValue());
        expectedOne.add(firstComponent.getSecondValue());
        getBothCheck(firstComponent, expectedOne);
        final ModifiableMinMaxPairIntComponent secondComponent = prepareOneHundredZeroComponent();
        final List<Integer> storedDataTwo = secondComponent.getBoth();
        final List<Integer> expectedTwo = new ArrayList<>();
        expectedTwo.add(secondComponent.getFirstValue());
        expectedTwo.add(secondComponent.getSecondValue());
        getBothCheck(secondComponent, expectedTwo);
    }

    @Test
    public void packTest() {
        final ModifiableMinMaxPairIntComponent component = prepareZeroOneHundredComponent();
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairIntComponentModel.class));
        final PairIntComponentModel model = (PairIntComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        getFirstValueCheck(component, model.getFirstValue());
        getSecondValueCheck(component, model.getSecondValue());
        final ModifiableMinMaxPairIntComponent deserializedComponent =
                new ModifiableMinMaxPairIntComponent(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        getBothCheck(component, deserializedComponent);
    }

    @Test
    public void setBothTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        setBothCheck(component, Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        setBothCheck(component, Constants.ZERO_INT, Constants.ZERO_INT);
        setBothCheck(component, -1 * Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void setFirstTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        setFirstValueCheck(component, Constants.PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void setSecondTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        setSecondValueCheck(component, Constants.PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void increaseBothSameTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        increaseBothCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseBothCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
        increaseBothCheck(component, Constants.ZERO_INT);
    }

    @Test
    public void increaseBothDifferentTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        increaseBothCheck(component, Constants.ZERO_INT, Constants.ZERO_INT);
        increaseBothCheck(component, Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        increaseBothCheck(component,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseFirstValueTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        increaseFirstCheck(component, Constants.ZERO_INT);
        increaseFirstCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseFirstCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseSecondValueTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        increaseSecondCheck(component, Constants.ZERO_INT);
        increaseSecondCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBothSameTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseBothCheck(component, Constants.ZERO_INT);
        decreaseBothCheck(component, Constants.PERCENTAGE_CAP_INT);
        decreaseBothCheck(component, Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBothDifferentTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseBothCheck(component, Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseBothCheck(component, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseBothCheck(component,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseFirstValueTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseFirstCheck(component, Constants.ZERO_INT);
        decreaseFirstCheck(component, Constants.PERCENTAGE_CAP_INT);
        decreaseFirstCheck(component, -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseSecondValueTest() {
        final ModifiableMinMaxPairIntComponent component =
                prepareComponent(Constants.ZERO_INT, Constants.ZERO_INT);
        decreaseSecondCheck(component, Constants.ZERO_INT);
        decreaseSecondCheck(component, Constants.PERCENTAGE_CAP_INT);
        decreaseSecondCheck(component, -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationBothSameTest() {
        final ModifiableMinMaxPairIntComponent component =
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
        final ModifiableMinMaxPairIntComponent component =
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
        final ModifiableMinMaxPairIntComponent component =
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
        final ModifiableMinMaxPairIntComponent component =
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

    private @NotNull ModifiableMinMaxPairIntComponent prepareComponent(@NotNull Integer firstValue,
                                                                       @NotNull Integer secondValue) {
        return new ModifiableMinMaxPairIntComponent(firstValue, secondValue);
    }

    private @NotNull ModifiableMinMaxPairIntComponent prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiableMinMaxPairIntComponent prepareOneHundredZeroComponent() {
        return prepareComponent(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
    }

    private void getFirstValueCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                    @NotNull Integer expected) {
        assertEquals(expected, component.getFirstValue(),
                "component first value shall be " + expected
                        + " but found " + component.getFirstValue() + '.');
    }

    private void getSecondValueCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                     @NotNull Integer expected) {
        assertEquals(expected, component.getSecondValue(),
                "component first value shall be " + expected
                        + " but found " + component.getSecondValue() + '.');
    }

    private void getBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
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

    private void getBothCheck(@NotNull ModifiableMinMaxPairIntComponent first,
                              @NotNull ModifiableMinMaxPairIntComponent second) {
        final List<Integer> firstData = first.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, firstData.size(), "" +
                "first component's list size shall be 2.");
        final List<Integer> secondData = second.getBoth();
        assertEquals(firstData.size(), secondData.size(),
                "lists sizes shall be equal.");
        for (int i = Constants.ZERO_INT; i < firstData.size(); ++i) {
            assertEquals(firstData.get(i), secondData.get(i),
                    "element " + i + " shall be "
                            + firstData.get(i) + " but found "
                            + secondData.get(i) + '.');
        }
    }

    private void setBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                              @NotNull Integer firstValue,
                              @NotNull Integer secondValue) {
        component.setBoth(firstValue, secondValue);
        if (firstValue <= secondValue) {
            getFirstValueCheck(component, firstValue);
            getSecondValueCheck(component, secondValue);
        } else {
            getFirstValueCheck(component, secondValue);
            getSecondValueCheck(component, firstValue);
        }
    }

    private void setFirstValueCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                    @NotNull Integer value) {
        final Integer second = component.getSecondValue();
        component.setFirstValue(value);
        if (second < value) {
            getFirstValueCheck(component, second);
        } else {
            getFirstValueCheck(component, value);
        }
    }

    private void setSecondValueCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                     @NotNull Integer value) {
        final Integer first = component.getFirstValue();
        component.setSecondValue(value);
        if (value < first) {
            getSecondValueCheck(component, first);
        } else {
            getSecondValueCheck(component, value);
        }
    }

    private void increaseBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                   @NotNull Integer amount) {
        Integer expectedFirst = component.getFirstValue() + amount;
        Integer expectedSecond = component.getSecondValue() + amount;
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.increaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                   @NotNull Integer firstIncrease,
                                   @NotNull Integer secondIncrease) {
        Integer expectedFirst = component.getFirstValue() + firstIncrease;
        Integer expectedSecond = component.getSecondValue() + secondIncrease;
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.increaseBoth(firstIncrease, secondIncrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseFirstCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                    @NotNull Integer amount) {
        Integer expected = component.getFirstValue() + amount;
        if (expected > component.getSecondValue()) {
            expected = component.getSecondValue();
        }
        component.increaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void increaseSecondCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                     @NotNull Integer amount) {
        Integer expected = component.getSecondValue() + amount;
        if (expected < component.getFirstValue()) {
            expected = component.getFirstValue();
        }
        component.increaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void decreaseBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                   @NotNull Integer amount) {
        Integer expectedFirst = component.getFirstValue() - amount;
        Integer expectedSecond = component.getSecondValue() - amount;
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.decreaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                   @NotNull Integer firstDecrease,
                                   @NotNull Integer secondDecrease) {
        Integer expectedFirst = component.getFirstValue() - firstDecrease;
        Integer expectedSecond = component.getSecondValue() - secondDecrease;
        if (expectedFirst > expectedSecond) {
            expectedFirst += expectedSecond;
            expectedSecond = expectedFirst - expectedSecond;
            expectedFirst -= expectedSecond;
        }
        component.decreaseBoth(firstDecrease, secondDecrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseFirstCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                    @NotNull Integer amount) {
        Integer expected = component.getFirstValue() - amount;
        if (expected > component.getSecondValue()) {
            expected = component.getSecondValue();
        }
        component.decreaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void decreaseSecondCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                     @NotNull Integer amount) {
        Integer expected = component.getSecondValue() - amount;
        if (expected < component.getFirstValue()) {
            expected = component.getFirstValue();
        }
        component.decreaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                                 @NotNull Integer percent) {
        Integer expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        Integer expectedSecond = calculateExpectedValue(
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

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                                 @NotNull Float percent) {
        Integer expectedFirst = calculateExpectedValue(
                component.getFirstValue(), percent);
        Integer expectedSecond = calculateExpectedValue(
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

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                                 @NotNull Integer firstPercent,
                                                 @NotNull Integer secondPercent) {
        Integer expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(firstPercent));
        Integer expectedSecond = calculateExpectedValue(
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

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                                 @NotNull Float firstPercent,
                                                 @NotNull Float secondPercent) {
        Integer expectedFirst = calculateExpectedValue(
                component.getFirstValue(), firstPercent);
        Integer expectedSecond = calculateExpectedValue(
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

    private void percentageModificationFirstCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                                  @NotNull Integer percent) {
        Integer expected = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        if (expected > component.getSecondValue()) {
            expected = component.getSecondValue();
        }
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                                  @NotNull Float percent) {
        Integer expected = calculateExpectedValue(
                component.getFirstValue(), percent);
        if (expected > component.getSecondValue()) {
            expected = component.getSecondValue();
        }
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                                   @NotNull Integer percent) {
        Integer expected = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        if (expected < component.getFirstValue()) {
            expected = component.getFirstValue();
        }
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiableMinMaxPairIntComponent component,
                                                   @NotNull Float percent) {
        Integer expected = calculateExpectedValue(
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

    private @NotNull Integer calculateExpectedValue(@NotNull Integer initial,
                                                    @NotNull Float percentage) {
        return FloatMath.multiply(initial.floatValue(),
                Constants.PERCENTAGE_CAP_FLOAT + percentage).intValue();
    }
}
