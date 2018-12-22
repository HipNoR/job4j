package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindBySurname() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Roman", "Bednyashov", "555784", "Rosva")
        );
        var persons = phones.find("Bed");
        assertThat(persons.iterator().next().getName(), is("Roman"));
    }

    @Test
    public void whenFindByPhone() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Roman", "Bednyashov", "555784", "Rosva")
        );
        phones.add(
                new Person("Ivan", "Ivanov", "555784", "Rosva")
        );
        var persons = phones.find("555");
        assertThat(persons.get(0).getName(), is("Roman"));
        assertThat(persons.get(1).getName(), is("Ivan"));
    }
}