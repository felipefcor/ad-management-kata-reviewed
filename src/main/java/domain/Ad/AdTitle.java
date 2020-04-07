package domain.Ad;

import domain.Ad.DTO.AdDTOTitle;
import domain.Ad.exceptions.TitleTooLongException;

public class AdTitle {
    private String adTitle;

    public AdTitle(String adTitle) {
        if(adTitle.length() > 50 ) throw new TitleTooLongException();
        this.adTitle = adTitle;
    }

    public AdDTOTitle createTitleDTO() {
        AdDTOTitle adDTOTitle = new AdDTOTitle();
        adDTOTitle.adTitle = this.adTitle;
        return adDTOTitle;
    }
}
