package ru.job4j.userstorage.persistent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class keeps countries and cities in RAM.
 * The name of the country is the key to the list of cities.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 24.11.2018
 */
public class AddressStorage {

    private final static AddressStorage INSTANCE = new AddressStorage();

    private final ConcurrentHashMap<String, List<String>> store = new ConcurrentHashMap<>();

    private AddressStorage() {
        fillStorage();
    }

    public static AddressStorage getInstance() {
        return INSTANCE;
    }

    /**
     * Returns lust of all countries in map.
     * @return list of countries.
     */
    public List<String> getCountries() {
        List<String> result = new ArrayList<>();
        result.addAll(store.keySet());
        Collections.sort(result);
        return result;
    }

    /**
     * Returns list of cities by country name.
     * @param country name.
     * @return list of cities.
     */
    public List<String> getCities(String country) {
        return store.get(country);
    }

    /**
     * Adds a country to the repository.
     * @param country name.
     */
    public void addCountry(String country) {
        store.put(country, new ArrayList<>());
    }

    /**
     * Adds a city to the repository.
     * If country does not exist, creates new pair country-cities.
     * @param country name.
     * @param city name.
     */
    public void addCity(String country, String city) {
        List<String> cities;
        if (!store.containsKey(country)) {
            cities = new ArrayList<>();
            store.put(country, cities);
        } else {
            cities = store.get(country);
        }
        cities.add(city);
        Collections.sort(cities);
    }

    private void fillStorage() {
        addCity("Russia", "Moscow");
        addCity("Russia", "Saint-Petersburg");
        addCity("Russia", "Kaluga");
        addCity("USA", "New York");
        addCity("USA", "Washington");
        addCity("Japan", "Tokyo");
        addCity("Japan", "Kyoto");
    }
}
