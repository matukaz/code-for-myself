package revmob.customevent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;
import com.revmob.client.RevMobClient;

public class RevMobBannerAdapter implements CustomEventBanner {
    final static String TAG = "RevMobBannerAdapter";

    @Override
    public void requestBannerAd(Context context,
                                final CustomEventBannerListener listener,
                                String serverParameter,
                                AdSize size,
                                MediationAdRequest mediationAdRequest,
                                Bundle customEventExtras) {
        RevMobClient.setSDKName("admob-android");
        Activity bannerActivity;
        if (context instanceof Activity) {
            bannerActivity = (Activity) context;
        } else {
            listener.onAdFailedToLoad(AdRequest.ERROR_CODE_INTERNAL_ERROR);
            Log.e(TAG, "Context should be as instance of Activity");
            return;
        }
        RevMobWrapper.setMediaId(serverParameter);
        RevMobWrapper.getInstance().requestBanner(bannerActivity, new ExternalBannerListener(listener));
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    private class ExternalBannerListener implements IExternalBannerListener {
        private CustomEventBannerListener customEventBannerListener;
        public ExternalBannerListener (CustomEventBannerListener customEventBannerListener) {
            this.customEventBannerListener = customEventBannerListener;
        }
        @Override
        public void clicked() {
            customEventBannerListener.onAdClicked();
            customEventBannerListener.onAdLeftApplication();
        }

        @Override
        public void closed() {
            customEventBannerListener.onAdClosed();
        }

        @Override
        public void displayed() {
            customEventBannerListener.onAdOpened();
        }

        @Override
        public void failedToLoad() {
            customEventBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INTERNAL_ERROR);
        }

        @Override
        public void loaded(View v) {
            customEventBannerListener.onAdLoaded(v);
        }
    }
}
