package be.pxl.bytebros.backend.service;

public class HabConService {
    public String isNumberADisariumNumberYesOrNo(){ //correct
        int num = 598;
        int copy = num, d = 0, sum = 0;
        String s = Integer.toString(num);
        int len = s.length();

        while(copy>0)
        {
            d = copy % 10;
            sum = sum + (int)Math.pow(d,len);
            len--;
            copy = copy / 10;
        }

        if(sum == num)
            return "Y";
        else
            return "N";
    }

    public static boolean isPerfect(int number) {
        int sum = 0;
        // Calculate sum of divisors
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        // Check if sum of divisors is equal to the number
        return sum == number;
    }

    // Method to find the nth perfect number
    public static int nthPerfectNumber() {
        int nth = 1;
        int count = 0;
        int num = 1;
        // Loop to find nth perfect number
        while (true) {
            if (isPerfect(num)) {
                count++;
                if (count == nth) {
                    return num;
                }
            }
            num++;
        }
    }
}
