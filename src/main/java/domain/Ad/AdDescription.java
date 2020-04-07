package domain.Ad;

import domain.Ad.DTO.AdDTODescription;

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
}
