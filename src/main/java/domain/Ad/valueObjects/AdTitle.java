package domain.Ad.valueObjects;

import domain.Ad.exceptions.TitleTooLongException;

import java.util.Objects;

public class AdTitle {
    private String adTitle;

    public AdTitle(String adTitle) {
        if(adTitle.length() > 50 ) throw new TitleTooLongException();
        this.adTitle = adTitle;
    }

    public String getAdTitle() {
        return adTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdTitle adTitle1 = (AdTitle) o;
        return Objects.equals(adTitle, adTitle1.adTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adTitle);
    }

    @Override
    public String toString() {
        return "AdTitle{" +
                "adTitle='" + adTitle + '\'' +
                '}';
    }
}
