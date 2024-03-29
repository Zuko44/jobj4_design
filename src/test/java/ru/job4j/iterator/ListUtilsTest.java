package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    public void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void whenRemoveIf() {
        ListUtils.removeIf(input, num -> num > 2);
        assertThat(input).containsSequence(1);
    }

    @Test
    public void whenReplaceIf() {
        ListUtils.replaceIf(input, num -> num > 2, 5);
        assertThat(input).containsSequence(1, 5);
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 1, 4);
        assertThat(input).hasSize(4);
        ListUtils.addAfter(input, 2, 5);
        assertThat(input).hasSize(5);
        ListUtils.removeAll(input, list);
        assertThat(input).containsSequence(4, 5);
    }
}