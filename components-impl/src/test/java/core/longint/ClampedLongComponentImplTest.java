package core.longint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint.ClampedLongComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.ClampedLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.proxies.LongArithmeticsProxyImpl;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("Duplicates")
public final class ClampedLongComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ClampedNumberComponent.ClampedLongComponent componentOne =
                prepareZeroOneHundredComponent();
        final ClampedNumberComponent.ClampedLongComponent componentTwo =
                prepareZeroOneHundredComponent();
        assertEquals(1L, componentTwo.getId() - componentOne.getId(),
                "getId() generated adjacent ids difference shal be one.");
    }

    @Test
    public void getLowerBoundaryTest() {
        getLowerBoundaryCheck(Constants.ZERO_LONG, Constants.ZERO_LONG);
        getLowerBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        getLowerBoundaryCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
    }

    @Test
    public void getUpperBoundaryTest() {
        getUpperBoundaryCheck(Constants.ZERO_LONG, Constants.ZERO_LONG);
        getUpperBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        getUpperBoundaryCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
    }

    @Test
    public void getBoundariesTest() {
        getBoundariesCheck(Constants.ZERO_LONG, Constants.ZERO_LONG);
        getBoundariesCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        getBoundariesCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
    }

    @Test
    public void getCurrentValueTest() {
        getCurrentValueCheck(Constants.ZERO_LONG,
                Constants.ZERO_LONG, Constants.ZERO_LONG);
        getCurrentValueCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        getCurrentValueCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
        getCurrentValueCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        getCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        getCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        getCurrentValueCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
    }

    @Test
    public void isEmptyTest() {
        isEmptyCheck(Constants.ZERO_LONG, Constants.ZERO_LONG, Constants.ZERO_LONG);
        isEmptyCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
        isEmptyCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, Constants.ZERO_LONG);
        isEmptyCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        isEmptyCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
        isEmptyCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
        isEmptyCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
    }

    @Test
    public void isFullTest() {
        isFullCheck(Constants.ZERO_LONG,
                Constants.ZERO_LONG,
                Constants.ZERO_LONG);
        isFullCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
        isFullCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, Constants.ZERO_LONG);
        isFullCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        isFullCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        isFullCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
        isFullCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
    }

    @Test
    public void getPercentageTest() {
        getPercentageCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, Constants.ZERO_LONG);
        getPercentageCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        getPercentageCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        getPercentageCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        getPercentageCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        getPercentageCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
    }

    @Test
    public void setCurrentValueTest() {
        setCurrentValueCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        setCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        setCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        setCurrentValueCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        setCurrentValueCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setCurrentValueCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        setCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, Constants.ZERO_LONG);
    }

    @Test
    public void increaseCurrentValueTest() {
        increaseCurrentValueCheck(Constants.ZERO_LONG, Constants.ZERO_LONG,
                Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) IdsConstants.SECOND_VALUE_INDEX);
        increaseCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                Constants.ZERO_LONG, (long) IdsConstants.SECOND_VALUE_INDEX);
        increaseCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, (long) Constants.PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseCurrentValueTest() {
        decreaseCurrentValueCheck(Constants.ZERO_LONG, Constants.ZERO_LONG,
                Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) IdsConstants.SECOND_VALUE_INDEX);
        decreaseCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                Constants.ZERO_LONG, (long) IdsConstants.SECOND_VALUE_INDEX);
        decreaseCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void modifyCurrentValueByPercentage() {
        percentageModificationCurrentCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        percentageModificationCurrentCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        percentageModificationCurrentCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        percentageModificationCurrentCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        percentageModificationCurrentCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                Constants.ZERO_LONG, Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                Constants.ZERO_LONG, Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void packTest() {
        packCheck(Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        packCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, Constants.ZERO_LONG);
        packCheck(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        packCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        packCheck(Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ClampedNumberComponent.ClampedLongComponent prepareComponent(
            @NotNull Long lowerBoundary,
            @NotNull Long upperBoundary,
            @NotNull Long currentValue) {
        return new ClampedLongComponentImpl(lowerBoundary, upperBoundary, currentValue);
    }

    private @NotNull ClampedNumberComponent.ClampedLongComponent prepareDefaultComponent(
            @NotNull Long lowerBoundary,
            @NotNull Long upperBoundary) {
        return new ClampedLongComponentImpl(lowerBoundary, upperBoundary);
    }

    private @NotNull ClampedNumberComponent.ClampedLongComponent prepareZeroOneHundredComponent() {
        return prepareDefaultComponent(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
    }

    private void getLowerBoundaryCheck(@NotNull Long lower,
                                       @NotNull Long upper) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "getLowerBoundary()");
    }

    private void getUpperBoundaryCheck(@NotNull Long lower,
                                       @NotNull Long upper) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "getUpperBoundary()");
    }

    private void getBoundariesCheck(@NotNull Long lower,
                                    @NotNull Long upper) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareDefaultComponent(lower, upper);
        final List<Long> boundaries = component.getBoundaries();
        checkEquality(IdsConstants.PAIR_ARRAY_SIZE, boundaries.size(),
                "getBoundaries().size()");
        checkEquality(getMinimal(lower, upper),
                boundaries.get(IdsConstants.MIN_VALUE_INDEX),
                "getBoundaries().get(MIN_VALUE_INDEX)");
        checkEquality(getMaximal(lower, upper),
                boundaries.get(IdsConstants.MAX_VALUE_INDEX),
                "getBoundaries().get(MAX_VALUE_INDEX)");
    }

    private void getCurrentValueCheck(@NotNull Long lower,
                                      @NotNull Long upper,
                                      @NotNull Long current) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final Long expected = clamp(current, getMinimal(lower, upper),
                getMaximal(lower, upper));
        checkEquality(expected, component.getCurrentValue(), "getCurrentValue()");
    }

    private void isEmptyCheck(@NotNull Long lower,
                              @NotNull Long upper,
                              @NotNull Long current) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final Long minimal = getMinimal(lower, upper);
        final Long maximal = getMaximal(lower, upper);
        final Long expected = clamp(current, minimal, maximal);
        final String message = "isEmpty(): expected "
                + minimal.equals(expected) + " but found " + component.isEmpty() + '.';
        if (minimal.equals(expected)) {
            assertTrue(component.isEmpty(), message);
        } else {
            assertFalse(component.isEmpty(), message);
        }
    }

    private void isFullCheck(@NotNull Long lower,
                             @NotNull Long upper,
                             @NotNull Long current) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final Long minimal = getMinimal(lower, upper);
        final Long maximal = getMaximal(lower, upper);
        final Long expected = clamp(current, minimal, maximal);
        final String message = "isFull(): expected "
                + maximal.equals(expected) + " but found " + component.isEmpty() + '.';
        if (maximal.equals(expected)) {
            assertTrue(component.isFull(), message);
        } else {
            assertFalse(component.isFull(), message);
        }
    }

    private void getPercentageCheck(@NotNull Long lower,
                                    @NotNull Long upper,
                                    @NotNull Long current) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final Long minimal = getMinimal(lower, upper);
        final Long maximal = getMaximal(lower, upper);
        final Float expectedFloat = FloatMath.divide(
                clamp(current, minimal, maximal).floatValue(),
                getMaximal(lower, upper).floatValue());
        checkEquality(expectedFloat, component.getPercentageFloat(),
                "getPercentageFloat()");
        final int expectedInt = FloatMath.multiply(expectedFloat,
                (float) Constants.PERCENTAGE_CAP_INT).intValue();
        checkEquality(expectedInt, component.getPercentageInt(),
                "getPercentageInt()");
    }

    private void setCurrentValueCheck(@NotNull Long lower,
                                      @NotNull Long upper,
                                      @NotNull Long newCurrent) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "setCurrentValue() initial");
        component.setCurrentValue(newCurrent);
        checkEquality(clamp(newCurrent, getMinimal(lower, upper),
                getMaximal(lower, upper)), component.getCurrentValue(),
                "setCurrentValue() new");
    }

    private void increaseCurrentValueCheck(@NotNull Long lower,
                                           @NotNull Long upper,
                                           @NotNull Long current,
                                           @NotNull Long toAdd) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final Long minimal = getMinimal(lower, upper);
        final Long maximal = getMaximal(lower, upper);
        Long expected = clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "increaseCurrentValue() initial");
        expected = clamp(expected + toAdd, minimal, maximal);
        component.increaseCurrentValue(toAdd);
        checkEquality(expected, component.getCurrentValue(),
                "increaseCurrentValue() new");
    }

    private void decreaseCurrentValueCheck(@NotNull Long lower,
                                           @NotNull Long upper,
                                           @NotNull Long current,
                                           @NotNull Long toSubtract) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final Long minimal = getMinimal(lower, upper);
        final Long maximal = getMaximal(lower, upper);
        Long expected = clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "decreaseCurrentValue() initial");
        expected = clamp(expected - toSubtract, minimal, maximal);
        component.decreaseCurrentValue(toSubtract);
        checkEquality(expected, component.getCurrentValue(),
                "decreaseCurrentValue() new");
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationCurrentCheck(@NotNull Long lower,
                                                    @NotNull Long upper,
                                                    @NotNull Long current,
                                                    @NotNull Integer percent) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final Long minimal = getMinimal(lower, upper);
        final Long maximal = getMaximal(lower, upper);
        Long expected = clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) initial");
        expected = clamp(new LongArithmeticsProxyImpl()
                .modifyByPercentage(expected, percent), minimal, maximal);
        component.modifyCurrentValueByPercentage(percent);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) new");
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationCurrentCheck(@NotNull Long lower,
                                                    @NotNull Long upper,
                                                    @NotNull Long current,
                                                    @NotNull Float percent) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final Long minimal = getMinimal(lower, upper);
        final Long maximal = getMaximal(lower, upper);
        final Long expected = clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) initial");
        final Long newExpected = clamp(new LongArithmeticsProxyImpl()
                .modifyByPercentage(expected, percent), minimal, maximal);
        component.modifyCurrentValueByPercentage(percent);
        checkEquality(newExpected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) new");
    }

    private void packCheck(@NotNull Long lower,
                           @NotNull Long upper,
                           @NotNull Long current) {
        final ClampedNumberComponent.ClampedLongComponent component =
                prepareComponent(lower, upper, current);
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(ClampedLongComponentModel.class));
        final ClampedLongComponentModel model = (ClampedLongComponentModel) rawModel;
        checkEquality(component.getId(), model.getId(),
                "component.getId() vs model.getId()");
        checkEquality(component.getLowerBoundary(), model.getLowerBoundary(),
                "component.getLowerBoundary() vs model.getLowerBoundary()");
        checkEquality(component.getUpperBoundary(), model.getUpperBoundary(),
                "component.getUpperBoundary() vs model.getUpperBoundary()");
        final ClampedNumberComponent.ClampedLongComponent restored =
                new ClampedLongComponentImpl(model);
        checkEquality(component.getId(), restored.getId(),
                "component.getId() vs restored.getId()");
        checkEquality(component.getLowerBoundary(), restored.getLowerBoundary(),
                "component.getLowerBoundary() vs restored.getLowerBoundary()");
        assertEquals(component.getUpperBoundary(), restored.getUpperBoundary(),
                "component.getUpperBoundary() vs restored.getUpperBoundary()");
    }

    private void checkEquality(@NotNull Long expected,
                               @NotNull Long actual,
                               @NotNull String message) {
        assertEquals(expected, actual, message + ": expected "
                + expected + " but found " + actual + '.');
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

    private @NotNull Long getMinimal(@NotNull Long lhs,
                                     @NotNull Long rhs) {
        return lhs <= rhs ? lhs : rhs;
    }

    private @NotNull Long getMaximal(@NotNull Long lhs,
                                     @NotNull Long rhs) {
        return rhs >= lhs ? rhs : lhs;
    }

    private @NotNull Long clamp(@NotNull Long value,
                                @NotNull Long minimal,
                                @NotNull Long maximal) {
        return trimMax(trimMin(value, minimal), maximal);
    }

    private @NotNull Long trimMin(@NotNull Long value,
                                  @NotNull Long minimal) {
        return value >= minimal ? value : minimal;
    }

    private @NotNull Long trimMax(@NotNull Long value,
                                  @NotNull Long maximal) {
        return value <= maximal ? value : maximal;
    }
}
