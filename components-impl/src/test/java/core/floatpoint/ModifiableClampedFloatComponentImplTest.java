package core.floatpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.modifiable.floatpoint.ModifiableClampedFloatComponentImpl;
import ru.rougegibbons.landsanddungeons.components.interfaces.core.numbers.ModifiableClampedNumberComponent;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.ClampedFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.constants.IdsConstants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatComparator;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatMath;
import ru.rougegibbons.landsanddungeons.utils.functions.MathUtils;
import ru.rougegibbons.landsanddungeons.utils.proxies.ArithmeticsProxy;
import ru.rougegibbons.landsanddungeons.utils.proxies.FloatArithmeticsProxyImpl;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("Duplicates, NewMethodNamingConvention, SameParameterValue")
public final class ModifiableClampedFloatComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                componentOne = prepareZeroOneHundredComponent();
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                componentTwo = prepareZeroOneHundredComponent();
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

    @Test
    public void setLowerBoundaryTest() {
        setLowerBoundaryCheck(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setLowerBoundaryCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        setLowerBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void setUpperBoundaryTest() {
        setUpperBoundaryCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setUpperBoundaryCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        setUpperBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void setBoundariesTest() {
        setBoundariesCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        setBoundariesCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        setBoundariesCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseLowerBoundaryTest() {
        increaseLowerBoundaryCheck(
                Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        increaseLowerBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseLowerBoundaryCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseUpperBoundaryTest() {
        increaseUpperBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        increaseUpperBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        increaseUpperBoundaryCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseBoundariesSimilarTest() {
        increaseBoundariesCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck((float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT, (float) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void increaseBoundariesDifferentTest() {
        increaseBoundariesCheck(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        increaseBoundariesCheck(
                Constants.ZERO_FLOAT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        increaseBoundariesCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseLowerBoundaryTest() {
        decreaseLowerBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT);
        decreaseLowerBoundaryCheck(
                Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseLowerBoundaryCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseUpperBoundaryTest() {
        decreaseUpperBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        decreaseUpperBoundaryCheck(Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
        decreaseUpperBoundaryCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBoundariesSimilarTest() {
        decreaseBoundariesCheck(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void decreaseBoundariesDifferentTest() {
        decreaseBoundariesCheck(Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT);
        decreaseBoundariesCheck(Constants.ZERO_FLOAT,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT);
        decreaseBoundariesCheck(
                (float) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_FLOAT,
                (float) -1 * Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT);
    }

    @Test
    public void modifyLowerByPercentageTest() {
        percentModificationLowerCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(
                Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(
                Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationLowerCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationLowerCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyUpperByPercentageTest() {
        percentModificationUpperCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                (float) Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(
                Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(
                Constants.ZERO_FLOAT,
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationUpperCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationUpperCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyBoundariesByPercentageSimilarTest() {
        percentModificationBoundariesCheck(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck((float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
    }

    @Test
    public void modifyBoundariesByPercentageDifferentTest() {
        percentModificationBoundariesCheck(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_INT, Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Constants.PERCENTAGE_CAP_FLOAT, Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                (float) Constants.PERCENTAGE_CAP_INT,
                (float) Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT);
        percentModificationBoundariesCheck(
                (float) -1 * Constants.PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_INT,
                Constants.WIDE_PERCENTAGE_CAP_INT);
        percentModificationBoundariesCheck(
                (float) -1 * Constants.PERCENTAGE_CAP_INT,
                (float) -1 * Constants.WIDE_PERCENTAGE_CAP_INT,
                -1 * Constants.PERCENTAGE_CAP_FLOAT,
                FloatMath.divide((float) Constants.WIDE_PERCENTAGE_CAP_INT,
                        (float) Constants.PERCENTAGE_CAP_INT));
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
    prepareComponent(
            @NotNull Float lowerBoundary,
            @NotNull Float upperBoundary,
            @NotNull Float currentValue) {
        return new ModifiableClampedFloatComponentImpl(
                lowerBoundary, upperBoundary, currentValue);
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
    prepareDefaultComponent(
            @NotNull Float lowerBoundary,
            @NotNull Float upperBoundary) {
        return new ModifiableClampedFloatComponentImpl(lowerBoundary, upperBoundary);
    }

    private @NotNull ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
    prepareZeroOneHundredComponent() {
        return prepareDefaultComponent(Constants.ZERO_FLOAT, (float) Constants.PERCENTAGE_CAP_INT);
    }

    private void getLowerBoundaryCheck(@NotNull Float lower,
                                       @NotNull Float upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "getLowerBoundary()");
    }

    private void getUpperBoundaryCheck(@NotNull Float lower,
                                       @NotNull Float upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
                prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "getUpperBoundary()");
    }

    private void getBoundariesCheck(@NotNull Float lower,
                                    @NotNull Float upper) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
                prepareComponent(lower, upper, current);
        final Float expected = MathUtils.clamp(current, getMinimal(lower, upper),
                getMaximal(lower, upper));
        checkEquality(expected, component.getCurrentValue(), "getCurrentValue()");
    }

    private void isEmptyCheck(@NotNull Float lower,
                              @NotNull Float upper,
                              @NotNull Float current) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent component =
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
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent restored =
                new ModifiableClampedFloatComponentImpl(model);
        checkEquality(component.getId(), restored.getId(),
                "component.getId() vs restored.getId()");
        checkEquality(component.getLowerBoundary(), restored.getLowerBoundary(),
                "component.getLowerBoundary() vs restored.getLowerBoundary()");
        assertEquals(component.getUpperBoundary(), restored.getUpperBoundary(),
                "component.getUpperBoundary() vs restored.getUpperBoundary()");
    }

    private void setLowerBoundaryCheck(@NotNull Float lower,
                                       @NotNull Float upper,
                                       @NotNull Float newLower) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
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

    private void setUpperBoundaryCheck(@NotNull Float lower,
                                       @NotNull Float upper,
                                       @NotNull Float newUpper) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
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

    private void setBoundariesCheck(@NotNull Float lower,
                                    @NotNull Float upper,
                                    @NotNull Float newLower,
                                    @NotNull Float newUpper) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
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

    private void increaseLowerBoundaryCheck(@NotNull Float lower,
                                            @NotNull Float upper,
                                            @NotNull Float toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMinimal(lower, upper), component.getLowerBoundary(),
                "increaseLowerBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "increaseLowerBoundary() currentValue initial");
        component.increaseLowerBoundary(toAdd);
        final Float minimal = getMinimal(
                FloatMath.add(getMinimal(lower, upper), toAdd),
                getMaximal(lower, upper));
        final Float maximal = getMaximal(
                FloatMath.add(getMinimal(lower, upper), toAdd),
                getMaximal(lower, upper));
        final Float current = MathUtils.clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseLowerBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "increaseLowerBoundary() currentValue new");
    }

    private void increaseUpperBoundaryCheck(@NotNull Float lower,
                                            @NotNull Float upper,
                                            @NotNull Float toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "increaseUpperBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "increaseUpperBoundary() currentValue initial");
        component.increaseUpperBoundary(toAdd);
        final Float minimal = getMinimal(
                FloatMath.add(getMaximal(lower, upper), toAdd),
                getMinimal(lower, upper));
        final Float maximal = getMaximal(
                FloatMath.add(getMaximal(lower, upper), toAdd),
                getMinimal(lower, upper));
        final Float current = MathUtils.clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseUpperBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "increaseUpperBoundary() currentValue new");
    }

    private void increaseBoundariesCheck(@NotNull Float lower,
                                         @NotNull Float upper,
                                         @NotNull Float toAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue initial");
        component.increaseBoundaries(toAdd);
        minimal = FloatMath.add(minimal, toAdd);
        maximal += FloatMath.add(maximal, toAdd);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void increaseBoundariesCheck(@NotNull Float lower,
                                         @NotNull Float upper,
                                         @NotNull Float lowerAdd,
                                         @NotNull Float upperAdd) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "increaseLowerBoundary() currentValue initial");
        component.increaseBoundaries(lowerAdd, upperAdd);
        minimal = getMinimal(
                FloatMath.add(getMinimal(lower, upper), lowerAdd),
                FloatMath.add(getMaximal(lower, upper), upperAdd));
        maximal = getMaximal(
                FloatMath.add(getMinimal(lower, upper), lowerAdd),
                FloatMath.add(getMaximal(lower, upper), upperAdd));
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void decreaseLowerBoundaryCheck(@NotNull Float lower,
                                            @NotNull Float upper,
                                            @NotNull Float toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "decreaseLowerBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "decreaseLowerBoundary() currentValue initial");
        component.decreaseLowerBoundary(toSubtract);
        final Float minimal = getMinimal(
                FloatMath.subtract(getMinimal(lower, upper), toSubtract),
                getMaximal(lower, upper));
        final Float maximal = getMaximal(
                FloatMath.subtract(getMinimal(lower, upper), toSubtract),
                getMaximal(lower, upper));
        final Float current = MathUtils.clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "decreaseLowerBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "decreaseLowerBoundary() currentValue new");
    }

    private void decreaseUpperBoundaryCheck(@NotNull Float lower,
                                            @NotNull Float upper,
                                            @NotNull Float toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        checkEquality(getMaximal(lower, upper), component.getUpperBoundary(),
                "decreaseUpperBoundary() lower initial");
        checkEquality(getMinimal(lower, upper), component.getCurrentValue(),
                "decreaseUpperBoundary() currentValue initial");
        component.decreaseUpperBoundary(toSubtract);
        final Float minimal = getMinimal(
                FloatMath.subtract(getMaximal(lower, upper), toSubtract),
                getMinimal(lower, upper));
        final Float maximal = getMaximal(
                FloatMath.subtract(getMaximal(lower, upper), toSubtract),
                getMinimal(lower, upper));
        final Float current = MathUtils.clamp(getMinimal(lower, upper), minimal, maximal);
        checkEquality(maximal, component.getUpperBoundary(),
                "decreaseUpperBoundary() lower new");
        checkEquality(current, component.getCurrentValue(),
                "decreaseUpperBoundary() currentValue new");
    }

    private void decreaseBoundariesCheck(@NotNull Float lower,
                                         @NotNull Float upper,
                                         @NotNull Float toSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "decreaseBoundaries() currentValue initial");
        component.decreaseBoundaries(toSubtract);
        minimal = FloatMath.subtract(minimal, toSubtract);
        maximal = FloatMath.subtract(maximal, toSubtract);
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }


    private void decreaseBoundariesCheck(@NotNull Float lower,
                                         @NotNull Float upper,
                                         @NotNull Float lowerSubtract,
                                         @NotNull Float upperSubtract) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = getMinimal(lower, upper);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower initial");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper initial");
        checkEquality(current, component.getCurrentValue(),
                "increaseLowerBoundary() currentValue initial");
        component.decreaseBoundaries(lowerSubtract, upperSubtract);
        minimal = getMinimal(FloatMath.subtract(
                getMinimal(lower, upper), lowerSubtract),
                FloatMath.subtract(getMaximal(lower, upper), upperSubtract));
        maximal = getMaximal(
                FloatMath.subtract(getMinimal(lower, upper), lowerSubtract),
                FloatMath.subtract(getMaximal(lower, upper), upperSubtract));
        current = MathUtils.clamp(current, minimal, maximal);
        checkEquality(minimal, component.getLowerBoundary(),
                "increaseBoundaries() lower new");
        checkEquality(maximal, component.getUpperBoundary(),
                "increaseBoundaries() upper new");
        checkEquality(current, component.getCurrentValue(),
                "increaseBoundaries() currentValue new");
    }

    private void percentModificationLowerCheck(@NotNull Float lower,
                                               @NotNull Float upper,
                                               @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Integer) currentValue initial");
        minimal = new FloatArithmeticsProxyImpl()
                .modifyByPercentage(minimal, percent);
        final Float newMinimal = getMinimal(minimal, maximal);
        maximal = getMaximal(minimal, maximal);
        current = MathUtils.clamp(current, newMinimal, maximal);
        component.modifyLowerByPercentage(percent);
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Integer) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Integer) currentValue new");
    }

    private void percentModificationLowerCheck(@NotNull Float lower,
                                               @NotNull Float upper,
                                               @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Float) currentValue initial");
        minimal = new FloatArithmeticsProxyImpl()
                .modifyByPercentage(minimal, percent);
        final Float newMinimal = getMinimal(minimal, maximal);
        maximal = getMaximal(minimal, maximal);
        current = MathUtils.clamp(current, newMinimal, maximal);
        component.modifyLowerByPercentage(percent);
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyLowerByPercentage(Float) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyLowerByPercentage(Float) currentValue new");
    }

    private void percentModificationUpperCheck(@NotNull Float lower,
                                               @NotNull Float upper,
                                               @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Integer) currentValue initial");
        maximal = new FloatArithmeticsProxyImpl()
                .modifyByPercentage(maximal, percent);
        final Float newMaximal = getMaximal(minimal, maximal);
        minimal = getMinimal(minimal, maximal);
        current = MathUtils.clamp(current, minimal, newMaximal);
        component.modifyUpperByPercentage(percent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Integer) upper new");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Integer) currentValue new");
    }

    private void percentModificationUpperCheck(@NotNull Float lower,
                                               @NotNull Float upper,
                                               @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Float) upper initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Float) currentValue initial");
        maximal = new FloatArithmeticsProxyImpl()
                .modifyByPercentage(maximal, percent);
        final Float newMaximal = getMaximal(minimal, maximal);
        minimal = getMinimal(minimal, maximal);
        current = MathUtils.clamp(current, minimal, newMaximal);
        component.modifyUpperByPercentage(percent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyUpperByPercentage(Float) upper new");
        checkEquality(current, component.getCurrentValue(),
                "modifyUpperByPercentage(Float) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Float lower,
                                                    @NotNull Float upper,
                                                    @NotNull Integer percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue initial");
        final ArithmeticsProxy.FloatArithmeticsProxy proxy =
                new FloatArithmeticsProxyImpl();
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

    private void percentModificationBoundariesCheck(@NotNull Float lower,
                                                    @NotNull Float upper,
                                                    @NotNull Float percent) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue initial");
        final ArithmeticsProxy.FloatArithmeticsProxy proxy =
                new FloatArithmeticsProxyImpl();
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

    private void percentModificationBoundariesCheck(@NotNull Float lower,
                                                    @NotNull Float upper,
                                                    @NotNull Integer lowerPercent,
                                                    @NotNull Integer upperPercent) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue initial");
        final ArithmeticsProxy.FloatArithmeticsProxy proxy =
                new FloatArithmeticsProxyImpl();
        maximal = proxy.modifyByPercentage(maximal, upperPercent);
        minimal = proxy.modifyByPercentage(minimal, lowerPercent);
        final Float newMaximal = getMaximal(minimal, maximal);
        final Float newMinimal = getMinimal(minimal, maximal);
        current = MathUtils.clamp(current, newMinimal, newMaximal);
        component.modifyBoundariesByPercentage(lowerPercent, upperPercent);
        checkEquality(newMaximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Integer) upper new");
        checkEquality(newMinimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Integer) lower new");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Integer) currentValue new");
    }

    private void percentModificationBoundariesCheck(@NotNull Float lower,
                                                    @NotNull Float upper,
                                                    @NotNull Float lowerPercent,
                                                    @NotNull Float upperPercent) {
        final ModifiableClampedNumberComponent.ModifiableClampedFloatComponent
                component = prepareDefaultComponent(lower, upper);
        Float minimal = getMinimal(lower, upper);
        Float maximal = getMaximal(lower, upper);
        Float current = minimal;
        checkEquality(maximal, component.getUpperBoundary(),
                "modifyBoundariesByPercentage(Float) upper initial");
        checkEquality(minimal, component.getLowerBoundary(),
                "modifyBoundariesByPercentage(Float) lower initial");
        checkEquality(current, component.getCurrentValue(),
                "modifyBoundariesByPercentage(Float) currentValue initial");
        final ArithmeticsProxy.FloatArithmeticsProxy proxy =
                new FloatArithmeticsProxyImpl();
        maximal = proxy.modifyByPercentage(maximal, upperPercent);
        minimal = proxy.modifyByPercentage(minimal, lowerPercent);
        final Float newMaximal = getMaximal(minimal, maximal);
        final Float newMinimal = getMinimal(minimal, maximal);
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
