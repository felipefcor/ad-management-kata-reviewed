package domain.Ad;
import domain.Ad.DTO.AdCatalogDTO;
import domain.Ad.DTO.AdDTO;
import domain.Ad.exceptions.AdDoesNotExistException;
import domain.Ad.exceptions.AdExistsAlreadyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdCatalog {
    private List<Ad> adList = new ArrayList<>();

    public void add(Ad ad) {
        for (Ad adIter : adList) {
            AdDTO adDTO = adIter.createAdDTO();
            if(adDTO.adTitle.equals(ad.createAdDTO().adTitle) && adDTO.adDescription.equals(ad.createAdDTO().adDescription))
                throw new AdExistsAlreadyException();
        }
        this.adList.add(ad);
      }

    public void remove(AdTitle adTitle) {
        if(!this.adList.contains(adTitle)) throw new AdDoesNotExistException();
        for (Ad ad : this.adList) {
            AdDTO adDTO = ad.createAdDTO();
            if(adDTO.adTitle.equals(adTitle))  adList.remove(ad);
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
