package domain.Ad.AdCatalog;

import domain.Ad.Ad;
import domain.Ad.DTO.AdCatalogDTO;
import domain.Ad.DateSorter;
import domain.Ad.exceptions.AdDoesNotExistException;
import domain.Ad.exceptions.AdExistsAlreadyException;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AdCatalog {
    private List<Ad> adList = new ArrayList<>();
    SortsAdsByCountry getAdToDelete;
    private List<AdCatalogObserver> adCatalogObservers = new ArrayList<>();

    public AdCatalog(SortsAdsByCountry deleteAdsByCountry) {
        this.getAdToDelete = deleteAdsByCountry;
    }

    public void add(Ad ad) {
        if(adList.size() == 100) deleteAdsByCountry();
        for (Ad adIter : adList) {
            if(adIter.equals(ad)) throw new AdExistsAlreadyException();
        }
        adList.add(ad);
      }

    private void deleteAdsByCountry() {
      this.remove(getAdToDelete.byStrategy(this.adList));
    }

    public void remove(Ad ad) {
         if(!adList.contains(ad)) throw new AdDoesNotExistException();
         for (AdCatalogObserver adCatalogObserver : this.adCatalogObservers) {
            adCatalogObserver.updateFavourites(ad);
         }
         if(adList.contains(ad)) adList.remove(ad);
}
    public List<Ad> getList() {
         return this.adList;
    }


    public void purge(LocalDate date) {
        adList.sort(new DateSorter());
        for (Iterator<Ad> iterator = adList.iterator(); iterator.hasNext(); ) {
            Ad ad = iterator.next();
            if(ad.dateIsBefore(date)) {
                for (AdCatalogObserver adCatalogObserver : this.adCatalogObservers) {
                    adCatalogObserver.updateFavourites(ad);
                }
                iterator.remove();
            }
        }
    }

    public Ad get(AdTitle adTitle, AdDescription adDescription) {
        for (Ad ad : adList) {
            ad.increaseAdVisits();
            if(ad.checkTitle(adTitle) && ad.checkDescription(adDescription)) return ad;
        }
        return null;
    }

    public void addObserver(AdCatalogObserver adCatalogObserver){
        this.adCatalogObservers.add(adCatalogObserver);
    }

    public AdCatalogDTO createAdCatalogDTO() {
        AdCatalogDTO adCatalogDTO = new AdCatalogDTO();
        adCatalogDTO.adList = this.adList;
        return adCatalogDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdCatalog adCatalog = (AdCatalog) o;
        return Objects.equals(adList, adCatalog.adList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adList);
    }
}
