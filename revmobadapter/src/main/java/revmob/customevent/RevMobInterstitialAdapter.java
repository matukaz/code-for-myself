package revmob.customevent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;
import com.revmob.client.RevMobClient;

public class RevMobInterstitialAdapter implements CustomEventInterstitial {
    final static String TAG = "RevMobInterstitial";
    @Override
    public void requestInterstitialAd(Context context,
                                      CustomEventInterstitialListener listener,
                                      String serverParameter,
                                      MediationAdRequest mediationAdRequest,
                                      Bundle customEventExtras) {
        RevMobClient.setSDKName("admob-android");
        Activity fullscreenActivity;
        if (context instanceof Activity) {
            fullscreenActivity = (Activity) context;
        } else {
            listener.onAdFailedToLoad(AdRequest.ERROR_CODE_INTERNAL_ERROR);
            Log.e(TAG, "Context should be as instance of Activity");
            return;
        }
        RevMobWrapper.setMediaId(serverParameter);
        RevMobWrapper.getInstance().requestInterstitial(fullscreenActivity, new ExternalInterstitialListener(listener));
    }

    @Override
    public void showInterstitial() {
        RevMobWrapper.getInstance().showInterstitial();
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

    private class ExternalInterstitialListener implements IExternalInterstitialListener {
        private CustomEventInterstitialListener customEventInterstitialListener;
        public ExternalInterstitialListener(CustomEventInterstitialListener customEventInterstitialListener) {
            this.customEventInterstitialListener = customEventInterstitialListener;
        }

        @Override
        public void clicked() {
            customEventInterstitialListener.onAdClicked();
        }

        @Override
        public void closed() {
            customEventInterstitialListener.onAdClosed();
        }

        @Override
        public void displayed() {
            customEventInterstitialListener.onAdOpened();
        }

        @Override
        public void failedToLoad() {
            customEventInterstitialListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INTERNAL_ERROR);
        }

        @Override
        public void loaded() {
            customEventInterstitialListener.onAdLoaded();
        }
    }
}
