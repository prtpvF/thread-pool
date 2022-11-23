import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        /*
        * класс executor который содержит статические методы.
        * Эти методы принимают какие-то аргументы и возвращают ExecutorServices
        * сам ExecutorService является пулом потоков
        *
        *
        *                                                            кол-во потоков
        */
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++)
            executorService.submit(new Work(i)); // передаем задачи
        executorService.shutdown(); // перестаем принимать задачи которые были переданны с помощью submit
        System.out.println("all tasks submitted");
        executorService.awaitTermination(1, TimeUnit.MINUTES); // поток main останавливается и ждет завершения работы потоков
    }                                                                 // если потоки не справилиь за 1 минуту, то поток Main просыпается и работает дальше
}
class Work implements Runnable{
    private  int id;
    public Work( int id){
        this.id = id;
    }



    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("work " + id + " was completed");
    }

}

