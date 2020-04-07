package domain.Ad;
import java.util.Comparator;

public class DateSorter implements Comparator<Ad> {

        @Override
        public int compare(Ad ad1, Ad ad2) {
            return ad1.createAdDTO().date.compareTo(ad2.createAdDTO().date);
        }
}
