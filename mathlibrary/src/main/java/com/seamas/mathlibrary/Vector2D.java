package com.seamas.mathlibrary;

import android.graphics.PointF;

public class Vector2D extends PointF {

    public Vector2D(Vector2D v) {
        x = v.x;
        y = v.y;
    }

    public Vector2D(PointF p) {
        x = p.x;
        y = p.y;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isZeroVector() {
        return Utils.isZero(x) && Utils.isZero(y);
    }

    public Vector2D getNormalVector() {
        return new Vector2D(y, -x);
    }

    public boolean isParallelWith(Vector2D v) {
        return Utils.isZero(x * v.y - v.x * y);
    }

    public boolean equals(Vector2D v) {
        return Utils.isZero(x - v.x) && Utils.isZero(y - v.y);
    }

    public float dotWith(Vector2D v) {
        return x * v.x + y * v.y;
    }

    public boolean isOrthogonalWith(Vector2D v) {
        return Utils.isZero(dotWith(v));
    }
}
