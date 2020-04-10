package domain.Ad.valueObjects;

import java.util.Objects;

public class AdVisits {

    public Integer visits = 0;

    public void increaseVisits() {
        visits++;
    }

    private int getVisits(){
        return visits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdVisits adVisits = (AdVisits) o;
        return Objects.equals(visits, adVisits.visits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visits);
    }

    @Override
    public String toString() {
        return "AdVisits{" +
                "visits=" + visits +
                '}';
    }
}
