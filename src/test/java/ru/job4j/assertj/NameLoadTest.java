package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void whenNoSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"VasiliyIvanov"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the symbol");
    }

    @Test
    void whenNoKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=VasiliyIvanov"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key");
    }

    @Test
    void whenNoValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("VasiliyIvanov="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value");
    }

    @Test
    void whenOk() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("Vasiliy=Ivanov", "Vasiliy=Petrov", "Ivan=Ivanov");
        assertThat(nameLoad.getMap()).containsAllEntriesOf(Map.of(
                "Vasiliy", "Ivanov+Petrov",
                "Ivan", "Ivanov")
        );
    }
}