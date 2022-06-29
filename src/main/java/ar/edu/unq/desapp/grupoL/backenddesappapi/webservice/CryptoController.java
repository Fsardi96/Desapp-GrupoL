package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyEnum;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyList;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.BaseCryptoDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.CryptoDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.CryptosPriceService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;


@Api (tags = "CryptoCurrency services")
@Tag(name = "CryptoCurrency services", description = "Manage cryptocurrencies")
@RestController
@RequestMapping("/api/crypto")
public class CryptoController {
    @Autowired
    CryptosPriceService cryptosPriceService;
    private RestTemplate restTemplate = new RestTemplate();


    @Operation(summary = "Get a cryptocurrency price")
    @GetMapping("/getCrypoValue/{cryptoSymbol}")
    public ResponseEntity<CryptoDTO> getCryptoCurrencyValue(@Parameter(description = "The cryptocurrency symbol that needs to be fetched", required = true)
                                                                     @PathVariable String cryptoSymbol) throws IOException {
        Float price = cryptosPriceService.getCryptoPrice(cryptoSymbol);
        return ResponseEntity.ok().body(new CryptoDTO(cryptoSymbol, price, LocalDateTime.now().toString()) );
    }


    @Operation(summary = "Get all cryptocurrency prices")
    @GetMapping("/getCrypoValue/all")
    public ResponseEntity<CryptoCurrencyList> getAllCryptoCurrencyPrices() {
        CryptoCurrencyList list = new CryptoCurrencyList();
        for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values()) {
            BaseCryptoDTO entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + crypto.name(), BaseCryptoDTO.class);
            list.addCrypto(entity);
        }
        return ResponseEntity.ok().body(list);
    }
}
