package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void whenGetEmptyNames() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenNullInputToParseThenNPE() {
        NameLoad nameLoad = new NameLoad();
        String[] names = null;
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void whenEmptyInputToParseThenIAE() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEmptyInputToParseMessageContainsArrayIsEmty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .contains("array is empty");
    }

    @Test
    void checkEmptyKeyInputToParse() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=map", "2=loop"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkEmptyKeyInputToParseMassageDoesNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=map", "2=loop"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .contains("does not contain a key");
    }

    @Test
    void whenEmptyDataInputToParseThenMassageDoesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1=", "2=loop"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .contains("does not contain a value");
    }

    @Test
    void whenEmptyDataInputToParseThenIAE() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1=", "2=loop"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoEqualSignInputToParseThenIAE() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1=job", "2+loop"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoEqualSignInputThenMessageDoesNotContainTheSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"1=job", "2+loop"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .contains("does not contain the symbol '='");
    }
}