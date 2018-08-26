package core.floatpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.floatpoint.PairFloatComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairFloatComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;
import ru.rougegibbons.landsanddungeons.utils.functions.FloatComparator;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public final class PairFloatComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final PairFloatComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final PairFloatComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final PairFloatComponentImpl firstComponent = prepareZeroOneHundredComponent();
        assertTrue(FloatComparator.areEqual(Constants.ZERO_FLOAT, firstComponent.getFirstValue()),
                "first value shall be zero.");
        final PairFloatComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertTrue(FloatComparator.areEqual(Constants.PERCENTAGE_CAP_FLOAT, secondComponent.getFirstValue()),
                "first value shall be 100.");
        assertFalse(FloatComparator.areEqual(firstComponent.getFirstValue(), secondComponent.getFirstValue()),
                "components' first values shall be different.");
    }

    @Test
    public void getSecondValueTest() {
        final PairFloatComponentImpl firstComponent = prepareZeroOneHundredComponent();
        assertEquals(Constants.PERCENTAGE_CAP_FLOAT, firstComponent.getSecondValue().intValue(),
                "first value shall be zero.");
        final PairFloatComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertTrue(FloatComparator.areEqual(Constants.ZERO_FLOAT, secondComponent.getSecondValue()),
                "first value shall be 100.");
        assertFalse(FloatComparator.areEqual(firstComponent.getSecondValue(), secondComponent.getSecondValue()),
                "components' first values shall be different.");
    }

    @Test
    public void getBothTest() {
        final PairFloatComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final List<Float> storedData = firstComponent.getBoth();
        assertTrue(FloatComparator.areEqual(firstComponent.getFirstValue(), storedData.get(0)),
                "first value in the list and in component shall be equal.");
        assertTrue(FloatComparator.areEqual(firstComponent.getSecondValue(), storedData.get(1)),
                "second value in the list and in component shall be equal.");
        final PairFloatComponentImpl secondComponent = prepareOneHundredZeroComponent();
        final List<Float> storedDataTwo = secondComponent.getBoth();
        assertTrue(FloatComparator.areEqual(secondComponent.getFirstValue(), storedDataTwo.get(0)),
                "first value in the list and in component shall be equal.");
        assertTrue(FloatComparator.areEqual(secondComponent.getSecondValue(), storedDataTwo.get(1)),
                "second value in the list and in component shall be equal.");
        assertTrue(FloatComparator.areEqual(storedData.get(0), storedDataTwo.get(1)),
                "first element in first component list shall be equal" +
                        " to second element in second component's list");
        assertTrue(FloatComparator.areEqual(storedData.get(1), storedDataTwo.get(0)),
                "second element in first component list shall be equal" +
                        " to first element in second component's list");
    }

    @Test
    public void packTest() {
        final PairFloatComponentImpl component = prepareZeroOneHundredComponent();
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairFloatComponentModel.class));
        final PairFloatComponentModel model = (PairFloatComponentModel)rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        assertTrue(FloatComparator.areEqual(component.getFirstValue(), model.getFirstValue()),
                "component and model first values shall be equal.");
        assertTrue(FloatComparator.areEqual(component.getSecondValue(), model.getSecondValue()),
                "component and model second values shall be equal.");
        final PairFloatComponentImpl deserializedComponent = new PairFloatComponentImpl(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        assertTrue(FloatComparator.areEqual(component.getFirstValue(), deserializedComponent.getFirstValue()),
                "source and deserialized components' first values shall be equal.");
        assertTrue(FloatComparator.areEqual(component.getSecondValue(), deserializedComponent.getSecondValue()),
                "source and deserialized components' ids shall be equal.");
    }

    private @NotNull PairFloatComponentImpl prepareComponent(@NotNull Float firstValue,
                                                             @NotNull Float secondValue) {
        return new PairFloatComponentImpl(firstValue, secondValue);
    }

    private @NotNull PairFloatComponentImpl prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_FLOAT, Constants.PERCENTAGE_CAP_FLOAT);
    }

    private @NotNull PairFloatComponentImpl prepareOneHundredZeroComponent() {
        return prepareComponent(Constants.PERCENTAGE_CAP_FLOAT, Constants.ZERO_FLOAT);
    }
}
