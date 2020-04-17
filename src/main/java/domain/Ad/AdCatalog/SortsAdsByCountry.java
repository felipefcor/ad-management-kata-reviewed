package domain.Ad.AdCatalog;
import domain.Ad.Ad;

import java.util.List;

public interface SortsAdsByCountry {
    Ad byStrategy(List<Ad> ads);
}
