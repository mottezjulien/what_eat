package what.eat.generic.brain;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BrainIntegrationTest {

    private final BrainRepositoryStringData repository = new BrainRepositoryStringData();
    private final Brain<StringData, StringDataIndicator> brain = new Brain<>(repository);

    @Test
    public void _withNoCondition() throws BrainException {
        BrainQuery<StringDataIndicator> query = new BrainQuery<>();
        BrainResult<StringData> result = brain.generate(query);

        assertThat(result)
                .hasSize(5)
                .allSatisfy(satisfy -> assertThat(repository.backDoor()).contains(satisfy));
    }


    @Test
    public void _withOneConditionAskNumberOne() throws BrainException {

        Brain<StringData, StringDataIndicator> brain = new Brain<>(repository);
        BrainQuery<StringDataIndicator> query = new BrainQuery<>();
        query.addAsk(aaaIndicator(), 5);

        BrainResult<StringData> result = brain.generate(query);

        assertThat(result)
                .hasSize(5)
                .allSatisfy(satisfy -> assertThat(repository.backDoor()).contains(satisfy))
                .allSatisfy(satisfy -> assertThat(satisfy.value()).startsWith("AAA"));
    }


    @Test
    public void _withOneConditionAskNumberThree() throws BrainException {

        Brain<StringData, StringDataIndicator> brain = new Brain<>(repository);
        BrainQuery<StringDataIndicator> query = new BrainQuery<>();
        query.addAsk(aaaIndicator(), 3);

        BrainResult<StringData> result = brain.generate(query);

        assertThat(result)
                .hasSize(5)
                .allSatisfy(satisfy -> assertThat(repository.backDoor()).contains(satisfy))
                .areAtLeast(3, startsWith("AAA"));
    }

    @Test
    public void _withThreeConditionsAskNumberOnes() throws BrainException {

        Brain<StringData, StringDataIndicator> brain = new Brain<>(repository);
        BrainQuery<StringDataIndicator> query = new BrainQuery<>();
        query.addAsk(aaaIndicator(), 1);
        query.addAsk(bbbIndicator(), 1);
        query.addAsk(cccIndicator(), 1);

        BrainResult<StringData> result = brain.generate(query);

        assertThat(result)
                .hasSize(5)
                .allSatisfy(satisfy -> assertThat(repository.backDoor()).contains(satisfy))
                .areAtLeast(1, startsWith("AAA"))
                .areAtLeast(1, startsWith("BBB"))
                .areAtLeast(1, startsWith("CCC"));
    }


    @Test
    public void _withOneAskAndOneNoMoreConditions() throws BrainException {

        Brain<StringData, StringDataIndicator> brain = new Brain<>(repository);
        BrainQuery<StringDataIndicator> query = new BrainQuery<>();
        query.addAsk(aaaIndicator(), 1);
        query.addNoMore(aaaIndicator(), 1);

        BrainResult<StringData> result = brain.generate(query);

        assertThat(result)
                .hasSize(5)
                .allSatisfy(satisfy -> assertThat(repository.backDoor()).contains(satisfy))
                .areExactly(1, startsWith("AAA"));
    }

    @Test
    public void _withTwosAskAndOneNoMoreConditions() throws BrainException {

        Brain<StringData, StringDataIndicator> brain = new Brain<>(repository);
        BrainQuery<StringDataIndicator> query = new BrainQuery<>();
        query.addAsk(aaaIndicator(), 2);
        query.addNoMore(aaaIndicator(), 2);
        query.addAsk(cccIndicator(), 1);
        query.addNoMore(cccIndicator(), 1);

        BrainResult<StringData> result = brain.generate(query);

        assertThat(result)
                .hasSize(5)
                .allSatisfy(satisfy -> assertThat(repository.backDoor()).contains(satisfy))
                .areExactly(2, startsWith("AAA"))
                .areExactly(1, startsWith("CCC"));
    }


    private StringDataIndicator aaaIndicator() {
        return new StringDataIndicator("AAA");
    }

    private StringDataIndicator bbbIndicator() {
        return new StringDataIndicator("BBB");
    }

    private StringDataIndicator cccIndicator() {
        return new StringDataIndicator("CCC");
    }

    private Condition<StringData> startsWith(String start) {
        return new Condition<>(data -> data.value().startsWith(start), "start with " + start);
    }

}

