package domain.Ad.AdCatalog;

import domain.Ad.Ad;
import domain.Ad.VisitorSorter;

import java.util.List;

public class SortAndGetTheLessVisitedAd implements SortsAdsByCountry {
    @Override
    public Ad byStrategy(List<Ad> ads) {
        ads.sort(new VisitorSorter());
        return ads.get(0);
    }
}

