package domain.Ad;
import java.util.Comparator;

public class VisitorSorter implements Comparator<Ad> {

    @Override
    public int compare(Ad ad1, Ad ad2) {
        return ad1.createAdDTO().adVisits.createAdAccessesDTO().queueVisits.element().compareTo(ad2.createAdDTO().adVisits.createAdAccessesDTO().queueVisits.element());
    }
}
