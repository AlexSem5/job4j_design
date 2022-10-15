package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;

    @BeforeEach
    void init() {
        linked = new ForwardLinked<>();
    }

    @Test
    void whenSize0ThenReturnFalse() {
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        linked.addLast(1);
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        linked.addLast(1);
        linked.addLast(2);
        linked.addLast(3);
        linked.addLast(4);
        assertThat(linked).containsSequence(1, 2, 3, 4);
        assertThat(linked.revert()).isTrue();
        assertThat(linked).containsSequence(4, 3, 2, 1);
    }

    @Test
    void whenAddAndRevertTrue1() {
        linked.addLast(1);
        linked.addLast(2);
        assertThat(linked).containsSequence(1, 2);
        assertThat(linked.revert()).isTrue();
        assertThat(linked).containsSequence(2, 1);
    }
}