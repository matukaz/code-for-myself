package com.teddydeveloper.stardewvalleywiki.Helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by matuk on 09.04.2017.
 */

public class Helper {

    public static void openWebPage(Context context, String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
