package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyEnum;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptosPrice;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.CryptosPriceRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ar.edu.unq.desapp.grupoL.backenddesappapi.service.CryptoService.getResponseBody;

@Service
public class CryptosPriceService {
    @Autowired
    CryptosPriceRepository cryptosPriceRepository;

    public CryptosPriceService() {
        //Empty constructor
    }

    @Transactional
    public Optional<CryptosPrice> findById(Long id){
        return  cryptosPriceRepository.findById(id);
    }

    @Scheduled(cron = "0 0/1 * * * *")
    @Transactional
    public CryptosPrice createCryptosPrice() throws IOException {
        System.out.println("Comienzo a buscar cryptos");

        ArrayList<Float> prices = new ArrayList();
        for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values() ){
            Float price = this.getCryptoPrice(crypto.name());
            prices.add(price);
        }
        CryptosPrice cryptosPrice = new CryptosPrice(prices.get(0),prices.get(1),prices.get(2),prices.get(3),prices.get(4),prices.get(5),prices.get(6),
                                                    prices.get(7),prices.get(8),prices.get(9),prices.get(10),prices.get(11),prices.get(12),prices.get(13));
        System.out.println("Fin de busqueda");
        return this.cryptosPriceRepository.save(cryptosPrice);

    }



    public Float getCryptoPrice(String symbol) throws IOException {
        URL url = new URL("https://api1.binance.com/api/v3/ticker/price?symbol="+symbol);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        String response = getResponseBody(http);
        JSONObject json = new JSONObject(response);
        return json.getFloat("price");
    }

    public Boolean isThereAnyRecord() {
        return cryptosPriceRepository.anyRecord() ;
    }


    public CryptosPrice fetchCryptoPriceByDB(String cryptoSymbol) {
        return cryptosPriceRepository.getPriceOf(cryptoSymbol);
    }
}
