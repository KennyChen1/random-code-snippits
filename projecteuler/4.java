/*
A palindromic number reads the same both ways.
The largest palindrome made from the product of two 2-digit numbers is
9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.

// ANS: 906609
*/


public class palindromeProduct {
    public static void main(String []args) {
        int maxPaliProd = 0;
        // brute force solution
        for(int i = 100; i < 1000; i++){
            for(int j = 100; j < 1000; j++){
                if(isPalidrome(i*j)){
                    if(i*j > maxPaliProd)
                        maxPaliProd = i*j;
                }
            }// end for j
        }   // end for i
        System.out.println(maxPaliProd);
    }

    private static boolean isPalidrome(int n){
        int orig = n;
        int rev = 0;
        while(n > 0){
            rev = rev*10 + n%10;
            n=n/10;
        }
        //System.out.printf("%d %d \n", orig, rev);
        return rev==orig;
    }

}


