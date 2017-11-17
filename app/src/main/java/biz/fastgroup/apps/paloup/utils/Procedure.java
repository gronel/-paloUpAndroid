package biz.fastgroup.apps.paloup.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gronel on 05/02/2017.
 */

public class Procedure {

    Context c;

    public  Procedure(Context c){
        this.c=c;
    }

    public Bitmap getBitmapFromAsset(String strName) throws IOException
    {
        AssetManager assetManager = c.getAssets();
        InputStream istr = assetManager.open(strName);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
}
