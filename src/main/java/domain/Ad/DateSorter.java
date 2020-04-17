package domain.Ad;

import java.util.Comparator;

public class DateSorter implements Comparator<Ad> {

        @Override
        public int compare(Ad ad1, Ad ad2) {
            return ad1.getDate().compareTo(ad2.getDate());
        }
}
