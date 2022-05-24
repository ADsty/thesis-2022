package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.dao.ConfirmationCodeDAO;

import java.util.HashMap;
import java.util.Random;

@Service
public class ConfirmationCodeService {

    private final ConfirmationCodeDAO confirmationCodeDAO;

    @Autowired
    public ConfirmationCodeService(ConfirmationCodeDAO confirmationCodeDAO) {
        this.confirmationCodeDAO = confirmationCodeDAO;
    }

    public Boolean checkConfirmationCode(String phoneNumber, Integer confirmationCode) {
        HashMap<String, Integer> confirmationCodeMap = confirmationCodeDAO.getConfirmationCodeMap();
        return confirmationCodeMap.get(phoneNumber).equals(confirmationCode);
    }

    public String setConfirmationCode(String phoneNumber) {
        Random random = new Random();
        Integer result = 10000000 + random.nextInt(89999999);
        HashMap<String, Integer> confirmationCodeMap = confirmationCodeDAO.getConfirmationCodeMap();
        confirmationCodeMap.put(phoneNumber, result);
        return sendConfirmationCode(phoneNumber, result);
    }

    public String sendConfirmationCode(String phoneNumber, Integer confirmationCode) {
        return "Код" + confirmationCode + "был отправлен на номер" + phoneNumber;
    }
}
