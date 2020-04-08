package domain.Ad.AdCatalog;
import domain.Ad.Ad;
import java.util.List;

public interface SortsAdsByCountry {
    List<Ad> sortAds(List<Ad> ads);
}
