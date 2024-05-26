package ru.job4j.sandbox.assertj;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ArrayIteratorTest {
    @Test
    void whenMultiCallHasNextThenTrue() {
        ArrayIterator iterator = new ArrayIterator(
                new int[] {1, 2, 3}
        );
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenReadSequence() {
        ArrayIterator iterator = new ArrayIterator(
                new int[] {1, 2, 3}
        );
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
    }

    @Test
    void  whenNextFromEmpty() {
        ArrayIterator iterator = new ArrayIterator(
                new int[] {}
        );
        assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
    }

}