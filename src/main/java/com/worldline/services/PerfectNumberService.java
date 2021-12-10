package com.worldline.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class PerfectNumberService {

    public int[] getAllPerfectNumbers(int start, int end) {
        return IntStream.rangeClosed(start, end).filter(this::isPerfectNumberUsingEuclidTheorem).toArray();
    }

    public List<Integer> getAllPrimeNumbers(int number) {
        return getPrimeNumbersUsingSieveOfEratosthenes(number);
    }

    /** Time complexity : O(log(n)) and space complexity O(logn) */
    public boolean isPerfectNumberUsingEuclidTheorem(int num) {
        //find primes less than num
        final var primes = getPrimeNumbersUsingSieveOfEratosthenes(num);
        return  primes.stream().anyMatch(prime -> perfectNumber(prime) == num);
    }

   /** Time complexity : O(sqrt(n)) and Space complexity : O(1) constant */
    public boolean checkIfPerfectPrime(int number) {
        if (number <= 0) return false;
        int sum = 0;
        for (int i = 1; i * i <= number; i++) {
            if (number % i == 0) {
                sum += i;
                if (i * i != number) {
                    sum += number / i;
                }
            }
        }
        return sum - number == number;
    }

    private int perfectNumber(int p) {
        return (1 << (p - 1)) * ((1 << p) - 1);
    }

    private List<Integer> getPrimeNumbersUsingSieveOfEratosthenes(int n){
        boolean [] prime= new boolean [n+1];
        List<Integer> primeNumbers=new ArrayList<>();
        //Initialized all as prime
        IntStream.rangeClosed(0, n).forEach(i-> prime[i] = true);

        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p])
            {
                 for(int i = p*p; i <= n; i += p)
                   prime[i] = false;
            }
        }
        //If it's prime add
        IntStream.rangeClosed(2, n).filter(i-> prime[i]).forEach(primeNumbers::add);
        return primeNumbers;
    }

}

