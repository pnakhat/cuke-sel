package org.qagile.app;

/**
 * Created with IntelliJ IDEA.
 * User: pankajnakhat
 * Date: 18/07/2013
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class Fizzbuzz {

    public static String fbuzz(int i) {
        if(divisibleby(i, 3)) {
            if (divisibleby(i ,5)) {
                return "fizz buzz";
            } else {
                return "fizz";
            }

        } else if (divisibleby(i, 5)) {
            return "buzz";

        }  else {
            return null;
        }
    }

    public static boolean divisibleby(int numerator, int denominator) {
        if(numerator % denominator == 0) {
            return true;
        } else {
            return false;
        }
    }



    public static void main(String args[]) {
        System.out.println(fbuzz(15));
        System.out.println(fbuzz(9));
        System.out.println(fbuzz(10));
        System.out.println(fbuzz(1));

    }

}
