package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ConfigTest {

    @Test
    void when2PairsWithoutComments() {
        String path = "./data/pair_without_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name1")).isEqualTo("smb1");
        assertThat(config.value("name2")).isEqualTo("smb2");
    }

    @Test
    void when2PairsWithCommentsAndEmptyLinesAndEmptySpaces() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name1")).isEqualTo("smth1");
        assertThat(config.value("name2")).isEqualTo("smth2");
    }

    @Test
    void whenEmptyKey() {
        String path = "./data/empty_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> {
            config.load();
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty key");
    }

    @Test
    void whenEmptyValue() {
        String path = "./data/empty_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> {
            config.load();
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty value");
    }

    @Test
    void whenHaveNoEquals() {
        String path = "./data/no_equals.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> {
            config.load();
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Have no '='");
    }

    @Test
    void whenSeveralEquals() {
        String path = "./data/several_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name1")).isEqualTo("smb1=1");
        assertThat(config.value("name2")).isEqualTo("smb2=");
    }

    @Test
    void whenHaveNoPropertiesFile() {
        String path = "./data/no_such_file.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> {
            config.load();
        })
                .isInstanceOf(IllegalStateException.class)
                .hasCauseInstanceOf(FileNotFoundException.class);
    }
}