import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] arrayNum = new int[b - a + 1][2];
        for (int i = a; i <= b; i++) {
            Random random = new Random(i);
            arrayNum[i - a][0] = i;
            arrayNum[i - a][1] = random.nextInt(k);
            for (int j = 1; j < n; j++) {
                arrayNum[i - a][1] = Math.max(arrayNum[i - a][1], random.nextInt(k));
            }
        }
        int beginNum = arrayNum[0][0];
        int maxMin = arrayNum[0][1];
        for (int i = a; i <= b; i++) {
            if (arrayNum[i - a][1] < maxMin) {
                beginNum = arrayNum[i - a][0];
                maxMin = arrayNum[i - a][1];
            }
        }
        System.out.println(beginNum);
        System.out.println(maxMin);
    }
}