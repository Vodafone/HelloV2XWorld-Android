package com.vodafone.v2x.android.hellov2xworld.mapdrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.vodafone.v2x.android.hellov2xworld.AndroidApplication;
import com.vodafone.v2x.android.hellov2xworld.R;
/**
 * This class is responsible for getting all the icons the app needs. It uses the Singleton pattern to ensure
 * that only one instance of the class is created and all instances of the class refer to the same object.
 * The icons are loaded from the resources folder and are stored as Bitmaps.
 */
public class IconsFactory {
    /**
     * The static instance of the class
     */
    private static final IconsFactory instance = new IconsFactory(AndroidApplication.getContext());
    /**
     * The context in which the class is used
     */
    private final Context mContext;
    /**
     * Bitmap representation of the ITS icon
     */
    private Bitmap mITSIcon;
    /**
     * Bitmap representation of the ITS unavailable icon
     */
    private Bitmap mITSIconUnavailable;
    /**
     * Bitmap representation of the CAM icon
     */
    private Bitmap mCAMIcon;
    /**
     * Constructor that creates an instance of the class and preloads the icons.
     *
     * @param context The context in which the class is used
     */
    private IconsFactory(Context context) {
        mContext = context;
        preloadIcons();
    }
    /**
     * Returns the static instance of the class
     *
     * @return the static instance of the class
     */
    public static IconsFactory getInstance() {
        return instance;
    }
    /**
     * Preloads the icons by decoding them from the resources folder and storing them as Bitmaps
     */
    private void preloadIcons() {
        mITSIcon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.arrowsblue80), 100, 100, false);
        mITSIconUnavailable = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.arrowsbluedark80), 100, 100, false);
        mCAMIcon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.arrowsred80), 60, 60, false);

    }
    /**
     * Returns the ITS icon as a Drawable
     *
     * @return the ITS icon as a Drawable
     */
    public Drawable getITSIcon() {
        return new BitmapDrawable(mContext.getResources(), mITSIcon);
    }
    /**
     * Returns the ITS unavailable icon as a Drawable
     *
     * @return the ITS unavailable icon as a Drawable
     */
    public Drawable getITSUnavailableIcon() {
        return new BitmapDrawable(mContext.getResources(), mITSIconUnavailable);
    }
    /**
     * Returns the CAM icon as a Drawable
     *
     * @return the CAM icon as a Drawable
     */
    public Drawable getCAMIcon() {
        return new BitmapDrawable(mContext.getResources(), mCAMIcon);
    }
}
