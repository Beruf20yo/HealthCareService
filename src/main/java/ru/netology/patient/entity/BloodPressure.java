package ru.netology.patient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodPressure {

    private int high;

    private int low;

    @Override
    public String toString() {
        return "BloodPressure{" +
            "high=" + high +
            ", low=" + low +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloodPressure that = (BloodPressure) o;
        return high == that.high &&
            low == that.low;
    }

    @Override
    public int hashCode() {
        return Objects.hash(high, low);
    }
}
