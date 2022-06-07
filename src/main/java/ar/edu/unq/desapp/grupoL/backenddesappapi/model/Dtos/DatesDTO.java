package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

public class DatesDTO {
    private String dateFrom;
    private String dateTo;

    public DatesDTO(String dateFrom, String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public DatesDTO() { }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}


