import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    ReentrantLock r1 = new ReentrantLock();
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        r1.lock();
        System.out.println(this.name + " готовится");
        try {

            Thread.sleep(500 + (int)(Math.random() * 800));

        } catch (Exception e) {
            e.printStackTrace();
        }
        r1.unlock();
        r1.lock();
        System.out.println(this.name + " готов");
        try {
            Thread.sleep(500 + (int)(Math.random() * 800));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r1.unlock();
//        r1.lock();
//        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
//        try {
//            Thread.sleep(500 + (int)(Math.random() * 800));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        r1.unlock();
        r1.lock();
        for (int i = 0; i < race.getStages().size(); i++) {
            r1.lock();
            race.getStages().get(i).go(this);
            r1.unlock();
        }
        r1.unlock();
    }
}
