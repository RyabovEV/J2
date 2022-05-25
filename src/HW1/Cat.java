package HW1;

import java.sql.Struct;

public class Cat implements Acnions {
    protected boolean onDistance = true;
    protected int runPower;
    protected int jumpPower;

    Cat(int runPower, int jumpPower) {
        this.runPower = runPower;
        this.jumpPower = jumpPower;
    }

    @Override
    public void run() {
        if (onDistance) {
            System.out.println("кот пробежал");
        } else System.out.println("кот спллаховал");
    }

    @Override
    public void jump() {
        if (onDistance) {
            System.out.println("кот прыгнул");
        } else System.out.println("кот спллаховал");
    }

    @Override
    public void setOnDistanceFalse() {
        this.onDistance = false;
    }

    @Override
    public void obstacle(Obstacles obstacle) {
        if (!onDistance)return;
        if (obstacle instanceof Wall) {
            if (((Wall) obstacle).height > jumpPower) setOnDistanceFalse();
            jump();
        }
        if (obstacle instanceof Treadmill) {
            if (((Treadmill) obstacle).distance > runPower) setOnDistanceFalse();
            run();
        }
    }
}
