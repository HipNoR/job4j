package ru.job4j.userstorage.entity;

import lombok.*;

import javax.persistence.*;

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
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(callSuper = true)
@Table(name = "users")
public class User extends BaseEntity {

    @Column
    private String name;

}
