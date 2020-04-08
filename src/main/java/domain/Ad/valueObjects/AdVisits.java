package domain.Ad.valueObjects;

import domain.Ad.DTO.AdVisitsDTO;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class AdVisits {

    Queue<Integer> queueVisits;

    public AdVisits(){
        queueVisits = new LinkedList<Integer>();
    }
    private void visits(int access) {
        queueVisits.add(access);
    }

    private int getVisits() {
        return queueVisits.size();
    }

    public AdVisitsDTO createAdAccessesDTO() {
        AdVisitsDTO adVisitsDTO = new AdVisitsDTO();
        adVisitsDTO.queueVisits = this.queueVisits;
        return adVisitsDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdVisits adVisits = (AdVisits) o;
        return Objects.equals(queueVisits, adVisits.queueVisits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queueVisits);
    }
}
