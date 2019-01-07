package org.ocp.functions;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.ocp.dto.PersonDto;
import org.ocp.utils.TestUtils;

import java.util.function.BiFunction;
import java.util.function.Function;
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
}
