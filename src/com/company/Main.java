package com.company;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {

    public static void main(String[] args) {

        System.out.println(gettimes(0.13));

        long start = System.nanoTime();
        BigDecimal m = new BigDecimal(5).pow(100).subtract(BigDecimal.ONE);
        BigDecimal n = new BigDecimal(5).pow(120).subtract(BigDecimal.ONE);
		System.out.println(m.divide(gettimesBD(n.divide(m,5000,RoundingMode.HALF_UP)).round(new MathContext(70)),20,RoundingMode.HALF_UP)+ " Zeit: "+ (System.nanoTime() - start));

        start = System.nanoTime();
		System.out.println(gcdBD("1221","1234567891011121314151617181920212223242526272829")+ " Zeit: "+ (System.nanoTime() - start));

        start = System.nanoTime();
        System.out.println(new BigInteger("5").pow(100).subtract(BigInteger.ONE).gcd(new BigInteger("5").pow(120).subtract(BigInteger.ONE))+ " Zeit: "+ (System.nanoTime() - start));

        start = System.nanoTime();
        System.out.println(gcdLarge(1221,"1234567891011121314151617181920212223242526272829")+ " Zeit: "+ (System.nanoTime() - start));

        System.out.println(gcd(100,120));
        System.out.println(Math.pow(5,20)-1);
    }


    public static double gettimes(double d){
        d = d%1;
        double times = 1;

        while (d%1 > 0.0000001){
            d = d%1;
            d = 1/d;
            times *= d;
        }

        return times;
    }


    public static double gettimes(int m, int n){
        double d = (double)m/n;
        d = d%1;
        double times = 1;

        while (d%1 > 0.00001){
            d = d%1;
            d = 1/d;
            times *= d;
        }
        return (n/times);
    }



    public static BigDecimal gettimesBD(BigDecimal d){
        BigDecimal bd = d;
        bd = bd.remainder(BigDecimal.ONE);
        BigDecimal timesBD = BigDecimal.ONE;

        BigDecimal acc = new BigDecimal(10).movePointLeft(500);
        while (bd.remainder(BigDecimal.ONE).compareTo(acc) > 0){

            bd = bd.remainder(BigDecimal.ONE);
            bd = BigDecimal.ONE.divide(bd,1000,RoundingMode.HALF_UP);
            timesBD = timesBD.multiply(bd);
        }

        return timesBD;
    }

    public static BigDecimal gcdBD(String m, String n){

        BigDecimal bd = new BigDecimal(n).divide(new BigDecimal(m), 200, RoundingMode.HALF_UP);
        bd = bd.remainder(BigDecimal.ONE);
        BigDecimal timesBD = BigDecimal.ONE;

        BigDecimal acc =new BigDecimal(10).movePointLeft(5);
        while (bd.remainder(BigDecimal.ONE).compareTo(acc) > 0){

            bd = bd.remainder(BigDecimal.ONE);
            bd = BigDecimal.ONE.divide(bd,100,RoundingMode.HALF_UP);
            timesBD = timesBD.multiply(bd);
        }

        return new BigDecimal(m).divide(timesBD, 2, RoundingMode.HALF_UP);
    }





    // This function computes
    // the gcd of 2 numbers
    private static int gcd(int reduceNum, int b)
    {
        return b == 0 ?
                reduceNum : gcd(b, reduceNum % b);
    }

    // Here 'a' is integer and 'b'
    // is string. The idea is to make
    // the second number (represented
    // as b) less than and equal to
    // first number by calculating its
    // modulus with first integer
    // number using basic mathematics
    private static int reduceB(int a, String b)
    {
        int result = 0;
        for (int i = 0; i < b.length(); i++)
        {
            result = (result * 10 +
                    b.charAt(i) - '0') % a;
        }
        return result;
    }

    private static int gcdLarge(int a, String b)
    {
        // Reduce 'b' i.e the second
        // number after modulo with a
        int num = reduceB(a, b);

        // Now,use the euclid's algorithm
        // to find the gcd of the 2 numbers
        return gcd(num, a);
    }
}