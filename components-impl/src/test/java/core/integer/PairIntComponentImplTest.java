package core.integer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.rougegibbons.landsanddungeons.components.core.numbers.constant.integer.PairIntComponentImpl;
import ru.rougegibbons.landsanddungeons.components.models.ComponentModel;
import ru.rougegibbons.landsanddungeons.components.models.core.numbers.PairIntComponentModel;
import ru.rougegibbons.landsanddungeons.utils.constants.Constants;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class PairIntComponentImplTest {
    @Test
    public void idGeneratorTest() {
        final PairIntComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final PairIntComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(1, secondComponent.getId() - firstComponent.getId(),
                "difference between adjacent components' ids is always one.");
    }

    @Test
    public void getFirstValueTest() {
        final PairIntComponentImpl firstComponent = prepareZeroOneHundredComponent();
        assertEquals(Constants.ZERO_INT, firstComponent.getFirstValue().intValue(),
                "first value shall be zero.");
        final PairIntComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(Constants.PERCENTAGE_CAP_INT, secondComponent.getFirstValue().intValue(),
                "first value shall be 100.");
        assertNotEquals(firstComponent.getFirstValue(), secondComponent.getFirstValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getSecondValueTest() {
        final PairIntComponentImpl firstComponent = prepareZeroOneHundredComponent();
        assertEquals(Constants.PERCENTAGE_CAP_INT, firstComponent.getSecondValue().intValue(),
                "first value shall be zero.");
        final PairIntComponentImpl secondComponent = prepareOneHundredZeroComponent();
        assertEquals(Constants.ZERO_INT, secondComponent.getSecondValue().intValue(),
                "first value shall be 100.");
        assertNotEquals(firstComponent.getSecondValue(), secondComponent.getSecondValue(),
                "components' first values shall be different.");
    }

    @Test
    public void getBothTest() {
        final PairIntComponentImpl firstComponent = prepareZeroOneHundredComponent();
        final List<Integer> storedData = firstComponent.getBoth();
        assertEquals(firstComponent.getFirstValue(), storedData.get(0),
                "first value in the list and in component shall be equal.");
        assertEquals(firstComponent.getSecondValue(), storedData.get(1),
                "second value in the list and in component shall be equal.");
        final PairIntComponentImpl secondComponent = prepareOneHundredZeroComponent();
        final List<Integer> storedDataTwo = secondComponent.getBoth();
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
        final PairIntComponentImpl component = prepareZeroOneHundredComponent();
        final ComponentModel rawModel = component.pack();
        assertThat(rawModel, instanceOf(PairIntComponentModel.class));
        final PairIntComponentModel model = (PairIntComponentModel)rawModel;
        assertEquals(component.getId(), model.getId(),
                "component and model ids shall be equal.");
        assertEquals(component.getFirstValue(), model.getFirstValue(),
                "component and model first values shall be equal.");
        assertEquals(component.getSecondValue(), model.getSecondValue(),
                "component and model second values shall be equal.");
        final PairIntComponentImpl deserializedComponent = new PairIntComponentImpl(model);
        assertEquals(component.getId(), deserializedComponent.getId(),
                "source and deserialized components' ids shall be equal.");
        assertEquals(component.getFirstValue(), deserializedComponent.getFirstValue(),
                "source and deserialized components' first values shall be equal.");
        assertEquals(component.getSecondValue(), deserializedComponent.getSecondValue(),
                "source and deserialized components' ids shall be equal.");
    }

    private @NotNull PairIntComponentImpl prepareComponent(@NotNull Integer firstValue,
                                                           @NotNull Integer secondValue) {
        return new PairIntComponentImpl(firstValue, secondValue);
    }

    private @NotNull PairIntComponentImpl prepareZeroOneHundredComponent() {
        return prepareComponent(Constants.ZERO_INT, Constants.PERCENTAGE_CAP_INT);
    }

    private @NotNull PairIntComponentImpl prepareOneHundredZeroComponent() {
        return prepareComponent(Constants.PERCENTAGE_CAP_INT, Constants.ZERO_INT);
    }
}
