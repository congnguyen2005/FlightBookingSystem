package com.example.flightbookingsystem;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

import androidx.annotation.Nullable;

public class MovieDrawable extends Drawable {
    private Movie mMovie;
    private long mStartTime;

    public MovieDrawable(Movie movie) {
        mMovie = movie;
        mStartTime = SystemClock.uptimeMillis();
    }

    @Override
    public void draw(Canvas canvas) {
        // Tính toán thời gian trôi qua
        long currentTime = SystemClock.uptimeMillis();
        int relTime = (int) (currentTime - mStartTime);
        mMovie.setTime(relTime);

        // Vẽ GIF lên canvas
        mMovie.draw(canvas, 0, 0);
        invalidateSelf();
    }

    @Override
    public int getIntrinsicWidth() {
        return mMovie.width();
    }

    @Override
    public int getIntrinsicHeight() {
        return mMovie.height();
    }

    @Override
    public void setAlpha(int alpha) {
        // Không hỗ trợ alpha cho MovieDrawable
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
