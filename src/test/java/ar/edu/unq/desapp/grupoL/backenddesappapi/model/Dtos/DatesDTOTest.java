package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatesDTOTest {

    @Test
    void testDatesDTOModel() {
        DatesDTO dto = new DatesDTO("date", "date");
        dto.setDateFrom("from");
        dto.setDateTo("to");

        Assertions.assertEquals("from", dto.getDateFrom());
        Assertions.assertEquals("to", dto.getDateTo());
    }
}
