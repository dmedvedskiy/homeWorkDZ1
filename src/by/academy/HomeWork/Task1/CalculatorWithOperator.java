package by.academy.HomeWork.Task1;

import java.util.Arrays;

public class CalculatorWithOperator {
    private String fullExpression;
    public String expression;


    public CalculatorWithOperator(String fullExpression) {
        this.fullExpression = fullExpression;
        this.expression = fullExpression;
    }

    private double plus(double a, double b) {
        return a + b;
    }

    private double minus(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        return a / b;
    }

    private double toPower(double a, double b) {
        return Math.pow(a, b);
    }

//    public double takeRoot(double a, double b) {
//        return Math.pow(a, (1 / b));
//    }
//
//    public double takeModulus(double a, double b) {
//        return Math.abs(a + b);
//    }


    private void findAndExecuteExpressionInBracket() {
        int a = this.expression.indexOf("(");
        int b = this.expression.lastIndexOf(")");
        String expresInSkWhithBracket = this.expression.substring(a, b + 1);
        String expresInSkWhithOutBracket = expresInSkWhithBracket.replaceAll("\\(|\\)", "");

        String mathSighn = findMathSighn(expresInSkWhithOutBracket);
        int indMath = expresInSkWhithOutBracket.indexOf(mathSighn);
        String numb1 = expresInSkWhithOutBracket.substring(0, indMath).trim();
        double x1 = Double.parseDouble(numb1);
        String numb2 = expresInSkWhithOutBracket.substring(indMath + 1).trim();
        double x2 = Double.parseDouble(numb2);
        double resultInSk = executeExpr(x1, mathSighn, x2);
        String result = String.valueOf(resultInSk);
        this.expression = this.expression.replace(expresInSkWhithBracket, result);
    }

    private String findMathSighn 
            (String expression) {
        if (expression.contains("/")) {
            return "/";
        } else if (expression.contains("*")) {
            return "*";
        }
        if (expression.contains("+")) {
            return "+";
        } else if (expression.contains("-")) {
            return "-";
        }
        return null;
    }

    private double executeExpr(double x1, String mathSymbol, double x2) {
        double result;
        switch (mathSymbol) {
            case "/":
                result = divide(x1, x2);
                break;
            case "*":
                result = multiply(x1, x2);
                break;
            case "+":
                result = plus(x1, x2);
                break;
            case "-":
                result = minus(x1, x2);
                break;
            case "^":
                result = toPower(x1, x2);
                break;
            default:
                System.out.println("Нет такого математического действия");
                result = 0;
                break;
        }
        return result;
    }

    private void findAndExecuteDegreeOf() {
        boolean isContains = this.expression.contains("^");
        if (!isContains) {
            return;
        }
        String[] myMass = this.expression.split(" ");

        int indMath = Arrays.asList(myMass).indexOf("^");
        String expresWhithDegreeOf = myMass[indMath - 1] + " " + myMass[indMath] + " " + myMass[indMath + 1];

        String numb1 = myMass[indMath - 1];
        double x1 = Double.parseDouble(numb1);
        String numb2 = myMass[indMath + 1];
        double x2 = Double.parseDouble(numb2);
        double resultInDegreeOf = executeExpr(x1, "^", x2);
        String result = String.valueOf(resultInDegreeOf);
        this.expression = this.expression.replace(expresWhithDegreeOf, result);

    }


    private void findAndExecuteMultiplAndDivide() {

        boolean isContains = this.expression.contains("*") || this.expression.contains("/");
        if (!isContains) {
            return;
        }

        do {

            String mathSighn;

            if (this.expression.contains("*")) {
                mathSighn = "*";
            } else if (this.expression.contains("/")) {
                mathSighn = "/";
            } else {
                return;
            }

            String[] myMass = this.expression.split(" ");

            int indMath = Arrays.asList(myMass).indexOf(mathSighn);
            String expressMultOrDivide = myMass[indMath - 1] + " " + myMass[indMath] + " " + myMass[indMath + 1];

            String numb1 = myMass[indMath - 1];
            double x1 = Double.parseDouble(numb1);
            String numb2 = myMass[indMath + 1];
            double x2 = Double.parseDouble(numb2);
            double resultInMultAndDiv = executeExpr(x1, mathSighn, x2);
            String result = String.valueOf(resultInMultAndDiv);
            this.expression = this.expression.replace(expressMultOrDivide, result);

            isContains = this.expression.contains("*") || this.expression.contains("/");

        }
        while (isContains);


    }


    private void findAndExecutePlusAndMinus() {
        boolean isContains = this.expression.contains("+") || this.expression.contains("- ");
        if (!isContains) {
            return;
        }
        do {
            String[] myMass = this.expression.split(" ");
            String mathSighn;
            if (this.expression.contains("+")) {
                mathSighn = "+";
            } else if (this.expression.contains("-")) {
                mathSighn = "-";
            } else {
                return;
            }

            int indMath = Arrays.asList(myMass).indexOf(mathSighn);
            String expresWhithSum = myMass[indMath - 1] + " " + myMass[indMath] + " " + myMass[indMath + 1];

            String numb1 = myMass[indMath - 1];
            double x1 = Double.parseDouble(numb1);
            String numb2 = myMass[indMath + 1];
            double x2 = Double.parseDouble(numb2);
            double resultInSum = executeExpr(x1, mathSighn, x2);
            String result = String.valueOf(resultInSum);
            this.expression = this.expression.replace(expresWhithSum, result);
            isContains = this.expression.contains("+") || this.expression.contains("- ");
        } while (isContains);
    }

    public double execute() {
//        System.out.println(this.expression);
        findAndExecuteExpressionInBracket();
//        System.out.println(this.expression);
        findAndExecuteDegreeOf();
//        System.out.println(this.expression);
        findAndExecuteMultiplAndDivide();
//        System.out.println(this.expression);
        findAndExecutePlusAndMinus();
//        System.out.println(this.expression);
        round();
        System.out.println("\nРезультат выражения " + this.fullExpression + " = " + this.expression);
        return Double.parseDouble(this.expression);
    }

    private void round() {
        double x = Double.parseDouble(this.expression);
        double d = Math.round(x * 100); // Округление до сотых
        double result = d / 100;
        this.expression = String.valueOf(result);
    }

}
