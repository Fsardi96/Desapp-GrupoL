package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.CryptosPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CryptosPriceServiceTest {

    @InjectMocks
    private CryptosPriceService cryptosPriceService;

    @Mock
    CryptosPriceRepository cryptosPriceRepository;

    @Test
    public void createCryptosPrice() throws IOException {

        cryptosPriceService.createCryptosPrice();
        verify(cryptosPriceRepository, atLeastOnce()).save(any());
    }
}
