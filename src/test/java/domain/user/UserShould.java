package domain.user;

import domain.Ad.Ad;
import domain.Ad.AdCatalog.AdCatalog;
import domain.Ad.AdCatalog.SortAndGetTheLastAd;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import domain.User.User;
import domain.User.UserId;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class UserShould {
    @Test
     public void mark_an_ad_as_a_favourite_by_user(){
        UserId userId = new UserId(1);
        User user = new User(userId);
        Ad ad = new Ad(new AdTitle("titulo"), new AdDescription("description"), LocalDate.now());
        user.addfavoriteAds(ad);

        Assert.assertEquals(1, user.getFavoriteAds().size());
    }
    @Test
    public void remove_ad_marked_as_favourite_when_the_ad_is_removed(){
        UserId userId = new UserId(1);
        User user = new User(userId);
        Ad ad = new Ad(new AdTitle("titulo"), new AdDescription("description"), LocalDate.now());
        Ad ad2 = new Ad(new AdTitle("titulo2"), new AdDescription("description2"), LocalDate.now());
        user.addfavoriteAds(ad);
        user.addfavoriteAds(ad2);
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());
        Ad ad3 = new Ad(new AdTitle("titulo3"), new AdDescription("description3"), LocalDate.now());
        Ad ad4 = new Ad(new AdTitle("titulo4"), new AdDescription("description4"), LocalDate.now());
        adCatalog.add(ad3);
        adCatalog.add(ad4);
        adCatalog.add(ad);
        adCatalog.addObserver(user);

        adCatalog.remove(ad);

        Assert.assertEquals(1, user.getFavoriteAds().size());
    }

    @Test
    public void remove_ad_marked_as_favourite_when_the_ad_is_purged(){
        UserId userId = new UserId(1);
        User user = new User(userId);
        LocalDate dateTest = LocalDate.of(2020, 4, 1);
        LocalDate dateTest2 = LocalDate.of(2020, 4, 2);
        LocalDate dateTest4 = LocalDate.of(2020, 4, 4);
        Ad ad = new Ad(new AdTitle("titulo"), new AdDescription("descripcion"), dateTest);
        Ad ad2 = new Ad(new AdTitle("titulo2"), new AdDescription("description2"), dateTest2);
        Ad ad3 = new Ad(new AdTitle("titulo3"), new AdDescription("description3"), LocalDate.now());
        user.addfavoriteAds(ad);
        user.addfavoriteAds(ad2);
        user.addfavoriteAds(ad3);
        AdCatalog adCatalog = new AdCatalog(new SortAndGetTheLastAd());
        Ad ad4 = new Ad(new AdTitle("titulo4"), new AdDescription("description4"), dateTest4);
        adCatalog.add(ad);
        adCatalog.add(ad4);
        adCatalog.addObserver(user);

        adCatalog.purge(LocalDate.now());

        Assert.assertEquals(2, user.getFavoriteAds().size());
    }
}
