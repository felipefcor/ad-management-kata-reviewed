import domain.Ad.Ad;
import domain.Ad.AdCatalog;
import domain.Ad.AdDescription;
import domain.Ad.AdTitle;
import domain.Ad.DTO.AdCatalogDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class AdCatalogShould {
    @Test
    public void save_and_delete_ads(){
        AdTitle adTitle = new AdTitle("titulo");
        AdTitle adTitle2 = new AdTitle("titulo2");
        AdDescription adDescription = new AdDescription("descipción");
        AdDescription adDescription2 = new AdDescription("descipción2");

        Ad ad1 = new Ad(adTitle, adDescription, LocalTime.now());
        Ad ad2 = new Ad(adTitle2, adDescription2, LocalTime.now());

        AdCatalog adCatalog = new AdCatalog();
        AdCatalogDTO adCatalogDTO = adCatalog.createAdCatalogDTO();

        adCatalog.add(ad1);
        adCatalog.add(ad2);
        adCatalog.remove(adTitle);

        Assert.assertEquals(1, adCatalogDTO.adList.size());
    }
}
