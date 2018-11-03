package core.longint;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.longint.PairLongComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.AbstractComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairLongComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class PairLongComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final PairLongComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final PairLongComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final PairLongComponentImpl firstComponent = prepareZeroOneHundredComponent();
        assertEquals(Constants.ZERO_LONG, firstComponent.getFirstValue().longValue(),
                "first value shall be zero.");
        final PairLongComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals((long) Constants.PERCENTAGE_CAP_INT, secondComponent.getFirstValue().longValue(),
                "first value shall be 100.");
        assertNotEquals(firstComponent.getFirstValue(), secondComponent.getFirstValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getSecondValueTest() {
        final PairLongComponentImpl firstComponent = prepareZeroOneHundredComponent();
        assertEquals((long) Constants.PERCENTAGE_CAP_INT, firstComponent.getSecondValue().longValue(),
                "first value shall be zero.");
        final PairLongComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(Constants.ZERO_LONG, secondComponent.getSecondValue().longValue(),
                "first value shall be 100.");
        assertNotEquals(firstComponent.getSecondValue(), secondComponent.getSecondValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getBothTest() {
        final PairLongComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final List<Long> storedData = firstComponent.getBoth();
        assertEquals(firstComponent.getFirstValue(), storedData.get(0),
                "first value in the list and in component shall be equal.");
        assertEquals(firstComponent.getSecondValue(), storedData.get(1),
                "second value in the list and in component shall be equal.");
        final PairLongComponentImpl secondComponent = prepareOneHundredZeroComponent();
        final List<Long> storedDataTwo = secondComponent.getBoth();
        assertEquals(secondComponent.getFirstValue(), storedDataTwo.get(0),
                "first value in the list and in component shall be equal.");
        assertEquals(secondComponent.getSecondValue(), storedDataTwo.get(1),
                "second value in the list and in component shall be equal.");
        assertEquals(storedData.get(0), storedDataTwo.get(1),
                "first element in first component list shall be equal" +
                        " to second element in second component's list");
        assertEquals(storedData.get(1), storedDataTwo.get(0),
                "second element in first component list shall be equal" +
                        " to first element in second component's list");
    }

    @Test
    public void packTest() {
        final PairLongComponentImpl component = prepareZeroOneHundredComponent();
        final AbstractComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairLongComponentModel.class));
        final PairLongComponentModel model = (PairLongComponentModel)rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        assertEquals(component.getFirstValue(), model.getFirstValue(),
                "component and model first values shall be equal.");
        assertEquals(component.getSecondValue(), model.getSecondValue(),
                "component and model second values shall be equal.");
        final PairLongComponentImpl deserializedComponent = new PairLongComponentImpl(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        assertEquals(component.getFirstValue(), deserializedComponent.getFirstValue(),
                "source and deserialized components' first values shall be equal.");
        assertEquals(component.getSecondValue(), deserializedComponent.getSecondValue(),
                "source and deserialized components' ids shall be equal.");
    }

    private @NotNull PairLongComponentImpl prepareComponent(@NotNull Long firstValue,
                                                            @NotNull Long secondValue) {
        return new PairLongComponentImpl(firstValue, secondValue);
    }

    private @NotNull PairLongComponentImpl prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_LONG, (long) Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull PairLongComponentImpl prepareOneHundredZeroComponent() {
        return prepareComponent((long) Constants.PERCENTAGE_CAP_INT, Constants.ZERO_LONG);
    }
}
