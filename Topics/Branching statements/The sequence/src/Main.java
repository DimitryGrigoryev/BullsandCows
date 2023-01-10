import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int print = 1;
        int countPrint = 1;
        while (n > 0) {
            if (countPrint == 0) {
                print++;
                countPrint = print;
            }
            System.out.print(print + " ");
            countPrint--;
            n--;
        }
        // start coding here
    }
}