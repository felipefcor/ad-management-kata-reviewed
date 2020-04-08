package domain.Ad.DTO;

import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import domain.Ad.valueObjects.AdVisits;

import java.time.LocalDate;
import java.util.Objects;

public class AdDTO {
    public AdTitle adTitle;
    public AdDescription adDescription;
    public LocalDate date;
    public AdVisits adVisits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDTO adDTO = (AdDTO) o;
        return Objects.equals(adTitle, adDTO.adTitle) &&
                Objects.equals(adDescription, adDTO.adDescription) &&
                Objects.equals(date, adDTO.date) &&
                Objects.equals(adVisits, adDTO.adVisits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adTitle, adDescription, date, adVisits);
    }
}
