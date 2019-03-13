package com.seamas.mathlibrary;

import android.graphics.PointF;

public class Line2D {
    public PointF a = new PointF();
    public PointF b = new PointF();

    public Line2D(PointF a, PointF b) {
        this.a.set(a);
        this.b.set(b);
    }

    public Line2D(PointF a, Vector2D vector) {
        this.a.set(a);
        this.b.set(a.x + vector.x, a.y + vector.y);
    }

    public Vector2D getVector() {
        return new Vector2D(b.x - a.x, b.y - a.y);
    }

    public Vector2D getNormalVector() {
        return getVector().getNormalVector();
    }

    public boolean isParallelWith(Line2D l) {
        return getVector().isParallelWith(l.getVector());
    }

    public boolean isParallelWith(Vector2D v) {
        return getVector().isParallelWith(v);
    }

    public boolean isContainPoint(PointF p) {
        return getVector().isParallelWith(new Vector2D(a.x - p.x, a.y - p.y));
    }

    public boolean equals(Line2D l) {
        return isParallelWith(l) && isContainPoint(l.a);
    }

    public boolean isOrthogonalWith(Line2D l) {
        return getVector().isOrthogonalWith(l.getVector());
    }

    public boolean isZero() {
        return getVector().isZeroVector();
    }

    public boolean isOrthogonalWith(Vector2D v) {
        return getVector().isOrthogonalWith(v);
    }

    public float getDistanceWith(PointF p) {
        float la = Utils.pointsDistance(a, p);
        float lb = Utils.pointsDistance(b, p);
        float lc = Utils.pointsDistance(a, b);
        float s = (la + lb + lc) / 2;
        return (float) Math.abs(Math.sqrt(s * (s - la) * (s - lb) * (s - lc)));
    }

    public float getDistanceWith(Line2D l) {
        if (isParallelWith(l)) {
            return getDistanceWith(l.a);
        }
        return 0;
    }

    public PointF getIntersectionWith(Line2D l) {
        if (isZero() || l.isZero())
            return null;
        if (isParallelWith(l)) {
            if (isContainPoint(l.a))
                return a;
            return null;
        }
        if (Utils.isZero(a.x - b.x))
            return new PointF(a.x, l.a.y + (l.a.x - a.x) / (l.a.x - l.b.x) * l.getVector().y);
        else if (Utils.isZero(a.y - b.y))
            return new PointF(l.a.x + (l.a.y - a.y) / (l.a.y - l.b.y) * l.getVector().x, a.y);
        else if (Utils.isZero(l.a.x - l.b.x))
            return new PointF(l.a.x, a.y + (a.x - l.a.x) / (a.x - b.x) * getVector().y);
        else if (Utils.isZero(l.a.y - l.b.y))
            return new PointF(b.x + (a.y - l.a.y) / (a.y - b.y) * l.getVector().x, l.a.y);
        else {
            float m1 = (b.y - a.y) / (b.x - a.x);
            float m2 = (l.b.y - l.a.y) / (l.b.x - l.a.x);
            float k1 = b.y - (m1 * b.x);
            float k2 = l.b.y - (m2 * l.b.x);
            float x = (k2 - k1) / (m1 - m2);
            float y = (m1 * x) + k1;
            return new PointF(x, y);
        }
    }
}
