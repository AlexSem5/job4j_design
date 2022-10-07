package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSameElementsAs(List.of("first", "second", "three", "four", "five"))
                .containsExactly("first", "second", "three", "four", "five")
                .contains("five", Index.atIndex(4))
                .containsExactlyElementsOf(List.of("first", "second", "three", "four", "five"))
                .startsWith("first")
                .containsSequence("three", "four", "five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).isNotEmpty()
                .allSatisfy(s -> {
                    assertThat(s).isNotBlank();
                    assertThat(s.length()).isGreaterThan(3);
                })
                .hasSize(5)
                .anyMatch(s -> s.equals("five"))
                .noneMatch(s -> s.startsWith("a"));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "third");
        assertThat(map).hasSize(3)
                .containsExactlyInAnyOrderEntriesOf(Map.of("first", 0, "second", 1, "third", 2))
                .containsKey("first")
                .doesNotContainKey("a")
                .allSatisfy((s, integer) -> {
                    assertThat(s).isNotEmpty();
                    assertThat(integer).isNotNegative();
                });
    }
}