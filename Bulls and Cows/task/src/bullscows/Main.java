package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private String passDigitStr = "";
    private boolean isErrorStatus = false;

    public void inputAndCheck() {
        Scanner s = new Scanner(System.in);
        int countTurn = 1;
        boolean win = false;
        if (!this.isErrorStatus) {
            while (!win) {
                System.out.printf("Turn %d:\n", countTurn);
                String inputStr = s.nextLine();
                char[] numChar = inputStr.toCharArray();
                char[] pass = this.passDigitStr.toCharArray();
                int cow = 0;
                int bull = 0;
                for (int i = 0; i < pass.length; i++) {
                    for (int j = 0; j < numChar.length; j++) {
                        if (numChar[j] == pass[i]) {
                            if (i == j) {
                                bull++;
                            } else {
                                cow++;
                            }
                        }
                    }
                }
                System.out.print("Grade: ");
                if (bull != 0 && cow != 0) System.out.printf("%d bull(s) and %d cow(s).\n", bull, cow);
                if (cow == 0) System.out.printf("%d bull(s)\n", bull);
                if (bull == 0) System.out.printf("%d cow(s)\n", cow);
                if (bull == this.passDigitStr.length()) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    win = true;
                }
                countTurn++;
            }
        }
    }

    private static boolean isCheckDouble(StringBuilder passDigit, char charAt) {
        boolean flag = false;
        for (int i = 0; i < passDigit.length(); i++) {
            if (Character.getNumericValue(charAt) == Character.getNumericValue(passDigit.charAt(i))) {
                flag = true;
            }
        }
        return flag;
    }


    private StringBuilder initialStringForPass(int countMaxChInPass) {
        StringBuilder stringBuilder = new StringBuilder();
        char ch = 'a';
        if (countMaxChInPass <= 10) {
            for (int i = 0; i < countMaxChInPass; i++) {
                stringBuilder.append(i);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                stringBuilder.append(i);
            }
            for (int i = 10; i < countMaxChInPass; i++) {
                stringBuilder.append(ch);
                ch++;
            }
        }
        return  stringBuilder;
    }

    public void generateSecretCode() {
        Scanner s = new Scanner(System.in);
        Random random = new Random();
        String temp1 = null;
        String temp2 = null;
        int countDigitPass = 0;
        int countMaxChInPass;
        System.out.println("Please, enter the secret code's length:");
        try {
            temp1 = s.nextLine();
            countDigitPass = Integer.parseInt(temp1);
            if (countDigitPass > 36) {
                System.out.println("Error: can't generate a secret number with a length 1 of 36.");
                this.isErrorStatus = true;
            } else if (countDigitPass < 1) {
                System.out.println("Error: can't generate a secret number with a length 1 of 36.");
                this.isErrorStatus = true;
            }
        } catch (Exception e) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", temp1);
            this.isErrorStatus = true;
        }
        if (!this.isErrorStatus) {
            System.out.println("Input the number of possible symbols in the code:");
            try {
                temp2 = s.nextLine();
                countMaxChInPass = Integer.parseInt(temp2);
                if (countMaxChInPass < countDigitPass) {
                    System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
                    this.isErrorStatus = true;
                } else {
                    int randomIndex;
                    if (countMaxChInPass > 36) {
                        System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                        this.isErrorStatus = true;
                    } else {
                        StringBuilder passDigit = new StringBuilder();
                        StringBuilder tmp = initialStringForPass(countMaxChInPass);
                        do {
                            randomIndex = random.nextInt(countMaxChInPass);
                            if (!isCheckDouble(passDigit, tmp.charAt(randomIndex)))
                                passDigit.append(tmp.charAt(randomIndex));
                        } while (passDigit.length() < countDigitPass);
                        this.passDigitStr = passDigit.toString();
//                        System.out.println(passDigitStr);
                        System.out.print("The secret is prepared: ");
                        printStar(countDigitPass);
                        if (countMaxChInPass <= 10) {
                            System.out.printf(" (0-%d).\n", countMaxChInPass - 1);
                        } else if (countMaxChInPass == 11) {
                            System.out.print(" (0-9, a).\n");
                        } else {
                            System.out.printf(" (0-9, a-%c).\n", (char) (countMaxChInPass + 86));
                        }
                        System.out.println("Okay, let's start a game!");
                    }
                }
            } catch (Exception e) {
                System.out.printf("Error: \"%s\" isn't a valid number.\n", temp2);
                this.isErrorStatus = true;
            }
        }

    }

    private void printStar(int countDigitPass) {
        for (int i = 0; i < countDigitPass; i++) {
            System.out.print("*");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.generateSecretCode();
        main.inputAndCheck();
    }
}
