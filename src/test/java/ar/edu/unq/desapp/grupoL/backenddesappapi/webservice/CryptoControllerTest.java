package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.CryptosPriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CryptoControllerTest {

    @InjectMocks
    private CryptoController cryptoController;

    @Mock
    CryptosPriceService cryptosPriceService;

    @Test
    public void getCryptoCurrencyValue() throws IOException {

        when(cryptosPriceService.getCryptoPrice("symbol")).thenReturn(2F);

        cryptoController.getCryptoCurrencyValue("symbol");

        verify(cryptosPriceService, atLeastOnce()).getCryptoPrice("symbol");
    }
}
