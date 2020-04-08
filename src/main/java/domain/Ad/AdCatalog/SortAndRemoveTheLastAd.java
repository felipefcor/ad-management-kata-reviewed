package domain.Ad.AdCatalog;
import domain.Ad.Ad;
import domain.Ad.DateSorter;

import java.util.List;

public class SortAndRemoveTheLastAd implements SortsAdsByCountry {
    @Override
    public List<Ad> sortAds(List<Ad> ads) {
        ads.sort(new DateSorter());
        ads.remove(ads.get(0));
        return ads;
    }
}
