package domain.Ad.AdCatalog;

public class AdCatalogExpireByOldesAd extends AdCatalog {
    public AdCatalogExpireByOldesAd(){
        sortsAdsByCountry = new SortAndRemoveTheLastAd();
    }


}
