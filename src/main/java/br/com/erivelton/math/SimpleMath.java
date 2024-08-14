package br.com.erivelton.math;

public class SimpleMath {

    public Double sum (Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtracao (Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiplicacao (Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public Double divisao (Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public Double media (Double numberOne, Double numberTwo) {
       return (numberOne + numberTwo)/2;
    }

    public Double root (Double number) {
       return Math.sqrt(number);
    }
}
