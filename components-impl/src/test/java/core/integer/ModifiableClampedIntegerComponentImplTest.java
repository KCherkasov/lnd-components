package core.integer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.integer.ModifiableClampedIntComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.ClampedIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.functions.MathUtils;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.IntegerArithmeticsProxyImpl;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("Duplicates, NewMethodNamingConvention, SameParameterValue")
public final class ModifiableClampedIntegerComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent componentOne =
                prepareZeroOneHundredComponent();
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent componentTwo =
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

    @Test
    public void packTest() {
        packCheck(Constants.ZERO_INT, Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        packCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT, Constants.ZERO_INT);
        packCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        packCheck(Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        packCheck(Constants.ZERO_INT, Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
    }

    @Test
    public void setLowerBoundaryTest() {
        setLowerBoundaryCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        setLowerBoundaryCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        setLowerBoundaryCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void setUpperBoundaryTest() {
        setUpperBoundaryCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        setUpperBoundaryCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        setUpperBoundaryCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void setBoundariesTest() {
        setBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
        setBoundariesCheck(Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        setBoundariesCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseLowerBoundaryTest() {
        increaseLowerBoundaryCheck(Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck(Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        increaseLowerBoundaryCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseUpperBoundaryTest() {
        increaseUpperBoundaryCheck(Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck(Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        increaseUpperBoundaryCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseBoundariesSimilarTest() {
        increaseBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseBoundariesDifferentTest() {
        increaseBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        increaseBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.ZERO_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                -1 * Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseLowerBoundaryTest() {
        decreaseLowerBoundaryCheck(Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        decreaseLowerBoundaryCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseUpperBoundaryTest() {
        decreaseUpperBoundaryCheck(Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        decreaseUpperBoundaryCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBoundariesSimilarTest() {
        decreaseBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBoundariesDifferentTest() {
        decreaseBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
        decreaseBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.ZERO_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                -1 * Constants.PERCENTAGE_CAP_INT, Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void modifyLowerByPercentageTest() {
        percentModificationLowerCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyUpperByPercentageTest() {
        percentModificationUpperCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT, -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyBoundariesByPercentageSimilarTest() {
        percentModificationBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, -Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT, -Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyBoundariesByPercentageDifferentTest() {
        percentModificationBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(Constants.ZERO_INT, Constants.ZERO_INT,
                Constants.PERCENTAGE_CAP_FLOAT, Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(-1 * Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(-1 * Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                FloatMath.divide((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                        (float) Constants.PERCENTAGE_CAP_INT));
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
    prepareComponent(
            @NotNull Integer lowerBoundary,
            @NotNull Integer upperBoundary,
            @NotNull Integer currentValue) {
        return new ModifiableClampedIntComponentImpl(lowerBoundary, upperBoundary, currentValue);
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
    prepareDefaultComponent(
            @NotNull Integer lowerBoundary,
            @NotNull Integer upperBoundary) {
        return new ModifiableClampedIntComponentImpl(lowerBoundary, upperBoundary);
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
    prepareZeroOneHundredComponent() {
        return prepareDefaultComponent(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
    }

    private void getLowerBoundaryCheck(@NotNull Integer lower,
                                       @NotNull Integer upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "getLowerBoundary()");
    }

    private void getUpperBoundaryCheck(@NotNull Integer lower,
                                       @NotNull Integer upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "getUpperBoundary()");
    }

    private void getBoundariesCheck(@NotNull Integer lower,
                                    @NotNull Integer upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final Integer expected = MathUtils.clamp(current, getMinimal(lower, upper),
                getMaximal(lower, upper));
        checkEquality(expected, component.getCurrentValue(), "getCurrentValue()");
    }

    private void isEmptyCheck(@NotNull Integer lower,
                              @NotNull Integer upper,
                              @NotNull Integer current) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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

    private void percentageModificationCurrentCheck(@NotNull Integer lower,
                                                    @NotNull Integer upper,
                                                    @NotNull Integer current,
                                                    @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
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

    private void packCheck(@NotNull Integer lower,
                           @NotNull Integer upper,
                           @NotNull Integer current) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent component =
                prepareComponent(lower, upper, current);
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(ClampedIntComponentModel.class));
        final ClampedIntComponentModel model = (ClampedIntComponentModel) rawModel;
        checkEquality(component.getId(), model.getId(),
                "component.getId() vs model.getId()");
        checkEquality(component.getLowerBoundary(), model.getLowerBoundary(),
                "component.getLowerBoundary() vs model.getLowerBoundary()");
        checkEquality(component.getUpperBoundary(), model.getUpperBoundary(),
                "component.getUpperBoundary() vs model.getUpperBoundary()");
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent restored =
                new ModifiableClampedIntComponentImpl(model);
        checkEquality(component.getId(), restored.getId(),
                "component.getId() vs restored.getId()");
        checkEquality(component.getLowerBoundary(), restored.getLowerBoundary(),
                "component.getLowerBoundary() vs restored.getLowerBoundary()");
        assertEquals(component.getUpperBoundary(), restored.getUpperBoundary(),
                "component.getUpperBoundary() vs restored.getUpperBoundary()");
    }

    private void setLowerBoundaryCheck(@NotNull Integer lower,
                                       @NotNull Integer upper,
                                       @NotNull Integer newLower) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "setLowerBoundary() initial");
        checkEquality(current, component.getCurrentValue(),
                "setLowerBoundary() currentValue initial");
        component.setLowerBoundary(newLower);
        minimal = getMinimal(newLower, maximal);
        maximal = getMaximal(newLower, maximal);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "setLowerBoundary() new");
        checkEquality(current, component.getCurrentValue(),
                "setLowerBoundary() currentValue new");
    }

    private void setUpperBoundaryCheck(@NotNull Integer lower,
                                       @NotNull Integer upper,
                                       @NotNull Integer newUpper) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "setUpperBoundary() initial");
        checkEquality(current, component.getCurrentValue(),
                "setUpperBoundary() currentValue initial");
        component.setUpperBoundary(newUpper);
        maximal = getMaximal(minimal, newUpper);
        minimal = getMinimal(minimal, newUpper);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "setUpperBoundary() new");
        checkEquality(current, component.getCurrentValue(),
                "setUpperBoundary() currentValue new");
    }

    private void setBoundariesCheck(@NotNull Integer lower,
                                    @NotNull Integer upper,
                                    @NotNull Integer newLower,
                                    @NotNull Integer newUpper) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "setBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "setBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "setBoundaries() currentValue initial");
        component.setBoundaries(newLower, newUpper);
        minimal = getMinimal(newLower, newUpper);
        maximal = getMaximal(newLower, newUpper);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "setBoundaries() lower new");
        checkEquality(maximal, component.getUpperBoundary(),
                "setBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "setBoundaries() currentValue new");
    }

    private void increaseLowerBoundaryCheck(@NotNull Integer lower,
                                            @NotNull Integer upper,
                                            @NotNull Integer toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "increaseLowerBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "increaseLowerBoundary() currentValue initial");
        component.increaseLowerBoundary(toAdd);
        final Integer minimal = getMinimal(getMinimal(lower, upper) + toAdd,
                getMaximal(lower, upper));
        final Integer maximal = getMaximal(getMinimal(lower, upper) + toAdd,
                getMaximal(lower, upper));
        final Integer current = MathUtils.clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseLowerBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "increaseLowerBoundary() currentValue new");
    }

    private void increaseUpperBoundaryCheck(@NotNull Integer lower,
                                            @NotNull Integer upper,
                                            @NotNull Integer toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "increaseUpperBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "increaseUpperBoundary() currentValue initial");
        component.increaseUpperBoundary(toAdd);
        final Integer minimal = getMinimal(getMaximal(lower, upper) + toAdd,
                getMinimal(lower, upper));
        final Integer maximal = getMaximal(getMaximal(lower, upper) + toAdd,
                getMinimal(lower, upper));
        final Integer current = MathUtils.clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseUpperBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "increaseUpperBoundary() currentValue new");
    }

    private void increaseBoundariesCheck(@NotNull Integer lower,
                                         @NotNull Integer upper,
                                         @NotNull Integer toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue initial");
        component.increaseBoundaries(toAdd);
        minimal += toAdd;
        maximal += toAdd;
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void increaseBoundariesCheck(@NotNull Integer lower,
                                         @NotNull Integer upper,
                                         @NotNull Integer lowerAdd,
                                         @NotNull Integer upperAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "increaseLowerBoundary() currentValue initial");
        component.increaseBoundaries(lowerAdd, upperAdd);
        minimal = getMinimal(getMinimal(lower, upper) + lowerAdd,
                getMaximal(lower, upper) + upperAdd);
        maximal = getMaximal(getMinimal(lower, upper) + lowerAdd,
                getMaximal(lower, upper) + upperAdd);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void decreaseLowerBoundaryCheck(@NotNull Integer lower,
                                            @NotNull Integer upper,
                                            @NotNull Integer toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "decreaseLowerBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "decreaseLowerBoundary() currentValue initial");
        component.decreaseLowerBoundary(toSubtract);
        final Integer minimal = getMinimal(getMinimal(lower, upper) - toSubtract,
                getMaximal(lower, upper));
        final Integer maximal = getMaximal(getMinimal(lower, upper) - toSubtract,
                getMaximal(lower, upper));
        final Integer current = MathUtils.clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "decreaseLowerBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "decreaseLowerBoundary() currentValue new");
    }

    private void decreaseUpperBoundaryCheck(@NotNull Integer lower,
                                            @NotNull Integer upper,
                                            @NotNull Integer toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "decreaseUpperBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "decreaseUpperBoundary() currentValue initial");
        component.decreaseUpperBoundary(toSubtract);
        final Integer minimal = getMinimal(getMaximal(lower, upper) - toSubtract,
                getMinimal(lower, upper));
        final Integer maximal = getMaximal(getMaximal(lower, upper) - toSubtract,
                getMinimal(lower, upper));
        final Integer current = MathUtils.clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "decreaseUpperBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "decreaseUpperBoundary() currentValue new");
    }

    private void decreaseBoundariesCheck(@NotNull Integer lower,
                                         @NotNull Integer upper,
                                         @NotNull Integer toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "decreaseBoundaries() currentValue initial");
        component.decreaseBoundaries(toSubtract);
        minimal -= toSubtract;
        maximal -= toSubtract;
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }


    private void decreaseBoundariesCheck(@NotNull Integer lower,
                                         @NotNull Integer upper,
                                         @NotNull Integer lowerSubtract,
                                         @NotNull Integer upperSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "increaseLowerBoundary() currentValue initial");
        component.decreaseBoundaries(lowerSubtract, upperSubtract);
        minimal = getMinimal(getMinimal(lower, upper) - lowerSubtract,
                getMaximal(lower, upper) - upperSubtract);
        maximal = getMaximal(getMinimal(lower, upper) - lowerSubtract,
                getMaximal(lower, upper) - upperSubtract);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void percentModificationLowerCheck(@NotNull Integer lower,
                                               @NotNull Integer upper,
                                               @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Integer) currentValue initial");
        minimal = new IntegerArithmeticsProxyImpl()
                .modifyByPercentage(minimal, percent);
        final Integer newMinimal = getMinimal(minimal, maximal);
        maximal = getMaximal(minimal, maximal);
        current = MathUtils.clamp(current, newMinimal, maximal);
        component.modifyLowerByPercentage(percent);
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Integer) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Integer) currentValue new");
    }

    private void percentModificationLowerCheck(@NotNull Integer lower,
                                               @NotNull Integer upper,
                                               @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Float) currentValue initial");
        minimal = new IntegerArithmeticsProxyImpl()
                .modifyByPercentage(minimal, percent);
        final Integer newMinimal = getMinimal(minimal, maximal);
        maximal = getMaximal(minimal, maximal);
        current = MathUtils.clamp(current, newMinimal, maximal);
        component.modifyLowerByPercentage(percent);
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Float) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Float) currentValue new");
    }

    private void percentModificationUpperCheck(@NotNull Integer lower,
                                               @NotNull Integer upper,
                                               @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Integer) currentValue initial");
        maximal = new IntegerArithmeticsProxyImpl()
                .modifyByPercentage(maximal, percent);
        final Integer newMaximal = getMaximal(minimal, maximal);
        minimal = getMinimal(minimal, maximal);
        current = MathUtils.clamp(current, minimal, newMaximal);
        component.modifyUpperByPercentage(percent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Integer) upper new");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Integer) currentValue new");
    }

    private void percentModificationUpperCheck(@NotNull Integer lower,
                                               @NotNull Integer upper,
                                               @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Float) upper initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Float) currentValue initial");
        maximal = new IntegerArithmeticsProxyImpl()
                .modifyByPercentage(maximal, percent);
        final Integer newMaximal = getMaximal(minimal, maximal);
        minimal = getMinimal(minimal, maximal);
        current = MathUtils.clamp(current, minimal, newMaximal);
        component.modifyUpperByPercentage(percent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Float) upper new");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Float) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Integer lower,
                                                    @NotNull Integer upper,
                                                    @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue initial");
        final ArithmeticsProxy.IntArithmeticsProxy proxy =
                new IntegerArithmeticsProxyImpl();
        component.modifyBoundariesByPercentage(percent);
        maximal = proxy.modifyByPercentage(maximal, percent);
        minimal = proxy.modifyByPercentage(minimal, percent);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper new");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Integer lower,
                                                    @NotNull Integer upper,
                                                    @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue initial");
        final ArithmeticsProxy.IntArithmeticsProxy proxy =
                new IntegerArithmeticsProxyImpl();
        component.modifyBoundariesByPercentage(percent);
        maximal = proxy.modifyByPercentage(maximal, percent);
        minimal = proxy.modifyByPercentage(minimal, percent);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper new");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Integer lower,
                                                    @NotNull Integer upper,
                                                    @NotNull Integer lowerPercent,
                                                    @NotNull Integer upperPercent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue initial");
        final ArithmeticsProxy.IntArithmeticsProxy proxy =
                new IntegerArithmeticsProxyImpl();
        maximal = proxy.modifyByPercentage(maximal, upperPercent);
        minimal = proxy.modifyByPercentage(minimal, lowerPercent);
        final Integer newMaximal = getMaximal(minimal, maximal);
        final Integer newMinimal = getMinimal(minimal, maximal);
        current = MathUtils.clamp(current, newMinimal, newMaximal);
        component.modifyBoundariesByPercentage(lowerPercent, upperPercent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper new");
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Integer lower,
                                                    @NotNull Integer upper,
                                                    @NotNull Float lowerPercent,
                                                    @NotNull Float upperPercent) {
        final ModifiableClampedNumberComponent.ModifiableClampedIntegerComponent
                component = prepareDefaultComponent(lower, upper);
        Integer minimal = getMinimal(lower, upper);
        Integer maximal = getMaximal(lower, upper);
        Integer current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue initial");
        final ArithmeticsProxy.IntArithmeticsProxy proxy =
                new IntegerArithmeticsProxyImpl();
        maximal = proxy.modifyByPercentage(maximal, upperPercent);
        minimal = proxy.modifyByPercentage(minimal, lowerPercent);
        final Integer newMaximal = getMaximal(minimal, maximal);
        final Integer newMinimal = getMinimal(minimal, maximal);
        current = MathUtils.clamp(current, newMinimal, newMaximal);
        component.modifyBoundariesByPercentage(lowerPercent, upperPercent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper new");
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue new");
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
