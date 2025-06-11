namespace Lab1
{
    internal class Program
    {
        public static bool canStop = false;
        private static int numThreads = 4;

        static void Main(string[] args)
        {
            Thread controlThread = new Thread(Stopper);

            controlThread.Start();

            for (int i = 0; i < numThreads; i++)
            {
                int threadIndex = i;

                Thread workerThread = new Thread(() => Calculator(threadIndex));

                workerThread.Start();
            }
        }

        static void Calculator(int threadIndex)
        {
            long sum = 0;
            int count = 0;
            int step = 1;

            for (long i = 0; !canStop; i += step)
            {
                sum += i;
                count++;
                Thread.Sleep(10);
            }

            Console.WriteLine($"Thread {threadIndex + 1}: Sum = {sum}, Count = {count}");
        }

        static void Stopper()
        {
            Thread.Sleep(3000);
            canStop = true;
        }
    }
}
