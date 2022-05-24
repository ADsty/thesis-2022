package ru.vitaliy.petrov.server.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ConfirmationCodeDAO {
    HashMap<String, Integer> confirmationCodeMap;

    {
        confirmationCodeMap = new HashMap<>();
    }

    public HashMap<String, Integer> getConfirmationCodeMap() {
        return confirmationCodeMap;
    }
}
