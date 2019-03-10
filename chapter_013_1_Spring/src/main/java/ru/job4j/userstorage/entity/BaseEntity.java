package ru.job4j.userstorage.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Abstract class for all entities.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1
 * @since 0.1
 * 08.03.2019
 */
@MappedSuperclass
@EqualsAndHashCode
@Getter
@Setter
@ToString
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
