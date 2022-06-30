package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyEnum;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptosPrice;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.CryptosPriceRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;


@Transactional
@Service
public class CryptosPriceService {
    @Autowired
    CryptosPriceRepository cryptosPriceRepository;

    public CryptosPriceService() {
        //Empty constructor
    }

    private static Logger log = LoggerFactory.getLogger(CryptosPriceService.class);

    @Transactional
    public Optional<CryptosPrice> findById(Long id){
        return  cryptosPriceRepository.findById(id);
    }

    @Scheduled(cron = "0 0/3 * * * *")
    @Transactional
    public CryptosPrice createCryptosPrice() throws IOException {
        log.info("**************** Getting Crypto Prices ****************");

        ArrayList<Float> prices = new ArrayList();
        for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values() ){
            Float price = this.getCryptoPrice(crypto.name());
            prices.add(price);
        }
        CryptosPrice cryptosPrice = new CryptosPrice(prices.get(0),prices.get(1),prices.get(2),prices.get(3),prices.get(4),prices.get(5),prices.get(6),
                                                    prices.get(7),prices.get(8),prices.get(9),prices.get(10),prices.get(11),prices.get(12),prices.get(13));
        log.info("**************** Finished ****************");
        return this.cryptosPriceRepository.save(cryptosPrice);

    }

    @Transactional
    public Float getCryptoPrice(String symbol) throws IOException {
        URL url = new URL("https://api1.binance.com/api/v3/ticker/price?symbol="+symbol);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        String response = getResponseBody(http);
        JSONObject json = new JSONObject(response);
        return json.getFloat("price");
    }

    @Transactional
    public float getUSDPrice() throws IOException {
        URL url = new URL("https://api.estadisticasbcra.com/usd_of");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        // http.setRequestProperty("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODYwMTU4MjUsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJhaWxpbnNwNTBAZ21haWwuY29tIn0.ZWeaojfLJy2rJU768pgv7_67Tap4CGwnwLpg7cE_8Q5evl3gm7DAf8RHFijPDCzQUixHA2RT_KI2qKEUZW5QqA");
        http.setRequestProperty("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODE4Njc5NzUsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJmYWN1c2FyZGk5NkBnbWFpbC5jb20ifQ.HC2l0NbZ6vdZLEEOUkkZnSy7Ce8x3nbvg2m7nLzdmERmTyKphnVK1v8SE7g-VzYohHc97hyDXwkQViq6w0gasg");
        String response = getResponseBody(http);
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
