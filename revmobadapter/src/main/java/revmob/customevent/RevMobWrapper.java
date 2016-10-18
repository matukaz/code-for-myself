package revmob.customevent;

import android.app.Activity;
import android.util.Log;

import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.ads.interstitial.RevMobFullscreen;

class RevMobWrapper {
    private static final String LOG_TAG = "RevMobWrapper";
    private RevMob session;
    private boolean sessionStarted;

    private Activity bannerActivity;
    private RevMobBanner revMobBanner;
    private RevMobAdsListener bannerListener;
    private boolean shouldLoadBanner;

    private Activity fullscreenActivity;
    private RevMobFullscreen revMobFullscreen;
    private RevMobAdsListener fullscreenListener;
    private boolean fullscreenLoaded;
    private boolean shouldLoadInterstitial;


    private static RevMobWrapper ourInstance;
    private static String mediaId;
    private static String bannerPlacementId = null;
    private static String interstitialPlacementId = null;

    /**
     * Sets Wrapper's mediaId to be used by RevMob SDK
     *
     * @param mediaId Value of media id
     */

    public static void setMediaId(String mediaId) {
        RevMobWrapper.mediaId = mediaId;
    }

    /**
     * Sets Wrapper's placementId to be used by RevMob SDK
     *
     * @param bannerPlacementId Value of media id
     */
    public static void setBannerPlacementId(String bannerPlacementId) {
        RevMobWrapper.bannerPlacementId = bannerPlacementId;
    }

    /**
     * Sets Wrapper's placementId to be used by RevMob SDK
     *
     * @param interstitialPlacementId Value of media id
     */
    public static void setInterstitialPlacementId(String interstitialPlacementId) {
        RevMobWrapper.interstitialPlacementId = interstitialPlacementId;
    }

    /**
     * Singleton implementation
     * @return the instance
     */
    public synchronized static RevMobWrapper getInstance() {
        if(ourInstance == null){
            synchronized(RevMobWrapper.class){
                if(ourInstance == null){
                    ourInstance = new RevMobWrapper();
                }
            }
        }
        return ourInstance;
    }

    private RevMobWrapper() {
    }

    /**
     * Requests banner ad via {@link RevMob} and initialize session if necessary
     *
     * @param bannerActivity {@link Activity} that banner ad was requested
     * @param externalBannerListener Implementation of {@link IExternalBannerListener} that listens to banner events
     */

    void requestBanner (final Activity bannerActivity, final IExternalBannerListener externalBannerListener) {
        this.bannerActivity = bannerActivity;
        session = RevMob.session();
        bannerListener = createRevMobListener(externalBannerListener);
        if ( session == null || !sessionStarted ) {
            shouldLoadBanner = true;
            if (session == null)  session = RevMob.startWithListener(bannerActivity, createSessionListener(externalBannerListener), mediaId);
        } else {
            createBanner();
        }
    }

    /**
     * Requests interstitial ad via {@link RevMob} and initialize session if necessary.
     * @param fullscreenActivity {@link Activity} that interstitial ad was requested.
     * @param externalInterstitialListener Implementation of {@link IExternalInterstitialListener} that listens to insterstitial ad events
     */

    void requestInterstitial (final Activity fullscreenActivity, final IExternalInterstitialListener externalInterstitialListener) {
        this.fullscreenActivity = fullscreenActivity;
        session = RevMob.session();
        fullscreenListener = createRevMobListener(externalInterstitialListener);
        if ( session == null || !sessionStarted ) {
            shouldLoadInterstitial = true;
            if (session == null) session = RevMob.startWithListener(fullscreenActivity, createSessionListener(externalInterstitialListener), mediaId);
        } else {
            createFullscreen();
        }

    }

    /**
     * Shows interstitial ad if loaded.
     */

    void showInterstitial () {
        if (fullscreenLoaded) {
            if (revMobFullscreen != null) revMobFullscreen.show();
        } else {
            Log.w(LOG_TAG, "RevMob interstitial is not ready yet!");
        }
    }

    /**
     * Creates Fullscreen Ad from SDK.
     */

    private void createFullscreen () {
        if(session!= null) revMobFullscreen = session.createFullscreen(fullscreenActivity, interstitialPlacementId, fullscreenListener);
    }

    /**
     * Creates Banner Ad from SDK.
     */

    private void createBanner () {
        if(session!= null) revMobBanner = session.preLoadBanner(bannerActivity, bannerPlacementId, bannerListener);
    }

    /**
     * Creates {@link RevMobAdsListener} instance using implementations of {@link IExternalAdListener}.
     * @param adListener Should be an implementation of either {@link IExternalBannerListener} or {@link IExternalInterstitialListener}
     * @return instance of {@link RevMobAdsListener} according to ad unit used
     */

    private RevMobAdsListener createRevMobListener (final IExternalAdListener adListener) {
        final boolean isBanner = adListener instanceof IExternalBannerListener;
        final Activity currentActivity = isBanner ? bannerActivity : fullscreenActivity;
        return new RevMobAdsListener() {
            @Override
            public void onRevMobAdClicked() {
                super.onRevMobAdClicked();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adListener.clicked();
                    }
                });
            }

            @Override
            public void onRevMobAdDismissed() {
                super.onRevMobAdDismissed();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adListener.closed();
                    }
                });
            }

            @Override
            public void onRevMobAdDisplayed() {
                super.onRevMobAdDisplayed();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adListener.displayed();
                    }
                });
            }

            @Override
            public void onRevMobAdNotReceived(String s) {
                super.onRevMobAdNotReceived(s);
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adListener.failedToLoad();
                    }
                });
            }

            @Override
            public void onRevMobAdReceived() {
                super.onRevMobAdReceived();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (adListener instanceof IExternalBannerListener) {
                            revMobBanner.show();
                            ((IExternalBannerListener) adListener).loaded(revMobBanner);
                        } else if (adListener instanceof IExternalInterstitialListener){
                            fullscreenLoaded = true;
                            ((IExternalInterstitialListener) adListener).loaded();
                        }
                    }
                });
            }
        };
    }

    /**
     * Creates {@link RevMobAdsListener} that listens to session events and load ad units if needed.
     * @param adListener instance to be passed to load methods
     * @return listener to session events
     */
    private RevMobAdsListener createSessionListener (final IExternalAdListener adListener) {
        final boolean isBanner = adListener instanceof IExternalBannerListener;
        final Activity currentActivity = isBanner ? bannerActivity : fullscreenActivity;
        return new RevMobAdsListener() {
            @Override
            public void onRevMobSessionNotStarted(String s) {
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adListener.failedToLoad();
                    }
                });
            }

            @Override
            public void onRevMobSessionStarted() {
                sessionStarted = true;
                if (shouldLoadBanner) {
                    createBanner();
                }
                if (shouldLoadInterstitial) {
                    createFullscreen();
                }
            }
        };
    }
}
