package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.CryptoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CryptoService {
    @Autowired
    CryptoRepository cryptoRepository;
    private RestTemplate restTemplate = new RestTemplate();


    @Transactional
    public CryptoCurrency findCrypto(String cryptoSymbol) {
        CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + cryptoSymbol, CryptoCurrency.class);
        LocalDateTime date = LocalDateTime.now();
        if (entity != null) {
            entity.setLastUpdateDateAndTime(date);
        }
        return entity;
    }

    @Transactional
    public float getUSDPrice() throws IOException {
        URL url = new URL("https://api.estadisticasbcra.com/usd_of");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODYwMTU4MjUsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJhaWxpbnNwNTBAZ21haWwuY29tIn0.ZWeaojfLJy2rJU768pgv7_67Tap4CGwnwLpg7cE_8Q5evl3gm7DAf8RHFijPDCzQUixHA2RT_KI2qKEUZW5QqA");
        // http.setRequestProperty("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODE4Njc5NzUsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJmYWN1c2FyZGk5NkBnbWFpbC5jb20ifQ.HC2l0NbZ6vdZLEEOUkkZnSy7Ce8x3nbvg2m7nLzdmERmTyKphnVK1v8SE7g-VzYohHc97hyDXwkQViq6w0gasg");
        String response = CryptoService.getResponseBody(http);
        JSONArray array = new JSONArray(response);
        JSONObject json = new JSONObject();
        json.put("dolar_today", array.get(array.length() -1));
        return  json.getJSONObject("dolar_today").getFloat("v");
    }



    public static String getResponseBody(HttpURLConnection conn) {
        BufferedReader br = null;
        StringBuilder body = null;
        String line = "";
        try {//  w  w w .j  a  v a 2s . c  o m
            br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            body = new StringBuilder();
            while ((line = br.readLine()) != null)
                body.append(line);
            return body.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
