package domain.ad;

import domain.Ad.Ad;
import domain.Ad.AdCatalog.AdCatalog;
import domain.Ad.AdCatalog.SortAndGetTheLastAd;
import domain.Ad.AdCatalog.SortAndGetTheLessVisitedAd;
import domain.Ad.exceptions.AdDoesNotExistException;
import domain.Ad.exceptions.AdExistsAlreadyException;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdCatalogShould {
    @Test
    public void add_and_delete_ads() {
        AdTitle adTitle = new AdTitle("titulo");
        AdTitle adTitle2 = new AdTitle("titulo2");
        AdDescription adDescription = new AdDescription("descipción");
        AdDescription adDescription2 = new AdDescription("descipción2");
        Ad ad1 = new Ad(adTitle, adDescription, LocalDate.now());
        Ad ad2 = new Ad(adTitle2, adDescription2, LocalDate.now());
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());

        adCatalog.add(ad1);
        adCatalog.add(ad2);
        adCatalog.remove(ad1);

        Assert.assertEquals(1, adCatalog.getList().size());
    }

    @Test
    public void get_the_list_of_existent_ads() {
        AdTitle adTitle = new AdTitle("titulo");
        AdDescription adDescription = new AdDescription("descripcion");
        AdTitle adTitle2 = new AdTitle("titulo1");
        AdDescription adDescription2 = new AdDescription("descripcion1");
        Ad ad = new Ad(adTitle, adDescription, LocalDate.now());
        Ad ad2 = new Ad(adTitle2, adDescription2, LocalDate.now());
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());
        adCatalog.add(ad);
        adCatalog.add(ad2);

        List<Ad> adCatalogExpected = new ArrayList<>();
        adCatalogExpected.add(ad);
        adCatalogExpected.add(ad2);

        Assert.assertEquals(adCatalogExpected.get(0), adCatalog.getList().get(0));
        Assert.assertEquals(adCatalogExpected.get(1), adCatalog.getList().get(1));

    }

    @Test
    public void throw_an_error_when_tries_to_remove_an_unexistent_ad() {
        AdTitle adTitle = new AdTitle("Titulo");
        AdDescription adDescription = new AdDescription("descipción");
        Ad ad = new Ad(adTitle, adDescription, LocalDate.now());
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());

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
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());

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
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());
        adCatalog.add(ad);
        adCatalog.add(ad2);
        adCatalog.add(ad3);

        adCatalog.purge(LocalDate.now());

        Assert.assertEquals(ad3, adCatalog.getList().get(0));
        Assert.assertEquals(1, adCatalog.getList().size());

    }

    @Test
    public void remove_the_oldest_ad_when_the_catalog_reaches_100_ads(){
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());
        for (int i = 1; i < 103; i++) {
            Ad ad = new Ad(new AdTitle("titulo" + i), new AdDescription("descripcion"+ i), LocalDate.ofYearDay(2019, i));
            adCatalog.add(ad);
        }

        Assert.assertEquals(new Ad(new AdTitle("titulo3"), new AdDescription("descripcion3"), LocalDate.ofYearDay(2019, 3)), adCatalog.getList().get(0));
        Assert.assertEquals(100, adCatalog.getList().size());
    }

    @Test
    public void retrieve_a_concrete_ad(){
        AdTitle adTitle = new AdTitle("titulo");
        AdDescription adDescription = new AdDescription("descripcion");
        Ad ad = new Ad(adTitle, adDescription, LocalDate.now());
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());
        adCatalog.add(ad);

        Assert.assertEquals(ad, adCatalog.get(adTitle, adDescription));
    }

    @Test
    public void remove_the_less_visited_ad_when_catalog_reaches_100_ads(){
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLessVisitedAd());
        for (int i = 1; i < 102; i++) {
            Ad ad = new Ad(new AdTitle("titulo" + i), new AdDescription("descripcion"+ i), LocalDate.ofYearDay(2019, i));
            for (int j = 0; j < 5; j++) {
                ad.increaseAdVisits();
            }
            adCatalog.add(ad);
        }
        Ad adExpected = new Ad(new AdTitle("titulo2"), new AdDescription("descripcion2"), LocalDate.ofYearDay(2019, 2));
        for (int i = 0; i < 5; i++) {
            adExpected.increaseAdVisits();
        }

        Assert.assertEquals(adExpected, adCatalog.getList().get(0));
        Assert.assertEquals(100, adCatalog.getList().size());
    }

    @Test
    public void retrieve_a_concrete_ad_and_increase_visits(){
        AdTitle adTitle = new AdTitle("titulo");
        AdDescription adDescription = new AdDescription("descripcion");
        Ad ad = new Ad(adTitle, adDescription, LocalDate.now());
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());
        adCatalog.add(ad);
        adCatalog.get(adTitle, adDescription);

        adCatalog.get(adTitle, adDescription);

        Assert.assertEquals("2", ad.getAdVisits().toString());
        }
}


