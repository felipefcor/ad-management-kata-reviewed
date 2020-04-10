package domain.ad;

import domain.Ad.Ad;
import domain.Ad.exceptions.TitleAndDescriptionAreTheSameException;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import domain.User.User;
import domain.User.UserId;

import java.time.LocalDate;


public class AdShould {
    @Test
    public void throw_an_error_when_title_and_description_are_the_same(){
        AdTitle adTitle = new AdTitle("Esto es una prueba");
        AdDescription adDescription = new AdDescription("Esto es una prueba");

        Assertions.assertThrows(TitleAndDescriptionAreTheSameException.class, () -> new Ad(adTitle, adDescription, LocalDate.now()));
    }

    @Test
    public void add_and_retrive_the_favourite_users(){
        Ad ad = new Ad(new AdTitle("titulo"), new AdDescription("descripcion"), LocalDate.now());
        UserId userId = new UserId(1);
        UserId userId2 = new UserId(2);
        User user = new User(userId);
        User user2 = new User(userId2);

        ad.markedAsAFavouriteByAUser(user);
        ad.markedAsAFavouriteByAUser(user2);


        Assertions.assertEquals(2, ad.getUsersFavouritedInAnAd().size());
    }
}
