package ru.kulikovman.atomiccircles;

import android.graphics.Color;
import java.util.Random;

public class EnemyCircle extends SimpleCircle {
    public static final int FROM_RADIUS = 10;
    public static final int TO_RADIUS = 110;
    public static final int ENEMY_COLOR = Color.rgb(255, 82, 76);
    public static final int FOOD_COLOR = Color.rgb(80, 229, 117);
    public static final int RANDOM_SPEED = 10;
    private int dx;
    private int dy;

    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();

        //Create circle radius
        int radius = CanvasView.recalculateRadius(FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS));

        //Create start position
        int x = radius + random.nextInt(GameManager.getWidth() - radius * 2);
        int y = radius + random.nextInt(GameManager.getHeight() - radius * 2);

        //Initial direction of movement
        double direction = random.nextDouble() * 360;
        double angel = Math.toRadians(direction);
        int dx = (int) (Math.cos(angel) * RANDOM_SPEED);
        int dy = (int) (-Math.sin(angel) * RANDOM_SPEED);

        return new EnemyCircle(x, y, radius, dx, dy);
    }

    public void setEnemyOrFoodColorDependsOn(MainCircle mainCircle) {
        if (isSmallerThan(mainCircle)) {
            setColor(FOOD_COLOR);
        } else setColor(ENEMY_COLOR);
    }

    public boolean isSmallerThan(SimpleCircle circle) {
        if (radius < circle.radius) return true;
        return false;
    }

    public void moveOneStep() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if (x > GameManager.getWidth() - radius || x < radius) dx = -dx;
        if (y > GameManager.getHeight() - radius || y < radius) dy = -dy;
    }
}
