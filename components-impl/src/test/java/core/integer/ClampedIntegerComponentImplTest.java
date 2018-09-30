package core.integer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer.ClampedIntegerComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.functions.MathUtils;
import ru.rougegibbons.landsanddungeons.utils.proxies.IntegerArithmeticsProxyImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class ClampedIntegerComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ClampedNumberComponent.ClampedIntegerComponent componentOne =
                prepareZeroOneHundredComponent();
        final ClampedNumberComponent.ClampedIntegerComponent componentTwo =
                prepareZeroOneHundredComponent();
        assertEquals(1L, componentTwo.getId() - componentOne.getId(),
                "getId() generated adjacent ids difference shal be one.");
    }

    @Test
    public void getLowerBoundaryTest() {
        getLowerBoundaryCheck(Constants.ZERO_INT, Constants.ZERO_INT);
        getLowerBoundaryCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        getLowerBoundaryCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT);
    }

    @Test
    public void getUpperBoundaryTest() {
        getUpperBoundaryCheck(Constants.ZERO_INT, Constants.ZERO_INT);
        getUpperBoundaryCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        getUpperBoundaryCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT);
    }

    @Test
    public void getBoundariesTest() {
        getBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT);
        getBoundariesCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        getBoundariesCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
    }

    @Test
    public void getCurrentValueTest() {
        getCurrentValueCheck(Constants.ZERO_INT, Constants.ZERO_INT, Constants.ZERO_INT);
        getCurrentValueCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        getCurrentValueCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        getCurrentValueCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        getCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        getCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        getCurrentValueCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
    }

    @Test
    public void isEmptyTest() {
        isEmptyCheck(Constants.ZERO_INT, Constants.ZERO_INT, Constants.ZERO_INT);
        isEmptyCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        isEmptyCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.ZERO_INT);
        isEmptyCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        isEmptyCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        isEmptyCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        isEmptyCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
    }

    @Test
    public void isFullTest() {
        isFullCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.ZERO_INT);
        isFullCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        isFullCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.ZERO_INT);
        isFullCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        isFullCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        isFullCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        isFullCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
    }

    @Test
    public void getPercentageTest() {
        getPercentageCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.ZERO_INT);
        getPercentageCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        getPercentageCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        getPercentageCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
    }

    @Test
    public void setCurrentValueTest() {
        setCurrentValueCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        setCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        setCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        setCurrentValueCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        setCurrentValueCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        setCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        setCurrentValueCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        setCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.ZERO_INT);
    }

    @Test
    public void increaseCurrentValueTest() {
        increaseCurrentValueCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, IdsConstants.SECOND_VALUE_INDEX);
        increaseCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.ZERO_INT, IdsConstants.SECOND_VALUE_INDEX);
        increaseCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseCurrentValueTest() {
        decreaseCurrentValueCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, IdsConstants.SECOND_VALUE_INDEX);
        decreaseCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.ZERO_INT, IdsConstants.SECOND_VALUE_INDEX);
        decreaseCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void modifyCurrentValueByPercentage() {
        percentageModificationCurrentCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        percentageModificationCurrentCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        percentageModificationCurrentCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        percentageModificationCurrentCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        percentageModificationCurrentCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ClampedNumberComponent.ClampedIntegerComponent prepareComponent(
            @NotNull Integer lowerBoundary,
            @NotNull Integer upperBoundary,
            @NotNull Integer currentValue) {
        return new ClampedIntegerComponentImpl(lowerBoundary, upperBoundary, currentValue);
    }

    private @NotNull ClampedNumberComponent.ClampedIntegerComponent prepareDefaultComponent(
            @NotNull Integer lowerBoundary,
            @NotNull Integer upperBoundary) {
        return new ClampedIntegerComponentImpl(lowerBoundary, upperBoundary);
    }

    private @NotNull ClampedNumberComponent.ClampedIntegerComponent prepareZeroOneHundredComponent() {
        return prepareDefaultComponent(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
    }

    private void getLowerBoundaryCheck(@NotNull Integer lower,
                                       @NotNull Integer upper) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "getLowerBoundary()");
    }

    private void getUpperBoundaryCheck(@NotNull Integer lower,
                                       @NotNull Integer upper) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "getUpperBoundary()");
    }

    private void getBoundariesCheck(@NotNull Integer lower,
                                    @NotNull Integer upper) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareDefaultComponent(lower, upper);
        final List<Integer> boundaries = component.getBoundaries();
        checkEquality(IdsConstants.PAIR_ARRAY_SIZE, boundaries.size(),
                "getBoundaries().size()");
        checkEquality(getMinimal(lower, upper),
                boundaries.get(IdsConstants.MIN_VALUE_INDEX),
                "getBoundaries().get(MIN_VALUE_INDEX)");
        checkEquality(getMaximal(lower, upper),
                boundaries.get(IdsConstants.MAX_VALUE_INDEX),
                "getBoundaries().get(MAX_VALUE_INDEX)");
    }

    private void getCurrentValueCheck(@NotNull Integer lower,
                                      @NotNull Integer upper,
                                      @NotNull Integer current) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer expected = MathUtils.clamp(current, getMinimal(lower, upper),
                getMaximal(lower, upper));
        checkEquality(expected, component.getCurrentValue(), "getCurrentValue()");
    }

    private void isEmptyCheck(@NotNull Integer lower,
                              @NotNull Integer upper,
                              @NotNull Integer current) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer minimal = getMinimal(lower, upper);
        final Integer maximal = getMaximal(lower, upper);
        final Integer expected = MathUtils.clamp(current, minimal, maximal);
        final String message = "isEmpty(): expected "
                + minimal.equals(expected) + " but found " + component.isEmpty() + '.';
        if (minimal.equals(expected)) {
            assertTrue(component.isEmpty(), message);
        } else {
            assertFalse(component.isEmpty(), message);
        }
    }

    private void isFullCheck(@NotNull Integer lower,
                             @NotNull Integer upper,
                             @NotNull Integer current) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer minimal = getMinimal(lower, upper);
        final Integer maximal = getMaximal(lower, upper);
        final Integer expected = MathUtils.clamp(current, minimal, maximal);
        final String message = "isFull(): expected "
                + maximal.equals(expected) + " but found " + component.isEmpty() + '.';
        if (maximal.equals(expected)) {
            assertTrue(component.isFull(), message);
        } else {
            assertFalse(component.isFull(), message);
        }
    }

    private void getPercentageCheck(@NotNull Integer lower,
                                    @NotNull Integer upper,
                                    @NotNull Integer current) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer minimal = getMinimal(lower, upper);
        final Integer maximal = getMaximal(lower, upper);
        final Float expectedFloat = FloatMath.divide(
                MathUtils.clamp(current, minimal, maximal).floatValue(),
                getMaximal(lower, upper).floatValue());
        checkEquality(expectedFloat, component.getPercentageFloat(),
                "getPercentageFloat()");
        final Integer expectedInt = FloatMath.multiply(expectedFloat,
                (float) Constants.PERCENTAGE_CAP_INT).intValue();
        checkEquality(expectedInt, component.getPercentageInt(),
                "getPercentageInt()");
    }

    private void setCurrentValueCheck(@NotNull Integer lower,
                                      @NotNull Integer upper,
                                      @NotNull Integer newCurrent) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "setCurrentValue() initial");
        component.setCurrentValue(newCurrent);
        checkEquality(MathUtils.clamp(newCurrent, getMinimal(lower, upper),
                getMaximal(lower, upper)), component.getCurrentValue(),
                "setCurrentValue() new");
    }

    private void increaseCurrentValueCheck(@NotNull Integer lower,
                                           @NotNull Integer upper,
                                           @NotNull Integer current,
                                           @NotNull Integer toAdd) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer minimal = getMinimal(lower, upper);
        final Integer maximal = getMaximal(lower, upper);
        Integer expected = MathUtils.clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "increaseCurrentValue() initial");
        expected = MathUtils.clamp(expected + toAdd, minimal, maximal);
        component.increaseCurrentValue(toAdd);
        checkEquality(expected, component.getCurrentValue(),
                "increaseCurrentValue() new");
    }

    private void decreaseCurrentValueCheck(@NotNull Integer lower,
                                           @NotNull Integer upper,
                                           @NotNull Integer current,
                                           @NotNull Integer toSubtract) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer minimal = getMinimal(lower, upper);
        final Integer maximal = getMaximal(lower, upper);
        Integer expected = MathUtils.clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "decreaseCurrentValue() initial");
        expected = MathUtils.clamp(expected - toSubtract, minimal, maximal);
        component.decreaseCurrentValue(toSubtract);
        checkEquality(expected, component.getCurrentValue(),
                "decreaseCurrentValue() new");
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationCurrentCheck(@NotNull Integer lower,
                                                    @NotNull Integer upper,
                                                    @NotNull Integer current,
                                                    @NotNull Integer percent) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer minimal = getMinimal(lower, upper);
        final Integer maximal = getMaximal(lower, upper);
        Integer expected = MathUtils.clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) initial");
        expected = MathUtils.clamp(
                new IntegerArithmeticsProxyImpl().modifyByPercentage(
                        expected, percent), minimal, maximal);
        component.modifyCurrentValueByPercentage(percent);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) new");
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationCurrentCheck(@NotNull Integer lower,
                                                    @NotNull Integer upper,
                                                    @NotNull Integer current,
                                                    @NotNull Float percent) {
        final ClampedNumberComponent.ClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer minimal = getMinimal(lower, upper);
        final Integer maximal = getMaximal(lower, upper);
        final Integer expected = MathUtils.clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) initial");
        final Integer newExpected = MathUtils.clamp(
                new IntegerArithmeticsProxyImpl().modifyByPercentage(
                        expected, percent), minimal, maximal);
        component.modifyCurrentValueByPercentage(percent);
        checkEquality(newExpected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) new");
    }

    private void checkEquality(@NotNull Integer expected,
                               @NotNull Integer actual,
                               @NotNull String message) {
        assertEquals(expected, actual, message + ": expected "
                + expected + " but found " + actual + '.');
    }

    @SuppressWarnings("SameParameterValue")
    private void checkEquality(@NotNull Float expected,
                               @NotNull Float actual,
                               @NotNull String message) {
        assertEquals(expected, actual, message + ": expected "
                + expected + " but found " + actual + '.');
    }

    private @NotNull Integer getMinimal(@NotNull Integer lhs,
                                        @NotNull Integer rhs) {
        return lhs <= rhs ? lhs : rhs;
    }

    private @NotNull Integer getMaximal(@NotNull Integer lhs,
                                        @NotNull Integer rhs) {
        return rhs >= lhs ? rhs : lhs;
    }
}
