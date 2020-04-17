package domain.ad;

import domain.Ad.Ad;
import domain.Ad.AdCatalog.SortAndGetTheLastAd;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SortAndRemoveTheOldestAdShould {
    @Test
    public void sort_a_list_of_ads_by_date_and_remove_the_oldest(){
        List<Ad> list = new ArrayList<>();
        LocalDate dateTest3 = LocalDate.of(2020, 4, 3);
        LocalDate dateTest2 = LocalDate.of(2020, 4, 2);
        LocalDate dateTest4 = LocalDate.of(2020, 4, 4);
        LocalDate dateTest = LocalDate.of(2020, 4, 1);
        Ad ad = new Ad(new AdTitle("titulo"), new AdDescription("descripcion"), dateTest);
        Ad ad2 = new Ad(new AdTitle("titulo2"), new AdDescription("descripcion2"), dateTest2);
        Ad ad3 = new Ad(new AdTitle("titulo3"), new AdDescription("descripcion3"), dateTest3);
        Ad ad4 = new Ad(new AdTitle("titulo4"), new AdDescription("descripcion4"), dateTest4);
        list.add(ad4);
        list.add(ad3);
        list.add(ad);
        list.add(ad2);
        SortAndGetTheLastAd sortAndGetTheLastAd = new SortAndGetTheLastAd();

        Assert.assertEquals(ad, sortAndGetTheLastAd.advertToRemove(list));
    }
}
