package service.medical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
public class MedicalServiceImplMockitoTest {
    @Mock
    private SendAlertService sendAlertService;
    @Mock
    private PatientInfoRepository patientInfoRepository;
    @InjectMocks
    private MedicalServiceImpl medicalService;


    @Test
    void checkBloodPressureBadTest() {
        Mockito.when(patientInfoRepository.getById("1")).thenReturn(
                new PatientInfo("1", "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.6"), new BloodPressure(120, 80))));
        medicalService.checkBloodPressure("1", new BloodPressure(120, 60));
        Mockito.verify(sendAlertService, Mockito.times(1)).send("Warning, patient with id: 1, need help");
    }

    @Test
    void checkTemperatureGoodTest() {
        Mockito.when(patientInfoRepository.getById("1")).thenReturn(
                new PatientInfo("1", "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.6"), new BloodPressure(120, 80))));
        medicalService.checkTemperature("1", new BigDecimal("36.7"));
        Mockito.verify(sendAlertService, Mockito.times(0)).send("Warning, patient with id: 1, need help");
    }

    @Test
    void checkTemperatureBadTest() {
        Mockito.when(patientInfoRepository.getById("1")).thenReturn(
                new PatientInfo("1", "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("39.6"), new BloodPressure(120, 80))));

        SendAlertService sendAlertService = Mockito.spy(new SendAlertServiceImpl());
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        medicalService.checkTemperature("1", new BigDecimal("36.7"));

        Mockito.verify(sendAlertService).send(argumentCaptor.capture());
        Assertions.assertEquals("Warning, patient with id: 1, need help", argumentCaptor.getValue());
    }
}
