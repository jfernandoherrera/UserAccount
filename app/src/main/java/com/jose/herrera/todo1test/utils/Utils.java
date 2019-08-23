package com.jose.herrera.todo1test.utils;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateUtils;
import android.view.Display;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Calendar;


public class Utils {

    public final static int WHITE = 0xFFFFFFFF;
    public final static int BLACK = 0xFF000000;
    public final static int WIDTH = 400;
    public final static int HEIGHT = 400;

    public static String dateFormat(Calendar calendar, Context context) {

        String title = DateUtils.formatDateTime(context,

                calendar.getTimeInMillis(),

                DateUtils.FORMAT_SHOW_DATE

                        | DateUtils.FORMAT_SHOW_WEEKDAY

                        | DateUtils.FORMAT_SHOW_YEAR

                        | DateUtils.FORMAT_ABBREV_MONTH

                        | DateUtils.FORMAT_ABBREV_WEEKDAY);

        return title;

    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context

                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager

                .getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    public static boolean isEmailValid(CharSequence email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    public static Bitmap encodeAsBitmap(String str) throws WriterException {

        BitMatrix result;

        try {

            result = new MultiFormatWriter().encode(str,

                    BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);

        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }

        int w = result.getWidth();

        int h = result.getHeight();

        int[] pixels = new int[w * h];

        for (int y = 0; y < h; y++) {

            int offset = y * w;

            for (int x = 0; x < w; x++) {

                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;

            }

        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);

        return bitmap;

    }

    public static Point getDisplayRealSize(Activity context) {

        final Point size = new Point();

        if (context != null) {

            Display display = context.getWindowManager().getDefaultDisplay();

            display.getSize(size);

        }

        return size;

    }

}