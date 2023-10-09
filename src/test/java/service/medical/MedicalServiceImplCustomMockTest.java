package service.medical;

import entity.PatientInfoRepositoryMock;
import org.junit.jupiter.api.*;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.service.medical.MedicalServiceImpl;
import service.alert.SendAlertServiceMock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MedicalServiceImplCustomMockTest {
    private static final PatientInfoRepositoryMock patientInfoRepository = new PatientInfoRepositoryMock();
    private static final SendAlertServiceMock sendAlertService = new SendAlertServiceMock();
    private static MedicalServiceImpl medicalService;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void settings() {
        patientInfoRepository.setInfoStr("1");
        patientInfoRepository.setPatientInfo(
                new PatientInfo("1", "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("37.0"), new BloodPressure(120, 80))));
        medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void checkBloodPressureBadTest() {
        medicalService.checkBloodPressure("1", new BloodPressure(120, 60));
        Assertions.assertEquals("Warning, patient with id: 1, need help", outputStreamCaptor.toString().trim());

    }

    @Test
    void checkBloodPressureGoodTest() {
        medicalService.checkBloodPressure("1", new BloodPressure(120, 80));
        Assertions.assertEquals("", outputStreamCaptor.toString().trim());

    }

    @Test
    void checkTemperatureBadTest() {
        medicalService.checkTemperature("1", new BigDecimal("35.4"));
        Assertions.assertEquals("Warning, patient with id: 1, need helpWarning, patient with id: 1, need help", outputStreamCaptor.toString().trim());
    }

    @Test
    void checkTemperatureGoodTest() {
        medicalService.checkTemperature("1", new BigDecimal("36.7"));
        Assertions.assertEquals("", outputStreamCaptor.toString().trim());
    }
}
