package HW1;

public class Human implements Acnions{    protected boolean onDistance = true;
    protected int runPower;
    protected int jumpPower;

    Human(int runPower, int jumpPower) {
        this.runPower = runPower;
        this.jumpPower = jumpPower;
    }

    @Override
    public void run() {
        if (onDistance) {
            System.out.println("человег пробежал");
        } else System.out.println("челове спллаховал");
    }

    @Override
    public void jump() {
        if (onDistance) {
            System.out.println("человек прыгнул");
        } else System.out.println("человек спллаховал");
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