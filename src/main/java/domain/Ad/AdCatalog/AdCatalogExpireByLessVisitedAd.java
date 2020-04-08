package domain.Ad.AdCatalog;

public class AdCatalogExpireByLessVisitedAd extends AdCatalog {
    public AdCatalogExpireByLessVisitedAd(){
        sortsAdsByCountry = new SortAndRemoveTheLessVisitedAd();
    }
}
