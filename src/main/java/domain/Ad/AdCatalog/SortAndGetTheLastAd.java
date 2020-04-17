package domain.Ad.AdCatalog;

import domain.Ad.Ad;
import domain.Ad.DateSorter;

import java.util.List;

public class SortAndGetTheLastAd implements SortsAdsByCountry {
    @Override
    public Ad byStrategy(List<Ad> ads) {
        ads.sort(new DateSorter());
        return ads.get(0);
    }
}
