package domain.Ad;

import domain.Ad.DTO.AdDTO;
import domain.Ad.DTO.AdDTODescription;
import domain.Ad.DTO.AdDTOTitle;
import domain.Ad.exceptions.TitleAndDescriptionAreTheSameException;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import domain.Ad.valueObjects.AdVisits;
import domain.User.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ad {
    private final AdTitle adTitle;
    private final AdDescription adDescription;
    private final LocalDate date;
    private AdVisits adVisits = new AdVisits();
    private List<User> favouriteUsers = new ArrayList<>();

    public Ad(AdTitle adTitle, AdDescription adDescription, LocalDate date) {
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
        adDTO.adVisits = this.adVisits;
        return adDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(adTitle, ad.adTitle) &&
                Objects.equals(adDescription, ad.adDescription) &&
                Objects.equals(date, ad.date) &&
                Objects.equals(adVisits, ad.adVisits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adTitle, adDescription, date, adVisits);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "adTitle=" + adTitle +
                ", adDescription=" + adDescription +
                ", date=" + date +
                ", adVisits=" + adVisits +
                '}';
    }

    public void markedAsAFavouriteByAUser(User user) {
        this.favouriteUsers.add(user);
    }

    public List<User> getUsersFavouritedInAnAd() {
        return this.favouriteUsers;
    }
}
