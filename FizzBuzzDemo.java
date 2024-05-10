public class FizzBuzzDemo {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        try {
            fizzBuzz.A.join();
            fizzBuzz.B.join();
            fizzBuzz.E.join();
            fizzBuzz.C.join();
            fizzBuzz.D.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
