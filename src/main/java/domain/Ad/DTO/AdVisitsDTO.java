package domain.Ad.DTO;

import java.util.Objects;

public class AdVisitsDTO {
    public Integer visits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdVisitsDTO that = (AdVisitsDTO) o;
        return Objects.equals(visits, that.visits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visits);
    }
}
