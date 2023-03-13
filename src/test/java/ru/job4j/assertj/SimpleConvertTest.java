package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
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
        List<String> array = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .containsOnly("first", "second", "three", "four", "five")
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> array = simpleConvert.toSet("first", "second", "three", "four", "five", null);
        assertThat(array).hasSize(6)
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .containsOnly("first", "second", "three", "four", "five", null)
                .isNotEmpty()
                .isNotNull()
                .containsNull()
                .doesNotContain("nine");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> array = simpleConvert.toMap("first", "second", "three", "four", "five", null);
        assertThat(array).hasSize(6)
                .isNotEmpty()
                .isNotNull()
                .hasSizeBetween(4, 6)
                .hasSizeGreaterThan(3)
                .doesNotContainValue(9)
                .doesNotContainKey("nine")
                .doesNotContainKeys("ottomans", "muher")
                .containsKey("first");
    }
}