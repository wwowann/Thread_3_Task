import java.util.concurrent.RecursiveTask;

public class SummElementsArray extends RecursiveTask<Integer> {
    final int[] arrayInt;
    final int start;
    final int end;

    public SummElementsArray(int[] arrayInt, int start, int end) {
        this.arrayInt = arrayInt;
        this.start = start;
        this.end = end;
    }

    public int getEnd() {
        return this.end;
    }

    @Override
    protected Integer compute() {
        final int diff = getEnd() - start;
        switch (diff) {
            case 0:
                return 0;
            case 1:
                return arrayInt[start];
            case 2:
                return arrayInt[start] + arrayInt[start + 1];
            default:
                return this.forkTaskAndGetResult();
        }
    }

    public int forkTaskAndGetResult() {
        final int middle = (end - start) / 2 + start;
        SummElementsArray summElementsArray1 = new SummElementsArray(arrayInt, start, middle);
        SummElementsArray summElementsArray2 = new SummElementsArray(arrayInt, middle, end);
        invokeAll(summElementsArray1, summElementsArray2);
        return summElementsArray1.join() + summElementsArray2.join();
    }
}
