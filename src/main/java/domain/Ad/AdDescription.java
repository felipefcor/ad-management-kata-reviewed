package domain.Ad;

import domain.Ad.DTO.AdDTODescription;

import java.util.Objects;

public class AdDescription {
    private String adDescription;

    public AdDescription(String adDescription) {
        this.adDescription = adDescription;
    }

    public AdDTODescription createDescriptionDTO() {
        AdDTODescription adDTODescription = new AdDTODescription();
        adDTODescription.adDescription = this.adDescription;
        return adDTODescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDescription that = (AdDescription) o;
        return Objects.equals(adDescription, that.adDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adDescription);
    }
}
