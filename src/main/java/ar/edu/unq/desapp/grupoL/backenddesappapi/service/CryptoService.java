package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CryptoService {
    @Autowired
    CryptoRepository cryptoRepository;
    private RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public CryptoCurrency findCrypto(String cryptoSymbol) {
        CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + "BNBUSDT", CryptoCurrency.class);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (entity != null) {
            entity.setLastUpdateDateAndTime(formatter.format(date));
        }
        return entity;
    }
}
