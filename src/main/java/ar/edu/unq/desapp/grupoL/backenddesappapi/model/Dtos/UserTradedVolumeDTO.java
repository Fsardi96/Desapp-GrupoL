package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import java.util.ArrayList;

public class UserTradedVolumeDTO {
    private String userFullName;
    private String dateAndTime;
    private Float volumeInUSD;
    private Float volumeInARS;
    private ArrayList<CryptoActiveDTO> cryptoActives;

    public UserTradedVolumeDTO(String userFullName, String dateAndTime, Float volumeInUSD,
                               Float volumeInARS, ArrayList<CryptoActiveDTO> cryptoActives) {
        this.userFullName = userFullName;
        this.dateAndTime = dateAndTime;
        this.volumeInUSD = volumeInUSD;
        this.volumeInARS = volumeInARS;
        this.cryptoActives = cryptoActives;
    }

    public UserTradedVolumeDTO() { }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Float getVolumeInUSD() {
        return volumeInUSD;
    }

    public void setVolumeInUSD(Float volumeInUSD) {
        this.volumeInUSD = volumeInUSD;
    }

    public Float getVolumeInARS() {
        return volumeInARS;
    }

    public void setVolumeInARS(Float volumeInARS) {
        this.volumeInARS = volumeInARS;
    }

    public ArrayList<CryptoActiveDTO> getCryptoActives() {
        return cryptoActives;
    }

    public void setCryptoActives(ArrayList<CryptoActiveDTO> cryptoActives) {
        this.cryptoActives = cryptoActives;
    }
}
