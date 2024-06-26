package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLast() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        assertThat(input).hasSize(4).containsSequence(1, 2, 3, 4);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.removeAll(input, List.of(3, 4, 5));
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenAddBeforeAndRemoveAll() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.removeAll(input, List.of(3, 5));
        assertThat(input).hasSize(3).containsSequence(1, 2, 4);
    }

    @Test
    void whenAddBeforeAndRemoveIfEven() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.<Integer>removeIf(input, i -> i % 2 == 0);
        assertThat(input).hasSize(3).containsSequence(1, 3, 5);
    }

    @Test
    void whenAddBeforeAndReplaceIfEven() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.<Integer>replaceIf(input, i -> i % 2 == 0, 7);
        assertThat(input).hasSize(5).containsSequence(1, 7, 3, 7, 5);
    }
}