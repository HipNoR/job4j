package ru.job4j.profession;

public class Doctor extends Profession {
    public Diagnose heal (Patient patient){
       Diagnose check = new Diagnose();
       return check.diagnoseOf(patient);
    }
}
