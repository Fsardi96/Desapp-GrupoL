package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CryptosPrice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;
    private LocalDateTime date;
    private Float ALICEUSDT;
    private Float MATICUSDT;
    private Float AXSUSDT;
    private Float AAVEUSDT;
    private Float ATOMUSDT;
    private Float NEOUSDT;
    private Float DOTUSDT;
    private Float ETHUSDT;
    private Float CAKEUSDT;
    private Float BTCUSDT;
    private Float BNBUSDT;
    private Float ADAUSDT;
    private Float TRXUSDT;
    private Float AUDIOUSDT;


    public CryptosPrice(Float ALICEUSDT, Float MATICUSDT, Float AXSUSDT, Float AAVEUSDT, Float ATOMUSDT,
                        Float NEOUSDT, Float DOTUSDT, Float ETHUSDT, Float CAKEUSDT, Float BTCUSDT,
                        Float BNBUSDT, Float ADAUSDT, Float TRXUSDT, Float AUDIOUSDT) {
        this.date = LocalDateTime.now();
        this.ALICEUSDT = ALICEUSDT;
        this.MATICUSDT = MATICUSDT;
        this.AXSUSDT = AXSUSDT;
        this.AAVEUSDT = AAVEUSDT;
        this.ATOMUSDT = ATOMUSDT;
        this.NEOUSDT = NEOUSDT;
        this.DOTUSDT = DOTUSDT;
        this.ETHUSDT = ETHUSDT;
        this.CAKEUSDT = CAKEUSDT;
        this.BTCUSDT = BTCUSDT;
        this.BNBUSDT = BNBUSDT;
        this.ADAUSDT = ADAUSDT;
        this.TRXUSDT = TRXUSDT;
        this.AUDIOUSDT = AUDIOUSDT;
    }
}
