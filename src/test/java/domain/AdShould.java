package domain;

import domain.Ad.Ad;
import domain.Ad.valueObjects.AdDescription;
import domain.Ad.valueObjects.AdTitle;
import domain.Ad.exceptions.TitleAndDescriptionAreTheSameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class AdShould {
    @Test
    public void throw_an_error_when_title_and_description_are_the_same(){
        AdTitle adTitle = new AdTitle("Esto es una prueba");
        AdDescription adDescription = new AdDescription("Esto es una prueba");

        Assertions.assertThrows(TitleAndDescriptionAreTheSameException.class, () -> new Ad(adTitle, adDescription, LocalDate.now()));
    }
}
