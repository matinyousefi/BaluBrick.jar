package Logic.Game.Ball;

import Logic.DataBase.Constants;
import Logic.Game.Vector.Vector;

import java.util.*;

public class Ball {
    private static int radius = Constants.ballRadius;
    private static ArrayList<Vector> speedVectors;
    private Vector center;
    private Vector speed;
    private boolean active;
    private boolean onFire;
    private boolean isSpeedUp;
    private boolean isSpeedDown;


    public Ball() {
        if(speedVectors == null) speedVectorsConstructor();
        center = new Vector(Constants.gamePanelWidth / 2, Constants.rocketTopLeftCornerY - radius - 5);
        Random random = new Random();
        int index = random.nextInt(speedVectors.size());
        speed = new Vector(speedVectors.get(index).getX(), speedVectors.get(index).getY());
        active = true;
        onFire = false;
        isSpeedUp = false;
        isSpeedDown = false;
    }

    public static void speedVectorsConstructor() {
        speedVectors = new ArrayList<>();
        int velocityRange = (int) Constants.ballVelocityRange;
        int speedComponentMin = Constants.speedComponentMin;
        for (int i = -velocityRange; i < velocityRange; i++) {
            if (i >= speedComponentMin || i <= -speedComponentMin) {
                int j = - (int) Math.sqrt(velocityRange * velocityRange - i * i);
                if (j < - speedComponentMin) {
                    speedVectors.add(new Vector(i, j));
                }
            }
        }
    }

    public Vector nextCenter() {
        return Vector.sum(speed, center);
    }

    public Vector getCenter() {
        return center;
    }

    public void setCenter(Vector center) {
        this.center = center;
    }

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public int getSpeedXSign() {
        if (speed.getX() < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public int getSpeedYSign() {
        if (speed.getY() < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public int getRadius() {
        return Ball.radius;
    }

    public void setActive(boolean b) {
        this.active = b;
    }

    public boolean isActive() {
        return active;
    }

    public void setOnFire(boolean b) {
        this.onFire = b;
    }

    public boolean isOnFire() {
        return onFire;
    }

    public boolean isSpeedUp() {
        return isSpeedUp;
    }

    public void setSpeedUp(boolean speedUp) {
        isSpeedUp = speedUp;
    }

    public boolean isSpeedDown() {
        return isSpeedDown;
    }

    public void setSpeedDown(boolean speedDown) {
        isSpeedDown = speedDown;
    }

    public static ArrayList<Vector> getSpeedVectors() {
        return speedVectors;
    }
}