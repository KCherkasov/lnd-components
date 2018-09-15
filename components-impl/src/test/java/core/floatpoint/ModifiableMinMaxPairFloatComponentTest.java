package core.floatpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.floatpoint.ModifiableMinMaxPairFloatComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatComparator;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ModifiableMinMaxPairFloatComponentTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableMinMaxPairFloatComponent firstComponent = prepareZeroOneHundredComponent();
        final ModifiableMinMaxPairFloatComponent secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final ModifiableMinMaxPairFloatComponent firstComponent = prepareZeroOneHundredComponent();
        getFirstValueCheck(firstComponent, Constants.ZERO_FLOAT);
        final ModifiableMinMaxPairFloatComponent secondComponent = prepareOneHundredZeroComponent();
        getFirstValueCheck(secondComponent, firstComponent.getFirstValue());
    }

    @Test
    public void getSecondValueTest() {
        final ModifiableMinMaxPairFloatComponent firstComponent = prepareZeroOneHundredComponent();
        getSecondValueCheck(firstComponent, (float) Constants.PERCENTAGE_CAP_INT);
        final ModifiableMinMaxPairFloatComponent secondComponent = prepareOneHundredZeroComponent();
        getSecondValueCheck(secondComponent, firstComponent.getSecondValue());
    }

    @Test
    public void getBothTest() {
        final ModifiableMinMaxPairFloatComponent firstComponent = prepareZeroOneHundredComponent();
        final List<Float> expectedOne = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        expectedOne.add(firstComponent.getFirstValue());
        expectedOne.add(firstComponent.getSecondValue());
        getBothCheck(firstComponent, expectedOne);
        final ModifiableMinMaxPairFloatComponent secondComponent = prepareOneHundredZeroComponent();
        final List<Float> expectedTwo = new ArrayList<>();
        expectedTwo.add(secondComponent.getFirstValue());
        expectedTwo.add(secondComponent.getSecondValue());
        getBothCheck(secondComponent, expectedTwo);
        getBothCheck(firstComponent, secondComponent);
    }

    @Test
    public void packTest() {
        final ModifiableMinMaxPairFloatComponent component = prepareZeroOneHundredComponent();
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairFloatComponentModel.class));
        final PairFloatComponentModel model = (PairFloatComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        getFirstValueCheck(component, model.getFirstValue());
        getSecondValueCheck(component, model.getSecondValue());
        final ModifiableMinMaxPairFloatComponent deserializedComponent =
                new ModifiableMinMaxPairFloatComponent(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        getBothCheck(component, deserializedComponent);
    }

    @Test
    public void setBothTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        setBothCheck(component,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setBothCheck(component, Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        setBothCheck(component,
                (float) (-1 * Constants.PERCENTAGE_CAP_INT),
                (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void setFirstTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        setFirstValueCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
        setFirstValueCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void setSecondTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        setSecondValueCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
        setSecondValueCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void increaseBothSameTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        increaseBothCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseBothCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
        increaseBothCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void increaseBothDifferentTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        increaseBothCheck(component, Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        increaseBothCheck(component,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        increaseBothCheck(component,
                (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT),
                (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void increaseFirstValueTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        increaseFirstCheck(component, Constants.ZERO_FLOAT);
        increaseFirstCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseFirstCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void increaseSecondValueTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        increaseSecondCheck(component, Constants.ZERO_FLOAT);
        increaseSecondCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseSecondCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseBothSameTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        decreaseBothCheck(component, Constants.ZERO_FLOAT);
        decreaseBothCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        decreaseBothCheck(component, (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseBothDifferentTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        decreaseBothCheck(component, Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        decreaseBothCheck(component,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseBothCheck(component,
                (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT),
                (float) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseFirstValueTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        decreaseFirstCheck(component, Constants.ZERO_FLOAT);
        decreaseFirstCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        decreaseFirstCheck(component, (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseSecondValueTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        decreaseSecondCheck(component, Constants.ZERO_FLOAT);
        decreaseSecondCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        decreaseSecondCheck(component, (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void percentageModificationBothSameTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent((float) Constants.PERCENTAGE_CAP_INT,
                        (float) Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_FLOAT);
        percentageModificationBothCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationBothCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setBoth((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_INT);
        percentageModificationBothCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationBothCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationBothDifferentTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareComponent((float) Constants.PERCENTAGE_CAP_INT,
                        (float) Constants.PERCENTAGE_CAP_INT);

        percentageModificationBothCheck(component, Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        percentageModificationBothCheck(component,
                Constants.PERCENTAGE_CAP_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationBothCheck(component,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setBoth((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);

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
        final ModifiableMinMaxPairFloatComponent component =
                prepareOneHundredZeroComponent();
        percentageModificationFirstCheck(component, Constants.ZERO_FLOAT);
        percentageModificationFirstCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationFirstCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setFirstValue((float) Constants.PERCENTAGE_CAP_INT);

        percentageModificationFirstCheck(component, Constants.ZERO_INT);
        percentageModificationFirstCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationFirstCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void percentageModificationSecondTest() {
        final ModifiableMinMaxPairFloatComponent component =
                prepareZeroOneHundredComponent();
        percentageModificationSecondCheck(component, Constants.ZERO_FLOAT);
        percentageModificationSecondCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setSecondValue((float) Constants.PERCENTAGE_CAP_INT);

        percentageModificationSecondCheck(component, Constants.ZERO_INT);
        percentageModificationSecondCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiableMinMaxPairFloatComponent prepareComponent(@NotNull Float firstValue,
                                                                         @NotNull Float secondValue) {
        return new ModifiableMinMaxPairFloatComponent(firstValue, secondValue);
    }

    private @NotNull ModifiableMinMaxPairFloatComponent prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiableMinMaxPairFloatComponent prepareOneHundredZeroComponent() {
        return prepareComponent((float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
    }

    private void getFirstValueCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                    @NotNull Float expected) {
        assertTrue(FloatComparator.areEqual(expected, component.getFirstValue()),
                "component first value shall be " + expected
                        + " but found " + component.getFirstValue() + '.');
    }

    private void getSecondValueCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                     @NotNull Float expected) {
        assertTrue(FloatComparator.areEqual(expected, component.getSecondValue()),
                "component first value shall be " + expected
                        + " but found " + component.getSecondValue() + '.');
    }

    private void getBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                              @NotNull List<Float> expected) {
        final List<Float> actual = component.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, actual.size(),
                "pair component list size shall be 2.");
        assertEquals(expected.size(), actual.size(),
                "component list size shall be equal to expected list size.");
        for (int i = Constants.ZERO_INT; i < expected.size(); ++i) {
            assertTrue(FloatComparator.areEqual(
                    expected.get(i), actual.get(i)),
                    "element " + i + " shall be "
                            + expected.get(i) + " but found "
                            + actual.get(i) + '.');
        }
    }

    private void getBothCheck(@NotNull ModifiableMinMaxPairFloatComponent first,
                              @NotNull ModifiableMinMaxPairFloatComponent second) {
        final List<Float> firstData = first.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, firstData.size(), "" +
                "first component's list size shall be 2.");
        final List<Float> secondData = second.getBoth();
        assertEquals(firstData.size(), secondData.size(),
                "lists sizes shall be equal.");
        for (int i = Constants.ZERO_INT; i < firstData.size(); ++i) {
            assertTrue(FloatComparator.areEqual(
                    firstData.get(i), secondData.get(i)),
                    "element " + i + " shall be "
                            + firstData.get(i) + " but found "
                            + secondData.get(i) + '.');
        }
    }

    private void setBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                              @NotNull Float firstValue,
                              @NotNull Float secondValue) {
        component.setBoth(firstValue, secondValue);
        if (FloatComparator.isGreater(firstValue, secondValue)) {
            getFirstValueCheck(component, secondValue);
            getSecondValueCheck(component, firstValue);
        } else {
            getFirstValueCheck(component, firstValue);
            getSecondValueCheck(component, secondValue);
        }
    }

    private void setFirstValueCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                    @NotNull Float value) {
        final Float second = component.getSecondValue();
        component.setFirstValue(value);
        if (FloatComparator.isGreater(value, second)) {
            getFirstValueCheck(component, second);
        } else {
            getFirstValueCheck(component, value);
        }
    }

    private void setSecondValueCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                     @NotNull Float value) {
        final Float first = component.getFirstValue();
        component.setSecondValue(value);
        if (FloatComparator.isLess(value, first)) {
            getSecondValueCheck(component, first);
        } else {
            getSecondValueCheck(component, value);
        }
    }

    private void increaseBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                   @NotNull Float amount) {
        Float expectedFirst = FloatMath.add(component.getFirstValue(), amount);
        Float expectedSecond = FloatMath.add(component.getSecondValue(), amount);
        if (FloatComparator.isLess(expectedSecond, expectedFirst)) {
            expectedFirst = FloatMath.add(expectedFirst, expectedSecond);
            expectedSecond = FloatMath.subtract(expectedFirst, expectedSecond);
            expectedFirst = FloatMath.subtract(expectedFirst, expectedSecond);
        }
        component.increaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                   @NotNull Float firstIncrease,
                                   @NotNull Float secondIncrease) {
        Float expectedFirst = FloatMath.add(component.getFirstValue(), firstIncrease);
        Float expectedSecond = FloatMath.add(component.getSecondValue(), secondIncrease);
        if (FloatComparator.isLess(expectedSecond, expectedFirst)) {
            expectedFirst = FloatMath.add(expectedFirst, expectedSecond);
            expectedSecond = FloatMath.subtract(expectedFirst, expectedSecond);
            expectedFirst = FloatMath.subtract(expectedFirst, expectedSecond);
        }
        component.increaseBoth(firstIncrease, secondIncrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseFirstCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                    @NotNull Float amount) {
        Float expected = FloatMath.add(component.getFirstValue(), amount);
        if (FloatComparator.isGreater(expected, component.getSecondValue())) {
            expected = component.getSecondValue();
        }
        component.increaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void increaseSecondCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                     @NotNull Float amount) {
        Float expected = FloatMath.add(component.getSecondValue(), amount);
        if (FloatComparator.isLess(expected, component.getFirstValue())) {
            expected = component.getFirstValue();
        }
        component.increaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void decreaseBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                   @NotNull Float amount) {
        Float expectedFirst = FloatMath.subtract(component.getFirstValue(), amount);
        Float expectedSecond = FloatMath.subtract(component.getSecondValue(), amount);
        component.decreaseBoth(amount);
        if (FloatComparator.isLess(expectedSecond, expectedFirst)) {
            expectedFirst = FloatMath.add(expectedFirst, expectedSecond);
            expectedSecond = FloatMath.subtract(expectedFirst, expectedSecond);
            expectedFirst = FloatMath.subtract(expectedFirst, expectedSecond);
        }
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                   @NotNull Float firstDecrease,
                                   @NotNull Float secondDecrease) {
        Float expectedFirst = FloatMath.subtract(component.getFirstValue(), firstDecrease);
        Float expectedSecond = FloatMath.subtract(component.getSecondValue(), secondDecrease);
        component.decreaseBoth(firstDecrease, secondDecrease);
        if (FloatComparator.isLess(expectedSecond, expectedFirst)) {
            expectedFirst = FloatMath.add(expectedFirst, expectedSecond);
            expectedSecond = FloatMath.subtract(expectedFirst, expectedSecond);
            expectedFirst = FloatMath.subtract(expectedFirst, expectedSecond);
        }
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseFirstCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                    @NotNull Float amount) {
        Float expected = FloatMath.subtract(component.getFirstValue(), amount);
        if (FloatComparator.isGreater(expected, component.getSecondValue())) {
            expected = component.getSecondValue();
        }
        component.decreaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void decreaseSecondCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                     @NotNull Float amount) {
        Float expected = FloatMath.subtract(component.getSecondValue(), amount);
        if (FloatComparator.isLess(expected, component.getFirstValue())) {
            expected = component.getFirstValue();
        }
        component.decreaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                                 @NotNull Integer percent) {
        Float expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        Float expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        if (FloatComparator.isLess(expectedSecond, expectedFirst)) {
            expectedFirst = FloatMath.add(expectedFirst, expectedSecond);
            expectedSecond = FloatMath.subtract(expectedFirst, expectedSecond);
            expectedFirst = FloatMath.subtract(expectedFirst, expectedSecond);
        }
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                                 @NotNull Float percent) {
        Float expectedFirst = calculateExpectedValue(
                component.getFirstValue(), percent);
        Float expectedSecond = calculateExpectedValue(
                component.getSecondValue(), percent);
        if (FloatComparator.isLess(expectedSecond, expectedFirst)) {
            expectedFirst = FloatMath.add(expectedFirst, expectedSecond);
            expectedSecond = FloatMath.subtract(expectedFirst, expectedSecond);
            expectedFirst = FloatMath.subtract(expectedFirst, expectedSecond);
        }
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                                 @NotNull Integer firstPercent,
                                                 @NotNull Integer secondPercent) {
        Float expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(firstPercent));
        Float expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(secondPercent));
        if (FloatComparator.isLess(expectedSecond, expectedFirst)) {
            expectedFirst = FloatMath.add(expectedFirst, expectedSecond);
            expectedSecond = FloatMath.subtract(expectedFirst, expectedSecond);
            expectedFirst = FloatMath.subtract(expectedFirst, expectedSecond);
        }
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                                 @NotNull Float firstPercent,
                                                 @NotNull Float secondPercent) {
        Float expectedFirst = calculateExpectedValue(
                component.getFirstValue(), firstPercent);
        Float expectedSecond = calculateExpectedValue(
                component.getSecondValue(), secondPercent);
        if (FloatComparator.isLess(expectedSecond, expectedFirst)) {
            expectedFirst = FloatMath.add(expectedFirst, expectedSecond);
            expectedSecond = FloatMath.subtract(expectedFirst, expectedSecond);
            expectedFirst = FloatMath.subtract(expectedFirst, expectedSecond);
        }
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                                  @NotNull Integer percent) {
        Float expected = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        if (FloatComparator.isGreater(expected, component.getSecondValue())) {
            expected = component.getSecondValue();
        }
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                                  @NotNull Float percent) {
        Float expected = calculateExpectedValue(
                component.getFirstValue(), percent);
        if (FloatComparator.isGreater(expected, component.getSecondValue())) {
            expected = component.getSecondValue();
        }
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                                   @NotNull Integer percent) {
        Float expected = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        if (FloatComparator.isLess(expected, component.getFirstValue())) {
            expected = component.getFirstValue();
        }
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiableMinMaxPairFloatComponent component,
                                                   @NotNull Float percent) {
        Float expected = calculateExpectedValue(
                component.getSecondValue(), percent);
        if (FloatComparator.isLess(expected, component.getFirstValue())) {
            expected = component.getFirstValue();
        }
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    private @NotNull Float toPercent(@NotNull Integer percent) {
        return FloatMath.divide(percent.floatValue(), (float) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull Float calculateExpectedValue(@NotNull Float initial,
                                                  @NotNull Float percentage) {
        return FloatMath.multiply(initial, Constants.PERCENTAGE_CAP_FLOAT + percentage);
    }
}
