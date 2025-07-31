import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== ENHANCED CALCULATOR ===");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. Scientific Calculator");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    basicArithmetic(sc);
                    break;
                case 2:
                    scientificCalc(sc);
                    break;
                case 3:
                    unitConversion(sc);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    //  arithmetic operations
    static void basicArithmetic(Scanner sc) {
        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();
        System.out.print("Enter operator (+, -, *, /): ");
        char op = sc.next().charAt(0);

        double result = 0;
        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b != 0)
                    result = a / b;
                else {
                    System.out.println("Error: Divide by zero!");
                    return;
                }
                break;
            default:
                System.out.println("Invalid operator!");
                return;
        }
        System.out.println("Result: " + result);
    }

    //  calculator 
    static void scientificCalc(Scanner sc) {
        System.out.println("1. Square Root");
        System.out.println("2. Power");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                System.out.print("Enter number: ");
                double num = sc.nextDouble();
                if (num < 0)
                    System.out.println("Error: Negative input!");
                else
                    System.out.println("Square root: " + Math.sqrt(num));
                break;
            case 2:
                System.out.print("Enter base: ");
                double base = sc.nextDouble();
                System.out.print("Enter exponent: ");
                double exp = sc.nextDouble();
                System.out.println("Result: " + Math.pow(base, exp));
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    // Unit conversion
    static void unitConversion(Scanner sc) {
        System.out.println("1. Temperature (C ↔ F)");
        System.out.println("2. Currency (INR ↔ USD)");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                System.out.print("Enter 'C' for Celsius or 'F' for Fahrenheit: ");
                char t = sc.next().toUpperCase().charAt(0);
                if (t == 'C') {
                    System.out.print("Enter temperature in Celsius: ");
                    double c = sc.nextDouble();
                    System.out.println("In Fahrenheit: " + ((c * 9 / 5) + 32));
                } else if (t == 'F') {
                    System.out.print("Enter temperature in Fahrenheit: ");
                    double f = sc.nextDouble();
                    System.out.println("In Celsius: " + ((f - 32) * 5 / 9));
                } else {
                    System.out.println("Invalid input!");
                }
                break;
            case 2:
                System.out.print("Enter 'I' for INR to USD or 'U' for USD to INR: ");
                char cur = sc.next().toUpperCase().charAt(0);
                if (cur == 'I') {
                    System.out.print("Enter amount in INR: ");
                    double inr = sc.nextDouble();
                    System.out.println("In USD: $" + (inr / 83.0));
                } else if (cur == 'U') {
                    System.out.print("Enter amount in USD: ");
                    double usd = sc.nextDouble();
                    System.out.println("In INR: ₹" + (usd * 83.0));
                } else {
                    System.out.println("Invalid input!");
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}
