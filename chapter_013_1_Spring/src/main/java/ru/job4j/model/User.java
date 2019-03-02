package ru.job4j.model;

import lombok.*;

/**
 * Simple user class.
 * Lombok used.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 17.02.2019
 */
@Data
@Builder
public class User {
    private String name;
}
