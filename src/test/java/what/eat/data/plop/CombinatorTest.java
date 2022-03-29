package what.eat.data.plop;

import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CombinatorTest {

    @Test
    public void testZeroElement() {
        Combinator<String> combinator = new Combinator();
        assertThat(combinator.combine()).isEmpty();
    }

    @Test
    public void testOneElement() {
        Combinator<String> combinator = new Combinator();
        combinator.add(List.of("AAA"));
        assertThat(combinator.combine())
                .hasSize(1)
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(1)
                        .contains("AAA"));
    }


    @Test
    public void testTwoElements_sameDimension() {
        Combinator<String> combinator = new Combinator();
        combinator.add(List.of("AAA", "BBB"));
        assertThat(combinator.combine())
                .hasSize(2)
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(1)
                        .contains("AAA"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(1)
                        .contains("BBB"));
    }


    @Test
    public void testTwoElements_twoDimensions() {
        Combinator<String> combinator = new Combinator();
        combinator.add(List.of("AAA"));
        combinator.add(List.of("KKK"));
        assertThat(combinator.combine())
                .hasSize(1)
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("AAA")
                        .contains("KKK"));
    }

    @Test
    public void testThreeElements_twoDimensions() {
        Combinator<String> combinator = new Combinator();
        combinator.add(List.of("AAA", "BBB"));
        combinator.add(List.of("KKK"));
        assertThat(combinator.combine())
                .hasSize(2)
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("AAA")
                        .contains("KKK"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("BBB")
                        .contains("KKK"));
    }

    @Test
    public void testFourElements_twoDimensions() {
        Combinator<String> combinator = new Combinator();
        combinator.add(List.of("AAA", "BBB"));
        combinator.add(List.of("KKK", "LLL"));
        assertThat(combinator.combine())
                .hasSize(4)
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("AAA")
                        .contains("KKK"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("BBB")
                        .contains("KKK"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("AAA")
                        .contains("LLL"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("BBB")
                        .contains("LLL"));
    }




    @Test
    public void testFourElements_withEmptyDimension() {
        Combinator<String> combinator = new Combinator();
        combinator.add(List.of("AAA", "BBB"));
        combinator.add(List.of());
        combinator.add(List.of("KKK", "LLL"));
        assertThat(combinator.combine())
                .hasSize(0);
    }


    @Test
    public void testXXXElements_twoDimensions() {
        Combinator<String> combinator = new Combinator();
        combinator.add(List.of("AAA", "BBB", "CCC", "DDD"));
        combinator.add(List.of("KKK", "LLL", "MMM"));
        assertThat(combinator.combine())
                .hasSize(12)
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("AAA")
                        .contains("KKK"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("BBB")
                        .contains("KKK"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("CCC")
                        .contains("KKK"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("DDD")
                        .contains("KKK"))

                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("AAA")
                        .contains("LLL"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("BBB")
                        .contains("LLL"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("CCC")
                        .contains("LLL"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("DDD")
                        .contains("LLL"))

                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("AAA")
                        .contains("MMM"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("BBB")
                        .contains("MMM"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("CCC")
                        .contains("MMM"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(2)
                        .contains("DDD")
                        .contains("MMM"));
    }


    @Test
    public void testXXXElements_XXXDimensions() {
        Combinator<String> combinator = new Combinator();
        combinator.add(List.of("AAA", "BBB", "CCC"));
        combinator.add(List.of("KKK"));
        combinator.add(List.of("MMM", "LLL"));
        combinator.add(List.of("WWW", "XXX", "YYY", "ZZZ"));
        final ListAssert<List<String>> resultAssert = assertThat(combinator.combine())
                .hasSize(24);

        assertXXX(resultAssert, "AAA");
        assertXXX(resultAssert, "BBB");
        assertXXX(resultAssert, "CCC");
    }

    private ListAssert<List<String>> assertXXX(ListAssert<List<String>> resultAssert, String first) {
        return resultAssert
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(4)
                        .contains(first)
                        .contains("KKK")
                        .contains("MMM")
                        .contains("WWW"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(4)
                        .contains(first)
                        .contains("KKK")
                        .contains("MMM")
                        .contains("XXX"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(4)
                        .contains(first)
                        .contains("KKK")
                        .contains("MMM")
                        .contains("YYY"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(4)
                        .contains(first)
                        .contains("KKK")
                        .contains("MMM")
                        .contains("ZZZ"))

                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(4)
                        .contains(first)
                        .contains("KKK")
                        .contains("LLL")
                        .contains("WWW"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(4)
                        .contains(first)
                        .contains("KKK")
                        .contains("LLL")
                        .contains("XXX"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(4)
                        .contains(first)
                        .contains("KKK")
                        .contains("LLL")
                        .contains("YYY"))
                .anySatisfy(satisfy -> assertThat(satisfy)
                        .hasSize(4)
                        .contains(first)
                        .contains("KKK")
                        .contains("LLL")
                        .contains("ZZZ"));
    }

}