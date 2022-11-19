package ru.job4j.regex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RegExSearchTest {
    
    @Test
    void whenMaskToRegexIsTrue() {
        String convert = RegExSearch.maskToRegex("*?.txt?");
        Boolean result = "okokokon.txtp".matches(convert);
        assertThat(result).isTrue();
    }
    
    @Test
    void whenValidateIsTrue() {
        String[] args = {"-d=c:/", "-n=*.?xt", "-t=mask", "-o=log.txt"};
        NewArgsName regEx = NewArgsName.of(args);
        assertThat(RegExSearch.validate(regEx)).isTrue();
    }
    
    @Test
    void whenExceptionIsThrown() {
        String[] args = {"-d=c:/", "-n=*|.?xt", "-t=mask", "-o=log.txt"};
        NewArgsName regEx = NewArgsName.of(args);
        assertThatThrownBy(() -> RegExSearch.validate(regEx))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Incorrect file name");
    }
}
