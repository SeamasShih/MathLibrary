package com.seamas.mathlibrary;

import android.graphics.PointF;

public class Utils {
    public static float pointsDistance(PointF a, PointF b) {
        float x = b.x - a.x;
        float y = b.y - a.y;
        return Math.abs((float) Math.abs(Math.sqrt(x * x)) + Math.abs((float) Math.sqrt(y * y)));
    }

    public static boolean isZero(float f) {
        return Math.abs(f) < Options.TOLERANCE;
    }
}
