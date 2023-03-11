package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere").isNotEmpty();
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube").isNotEmpty();
    }

    @Test
    void whenGetNumberOfVerticesIsEight() {
        Box box = new Box(8, 4);
        int name = box.getNumberOfVertices();
        assertThat(name).isEqualTo(8).isEven().isLessThan(9);
    }

    @Test
    void whenGetNumberOfVerticesIsFour() {
        Box box = new Box(4, 4);
        int name = box.getNumberOfVertices();
        assertThat(name).isEqualTo(4).isBetween(3, 5).isGreaterThan(2);
    }

    @Test
    void whenIsExist() {
        Box box = new Box(4, 4);
        boolean name = box.isExist();
        assertThat(name).isTrue().isNotEqualTo(false).isNotNull();
    }

    @Test
    void whenIsNotExist() {
        Box box = new Box(-1, 4);
        boolean name = box.isExist();
        assertThat(name).isFalse().isNotEqualTo(true).isEqualTo(false);
    }

    @Test
    void whenAreaIsZero() {
        Box box = new Box(0, 4);
        double name = Math.round (box.getArea());
        assertThat(name).isEqualTo(201).isNotNull().isGreaterThan(200);
    }

    @Test
    void whenAreaIsFour() {
        Box box = new Box(4, 4);
        double name = Math.round (box.getArea());
        assertThat(name).isEqualTo(28).isNotInfinite().isNotNegative();
    }
}