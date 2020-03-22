package by.academy.HomeWork.Task1;

public class Main {
    public static void main(String[] args) {
        CalculatorWithOperator calculator = new CalculatorWithOperator("4.1 + 15 * 7 + (28 / 5) ^ 2");
        double  x = calculator.execute();

    }
}
