package domain.Ad.DTO;

import domain.Ad.AdDescription;
import domain.Ad.AdTitle;

import java.time.LocalDate;
import java.util.Objects;

public class AdDTO {
    public AdTitle adTitle;
    public AdDescription adDescription;
    public LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDTO adDTO = (AdDTO) o;
        return Objects.equals(adTitle, adDTO.adTitle) &&
                Objects.equals(adDescription, adDTO.adDescription) &&
                Objects.equals(date, adDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adTitle, adDescription, date);
    }
}
