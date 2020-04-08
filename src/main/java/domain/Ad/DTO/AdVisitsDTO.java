package domain.Ad.DTO;

import java.util.Objects;
import java.util.Queue;

public class AdVisitsDTO {
    public Queue<Integer> queueVisits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdVisitsDTO that = (AdVisitsDTO) o;
        return Objects.equals(queueVisits, that.queueVisits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queueVisits);
    }
}
