import java.io.*;
import java.util.*;

public class Calculator {
    public static void main() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Assignment2/src/test_cases.txt"));
            PrintWriter writer = new PrintWriter(new FileWriter("Assignment2/src/test_results.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String result = evaluateExpression(line.trim());
                    writer.println(String.format("The function entered %s results in the following: %s", line, result));
                } catch (IllegalStateException e) {
                    writer.println(String.format("The function entered %s results in the following: Error - %s", line, e.getMessage()));
                }
            }
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Test cases file not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String evaluateExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalStateException("Empty or null expression");
        }

        while (expression.contains("(")) {
            int openIndex = expression.lastIndexOf('(');
            int closeIndex = expression.indexOf(')', openIndex);

            if (closeIndex == -1) {
                throw new IllegalStateException("Mismatched parentheses");
            }

            String subExpression = expression.substring(openIndex + 1, closeIndex);
            String subResult = evaluateSimpleExpression(subExpression);
            expression = expression.substring(0, openIndex) + subResult + expression.substring(closeIndex + 1);
        }
        return evaluateSimpleExpression(expression);
    }

    public static String evaluateSimpleExpression(String expression) {
        expression = expression.trim();
        if (expression.isEmpty()) {
            throw new IllegalStateException("Empty expression");
        }

        StackArray<Integer> operandStack = new StackArray<>(10);
        StackArray<Character> operatorStack = new StackArray<>(10);
        boolean booleanCheck = false;
        boolean hasValidTokens = false;

        StringBuilder number = new StringBuilder();
        boolean lastWasOperator = true;  // Used to detect negative values

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);

            if (Character.isWhitespace(token)) {
                if (number.length() > 0) {
                    operandStack.push(Integer.parseInt(number.toString()));
                    number.setLength(0);
                    hasValidTokens = true;
                }
                continue;
            }

            if (Character.isDigit(token)) {
                number.append(token);
                lastWasOperator = false;
                hasValidTokens = true;
            } else if (token == '-' && lastWasOperator) {
                number.append(token);
            } else if (isOperator(token)) {
                if (number.length() > 0) {
                    operandStack.push(Integer.parseInt(number.toString()));
                    number.setLength(0);
                }

                while (!operatorStack.isEmpty() &&
                        precedence(token) >= precedence(operatorStack.peek())) {
                    processOperator(operandStack, operatorStack);
                }
                operatorStack.push(token);
                lastWasOperator = true;
                hasValidTokens = true;
            } else if (token == '=' && i + 1 < expression.length() && expression.charAt(i + 1) == '=') {
                if (number.length() > 0) {
                    operandStack.push(Integer.parseInt(number.toString()));
                    number.setLength(0);
                }
                operatorStack.push('=');
                booleanCheck = true;
                i++;
                lastWasOperator = true;
                hasValidTokens = true;
            } else if (token == '!' && i + 1 < expression.length() && expression.charAt(i + 1) == '=') {
                if (number.length() > 0) {
                    operandStack.push(Integer.parseInt(number.toString()));
                    number.setLength(0);
                }
                operatorStack.push('!');
                booleanCheck = true;
                i++;
                lastWasOperator = true;
                hasValidTokens = true;
            } else if (token == '>' || token == '<') {
                if (number.length() > 0) {
                    operandStack.push(Integer.parseInt(number.toString()));
                    number.setLength(0);
                }
                if (i + 1 < expression.length() && expression.charAt(i + 1) == '=') {
                    operatorStack.push(token == '>' ? 'g' : 'l');
                    i++;
                } else {
                    operatorStack.push(token);
                }
                booleanCheck = true;
                lastWasOperator = true;
                hasValidTokens = true;
            }
        }

        if (!hasValidTokens) {
            throw new IllegalStateException("No valid mathematical expression found");
        }

        // Push the last number if exists
        if (number.length() > 0) {
            operandStack.push(Integer.parseInt(number.toString()));
        }

        // Process remaining operators
        while (!operatorStack.isEmpty()) {
            processOperator(operandStack, operatorStack);
        }

        if (operandStack.isEmpty()) {
            throw new IllegalStateException("No result produced");
        }

        int result = operandStack.pop();

        if (!operandStack.isEmpty()) {
            throw new IllegalStateException("Invalid expression");
        }

        if (booleanCheck) {
            return (result == 1) ? "true" : "false";
        }

        if (result == 3) {
            return "0 division error";
        }

        return String.valueOf(result);
    }

    private static void processOperator(StackArray<Integer> operandStack, StackArray<Character> operatorStack) {
        if (operandStack.size() < 2) {
            throw new IllegalStateException("Not enough operands for operation");
        }
        char operator = operatorStack.pop();
        int num2 = operandStack.pop();
        int num1 = operandStack.pop();
        int result = applyOperator(operator, num1, num2);
        operandStack.push(result);
    }

    private static int applyOperator(char operator, int num1, int num2) {
        switch (operator) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/':
                if (num2 == 0) return 3;
                return num1 / num2;
            case '^': return (int) Math.pow(num1, num2);
            case '>': return (num1 > num2) ? 1 : 0;
            case '<': return (num1 < num2) ? 1 : 0;
            case '=': return (num1 == num2) ? 1 : 0;
            case '!': return (num1 != num2) ? 1 : 0;
            case 'g': return (num1 >= num2) ? 1 : 0;
            case 'l': return (num1 <= num2) ? 1 : 0;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private static boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/' || token == '^';
    }

    private static int precedence(char operator) {
        return switch (operator) {
            case '^' -> 1;
            case '*', '/' -> 2;
            case '+', '-' -> 3;
            case '>', '<', '=', '!', 'g', 'l' -> 4;
            default -> 5;
        };
    }
}