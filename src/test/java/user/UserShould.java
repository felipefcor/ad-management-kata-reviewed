package user;

import domain.Ad.Ad;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
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
}
