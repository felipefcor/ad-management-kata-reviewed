package domain.Ad;
import java.util.Comparator;

public class VisitorSorter implements Comparator<Ad> {

    @Override
    public int compare(Ad ad1, Ad ad2) {
        return ad1.getAdVisits().compareTo(ad2.getAdVisits());
    }
}
