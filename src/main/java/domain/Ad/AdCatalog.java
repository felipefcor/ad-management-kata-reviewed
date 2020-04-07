package domain.Ad;
import domain.Ad.DTO.AdCatalogDTO;
import domain.Ad.DTO.AdDTO;

import java.util.ArrayList;
import java.util.List;

public class AdCatalog {
    List<Ad> adList = new ArrayList<>();

    public void add(Ad ad) {
        this.adList.add(ad);
    }

    public void remove(AdTitle adTitle) {
        for (Ad ad : this.adList) {
            AdDTO adDTO = ad.createAdDTO();
            if(adDTO.adTitle.equals(adTitle)){
                adList.remove(ad);
            }
        }
    }

    public AdCatalogDTO createAdCatalogDTO() {
        AdCatalogDTO adCatalogDTO = new AdCatalogDTO();
        adCatalogDTO.adList = this.adList;
        return adCatalogDTO;
    }
}
