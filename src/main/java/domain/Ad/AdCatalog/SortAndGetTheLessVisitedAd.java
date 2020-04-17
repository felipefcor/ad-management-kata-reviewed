package domain.Ad.AdCatalog;

import domain.Ad.Ad;
import domain.Ad.VisitorSorter;

import java.util.List;

public class SortAndGetTheLessVisitedAd implements RemovalStrategy {
    @Override
    public Ad advertToRemove(List<Ad> ads) {
        ads.sort(new VisitorSorter());
        return ads.get(0);
    }
}

