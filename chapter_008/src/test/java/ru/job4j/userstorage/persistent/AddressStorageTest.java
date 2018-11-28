package ru.job4j.userstorage.persistent;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AddressStorageTest {
    AddressStorage storage = AddressStorage.getInstance();

    @Test
    public void whenAddCountryWithCityThenTrue() {
        storage.addCity("TestCountry", "TestCity1");
        List<String> cities = storage.getCities("TestCountry");
        assertThat(cities.get(0), is("TestCity1"));
    }

    @Test
    public void whenAddCountryThanTrue() {
        storage.addCountry("TestCountry2");
        List<String> result = storage.getCountries();
        assertThat(result.contains("TestCountry2"), is(true));
    }

    @Test
    public void whenChecksSizeOfRussiaStorageThanThree() {
        storage.addCity("TestCountry3", "TestCity1");
        storage.addCity("TestCountry3", "TestCity2");
        List<String> cities = storage.getCities("TestCountry3");
        assertThat(cities.size(), is(2));
    }
}