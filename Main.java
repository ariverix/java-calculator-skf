import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        double result = 0.0;
        boolean hasResult = false;

        System.out.println("Калькулятор запущен.");
        System.out.println("Операции: +, -, *, /. C — сброс, S — выход.");

        while (true) {
            if (!hasResult) {
                System.out.print("Введите первое число: ");
                if (!scanner.hasNext()) {
                    break;
                }

                String input = scanner.next();
                if (input.equalsIgnoreCase("s")) {
                    break;
                }
                if (input.equalsIgnoreCase("c")) {
                    result = 0.0;
                    System.out.println("Результат сброшен: " + result);
                    continue;
                }

                try {
                    result = Double.parseDouble(input);
                    if (!Double.isFinite(result)) {
                        System.out.println("Ошибка: введите конечное число.");
                        hasResult = false;
                        continue;
                    }
                    hasResult = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число.");
                    continue;
                }
            }

            System.out.print("Введите операцию (+, -, *, /), C или S: ");
            if (!scanner.hasNext()) {
                break;
            }
            String operation = scanner.next();

            if (operation.equalsIgnoreCase("s")) {
                break;
            }
            if (operation.equalsIgnoreCase("c")) {
                result = 0.0;
                hasResult = false;
                System.out.println("Результат сброшен: " + result);
                continue;
            }
            if (!operation.equals("+") && !operation.equals("-")
                    && !operation.equals("*") && !operation.equals("/")) {
                System.out.println("Ошибка: неподдерживаемая операция.");
                continue;
            }

            System.out.print("Введите второе число: ");
            boolean inputAccepted = false;
            while (!inputAccepted) {
                if (!scanner.hasNext()) {
                    System.out.println("Работа завершена.");
                    return;
                }
                String input = scanner.next();

                if (input.equalsIgnoreCase("s")) {
                    System.out.println("Работа завершена.");
                    return;
                }
                if (input.equalsIgnoreCase("c")) {
                    result = 0.0;
                    hasResult = false;
                    System.out.println("Результат сброшен: " + result);
                    inputAccepted = true;
                    continue;
                }

                double secondOperand;
                try {
                    secondOperand = Double.parseDouble(input);
                    if (!Double.isFinite(secondOperand)) {
                        System.out.print("Ошибка: введите конечное число: ");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Ошибка: введите число: ");
                    continue;
                }

                if (operation.equals("/") && secondOperand == 0.0) {
                    System.out.println("Ошибка: деление на ноль. Результат не изменён: " + result);
                    inputAccepted = true;
                    continue;
                }

                switch (operation) {
                    case "+":
                        result += secondOperand;
                        break;
                    case "-":
                        result -= secondOperand;
                        break;
                    case "*":
                        result *= secondOperand;
                        break;
                    case "/":
                        result /= secondOperand;
                        break;
                    default:
                        break;
                }

                System.out.println("Результат: " + result);
                inputAccepted = true;
            }
        }

        System.out.println("Работа завершена.");
    }
}
