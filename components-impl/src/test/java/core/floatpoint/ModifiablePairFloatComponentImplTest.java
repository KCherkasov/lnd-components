package core.floatpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.floatpoint.ModifiablePairFloatComponentImpl;
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
import static org.junit.jupiter.api.Assertions.*;

public final class ModifiablePairFloatComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiablePairFloatComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final ModifiablePairFloatComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final ModifiablePairFloatComponentImpl firstComponent = prepareZeroOneHundredComponent();
        getFirstValueCheck(firstComponent, Constants.ZERO_FLOAT);
        final ModifiablePairFloatComponentImpl secondComponent = prepareOneHundredZeroComponent();
        getFirstValueCheck(secondComponent, (float) Constants.PERCENTAGE_CAP_INT);
        assertNotEquals(firstComponent.getFirstValue(), secondComponent.getFirstValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getSecondValueTest() {
        final ModifiablePairFloatComponentImpl firstComponent = prepareZeroOneHundredComponent();
        getSecondValueCheck(firstComponent, (float) Constants.PERCENTAGE_CAP_INT);
        final ModifiablePairFloatComponentImpl secondComponent = prepareOneHundredZeroComponent();
        getSecondValueCheck(secondComponent, Constants.ZERO_FLOAT);
        assertNotEquals(firstComponent.getSecondValue(), secondComponent.getSecondValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getBothTest() {
        final ModifiablePairFloatComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final List<Float> storedData = firstComponent.getBoth();
        final List<Float> expectedOne = new ArrayList<>(IdsConstants.PAIR_ARRAY_SIZE);
        expectedOne.add(firstComponent.getFirstValue());
        expectedOne.add(firstComponent.getSecondValue());
        getBothCheck(firstComponent, expectedOne);
        final ModifiablePairFloatComponentImpl secondComponent = prepareOneHundredZeroComponent();
        final List<Float> storedDataTwo = secondComponent.getBoth();
        final List<Float> expectedTwo = new ArrayList<>();
        expectedTwo.add(secondComponent.getFirstValue());
        expectedTwo.add(secondComponent.getSecondValue());
        getBothCheck(secondComponent, expectedTwo);
        assertTrue(FloatComparator.areEqual(storedData.get(0), storedDataTwo.get(1)),
                "first element in first component list shall be equal" +
                        " to second element in second component's list");
        assertTrue(FloatComparator.areEqual(storedData.get(1), storedDataTwo.get(0)),
                "second element in first component list shall be equal" +
                        " to first element in second component's list");
    }

    @Test
    public void packTest() {
        final ModifiablePairFloatComponentImpl component = prepareZeroOneHundredComponent();
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairFloatComponentModel.class));
        final PairFloatComponentModel model = (PairFloatComponentModel) rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        getFirstValueCheck(component, model.getFirstValue());
        getSecondValueCheck(component, model.getSecondValue());
        final ModifiablePairFloatComponentImpl deserializedComponent =
                new ModifiablePairFloatComponentImpl(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        getBothCheck(component, deserializedComponent);
    }

    @Test
    public void setBothTest() {
        final ModifiablePairFloatComponentImpl component =
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
        final ModifiablePairFloatComponentImpl component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        setFirstValueCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setFirstValueCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
        setFirstValueCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void setSecondTest() {
        final ModifiablePairFloatComponentImpl component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        setSecondValueCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setSecondValueCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
        setSecondValueCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void increaseBothSameTest() {
        final ModifiablePairFloatComponentImpl component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        increaseBothCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseBothCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
        increaseBothCheck(component, Constants.ZERO_FLOAT);
    }

    @Test
    public void increaseBothDifferentTest() {
        final ModifiablePairFloatComponentImpl component =
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
        final ModifiablePairFloatComponentImpl component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        increaseFirstCheck(component, Constants.ZERO_FLOAT);
        increaseFirstCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseFirstCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void increaseSecondValueTest() {
        final ModifiablePairFloatComponentImpl component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        increaseSecondCheck(component, Constants.ZERO_FLOAT);
        increaseSecondCheck(component, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseSecondCheck(component, (float) (-1 * Constants.PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseBothSameTest() {
        final ModifiablePairFloatComponentImpl component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        decreaseBothCheck(component, Constants.ZERO_FLOAT);
        decreaseBothCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        decreaseBothCheck(component, (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseBothDifferentTest() {
        final ModifiablePairFloatComponentImpl component =
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
        final ModifiablePairFloatComponentImpl component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        decreaseFirstCheck(component, Constants.ZERO_FLOAT);
        decreaseFirstCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        decreaseFirstCheck(component, (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void decreaseSecondValueTest() {
        final ModifiablePairFloatComponentImpl component =
                prepareComponent(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        decreaseSecondCheck(component, Constants.ZERO_FLOAT);
        decreaseSecondCheck(component, (float) Constants.PERCENTAGE_CAP_INT);
        decreaseSecondCheck(component, (float) (-1 * Constants.WIDE_PERCENTAGE_CAP_INT));
    }

    @Test
    public void percentageModificationBothSameTest() {
        final ModifiablePairFloatComponentImpl component =
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
        final ModifiablePairFloatComponentImpl component =
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
        final ModifiablePairFloatComponentImpl component =
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
        final ModifiablePairFloatComponentImpl component =
                prepareZeroOneHundredComponent();
        percentageModificationSecondCheck(component, Constants.ZERO_FLOAT);
        percentageModificationSecondCheck(component, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_FLOAT);

        component.setSecondValue((float) Constants.PERCENTAGE_CAP_INT);

        percentageModificationSecondCheck(component, Constants.ZERO_INT);
        percentageModificationSecondCheck(component, Constants.PERCENTAGE_CAP_INT);
        percentageModificationSecondCheck(component, -1 * Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiablePairFloatComponentImpl prepareComponent(@NotNull Float firstValue,
                                                                       @NotNull Float secondValue) {
        return new ModifiablePairFloatComponentImpl(firstValue, secondValue);
    }

    private @NotNull ModifiablePairFloatComponentImpl prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ModifiablePairFloatComponentImpl prepareOneHundredZeroComponent() {
        return prepareComponent((float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
    }

    private void getFirstValueCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                    @NotNull Float expected) {
        assertTrue(FloatComparator.areEqual(expected, component.getFirstValue()),
                "component first value shall be " + expected
                        + " but found " + component.getFirstValue() + '.');
    }

    private void getSecondValueCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                     @NotNull Float expected) {
        assertTrue(FloatComparator.areEqual(expected, component.getSecondValue()),
                "component first value shall be " + expected
                        + " but found " + component.getSecondValue() + '.');
    }

    private void getBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
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

    private void getBothCheck(@NotNull ModifiablePairFloatComponentImpl first,
                              @NotNull ModifiablePairFloatComponentImpl second) {
        final List<Float> firstData = first.getBoth();
        assertEquals(IdsConstants.PAIR_ARRAY_SIZE, firstData.size(), "" +
                "first component's list size shall be 2.");
        final List<Float> secondData = second.getBoth();
        assertEquals(firstData.size(), secondData.size(),
                "components lists sizes shall be equal.");
        for (int i = Constants.ZERO_INT; i < firstData.size(); ++i) {
            assertTrue(FloatComparator.areEqual(
                    firstData.get(i), secondData.get(i)),
                    "element " + i + " shall be "
                            + firstData.get(i) + " but found "
                            + secondData.get(i) + '.');
        }
    }

    private void setBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                              @NotNull Float firstValue,
                              @NotNull Float secondValue) {
        component.setBoth(firstValue, secondValue);
        getFirstValueCheck(component, firstValue);
        getSecondValueCheck(component, secondValue);
    }

    private void setFirstValueCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                    @NotNull Float value) {
        component.setFirstValue(value);
        getFirstValueCheck(component, value);
    }

    private void setSecondValueCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                     @NotNull Float value) {
        component.setSecondValue(value);
        getSecondValueCheck(component, value);
    }

    private void increaseBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                   @NotNull Float amount) {
        final Float expectedFirst = FloatMath.add(component.getFirstValue(), amount);
        final Float expectedSecond = FloatMath.add(component.getSecondValue(), amount);
        component.increaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                   @NotNull Float firstIncrease,
                                   @NotNull Float secondIncrease) {
        final Float expectedFirst = FloatMath.add(component.getFirstValue(), firstIncrease);
        final Float expectedSecond = FloatMath.add(component.getSecondValue(), secondIncrease);
        component.increaseBoth(firstIncrease, secondIncrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void increaseFirstCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                    @NotNull Float amount) {
        final Float expected = FloatMath.add(component.getFirstValue(), amount);
        component.increaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void increaseSecondCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                     @NotNull Float amount) {
        final Float expected = FloatMath.add(component.getSecondValue(), amount);
        component.increaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void decreaseBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                   @NotNull Float amount) {
        final Float expectedFirst = FloatMath.subtract(component.getFirstValue(), amount);
        final Float expectedSecond = FloatMath.subtract(component.getSecondValue(), amount);
        component.decreaseBoth(amount);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                   @NotNull Float firstDecrease,
                                   @NotNull Float secondDecrease) {
        final Float expectedFirst = FloatMath.subtract(component.getFirstValue(), firstDecrease);
        final Float expectedSecond = FloatMath.subtract(component.getSecondValue(), secondDecrease);
        component.decreaseBoth(firstDecrease, secondDecrease);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void decreaseFirstCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                    @NotNull Float amount) {
        final Float expected = FloatMath.subtract(component.getFirstValue(), amount);
        component.decreaseFirstValue(amount);
        getFirstValueCheck(component, expected);
    }

    private void decreaseSecondCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                     @NotNull Float amount) {
        final Float expected = FloatMath.subtract(component.getSecondValue(), amount);
        component.decreaseSecondValue(amount);
        getSecondValueCheck(component, expected);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                                 @NotNull Integer percent) {
        final Float expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        final Float expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                                 @NotNull Float percent) {
        final Float expectedFirst = calculateExpectedValue(
                component.getFirstValue(), percent);
        final Float expectedSecond = calculateExpectedValue(
                component.getSecondValue(), percent);
        component.modifyBothByPercentage(percent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                                 @NotNull Integer firstPercent,
                                                 @NotNull Integer secondPercent) {
        final Float expectedFirst = calculateExpectedValue(
                component.getFirstValue(), toPercent(firstPercent));
        final Float expectedSecond = calculateExpectedValue(
                component.getSecondValue(), toPercent(secondPercent));
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationBothCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                                 @NotNull Float firstPercent,
                                                 @NotNull Float secondPercent) {
        final Float expectedFirst = calculateExpectedValue(
                component.getFirstValue(), firstPercent);
        final Float expectedSecond = calculateExpectedValue(
                component.getSecondValue(), secondPercent);
        component.modifyBothByPercentage(firstPercent, secondPercent);
        getFirstValueCheck(component, expectedFirst);
        getSecondValueCheck(component, expectedSecond);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                                  @NotNull Integer percent) {
        final Float expected = calculateExpectedValue(
                component.getFirstValue(), toPercent(percent));
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    private void percentageModificationFirstCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                                  @NotNull Float percent) {
        final Float expected = calculateExpectedValue(
                component.getFirstValue(), percent);
        component.modifyFirstByPercentage(percent);
        getFirstValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                                   @NotNull Integer percent) {
        final Float expected = calculateExpectedValue(
                component.getSecondValue(), toPercent(percent));
        component.modifySecondByPercentage(percent);
        getSecondValueCheck(component, expected);
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationSecondCheck(@NotNull ModifiablePairFloatComponentImpl component,
                                                   @NotNull Float percent) {
        final Float expected = calculateExpectedValue(
                component.getSecondValue(), percent);
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
