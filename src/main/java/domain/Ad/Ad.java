package domain.Ad;

import domain.Ad.exceptions.TitleAndDescriptionAreTheSameException;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import domain.Ad.valueObjects.AdVisits;
import domain.User.UserId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ad {
    private final AdTitle adTitle;
    private final AdDescription adDescription;
    private final LocalDate date;
    private AdVisits adVisits = new AdVisits();

    private List<UserId> favouriteUsers = new ArrayList<>();

    public Ad(AdTitle adTitle, AdDescription adDescription, LocalDate date) {
        if (hasTheSameText(adTitle, adDescription)) throw new TitleAndDescriptionAreTheSameException();
        this.adTitle = adTitle;
        this.adDescription = adDescription;
        this.date = date;
    }

    private boolean hasTheSameText(AdTitle adTitle, AdDescription adDescription) {
        if (adTitle.getAdTitle().equals(adDescription.getAdDescription())) return true;
        return false;
    }
    public boolean checkTitle(AdTitle title){
        if(title.getAdTitle().equals(adTitle.getAdTitle())) return true;
        return false;
    }

    public boolean checkDescription(AdDescription description){
        if(description.getAdDescription().equals(adDescription.getAdDescription())) return true;
        return false;
    }

    public void markedAsAFavouriteByAUser(UserId userId) {
        this.favouriteUsers.add(userId);
    }

    public List<UserId> getUsersFavouritedInAnAd() {
        return this.favouriteUsers;
    }

    public void increaseAdVisits() {
        adVisits.increaseVisits();
    }

    public AdVisits getAdVisits() {
        return adVisits;
    }

    public boolean dateIsBefore(LocalDate dateToCompare){
        if(this.date.isBefore(dateToCompare)) return true;
        return false;
    }
    public LocalDate getDate(){
        return this.date;
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
        return Objects.hash(adTitle, adDescription, date, adVisits, favouriteUsers);
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
}
