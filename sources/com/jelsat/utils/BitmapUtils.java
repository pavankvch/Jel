package com.jelsat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;

public class BitmapUtils {
    private static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        options = options.outWidth;
        int i4 = 1;
        if (i3 > i2 || options > i) {
            i3 /= 2;
            options /= 2;
            while (i3 / i4 >= i2 && options / i4 >= i) {
                i4 *= 2;
            }
        }
        return i4;
    }

    public static Bitmap decodeSampledBitmapFromUri(Context context, Uri uri, int i, int i2) {
        Options options = new Options();
        try {
            context = context.getContentResolver().openInputStream(uri);
        } catch (Context context2) {
            context2.printStackTrace();
            context2 = null;
        }
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context2, null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = null;
        return BitmapFactory.decodeStream(context2, null, options);
    }

    public static Bitmap decodeSampledBitmapFromPath(String str, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static int calculateInSampleSize1(Options options, int i, int i2) {
        int i3 = options.outHeight;
        options = options.outWidth;
        if (i3 <= i2) {
            if (options <= i) {
                return 1;
            }
        }
        if (options > i3) {
            return Math.round(((float) i3) / ((float) i2));
        }
        return Math.round(((float) options) / ((float) i));
    }
}
