package core.floatpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.floatpoint.ClampedFloatComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.ClampedFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatComparator;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.functions.MathUtils;
import ru.rougegibbons.landsanddungeons.utils.proxies.FloatArithmeticsProxyImpl;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("Duplicates")
public final class ClampedFloatComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ClampedNumberComponent.ClampedFloatComponent componentOne =
                prepareZeroOneHundredComponent();
        final ClampedNumberComponent.ClampedFloatComponent componentTwo =
                prepareZeroOneHundredComponent();
        assertEquals(1L, componentTwo.getId() - componentOne.getId(),
                "getId() generated adjacent ids difference shal be one.");
    }

    @Test
    public void getLowerBoundaryTest() {
        getLowerBoundaryCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        getLowerBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        getLowerBoundaryCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
    }

    @Test
    public void getUpperBoundaryTest() {
        getUpperBoundaryCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        getUpperBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        getUpperBoundaryCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
    }

    @Test
    public void getBoundariesTest() {
        getBoundariesCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        getBoundariesCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        getBoundariesCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
    }

    @Test
    public void getCurrentValueTest() {
        getCurrentValueCheck(Constants.ZERO_FLOAT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        getCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        getCurrentValueCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
        getCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        getCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        getCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        getCurrentValueCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
    }

    @Test
    public void isEmptyTest() {
        isEmptyCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        isEmptyCheck(Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        isEmptyCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        isEmptyCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        isEmptyCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
        isEmptyCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        isEmptyCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
    }

    @Test
    public void isFullTest() {
        isFullCheck(Constants.ZERO_FLOAT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        isFullCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        isFullCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        isFullCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        isFullCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
        isFullCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        isFullCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
    }

    @Test
    public void getPercentageTest() {
        getPercentageCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        getPercentageCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        getPercentageCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
        getPercentageCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        getPercentageCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        getPercentageCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        getPercentageCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
    }

    @Test
    public void setCurrentValueTest() {
        setCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        setCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
        setCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        setCurrentValueCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        setCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        setCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
    }

    @Test
    public void increaseCurrentValueTest() {
        increaseCurrentValueCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                (float) IdsConstants.SECOND_VALUE_INDEX);
        increaseCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) IdsConstants.SECOND_VALUE_INDEX);
        increaseCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseCurrentValueTest() {
        decreaseCurrentValueCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                (float) IdsConstants.SECOND_VALUE_INDEX);
        decreaseCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) IdsConstants.SECOND_VALUE_INDEX);
        decreaseCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseCurrentValueCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void modifyCurrentValueByPercentage() {
        percentageModificationCurrentCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        percentageModificationCurrentCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        percentageModificationCurrentCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_INT);
        percentageModificationCurrentCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        percentageModificationCurrentCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentageModificationCurrentCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_INT);
        percentageModificationCurrentCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void packTest() {
        packCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
        packCheck(Constants.PERCENTAGE_CAP_FLOAT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT);
        packCheck(Constants.ZERO_FLOAT, Constants.PERCENTAGE_CAP_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
        packCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        packCheck(Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull ClampedNumberComponent.ClampedFloatComponent prepareComponent(
            @NotNull Float lowerBoundary,
            @NotNull Float upperBoundary,
            @NotNull Float currentValue) {
        return new ClampedFloatComponentImpl(lowerBoundary, upperBoundary, currentValue);
    }

    private @NotNull ClampedNumberComponent.ClampedFloatComponent prepareDefaultComponent(
            @NotNull Float lowerBoundary,
            @NotNull Float upperBoundary) {
        return new ClampedFloatComponentImpl(lowerBoundary, upperBoundary);
    }

    private @NotNull ClampedNumberComponent.ClampedFloatComponent prepareZeroOneHundredComponent() {
        return prepareDefaultComponent(Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
    }

    private void getLowerBoundaryCheck(@NotNull Float lower,
                                       @NotNull Float upper) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "getLowerBoundary()");
    }

    private void getUpperBoundaryCheck(@NotNull Float lower,
                                       @NotNull Float upper) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "getUpperBoundary()");
    }

    private void getBoundariesCheck(@NotNull Float lower,
                                    @NotNull Float upper) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareDefaultComponent(lower, upper);
        final List<Float> boundaries = component.getBoundaries();
        checkEquality(IdsConstants.PAIR_ARRAY_SIZE, boundaries.size(),
                "getBoundaries().size()");
        checkEquality(getMinimal(lower, upper),
                boundaries.get(IdsConstants.MIN_VALUE_INDEX),
                "getBoundaries().get(MIN_VALUE_INDEX)");
        checkEquality(getMaximal(lower, upper),
                boundaries.get(IdsConstants.MAX_VALUE_INDEX),
                "getBoundaries().get(MAX_VALUE_INDEX)");
    }

    private void getCurrentValueCheck(@NotNull Float lower,
                                      @NotNull Float upper,
                                      @NotNull Float current) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float expected = MathUtils.clamp(current, getMinimal(lower, upper),
                getMaximal(lower, upper));
        checkEquality(expected, component.getCurrentValue(), "getCurrentValue()");
    }

    private void isEmptyCheck(@NotNull Float lower,
                              @NotNull Float upper,
                              @NotNull Float current) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float minimal = getMinimal(lower, upper);
        final Float maximal = getMaximal(lower, upper);
        final Float expected = MathUtils.clamp(current, minimal, maximal);
        final String message = "isEmpty(): expected "
                + minimal.equals(expected) + " but found " + component.isEmpty() + '.';
        if (minimal.equals(expected)) {
            assertTrue(component.isEmpty(), message);
        } else {
            assertFalse(component.isEmpty(), message);
        }
    }

    private void isFullCheck(@NotNull Float lower,
                             @NotNull Float upper,
                             @NotNull Float current) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float minimal = getMinimal(lower, upper);
        final Float maximal = getMaximal(lower, upper);
        final Float expected = MathUtils.clamp(current, minimal, maximal);
        final String message = "isFull(): expected "
                + maximal.equals(expected) + " but found " + component.isEmpty() + '.';
        if (maximal.equals(expected)) {
            assertTrue(component.isFull(), message);
        } else {
            assertFalse(component.isFull(), message);
        }
    }

    private void getPercentageCheck(@NotNull Float lower,
                                    @NotNull Float upper,
                                    @NotNull Float current) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float minimal = getMinimal(lower, upper);
        final Float maximal = getMaximal(lower, upper);
        final Float expectedFloat = FloatMath.divide(
                MathUtils.clamp(current, minimal, maximal),
                getMaximal(lower, upper));
        checkEquality(expectedFloat, component.getPercentageFloat(),
                "getPercentageFloat()");
        final Integer expectedInt = FloatMath.multiply(expectedFloat,
                (float) Constants.PERCENTAGE_CAP_INT).intValue();
        checkEquality(expectedInt, component.getPercentageInt(),
                "getPercentageInt()");
    }

    private void setCurrentValueCheck(@NotNull Float lower,
                                      @NotNull Float upper,
                                      @NotNull Float newCurrent) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "setCurrentValue() initial");
        component.setCurrentValue(newCurrent);
        checkEquality(MathUtils.clamp(newCurrent, getMinimal(lower, upper),
                getMaximal(lower, upper)), component.getCurrentValue(),
                "setCurrentValue() new");
    }

    private void increaseCurrentValueCheck(@NotNull Float lower,
                                           @NotNull Float upper,
                                           @NotNull Float current,
                                           @NotNull Float toAdd) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float minimal = getMinimal(lower, upper);
        final Float maximal = getMaximal(lower, upper);
        Float expected = MathUtils.clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "increaseCurrentValue() initial");
        expected = MathUtils.clamp(FloatMath.add(expected, toAdd), minimal, maximal);
        component.increaseCurrentValue(toAdd);
        checkEquality(expected, component.getCurrentValue(),
                "increaseCurrentValue() new");
    }

    private void decreaseCurrentValueCheck(@NotNull Float lower,
                                           @NotNull Float upper,
                                           @NotNull Float current,
                                           @NotNull Float toSubtract) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float minimal = getMinimal(lower, upper);
        final Float maximal = getMaximal(lower, upper);
        Float expected = MathUtils.clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "decreaseCurrentValue() initial");
        expected = MathUtils.clamp(FloatMath.subtract(expected, toSubtract), minimal, maximal);
        component.decreaseCurrentValue(toSubtract);
        checkEquality(expected, component.getCurrentValue(),
                "decreaseCurrentValue() new");
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationCurrentCheck(@NotNull Float lower,
                                                    @NotNull Float upper,
                                                    @NotNull Float current,
                                                    @NotNull Integer percent) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float minimal = getMinimal(lower, upper);
        final Float maximal = getMaximal(lower, upper);
        Float expected = MathUtils.clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) initial");
        expected = MathUtils.clamp(
                new FloatArithmeticsProxyImpl().modifyByPercentage(
                        expected, percent), minimal, maximal);
        component.modifyCurrentValueByPercentage(percent);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) new");
    }

    @SuppressWarnings("NewMethodNamingConvention")
    private void percentageModificationCurrentCheck(@NotNull Float lower,
                                                    @NotNull Float upper,
                                                    @NotNull Float current,
                                                    @NotNull Float percent) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float minimal = getMinimal(lower, upper);
        final Float maximal = getMaximal(lower, upper);
        final Float expected = MathUtils.clamp(current, minimal, maximal);
        checkEquality(expected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) initial");
        final Float newExpected = MathUtils.clamp(
                new FloatArithmeticsProxyImpl().modifyByPercentage(
                        expected, percent), minimal, maximal);
        component.modifyCurrentValueByPercentage(percent);
        checkEquality(newExpected, component.getCurrentValue(),
                "modifyCurrentValueByPercentage(Integer) new");
    }

    private void packCheck(@NotNull Float lower,
                           @NotNull Float upper,
                           @NotNull Float current) {
        final ClampedNumberComponent.ClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(ClampedFloatComponentModel.class));
        final ClampedFloatComponentModel model = (ClampedFloatComponentModel) rawModel;
        checkEquality(component.getId(), model.getId(),
                "component.getId() vs model.getId()");
        checkEquality(component.getLowerBoundary(), model.getLowerBoundary(),
                "component.getLowerBoundary() vs model.getLowerBoundary()");
        checkEquality(component.getUpperBoundary(), model.getUpperBoundary(),
                "component.getUpperBoundary() vs model.getUpperBoundary()");
        final ClampedNumberComponent.ClampedFloatComponent restored =
                new ClampedFloatComponentImpl(model);
        checkEquality(component.getId(), restored.getId(),
                "component.getId() vs restored.getId()");
        checkEquality(component.getLowerBoundary(), restored.getLowerBoundary(),
                "component.getLowerBoundary() vs restored.getLowerBoundary()");
        assertEquals(component.getUpperBoundary(), restored.getUpperBoundary(),
                "component.getUpperBoundary() vs restored.getUpperBoundary()");
    }

    private void checkEquality(@NotNull Integer expected,
                               @NotNull Integer actual,
                               @NotNull String message) {
        assertEquals(expected, actual, message + ": expected "
                + expected + " but found " + actual + '.');
    }

    private void checkEquality(@NotNull Long expected,
                               @NotNull Long actual,
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

    private @NotNull Float getMinimal(@NotNull Float lhs,
                                      @NotNull Float rhs) {
        return FloatComparator.isGreater(rhs, lhs) ? lhs : rhs;
    }

    private @NotNull Float getMaximal(@NotNull Float lhs,
                                      @NotNull Float rhs) {
        return FloatComparator.isLess(lhs, rhs) ? rhs : lhs;
    }
}
