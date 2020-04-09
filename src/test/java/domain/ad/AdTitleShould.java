package domain.ad;

import domain.Ad.valueObjects.AdTitle;
import domain.Ad.exceptions.TitleTooLongException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;;

public class AdTitleShould {
    @Test
    public void throw_an_exception_when_the_title_is_over_50_characters(){

        Assertions.assertThrows(TitleTooLongException.class, () -> new AdTitle("Esto es una prueba para comprobar que no se puede añadir más de " +
                "cincuenta caracteres a un titulo"));
    }
}
