package com.worldline;

import com.worldline.services.PerfectNumberService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PerfectNumberApplicationTests {

    @Autowired
    PerfectNumberService perfectNumberService;

    @DisplayName("Test all perfect numbers from 1 to 10000")
    @Test
    public void test_the_first_four_valid_perfect_numbers(){
        // given
        int [] expected={6, 28, 496, 8128};
        //when
        final int[] actual=  perfectNumberService.getAllPerfectNumbers(1, 10000);

        List<Boolean> equalityResult = IntStream.range(0, actual.length)
                                            .mapToObj(i -> actual[i] == expected[i])
                                            .collect(Collectors.toList());

        int[] ints = IntStream.range(0, actual.length)
                            .map(i -> Integer.compare(actual[i], expected[i]))
                            .toArray();

        //then
        assertEquals(0, ints[0]);
        assertTrue(equalityResult.get(0));

        assertEquals(actual.length, 4);
        for(int i=0; i < actual.length; i++){
            assertEquals(expected[i], actual[i]);
        }
    }
    @DisplayName("Test all prime numbers from 1 to 100")
    @Test
    public void should_return_prime_numbers_when_number_is_provided(){
        // given
        int [] expected=new int [] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };
        //when

        final List<Integer> actual = perfectNumberService.getAllPrimeNumbers(100);

        //then
        assertEquals(actual.size(), expected.length);
        for(int i=0; i < actual.size();i++){
            assertEquals(expected[i], actual.get(i));
        }
    }
    @DisplayName("Test a number is perfect number or not ")
    @Test
    public void test_a_number_is_perfect_number_or_not(){

        assertTrue( perfectNumberService.isPerfectNumberUsingEuclidTheorem(6));
        assertTrue( perfectNumberService.isPerfectNumberUsingEuclidTheorem(28));
        assertTrue( perfectNumberService.isPerfectNumberUsingEuclidTheorem(496));
        assertTrue( perfectNumberService.checkIfPerfectPrime(8128));

        assertFalse( perfectNumberService.isPerfectNumberUsingEuclidTheorem(3));
        assertFalse( perfectNumberService.isPerfectNumberUsingEuclidTheorem(40));
        assertFalse( perfectNumberService.isPerfectNumberUsingEuclidTheorem(99));
        assertFalse( perfectNumberService.checkIfPerfectPrime(1000));
    }

}

