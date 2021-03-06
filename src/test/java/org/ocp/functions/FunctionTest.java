package org.ocp.functions;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.ocp.dto.PersonDto;
import org.ocp.testutils.TestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.LongToIntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTest {

    BiFunction<String, String, PersonDto> personBuilderBiFunction = (interest, name) -> PersonDto.builder()
                                                                                                 .interest(interest)
                                                                                                 .name(name).build();

    Function<Integer, String> sumNumEquation = num -> num + " + " + num + " = " + (2*num);

    UnaryOperator<String> checkerSumEquation = equation -> {
        String[] equationArr = equation.split("=");
        Integer result = parseInt(StringUtils.trimToEmpty(equationArr[1]));
        String[] numbersArr = equationArr[0].split("\\+");
        Integer numbersSum = parseInt(numbersArr[0].trim()) + parseInt(numbersArr[1].trim());
        return equation + (result.equals(numbersSum) ? " (correct)" : " (incorrect)");

    };

    Function<PersonDto, String> personInterestExtractorFunction = person -> person.getInterest();

    UnaryOperator<String> upperCaseOperation = str -> str.toUpperCase();

    @Test
    void functionAndThenTest() {
        PersonDto person = TestUtils.buildPerson();
        String expectation = person.getInterest().toUpperCase();
        String result = personInterestExtractorFunction.andThen(upperCaseOperation).apply(person);
        assertEquals(expectation, result);
    }


    @Test
    void biFunctionAndThenTest() {
        String interest = "Football";
        String expectation = interest.toUpperCase();
        String result = personBuilderBiFunction.andThen(personInterestExtractorFunction.andThen(upperCaseOperation)).apply(interest, "Max");
        assertEquals(expectation, result);
    }

    @Test
    void functionComposeTest() {
        String expectation = "2 + 2 = 4 (correct)";
        String result = checkerSumEquation.compose(sumNumEquation).apply(2);
        assertEquals(expectation, result);
    }

    @Test
    @Tag("ch4")
    void applyAsLong() {
        ToLongFunction<PersonDto> personToLongFunction = PersonDto::getId;
        PersonDto personDto = TestUtils.buildPerson();
        accept(personDto, 1L, l -> (int)l ,PersonDto::setId);
        assertEquals(1L, personToLongFunction.applyAsLong(personDto));
    }

    private void accept(PersonDto person, long id, LongToIntFunction converter, ObjIntConsumer<PersonDto> setter) {
        setter.accept(person, converter.applyAsInt(id));
    }

    @Test
    @Tag("ch4")
    void applyAsInt() {
        ToIntFunction<PersonDto> function = PersonDto::getId;
        PersonDto personDto = TestUtils.buildPerson();
        accept(personDto, 1, PersonDto::setId);
        assertEquals(1, function.applyAsInt(personDto));
    }

    void accept(PersonDto person, int id, ObjIntConsumer<PersonDto> setter) {
        setter.accept(person, id);
    }

    @Test
    @Tag("ch4")
    void applyAsDouble() {
        ToIntFunction<PersonDto> function = PersonDto::getId;
        PersonDto personDto = TestUtils.buildPerson();
        personDto.setId(1);
        accept(personDto, 1D, d -> (int)d, PersonDto::setId);
        assertEquals(1D, function.applyAsInt(personDto));
    }

    @Test
    void filter() {

        List<Movie> movies = Arrays.asList(
                new Movie("Titanic", Genre.DRAMA, 'U'),
                new Movie("Psycho", Genre.THRILLER, 'U'),
                new Movie("Oldboy", Genre.THRILLER, 'R'),
                new Movie("Shining", Genre.HORROR, 'U')
        );
        movies.stream()
              .filter(mov->mov.getRating()=='R')
              .peek(mov->System.out.println(mov.getName()))
              .map(mov->mov.getName()).forEach((d) -> {});
    }

    @Test
    void effectivelyFinal() {
        String name = "Bob";
        String val;
        Function<String, String> f = String::toUpperCase;

        // when
        val = f.apply(name);

        //then
        assertEquals("BOB", val);
    }

    class TestClass {
        public double process(double payment, int rate) {
            double defaultrate = 0.10;
            if (rate > 10) {
                defaultrate = rate;
            }
            class Implement {
                int apply(double data, Double defaultRate) {
                    BiFunction<Integer, Double, Integer> f = (x, m) -> x + (int) (x * m);
                    return f.apply((int) data, defaultRate);
                }

                void testNotEffectivelyFinal() {
                    //defaultrate //Not effectively final
                }
            }
            Implement i = new Implement();
            return i.apply(payment, defaultrate);
        }
    }


    private void accept(PersonDto person, Double id, DoubleToIntFunction converter, ObjIntConsumer<PersonDto> idSetter) {
        idSetter.accept(person, converter.applyAsInt(id));
    }

    private enum Genre  {DRAMA, THRILLER, HORROR, ACTION };

    private class Movie{

        private Genre genre;
        private String name;
        private char rating = 'R';

        Movie(String name, Genre genre, char rating) {
            this.name = name;
            this.genre = genre;
            this.rating = rating;
        }

        public Genre getGenre() {
            return genre;
        }

        public String getName() {
            return name;
        }

        public char getRating() {
            return rating;
        }
    }

}
