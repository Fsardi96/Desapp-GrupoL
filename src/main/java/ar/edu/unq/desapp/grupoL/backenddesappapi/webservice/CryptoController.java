package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;


import ar.edu.unq.desapp.grupoL.backenddesappapi.Helpers.CurrentDateTime;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyEnum;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyList;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Transactional
@RequestMapping("/api/crypto")
public class CryptoController {
    @Autowired
    CryptoService cryptoService;
    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/getCrypoValue/{symbol}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyValue(@PathVariable String symbol){
        CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, CryptoCurrency.class);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (entity != null) {
            entity.setLastUpdateDateAndTime(formatter.format(date));
        }
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/getCrypoValue/all")
    public ResponseEntity<CryptoCurrencyList> getAllCryptoCurrencyPrices() {
        CryptoCurrencyList list = new CryptoCurrencyList();
        for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values()) {
            CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + crypto.name(), CryptoCurrency.class);
            if (entity != null) {
                entity.setLastUpdateDateAndTime(CurrentDateTime.getNewDateString());
            }
            list.addCrypto(entity);

        }
        return ResponseEntity.ok().body(list);
    }

}
