package user;

import domain.Ad.Ad;
import domain.Ad.AdCatalog.AdCatalogObserver;

import java.util.ArrayList;
import java.util.List;

public class User implements AdCatalogObserver {
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

    @Override
    public void updateFavourites(Ad ad) {
         this.favoriteAds.remove(ad);
    }
}
