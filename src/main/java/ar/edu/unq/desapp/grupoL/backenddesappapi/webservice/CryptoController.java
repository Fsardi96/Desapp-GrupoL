package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.Helpers.CurrentDateTime;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyEnum;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyList;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.CryptoService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api (tags = "CryptoCurrency services")
@Tag(name = "CryptoCurrency services", description = "Manage cryptocurrencies")
@RestController
@Transactional
@RequestMapping("/api/crypto")
public class CryptoController {
    @Autowired
    CryptoService cryptoService;
    private RestTemplate restTemplate = new RestTemplate();






    @Operation(summary = "Get a cryptocurrency price")
    @GetMapping("/getCrypoValue/{cryptoSymbol}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyValue(@Parameter(description = "The cryptocurrency symbol that needs to be fetched", required = true)
                                                                     @PathVariable String cryptoSymbol){
        return ResponseEntity.ok().body(cryptoService.findCrypto(cryptoSymbol));
    }

    @Operation(summary = "Get all cryptocurrency prices")
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

    @GetMapping("/api/usd_price")
    public ResponseEntity<String> getUSDPrice() throws IOException {
        URL url = new URL("https://api.estadisticasbcra.com/usd_of");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODE4Njc5NzUsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJmYWN1c2FyZGk5NkBnbWFpbC5jb20ifQ.HC2l0NbZ6vdZLEEOUkkZnSy7Ce8x3nbvg2m7nLzdmERmTyKphnVK1v8SE7g-VzYohHc97hyDXwkQViq6w0gasg");
        String response = CryptoService.getResponseBody(http);
        JSONArray array = new JSONArray(response);
        JSONObject json = new JSONObject();
        json.put("usd_prices", array.get(array.length() -1));
        return ResponseEntity.ok().body(json.toString());
    }


}
