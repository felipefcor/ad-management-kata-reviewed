package domain.Ad;
import domain.Ad.DTO.AdCatalogDTO;
import domain.Ad.DTO.AdDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdCatalog {
    private List<Ad> adList = new ArrayList<>();

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

      public AdCatalogDTO getList() {
        AdCatalogDTO adCatalogDTO = this.createAdCatalogDTO();
        adCatalogDTO.adList = this.adList;
        return adCatalogDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdCatalog adCatalog = (AdCatalog) o;
        return Objects.equals(adList, adCatalog.adList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adList);
    }
}
