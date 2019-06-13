package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class Math {

    /**
     * (#412)
     * Write a program that outputs the string representation of numbers from 1 to n.
     *
     * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
     *
     * @param n The number.
     * @return The FizzBuzz strings.
     */
    public List<String> fizzBuzz(int n) {
        List<String> retList = new ArrayList<String>();
        String temp = "";
        int counter = 1;
        int compare3 = 0;
        int compare5 = 0;

        while(counter <= n) {
            compare3 = counter % 3;
            compare5 = counter % 5;
            if(compare3 != 0 && compare5 != 0) temp += counter;
            if(compare3 == 0) temp += "Fizz";
            if(compare5 == 0) temp += "Buzz";
            retList.add(temp);
            temp = "";
            counter++;
        }

        return retList;
    }

    /**
     * (#13)
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *    I can be placed before V (5) and X (10) to make 4 and 9.
     *    X can be placed before L (50) and C (100) to make 40 and 90.
     *    C can be placed before D (500) and M (1000) to make 400 and 900.
     *
     * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
     *
     * @param s The roman numeral.
     * @return The converted integer.
     */
    public int romanToInt(String s) {

        int counter = 0;
        String next;
        int sum = 0;
        char[] roman = s.toCharArray();

        while(counter < roman.length) {

            next = (counter+1 < roman.length) ? Character.toString(roman[counter+1]) : "";
            switch(Character.toString(roman[counter])) {
                case "I":
                    if(next.equals("V")) {
                        sum += 4;
                        counter++;
                    }
                    else if(next.equals("X")) {
                        sum += 9;
                        counter++;
                    }
                    else sum += 1;
                    break;
                case "V":
                    sum += 5;
                    break;
                case "X":
                    if(next.equals("L")) {
                        sum += 40;
                        counter++;
                    }
                    else if(next.equals("C")) {
                        sum += 90;
                        counter++;
                    }
                    else sum += 10;
                    break;
                case "L":
                    sum += 50;
                    break;
                case "C":
                    if(next.equals("D")) {
                        sum += 400;
                        counter++;
                    }
                    else if(next.equals("M")) {
                        sum += 900;
                        counter++;
                    }
                    else sum += 100;
                    break;
                case "D":
                    sum += 500;
                    break;
                case "M":
                    sum += 1000;
                    break;
            }
            counter++;
        }

        return sum;
    }

}
