package ru.job4j.regex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegExSearchTest {
    @Test
    void whenMaskToRegexTrue() {
        String convert = RegExSearch.maskToRegex("*?.txt?");
        Boolean rsl = "okokokon.txtp".matches(convert);
        assertThat(rsl).isTrue();
    }
}