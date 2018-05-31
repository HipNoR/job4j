package ru.job4j.profession;

public class Engineer extends Profession {
    public Build buildIt(House house) {
        Build build = new Build();
        return build.buildingOf(house);
    }
}
