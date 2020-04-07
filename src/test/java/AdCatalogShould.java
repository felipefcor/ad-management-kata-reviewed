import domain.Ad.Ad;
import domain.Ad.AdCatalog;
import domain.Ad.AdDescription;
import domain.Ad.AdTitle;
import domain.Ad.DTO.AdCatalogDTO;
import domain.Ad.exceptions.AdDoesNotExistException;
import domain.Ad.exceptions.AdExistsAlreadyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class AdCatalogShould {
    @Test
    public void save_and_delete_ads() {
        AdTitle adTitle = new AdTitle("titulo");
        AdTitle adTitle2 = new AdTitle("titulo2");
        AdDescription adDescription = new AdDescription("descipción");
        AdDescription adDescription2 = new AdDescription("descipción2");

        Ad ad1 = new Ad(adTitle, adDescription, LocalDate.now());
        Ad ad2 = new Ad(adTitle2, adDescription2, LocalDate.now());

        AdCatalog adCatalog = new AdCatalog();
        AdCatalogDTO adCatalogDTO = adCatalog.createAdCatalogDTO();

        adCatalog.add(ad1);
        adCatalog.add(ad2);
        adCatalog.remove(ad1);

        Assert.assertEquals(1, adCatalogDTO.adList.size());
    }

    @Test
    public void get_the_list_of_existent_ads() {
        Ad ad = new Ad(new AdTitle("titulo"), new AdDescription("descripcion"), LocalDate.now());
        Ad ad2 = new Ad(new AdTitle("titulo1"), new AdDescription("descripcion1"), LocalDate.now());
        AdCatalog adCatalog = new AdCatalog();
        adCatalog.add(ad);
        adCatalog.add(ad2);
        AdCatalogDTO adCatalogDTOexpected = adCatalog.createAdCatalogDTO();
        AdCatalogDTO adCatalogDTOactual;

        adCatalogDTOactual = adCatalog.getList();

        Assert.assertEquals(adCatalogDTOexpected.adList.get(0), adCatalogDTOactual.adList.get(0));
        Assert.assertEquals(adCatalogDTOexpected.adList.get(1), adCatalogDTOactual.adList.get(1));

    }

    @Test
    public void throw_an_error_when_tries_to_remove_an_unexistent_ad() {
        AdTitle adTitle = new AdTitle("Titulo");
        AdDescription adDescription = new AdDescription("descipción");
        Ad ad = new Ad(adTitle, adDescription, LocalDate.now());
        AdCatalog adCatalog = new AdCatalog();

        Assertions.assertThrows(AdDoesNotExistException.class, () -> adCatalog.remove(ad));
    }

    @Test
    public void throw_an_error_when_the_ad_exists_already() {

        AdTitle adTitle = new AdTitle("titulo");
        AdTitle adTitle2 = new AdTitle("titulo");
        AdDescription adDescription = new AdDescription("descripcion");
        AdDescription adDescription2 = new AdDescription("descripcion");

        Ad ad1 = new Ad(adTitle, adDescription, LocalDate.now());
        Ad ad2 = new Ad(adTitle2, adDescription2, LocalDate.now());

        AdCatalog adCatalog = new AdCatalog();

        adCatalog.add(ad1);

        Assertions.assertThrows(AdExistsAlreadyException.class, () -> adCatalog.add(ad2));
    }

    @Test
    public void purge_the_last_ad_added() {
        LocalDate dateTest = LocalDate.of(2020, 4, 1);
        Ad ad = new Ad(new AdTitle("titulo2"), new AdDescription("descripcion2"), dateTest);
        Ad ad2 = new Ad(new AdTitle("titulo"), new AdDescription("descripcion"), LocalDate.now());
        AdCatalog adCatalog = new AdCatalog();
        adCatalog.add(ad);
        adCatalog.add(ad2);

        adCatalog.purge(LocalDate.now());

        Assert.assertEquals(ad2, adCatalog.createAdCatalogDTO().adList.get(0));
        Assert.assertEquals(1, adCatalog.createAdCatalogDTO().adList.size());

    }

    @Test
    public void remove_the_oldest_ad_When_the_catalog_reaches_100_ads(){
        AdCatalog adCatalog = new AdCatalog();
        for (int i = 1; i < 150; i++) {
            Ad ad = new Ad(new AdTitle("titulo" + i), new AdDescription("descripcion"+ i), LocalDate.ofYearDay(2019, i));
            adCatalog.add(ad);
        }

        adCatalog.add(new Ad(new AdTitle("nuevo titulo"), new AdDescription("nueva descripcion"),LocalDate.ofYearDay(2019, 200)));

        Assert.assertEquals(new Ad(new AdTitle("titulo99"), new AdDescription("descripcion99"), LocalDate.ofYearDay(2019, 99)), adCatalog.createAdCatalogDTO().adList.get(98));
        Assert.assertEquals(100, adCatalog.createAdCatalogDTO().adList.size());
    }
}
