package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "third", "forth", "fifth");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "sixth")
                .doesNotContain("first", Index.atIndex(1))
                .containsExactly("first", "second", "third", "forth", "fifth");

    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "second", "third", "forth", "fifth");
        assertThat(list).hasSize(6)
                .endsWith("forth", "fifth")
                .startsWith("first", "second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "sixth")
                .doesNotContain("first", Index.atIndex(1))
                .containsExactlyInAnyOrder("second", "first", "third", "forth", "fifth", "second")
                .containsSequence("third", "forth")
                .anySatisfy(s -> {
                    assertThat(s.length()).isLessThan(7);
                    assertThat(s).isNotBlank();
                })
                .noneMatch(s -> s.equals("tenth"));

        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(1).isEqualTo("second");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "third", "forth", "fifth", "second");
        assertThat(set).hasSize(5)
                .containsAnyOf("zero", "second", "sixth")
                .doesNotContain("tenth")
                .containsExactlyInAnyOrder("second", "first", "third", "forth", "fifth")
                .anySatisfy(s -> {
                    assertThat(s.length()).isLessThan(7);
                    assertThat(s).isNotBlank();
                })
                .anyMatch(s -> s.equals("forth"))
                .filteredOn(s -> s.length() != 5)
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "third", "forth", "fifth", "second");
        assertThat(map).hasSize(5)
                .containsKeys("second", "fifth")
                .doesNotContainValue(8)
                .containsEntry("third", 2)
                .containsValues(1, 2, 3, 4)
                .containsOnlyKeys("first", "second", "third", "forth", "fifth");
    }
}