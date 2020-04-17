package domain.Ad.AdCatalog;
import domain.Ad.Ad;

import java.util.List;

public interface RemovalStrategy {
    Ad advertToRemove(List<Ad> ads);
}
