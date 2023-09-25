package ru.job4j.template;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class GeneratorProduceTest {
    @Test
    void whenAddStringsAndThanReplaceTemplate() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String total = new GeneratorProduce().produce(template, args);
        assertThat(total).isEqualTo("I am Petr Arsntev, Who are you? ");
    }

    @Test
    void whenAddStringsNotInMapAndThanThrow() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        String total = new GeneratorProduce().produce(template, args);
        Assertions.assertThat(total).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAddStringsNotInTemplateAndThanThrow() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("mistake", "you");
        String total = new GeneratorProduce().produce(template, args);
        Assertions.assertThat(total).isInstanceOf(IllegalArgumentException.class);
    }
}