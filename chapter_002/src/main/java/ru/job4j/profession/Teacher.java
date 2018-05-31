package ru.job4j.profession;

public class Teacher extends Profession{
    public Education lesson(Student student) {
        Education teach = new Education();
        return teach.educationOf(student);
    }
}
