package service.alert;

import ru.netology.patient.service.alert.SendAlertService;

public class SendAlertServiceMock implements SendAlertService {
    @Override
    public void send(String message) {
        System.out.println(message);
    }
}
