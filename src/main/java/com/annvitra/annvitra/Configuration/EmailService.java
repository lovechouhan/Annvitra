package com.annvitra.annvitra.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class EmailService {

    private String apiToken = "5cf14c731894be5414786af233281ee6";

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendEmail(String toEmail, String subject, String text) {
        String url = "https://send.api.mailtrap.io/api/send";

        Map<String, Object> body = new HashMap<>();
        body.put("from", Map.of(
                "email", "hello@mailtrap.io",
                "name", "Mailtrap Test"
        ));
        body.put("to", List.of(
                Map.of("email", toEmail)
        ));
        body.put("subject", subject);
        body.put("text", text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);

        HttpEntity<Map<String, Object>> req = new HttpEntity<>(body, headers);

        restTemplate.postForObject(url, req, String.class);
    }

    public void sendOtp(String email, String OTP) {
        String subject = "Your OTP for ANNVITRA registration";
        String text = "Your OTP for ANNVITRA registration is: " + OTP;
        sendEmail(email, subject, text);
    }
}
