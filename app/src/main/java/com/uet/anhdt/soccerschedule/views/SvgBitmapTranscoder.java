package com.uet.anhdt.soccerschedule.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.caverock.androidsvg.SVG;

/**
 * Created by anhdt on 4/3/2017.
 */

public final class SvgBitmapTranscoder implements ResourceTranscoder<SVG, Bitmap> {

    @Override
    public Resource<Bitmap> transcode(Resource<SVG> toTranscode) {
        SVG svg = toTranscode.get();
        Picture picture = svg.renderToPicture();
        PictureDrawable drawable = new PictureDrawable(picture);
        return new SimpleResource<>(pictureDrawableToBitmap(drawable));
    }

    @Override
    public String getId() {
        return "";
    }

    private Bitmap pictureDrawableToBitmap(PictureDrawable pictureDrawable) {
        Bitmap bmp = Bitmap.createBitmap(pictureDrawable.getIntrinsicWidth(), pictureDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawPicture(pictureDrawable.getPicture());
        return bmp;
    }

}
