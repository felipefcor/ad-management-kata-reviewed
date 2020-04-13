package domain.Ad.valueObjects;

import java.util.Objects;

public class AdDescription {
    private String adDescription;

    public AdDescription(String adDescription) {
        this.adDescription = adDescription;
    }

    public String getAdDescription() {
        return adDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDescription that = (AdDescription) o;
        return Objects.equals(adDescription, that.adDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adDescription);
    }

    @Override
    public String toString() {
        return "AdDescription{" +
                "adDescription='" + adDescription + '\'' +
                '}';
    }
}
