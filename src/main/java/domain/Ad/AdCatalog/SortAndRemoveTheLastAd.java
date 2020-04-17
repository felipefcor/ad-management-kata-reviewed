package domain.Ad.AdCatalog;

import domain.Ad.Ad;
import domain.Ad.DateSorter;

import java.util.List;

public class SortAndRemoveTheLastAd implements SortsAdsByCountry {
    @Override
    public void sortAds(List<Ad> ads) {
        ads.sort(new DateSorter());
        ads.remove(ads.get(0));
      
    }
}
