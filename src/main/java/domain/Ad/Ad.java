package domain.Ad;

import domain.Ad.DTO.AdDTO;
import domain.Ad.DTO.AdDTODescription;
import domain.Ad.DTO.AdDTOTitle;
import domain.Ad.exceptions.TitleAndDescriptionAreTheSameException;

import java.time.LocalTime;
import java.util.Objects;

public class Ad {
    private final AdTitle adTitle;
    private final AdDescription adDescription;
    private final LocalTime date;

    public Ad(AdTitle adTitle, AdDescription adDescription, LocalTime date) {
        if(checkTitleAndDescription(adTitle, adDescription)) throw new TitleAndDescriptionAreTheSameException();
        this.adTitle = adTitle;
        this.adDescription = adDescription;
        this.date = date;
    }

    private boolean checkTitleAndDescription(AdTitle adTitle, AdDescription adDescription) {
        AdDTOTitle adDTOTitle = adTitle.createTitleDTO();
        AdDTODescription adDTODescription = adDescription.createDescriptionDTO();
        if(adDTOTitle.adTitle == adDTODescription.adDescription) return true;
        return false;
    }

    public AdDTO createAdDTO() {
        AdDTO adDTO = new AdDTO();
        adDTO.adTitle = this.adTitle;
        adDTO.adDescription = this.adDescription;
        adDTO.date = this.date;
        return adDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(adTitle, ad.adTitle) &&
                Objects.equals(adDescription, ad.adDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adTitle, adDescription);
    }

   }
