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


    private void findAndExecuteExpressionInSkobki() {
        int a = this.expression.indexOf("(");
        int b = this.expression.lastIndexOf(")");
        String expresInSkWhithSk = this.expression.substring(a, b + 1);
        String expresInSkWhithOutSk = expresInSkWhithSk.replaceAll("\\(|\\)", "");

        String mathSymbol = findMathSymb(expresInSkWhithOutSk);
        int indMath = expresInSkWhithOutSk.indexOf(mathSymbol);
        String numb1 = expresInSkWhithOutSk.substring(0, indMath).trim();
        double x1 = Double.parseDouble(numb1);
        String numb2 = expresInSkWhithOutSk.substring(indMath + 1).trim();
        double x2 = Double.parseDouble(numb2);
        double resultInSk = executeExpr(x1, mathSymbol, x2);
        String result = String.valueOf(resultInSk);
        this.expression = this.expression.replace(expresInSkWhithSk, result);
    }

    private String findMathSymb(String expression) {
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

    private void findAndExecuteStepen() {
        boolean isContains = this.expression.contains("^");
        if (!isContains) {
            return;
        }
        String[] myMass = this.expression.split(" ");

        int indMath = Arrays.asList(myMass).indexOf("^");
        String expresWhithSt = myMass[indMath - 1] + " " + myMass[indMath] + " " + myMass[indMath + 1];

        String numb1 = myMass[indMath - 1];
        double x1 = Double.parseDouble(numb1);
        String numb2 = myMass[indMath + 1];
        double x2 = Double.parseDouble(numb2);
        double resultInSt = executeExpr(x1, "^", x2);
        String result = String.valueOf(resultInSt);
        this.expression = this.expression.replace(expresWhithSt, result);

    }


    private void findAndExecuteMultiplAndDivide() {

        boolean isContains = this.expression.contains("*") || this.expression.contains("/");
        if (!isContains) {
            return;
        }

        do {

            String mathSimb;

            if (this.expression.contains("*")) {
                mathSimb = "*";
            } else if (this.expression.contains("/")) {
                mathSimb = "/";
            } else {
                return;
            }

            String[] myMass = this.expression.split(" ");

            int indMath = Arrays.asList(myMass).indexOf(mathSimb);
            String expressMultOrDivide = myMass[indMath - 1] + " " + myMass[indMath] + " " + myMass[indMath + 1];

            String numb1 = myMass[indMath - 1];
            double x1 = Double.parseDouble(numb1);
            String numb2 = myMass[indMath + 1];
            double x2 = Double.parseDouble(numb2);
            double resultInMultAndDiv = executeExpr(x1, mathSimb, x2);
            String result = String.valueOf(resultInMultAndDiv);
            this.expression = this.expression.replace(expressMultOrDivide, result);

            isContains=this.expression.contains("*") || this.expression.contains("/");

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
            String mathSymbol;
            if (this.expression.contains("+")) {
                mathSymbol = "+";
            } else if (this.expression.contains("-")) {
                mathSymbol = "-";
            } else {
                return;
            }

            int indMath = Arrays.asList(myMass).indexOf(mathSymbol);
            String expresWhithSum = myMass[indMath - 1] + " " + myMass[indMath] + " " + myMass[indMath + 1];

            String numb1 = myMass[indMath - 1];
            double x1 = Double.parseDouble(numb1);
            String numb2 = myMass[indMath + 1];
            double x2 = Double.parseDouble(numb2);
            double resultInSum = executeExpr(x1, mathSymbol, x2);
            String result = String.valueOf(resultInSum);
            this.expression = this.expression.replace(expresWhithSum, result);
            isContains = this.expression.contains("+") || this.expression.contains("- ");
        } while (isContains);
    }

    public double execute (){
//        System.out.println(this.expression);
        findAndExecuteExpressionInSkobki();
//        System.out.println(this.expression);
        findAndExecuteStepen();
//        System.out.println(this.expression);
        findAndExecuteMultiplAndDivide();
//        System.out.println(this.expression);
        findAndExecutePlusAndMinus();
//        System.out.println(this.expression);
        round();
        System.out.println("\nРезультат выражения "+this.fullExpression+" = "+this.expression);
        return Double.parseDouble(this.expression);
    }

    private void round(){
        double x = Double.parseDouble(this.expression);
        //todo окоуглить релультат до конца
        this.expression = String.valueOf(Math.round(x));
    }

//    public static void execute (String exp){
//        CalculatorWithOperator calculator= new CalculatorWithOperator(exp);
//        calculator.execute();
//    }

}
