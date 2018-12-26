package ru.job4j.vacparser;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class describes the vacancy.
 * Contains the name of the job and the date of publication.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 18.10.2018
 */
public class Vacancy {
    int id;
    LocalDate posted;
    String title;
    String url;
    String description;

    public Vacancy(LocalDate posted, String title, String url, String description) {
        this.posted = posted;
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public Vacancy(int id, LocalDate posted, String title, String url, String description) {
        this.id = id;
        this.posted = posted;
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public LocalDate getPosted() {
        return posted;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Vacancy id: %s. Date of post: %s. Title %s. URL: %s", id, posted, title, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(posted, vacancy.posted)
                && Objects.equals(title, vacancy.title)
                && Objects.equals(url, vacancy.url)
                && Objects.equals(description, vacancy.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posted, title, url, description);
    }
}
