package be.pxl.bytebros.backend.service;

import be.pxl.bytebros.backend.controller.data.DisariumNumberRequest;
import be.pxl.bytebros.backend.controller.data.PerfectNumberRequest;
import org.springframework.stereotype.Service;

@Service
public class HabConService {
    public String isNumberADisariumNumberYesOrNo(DisariumNumberRequest disariumNumberRequest){
        int num = disariumNumberRequest.number();
        int copy = num, digit, sum = 0;
        String numInString = Integer.toString(num);
        int len = numInString.length();

        while(copy>0)
        {
            digit = copy % 10;
            sum = sum + (int)Math.pow(digit,len);
            len--;
            copy = copy / 10;
        }

        if(sum == num)
            return "Y";
        else
            return "N";
    }

    public boolean isPerfect(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }

    public int nthPerfectNumber(PerfectNumberRequest perfectNumberRequest) {
        int num = perfectNumberRequest.num();
        int nth = perfectNumberRequest.nth();
        int count = 0;
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
