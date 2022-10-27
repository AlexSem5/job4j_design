package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user.name")).isEqualTo("Vasiliy");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment_and_blank_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user.name")).isEqualTo("Vasiliy");
    }

    @Test
    void whenException() {
        String path = "./data/exception.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValid() {
        String path = "./data/valid.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user.name")).isEqualTo("value=Vasiliy");
    }
}