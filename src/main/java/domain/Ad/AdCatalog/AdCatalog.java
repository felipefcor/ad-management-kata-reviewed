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
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class AdCatalog {
    private final int MAX_SIZE = 100;
    private List<Ad> adList = new ArrayList<>();
    RemovalStrategy removalStrategy;
    private List<AdCatalogObserver> adCatalogObservers = new ArrayList<>();

    public AdCatalog(RemovalStrategy removalStrategy) {
        this.removalStrategy = removalStrategy;
    }

    public void add(Ad ad) {
        if(adList.size() == MAX_SIZE) deleteByStrategy();
        if(adIsNotInCatalog(ad)) adList.add(ad);
      }

    private boolean adIsNotInCatalog(Ad ad) {
        for (Ad adIter : adList) {
            if(adIter.equals(ad)) throw new AdExistsAlreadyException();
        }
        return true;
    }

    private void deleteByStrategy() {
      this.remove(removalStrategy.advertToRemove(this.adList));
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
        adList.stream().filter(ad -> ad.dateIsBefore(date)).collect(toList()).forEach(this::remove);
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
