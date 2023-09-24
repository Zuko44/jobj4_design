package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FoolTest {
    @Test
    public void whenFizzBuzz() {assertThat(Fool.check(15)).isEqualTo("FizzBuzz");}

    @Test
    public void whenFizz() {assertThat(Fool.check(3)).isEqualTo("Fizz");}

    @Test
    public void whenBuzz() {assertThat(Fool.check(5)).isEqualTo("Buzz");}

    @Test
    public void whenInt() {assertThat(Fool.check(2)).isEqualTo("2");}
}