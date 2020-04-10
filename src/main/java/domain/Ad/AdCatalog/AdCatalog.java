package domain.Ad.AdCatalog;

import domain.Ad.Ad;
import domain.Ad.DTO.AdCatalogDTO;
import domain.Ad.DTO.AdDTO;
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
    SortsAdsByCountry sortsAdsByCountry;
    private List<AdCatalogObserver> adCatalogObservers = new ArrayList<>();

    public void add(Ad ad) {
        if(adList.size() == 100) sortAds();
        for (Ad adIter : adList) {
            AdDTO adDTO = adIter.createAdDTO();
            if(adDTO.adTitle.equals(ad.createAdDTO().adTitle) && adDTO.adDescription.equals(ad.createAdDTO().adDescription))
                throw new AdExistsAlreadyException();
        }
        adList.add(ad);
      }

    private List<Ad> sortAds() {
      return sortsAdsByCountry.sortAds(this.adList);
    }

    public void remove(Ad ad) {
         if(!adList.contains(ad)) throw new AdDoesNotExistException();
         for (AdCatalogObserver adCatalogObserver : this.adCatalogObservers) {
            adCatalogObserver.updateFavourites(ad);
         }
         if(adList.contains(ad)) adList.remove(ad);
}
    public AdCatalogDTO getList() {
        AdCatalogDTO adCatalogDTO = this.createAdCatalogDTO();
        adCatalogDTO.adList = this.adList;
        return adCatalogDTO;
    }


    public void purge(LocalDate date) {
        adList.sort(new DateSorter());
        for (Iterator<Ad> iterator = adList.iterator(); iterator.hasNext(); ) {
            Ad ad = iterator.next();
            if(ad.createAdDTO().date.isBefore(date)) {
                for (AdCatalogObserver adCatalogObserver : this.adCatalogObservers) {
                    adCatalogObserver.updateFavourites(ad);
                }
                iterator.remove();
            }
        }
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

    public AdDTO get(AdTitle adTitle, AdDescription adDescription) {
        for (Ad ad : adList) {
            ad.increaseAdVisits();
            AdDTO adDTO = ad.createAdDTO();
            if(adDTO.adTitle.equals(adTitle) && adDTO.adDescription.equals(ad.createAdDTO().adDescription)) return adDTO;
        }
        return null;
    }
}
