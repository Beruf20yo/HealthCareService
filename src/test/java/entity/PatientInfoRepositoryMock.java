package entity;

import lombok.Setter;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;

@Setter
public class PatientInfoRepositoryMock implements PatientInfoRepository {
    private PatientInfo patientInfo;
    private String infoStr;

    @Override
    public PatientInfo getById(String id) {
        return patientInfo;
    }

    @Override
    public String add(PatientInfo patientInfo) {
        return infoStr;
    }

    @Override
    public PatientInfo remove(String id) {
        return patientInfo;
    }

    @Override
    public PatientInfo update(PatientInfo patientInfo) {
        return this.patientInfo;
    }
}
