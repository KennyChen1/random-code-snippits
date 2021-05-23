/*
https://projecteuler.net/problem=3

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?
ANS: 6857

 
https://en.wikipedia.org/wiki/Sieve_of_Atkin

https://stackoverflow.com/questions/10580159/sieve-of-atkin-explanation-and-java-example
*/
import java.util.Arrays;

public class Sieve {

    /*Program to run sieve of atkins forumula to generate primes*/
    public static void main(String []args){
        // 600851475143L: L is placed at the end of the number to denote a long data type
        int limit = (int) Math.ceil (Math.sqrt(600851475143L ));
        int sqrt = (int) Math.ceil (Math.sqrt(limit));
        boolean[] sieve = new boolean[limit + 1];

        // set all values of the sieve to false
        // false denotes composite (not prime)
        Arrays.fill (sieve, false);

        // sieve works for integers > 3
        // 2 and 3 are trivially set to true(prime)
        sieve[2] = true;
        sieve[3] = true;


        // loop through all possible integer values for x and y
        // up to the square root of the max prime for the sieve
        // we don't need any larger values for x or y since the
        // max value for x or y will be the square root of n
        // in the quadratics
        // the theorem showed that the quadratics will produce all
        // primes that also satisfy their wheel factorizations, so
        // we can produce the value of n from the quadratic first
        // and then filter n through the wheel quadratic
        // there may be more efficient ways to do this, but this
        // is the design in the Wikipedia article
        // loop through all integers for x and y for calculating
        // the quadratics
        for (int x = 1; x <= sqrt; x++){
            for (int y = 1; y <= sqrt; y++){
                int n = 4 * x * x + y * y;
                if (n <= limit && (n % 12 == 1 || n % 12 == 5) && y%2 == 1)	{
                    sieve[n] = !sieve[n];
                }

                n = 3 * x * x + y * y;
                if (n <= limit && (n % 12 == 7) && x%2==1 && y%2==0){
                    sieve[n] = !sieve[n];
                }

                n = 3 * x * x - y * y;
                if (n <= limit && (n % 12 == 11)
                        && ((x%2==1 && y%2==0) || (x%2==0 && y%2==1)))		{
                    sieve[n] = !sieve[n];
                }
            }// end for
        }// end for

        // remove all perfect squares since the quadratic
        // wheel factorization filter removes only some of them
        for (int n = 5; n <= sqrt; n++){
            if (sieve[n]){
                int x = n * n;
                for (int i = x; i < limit ; i += x){
                    sieve[i] = false;
                }
            }
        }


        if( 1 == 1){
            // prints out all the numbers
            for (int i = 0; i <= limit; i++) {
                if (sieve[i])   {
                    // finds the largest perfect prime
                    if(600851475143L%i == 0){
                        System.out.println (i);
                    }
                }
            }
        }


    }
}