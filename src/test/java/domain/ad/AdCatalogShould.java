package domain.ad;

import domain.Ad.Ad;
import domain.Ad.AdCatalog.AdCatalog;
import domain.Ad.AdCatalog.AdCatalogExpireByLessVisitedAd;
import domain.Ad.AdCatalog.AdCatalogExpireByOldesAd;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import domain.Ad.DTO.AdCatalogDTO;
import domain.Ad.DTO.AdDTO;
import domain.Ad.exceptions.AdDoesNotExistException;
import domain.Ad.exceptions.AdExistsAlreadyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class AdCatalogShould {
    @Test
    public void add_and_delete_ads() {
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
        LocalDate dateTest2 = LocalDate.of(2020, 4, 2);
        Ad ad = new Ad(new AdTitle("titulo"), new AdDescription("descripcion"), dateTest);
        Ad ad2 = new Ad(new AdTitle("titulo2"), new AdDescription("descripcion2"), dateTest2);
        Ad ad3 = new Ad(new AdTitle("titulo3"), new AdDescription("descripcion3"), LocalDate.now());
        AdCatalog adCatalog = new AdCatalog();
        adCatalog.add(ad);
        adCatalog.add(ad2);
        adCatalog.add(ad3);

        adCatalog.purge(LocalDate.now());

        Assert.assertEquals(ad3, adCatalog.createAdCatalogDTO().adList.get(0));
        Assert.assertEquals(1, adCatalog.createAdCatalogDTO().adList.size());

    }

    @Test
    public void remove_the_oldest_ad_when_the_catalog_reaches_100_ads(){
        AdCatalog adCatalog = new AdCatalogExpireByOldesAd();
        for (int i = 1; i < 103; i++) {
            Ad ad = new Ad(new AdTitle("titulo" + i), new AdDescription("descripcion"+ i), LocalDate.ofYearDay(2019, i));
            adCatalog.add(ad);
        }
        AdCatalogDTO adCatalogDTO =  adCatalog.createAdCatalogDTO();

        Assert.assertEquals(new Ad(new AdTitle("titulo3"), new AdDescription("descripcion3"), LocalDate.ofYearDay(2019, 3)), adCatalogDTO.adList.get(0));
        Assert.assertEquals(100, adCatalog.createAdCatalogDTO().adList.size());
    }

    @Test
    public void retrieve_a_concrete_ad(){
        AdTitle adTitle = new AdTitle("titulo");
        AdDescription adDescription = new AdDescription("descripcion");
        Ad ad = new Ad(adTitle, adDescription, LocalDate.now());
        AdDTO adDTO = ad.createAdDTO();
        AdCatalog adCatalog = new AdCatalog();
        adCatalog.add(ad);

        Assert.assertEquals(adDTO, adCatalog.get(adTitle, adDescription));
    }

    @Test
    public void remove_the_less_visited_ad_when_catalog_reaches_100_ads(){
        AdCatalog adCatalog = new AdCatalogExpireByLessVisitedAd();
        for (int i = 1; i < 102; i++) {
            Ad ad = new Ad(new AdTitle("titulo" + i), new AdDescription("descripcion"+ i), LocalDate.ofYearDay(2019, i));
            ad.createAdDTO().adVisits.visits = 1 + i;
            adCatalog.add(ad);
        }
        AdCatalogDTO adCatalogDTO =  adCatalog.createAdCatalogDTO();
        Ad adExpected = new Ad(new AdTitle("titulo2"), new AdDescription("descripcion2"), LocalDate.ofYearDay(2019, 2));
        adExpected.createAdDTO().adVisits.visits = 3;

        Assert.assertEquals(adExpected, adCatalogDTO.adList.get(0));
        Assert.assertEquals(100, adCatalog.createAdCatalogDTO().adList.size());
    }

    @Test
    public void retrieve_a_concrete_ad_and_increase_visits(){
        AdTitle adTitle = new AdTitle("titulo");
        AdDescription adDescription = new AdDescription("descripcion");
        Ad ad = new Ad(adTitle, adDescription, LocalDate.now());
        AdCatalog adCatalog = new AdCatalog();
        adCatalog.add(ad);
        adCatalog.get(adTitle, adDescription);

        adCatalog.get(adTitle, adDescription);
        int visitsExpected = ad.createAdDTO().adVisits.visits;

        Assert.assertEquals(2, visitsExpected);
        }
}


