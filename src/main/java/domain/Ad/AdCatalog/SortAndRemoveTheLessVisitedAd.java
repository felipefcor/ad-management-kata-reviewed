package domain.Ad.AdCatalog;

import domain.Ad.Ad;
import domain.Ad.VisitorSorter;

import java.util.List;

public class SortAndRemoveTheLessVisitedAd implements SortsAdsByCountry {
    @Override
    public void sortAds(List<Ad> ads) {
        ads.sort(new VisitorSorter());
        ads.remove(ads.get(0));

    }
}
