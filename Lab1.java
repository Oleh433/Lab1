public class Main {
    private static volatile boolean canStop = false;
    private static final int numThreads = 10;

    public static void main(String[] args) {
        
        Thread controlThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                canStop = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        controlThread.start();


        for (int i = 0; i < numThreads; i++) {
            final int threadIndex = i;
            Thread worker = new Thread(() -> calculator(threadIndex));
            worker.start();
        }
    }

    public static void calculator(int threadIndex) {
        long sum = 0;
        int count = 0;
        int step = 1;

        for (long i = 0; !canStop; i += step) {
            sum += i;
            count++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Thread %d: Sum = %d, Count = %d%n", threadIndex + 1, sum, count);
    }
}
