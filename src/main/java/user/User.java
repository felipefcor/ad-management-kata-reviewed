package user;

import domain.Ad.Ad;

import java.util.ArrayList;
import java.util.List;

public class User {
    private UserId userId;
    private List<Ad> favoriteAds;

     public User(UserId userId) {
        this.userId = userId;
        this.favoriteAds = new ArrayList<>();
    }

    public void addfavoriteAds(Ad ad) {
        this.favoriteAds.add(ad);
    }

    public List<Ad> getFavoriteAds() {
        return favoriteAds;
    }
}
