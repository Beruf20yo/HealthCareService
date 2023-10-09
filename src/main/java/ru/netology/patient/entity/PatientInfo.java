package ru.netology.patient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfo {
    private String id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private HealthInfo healthInfo;
    public PatientInfo(String name, String surname, LocalDate birthday, HealthInfo healthInfo) {
        this(null, name, surname, birthday, healthInfo);
    }

    @Override
    public String toString() {
        return "PatientInfo{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", birthday=" + birthday +
            ", healthInfo=" + healthInfo +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientInfo that = (PatientInfo) o;
        return id.equals(that.id) &&
            name.equals(that.name) &&
            surname.equals(that.surname) &&
            birthday.equals(that.birthday) &&
            healthInfo.equals(that.healthInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthday, healthInfo);
    }
}
