package core.longint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.longint.ModifiableClampedLongComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.components.interfaces.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.ClampedLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.LongArithmeticsProxyImpl;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("Duplicates, NewMethodNamingConvention, SameParameterValue")
public final class ModifiableClampedLongComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                componentOne = prepareZeroOneHundredComponent();
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                componentTwo = prepareZeroOneHundredComponent();
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

    @Test
    public void setLowerBoundaryTest() {
        setLowerBoundaryCheck(
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setLowerBoundaryCheck(
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        setLowerBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void setUpperBoundaryTest() {
        setUpperBoundaryCheck(
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setUpperBoundaryCheck(
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        setUpperBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void setBoundariesTest() {
        setBoundariesCheck(Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        setBoundariesCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        setBoundariesCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseLowerBoundaryTest() {
        increaseLowerBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck((long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        increaseLowerBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseUpperBoundaryTest() {
        increaseUpperBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
        increaseUpperBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck((long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseBoundariesSimilarTest() {
        increaseBoundariesCheck(Constants.ZERO_LONG,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck(Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseBoundariesDifferentTest() {
        increaseBoundariesCheck(Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        increaseBoundariesCheck(Constants.ZERO_LONG,
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck(
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG,
                (long) -1 * Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseLowerBoundaryTest() {
        decreaseLowerBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
        decreaseLowerBoundaryCheck(
                Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseUpperBoundaryTest() {
        decreaseUpperBoundaryCheck(Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(
                Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG);
        decreaseUpperBoundaryCheck(
                Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBoundariesSimilarTest() {
        decreaseBoundariesCheck(
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck(
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBoundariesDifferentTest() {
        decreaseBoundariesCheck(Constants.ZERO_LONG, Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
        decreaseBoundariesCheck(Constants.ZERO_LONG, Constants.ZERO_LONG,
                Constants.ZERO_LONG,
                (long) -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck((long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG, (long) -1 * Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void modifyLowerByPercentageTest() {
        percentModificationLowerCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(
                Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(
                Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyUpperByPercentageTest() {
        percentModificationUpperCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                (long) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(
                Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(
                Constants.ZERO_LONG,
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyBoundariesByPercentageSimilarTest() {
        percentModificationBoundariesCheck(
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_LONG,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyBoundariesByPercentageDifferentTest() {
        percentModificationBoundariesCheck(
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                Constants.ZERO_LONG, Constants.ZERO_LONG,
                Constants.PERCENTAGE_CAP_FLOAT, Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                (long) Constants.PERCENTAGE_CAP_INT,
                (long) Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(
                (long) -1 * Constants.PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                (long) -1 * Constants.PERCENTAGE_CAP_INT,
                (long) -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                FloatMath.divide((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                        (float) Constants.PERCENTAGE_CAP_INT));
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedLongComponent
    prepareComponent(
            @NotNull Long lowerBoundary,
            @NotNull Long upperBoundary,
            @NotNull Long currentValue) {
        return new ModifiableClampedLongComponentImpl(
                lowerBoundary, upperBoundary, currentValue);
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedLongComponent
    prepareDefaultComponent(
            @NotNull Long lowerBoundary,
            @NotNull Long upperBoundary) {
        return new ModifiableClampedLongComponentImpl(lowerBoundary, upperBoundary);
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedLongComponent
    prepareZeroOneHundredComponent() {
        return prepareDefaultComponent(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
    }

    private void getLowerBoundaryCheck(@NotNull Long lower,
                                       @NotNull Long upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "getLowerBoundary()");
    }

    private void getUpperBoundaryCheck(@NotNull Long lower,
                                       @NotNull Long upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "getUpperBoundary()");
    }

    private void getBoundariesCheck(@NotNull Long lower,
                                    @NotNull Long upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
        final Long expected = clamp(current, getMinimal(lower, upper),
                getMaximal(lower, upper));
        checkEquality(expected, component.getCurrentValue(), "getCurrentValue()");
    }

    private void isEmptyCheck(@NotNull Long lower,
                              @NotNull Long upper,
                              @NotNull Long current) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
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
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareComponent(lower, upper, current);
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(ClampedLongComponentModel.class));
        final ClampedLongComponentModel model = (ClampedLongComponentModel) rawModel;
        checkEquality(component.getId(), model.getId(),
                "component.getId() vs model.getId()");
        checkEquality(component.getLowerBoundary(), model.getLowerBoundary(),
                "component.getLowerBoundary() vs model.getLowerBoundary()");
        checkEquality(component.getUpperBoundary(), model.getUpperBoundary(),
                "component.getUpperBoundary() vs model.getUpperBoundary()");
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent restored =
                new ModifiableClampedLongComponentImpl(model);
        checkEquality(component.getId(), restored.getId(),
                "component.getId() vs restored.getId()");
        checkEquality(component.getLowerBoundary(), restored.getLowerBoundary(),
                "component.getLowerBoundary() vs restored.getLowerBoundary()");
        assertEquals(component.getUpperBoundary(), restored.getUpperBoundary(),
                "component.getUpperBoundary() vs restored.getUpperBoundary()");
    }

    private void setLowerBoundaryCheck(@NotNull Long lower,
                                       @NotNull Long upper,
                                       @NotNull Long newLower) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "setLowerBoundary() initial");
        checkEquality(current, component.getCurrentValue(),
                "setLowerBoundary() currentValue initial");
        component.setLowerBoundary(newLower);
        minimal = getMinimal(newLower, maximal);
        maximal = getMaximal(newLower, maximal);
        current = clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "setLowerBoundary() new");
        checkEquality(current, component.getCurrentValue(),
                "setLowerBoundary() currentValue new");
    }

    private void setUpperBoundaryCheck(@NotNull Long lower,
                                       @NotNull Long upper,
                                       @NotNull Long newUpper) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "setUpperBoundary() initial");
        checkEquality(current, component.getCurrentValue(),
                "setUpperBoundary() currentValue initial");
        component.setUpperBoundary(newUpper);
        maximal = getMaximal(minimal, newUpper);
        minimal = getMinimal(minimal, newUpper);
        current = clamp(current, minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "setUpperBoundary() new");
        checkEquality(current, component.getCurrentValue(),
                "setUpperBoundary() currentValue new");
    }

    private void setBoundariesCheck(@NotNull Long lower,
                                    @NotNull Long upper,
                                    @NotNull Long newLower,
                                    @NotNull Long newUpper) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "setBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "setBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "setBoundaries() currentValue initial");
        component.setBoundaries(newLower, newUpper);
        minimal = getMinimal(newLower, newUpper);
        maximal = getMaximal(newLower, newUpper);
        current = clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "setBoundaries() lower new");
        checkEquality(maximal, component.getUpperBoundary(),
                "setBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "setBoundaries() currentValue new");
    }

    private void increaseLowerBoundaryCheck(@NotNull Long lower,
                                            @NotNull Long upper,
                                            @NotNull Long toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "increaseLowerBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "increaseLowerBoundary() currentValue initial");
        component.increaseLowerBoundary(toAdd);
        final Long minimal = getMinimal(getMinimal(lower, upper) + toAdd,
                getMaximal(lower, upper));
        final Long maximal = getMaximal(getMinimal(lower, upper) + toAdd,
                getMaximal(lower, upper));
        final Long current = clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseLowerBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "increaseLowerBoundary() currentValue new");
    }

    private void increaseUpperBoundaryCheck(@NotNull Long lower,
                                            @NotNull Long upper,
                                            @NotNull Long toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "increaseUpperBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "increaseUpperBoundary() currentValue initial");
        component.increaseUpperBoundary(toAdd);
        final Long minimal = getMinimal(getMaximal(lower, upper) + toAdd,
                getMinimal(lower, upper));
        final Long maximal = getMaximal(getMaximal(lower, upper) + toAdd,
                getMinimal(lower, upper));
        final Long current = clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseUpperBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "increaseUpperBoundary() currentValue new");
    }

    private void increaseBoundariesCheck(@NotNull Long lower,
                                         @NotNull Long upper,
                                         @NotNull Long toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue initial");
        component.increaseBoundaries(toAdd);
        minimal += toAdd;
        maximal += toAdd;
        current = clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void increaseBoundariesCheck(@NotNull Long lower,
                                         @NotNull Long upper,
                                         @NotNull Long lowerAdd,
                                         @NotNull Long upperAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = getMinimal(lower, upper);
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
        current = clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void decreaseLowerBoundaryCheck(@NotNull Long lower,
                                            @NotNull Long upper,
                                            @NotNull Long toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "decreaseLowerBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "decreaseLowerBoundary() currentValue initial");
        component.decreaseLowerBoundary(toSubtract);
        final Long minimal = getMinimal(getMinimal(lower, upper) - toSubtract,
                getMaximal(lower, upper));
        final Long maximal = getMaximal(getMinimal(lower, upper) - toSubtract,
                getMaximal(lower, upper));
        final Long current = clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "decreaseLowerBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "decreaseLowerBoundary() currentValue new");
    }

    private void decreaseUpperBoundaryCheck(@NotNull Long lower,
                                            @NotNull Long upper,
                                            @NotNull Long toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "decreaseUpperBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "decreaseUpperBoundary() currentValue initial");
        component.decreaseUpperBoundary(toSubtract);
        final Long minimal = getMinimal(getMaximal(lower, upper) - toSubtract,
                getMinimal(lower, upper));
        final Long maximal = getMaximal(getMaximal(lower, upper) - toSubtract,
                getMinimal(lower, upper));
        final Long current = clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "decreaseUpperBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "decreaseUpperBoundary() currentValue new");
    }

    private void decreaseBoundariesCheck(@NotNull Long lower,
                                         @NotNull Long upper,
                                         @NotNull Long toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "decreaseBoundaries() currentValue initial");
        component.decreaseBoundaries(toSubtract);
        minimal -= toSubtract;
        maximal -= toSubtract;
        current = clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }


    private void decreaseBoundariesCheck(@NotNull Long lower,
                                         @NotNull Long upper,
                                         @NotNull Long lowerSubtract,
                                         @NotNull Long upperSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = getMinimal(lower, upper);
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
        current = clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void percentModificationLowerCheck(@NotNull Long lower,
                                               @NotNull Long upper,
                                               @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Integer) currentValue initial");
        minimal = new LongArithmeticsProxyImpl()
                .modifyByPercentage(minimal, percent);
        final Long newMinimal = getMinimal(minimal, maximal);
        maximal = getMaximal(minimal, maximal);
        current = clamp(current, newMinimal, maximal);
        component.modifyLowerByPercentage(percent);
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Integer) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Integer) currentValue new");
    }

    private void percentModificationLowerCheck(@NotNull Long lower,
                                               @NotNull Long upper,
                                               @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Float) currentValue initial");
        minimal = new LongArithmeticsProxyImpl()
                .modifyByPercentage(minimal, percent);
        final Long newMinimal = getMinimal(minimal, maximal);
        maximal = getMaximal(minimal, maximal);
        current = clamp(current, newMinimal, maximal);
        component.modifyLowerByPercentage(percent);
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Float) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Float) currentValue new");
    }

    private void percentModificationUpperCheck(@NotNull Long lower,
                                               @NotNull Long upper,
                                               @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Integer) currentValue initial");
        maximal = new LongArithmeticsProxyImpl()
                .modifyByPercentage(maximal, percent);
        final Long newMaximal = getMaximal(minimal, maximal);
        minimal = getMinimal(minimal, maximal);
        current = clamp(current, minimal, newMaximal);
        component.modifyUpperByPercentage(percent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Integer) upper new");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Integer) currentValue new");
    }

    private void percentModificationUpperCheck(@NotNull Long lower,
                                               @NotNull Long upper,
                                               @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Float) upper initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Float) currentValue initial");
        maximal = new LongArithmeticsProxyImpl()
                .modifyByPercentage(maximal, percent);
        final Long newMaximal = getMaximal(minimal, maximal);
        minimal = getMinimal(minimal, maximal);
        current = clamp(current, minimal, newMaximal);
        component.modifyUpperByPercentage(percent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Float) upper new");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Float) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Long lower,
                                                    @NotNull Long upper,
                                                    @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue initial");
        final ArithmeticsProxy.LongArithmeticsProxy proxy =
                new LongArithmeticsProxyImpl();
        component.modifyBoundariesByPercentage(percent);
        maximal = proxy.modifyByPercentage(maximal, percent);
        minimal = proxy.modifyByPercentage(minimal, percent);
        current = clamp(current, minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper new");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Long lower,
                                                    @NotNull Long upper,
                                                    @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue initial");
        final ArithmeticsProxy.LongArithmeticsProxy proxy =
                new LongArithmeticsProxyImpl();
        component.modifyBoundariesByPercentage(percent);
        maximal = proxy.modifyByPercentage(maximal, percent);
        minimal = proxy.modifyByPercentage(minimal, percent);
        current = clamp(current, minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper new");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Long lower,
                                                    @NotNull Long upper,
                                                    @NotNull Integer lowerPercent,
                                                    @NotNull Integer upperPercent) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue initial");
        final ArithmeticsProxy.LongArithmeticsProxy proxy =
                new LongArithmeticsProxyImpl();
        maximal = proxy.modifyByPercentage(maximal, upperPercent);
        minimal = proxy.modifyByPercentage(minimal, lowerPercent);
        final Long newMaximal = getMaximal(minimal, maximal);
        final Long newMinimal = getMinimal(minimal, maximal);
        current = clamp(current, newMinimal, newMaximal);
        component.modifyBoundariesByPercentage(lowerPercent, upperPercent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper new");
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Long lower,
                                                    @NotNull Long upper,
                                                    @NotNull Float lowerPercent,
                                                    @NotNull Float upperPercent) {
        final ModifiableClampedNumberComponent.ModifiableClampedLongComponent
                component = prepareDefaultComponent(lower, upper);
        Long minimal = getMinimal(lower, upper);
        Long maximal = getMaximal(lower, upper);
        Long current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue initial");
        final ArithmeticsProxy.LongArithmeticsProxy proxy =
                new LongArithmeticsProxyImpl();
        maximal = proxy.modifyByPercentage(maximal, upperPercent);
        minimal = proxy.modifyByPercentage(minimal, lowerPercent);
        final Long newMaximal = getMaximal(minimal, maximal);
        final Long newMinimal = getMinimal(minimal, maximal);
        current = clamp(current, newMinimal, newMaximal);
        component.modifyBoundariesByPercentage(lowerPercent, upperPercent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper new");
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue new");
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
