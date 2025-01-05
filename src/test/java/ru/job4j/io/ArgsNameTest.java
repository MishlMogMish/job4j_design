package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ArgsNameTest {

    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenKeyNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("The key: 'Xms' is missing");
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("No arguments provided to the program.");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Argument '-=?msg=Exit=' doesn't contain a key.");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Hello="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Argument '-=?msg=Hello=' doesn't contain a key.");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown3() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=NoKey"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Argument '-=NoKey' doesn't contain a key.");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-request="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Argument '-request=' is missing a value.");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-encoding="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Argument '-encoding=' is missing a value.");

    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown3() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-emptyValue="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Argument '-emptyValue=' is missing a value.");

    }

    @Test
    void whenThereNoEqualSignThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-request?msgHello"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Argument '-request?msgHello' doesn't contain '='");
    }

    @Test
    void whenThereNoEqualSignThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-NoEqual"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Argument '-NoEqual' doesn't contain '='");
    }

    @Test
    void whenNoHyphenPrefixThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "request=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("This argument 'request=?msg=Exit=' doesn't start with a '-'");
    }

    @Test
    void whenNoHyphenPrefixThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "key=value"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("This argument 'key=value' doesn't start with a '-'");
    }
}