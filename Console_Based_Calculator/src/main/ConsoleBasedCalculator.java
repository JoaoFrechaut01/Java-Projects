package main;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ConsoleBasedCalculator {

	private static final DecimalFormat DF = new DecimalFormat("#.##########");

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("====== Console Based Calculator ======");
			System.out.println("Type 'exit' anytime to quit.\n");

			while (true) {
				Double num1 = readDouble(sc, "Enter the first number: ");
				if (num1 == null)
					break;

				Character op = readOperator(sc, "Enter operator (+, -, *, /): ");
				if (op == null)
					break;

				Double num2 = readDouble(sc, "Enter the second number: ");
				if (num2 == null)
					break;

				Double result = calculate(num1, num2, op);
				if (result == null) {
					// e.g., division by zero
					continue;
				}

				System.out.println("Result: " + DF.format(result));

				if (!askToContinue(sc))
					break;
				System.out.println();
			}

			System.out.println("Calculator closed. Bye");
		}
	}

	private static Double readDouble(Scanner sc, String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = sc.nextLine().trim();

			if (input.equalsIgnoreCase("exit"))
				return null;

			try {
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid number. Try again (or type 'exit').");
			}
		}
	}

	private static Character readOperator(Scanner sc, String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = sc.nextLine().trim();

			if (input.equalsIgnoreCase("exit"))
				return null;

			if (input.length() == 1) {
				char op = input.charAt(0);
				if (op == '+' || op == '-' || op == '*' || op == '/')
					return op;
			}

			System.out.println("❌ Invalid operator. Use +, -, *, / (or type 'exit').");
		}
	}

	private static Double calculate(double a, double b, char op) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0) {
				System.out.println("Error: Division by zero!");
				return null;
			}
			return a / b;
		default:
			return null;
		}
	}

	private static boolean askToContinue(Scanner sc) {
		while (true) {
			System.out.print("Do you want to calculate again? (yes/no): ");
			String choice = sc.nextLine().trim();

			if (choice.equalsIgnoreCase("yes"))
				return true;
			if (choice.equalsIgnoreCase("no"))
				return false;

			System.out.println("Please type 'yes' or 'no'.");
		}
	}
}
