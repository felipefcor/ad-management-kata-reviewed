package domain.Ad;

import domain.Ad.DTO.AdDTOTitle;

public class AdTitle {
    private String adTitle;

    public AdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public AdDTOTitle createTitleDTO() {
        AdDTOTitle adDTOTitle = new AdDTOTitle();
        adDTOTitle.adTitle = this.adTitle;
        return adDTOTitle;
    }
}
