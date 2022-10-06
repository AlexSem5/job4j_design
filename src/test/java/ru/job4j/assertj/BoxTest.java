package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .startsWith("S")
                .isNotEmpty();
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(5, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .contains("k")
                .isNotBlank();
    }

    @Test
    void whenNumberOfVertices4() {
        Box box = new Box(4, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(4)
                .isGreaterThan(3)
                .isNotZero();
    }

    @Test
    void whenNumberOfVertices8() {
        Box box = new Box(8, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(8)
                .isLessThan(9)
                .isPositive();
    }

    @Test
    void whenSphereExists() {
        Box box = new Box(0, 10);
        Boolean exist = box.isExist();
        assertThat(exist).isTrue()
                .isNotEqualTo(false);
    }

    @Test
    void whenSphereDoesNotExist() {
        Box box = new Box(8, 0);
        Boolean exist = box.isExist();
        assertThat(exist).isFalse()
                .isNotEqualTo(true);
    }

    @Test
    void whenAreaIs6() {
        Box box = new Box(8, 1);
        double area = box.getArea();
        assertThat(area).isEqualTo(6)
                .isGreaterThan(5)
                .isNotZero()
                .isLessThan(11);

    }

    @Test
    void whenAreaIs314dot16() {
        Box box = new Box(0, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(314.16D, withPrecision(0.01))
                .isGreaterThan(314.15D)
                .isCloseTo(314.16D, withPrecision(0.01))
                .isNotZero()
                .isLessThan(315);

    }
}