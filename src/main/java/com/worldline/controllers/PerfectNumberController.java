package com.worldline.controllers;

import com.worldline.services.PerfectNumberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PerfectNumberController {

    PerfectNumberService perfectNumberService;
    /**
     * To get all perfect numbers between range ( start, end)
     * Example- to get perfect numbers in range[1,1000]->http://localhost:8083/api/getAllPerfectNumbersWithInRange?start=1&end=1000
     * @param start-start integer
     * @param end -end integer
     * @return - perfect number integers as ResponseEntity
     */
    @GetMapping("/getAllPerfectNumbersWithInRange")
    public ResponseEntity<int[]> getAllPerfectNumbers(@RequestParam("start") int start, @RequestParam("end")int end){
        return  ResponseEntity.ok(perfectNumberService.getAllPerfectNumbers(start, end));
    }

    /**
     * To check if a number is perfect number or not
     * @param number-given integer
     * @return- true if perfect number else otherwise
     */
    @GetMapping("/isPerfectNumber")
    public boolean verifyPerfectNumber(@RequestParam("number") int number){
        return perfectNumberService.isPerfectNumberUsingEuclidTheorem(number);
    }

    /**
     * To get all prime numbers for a given numbers
     * @param number-integer
     * @return-List of Integer as ResponseEntity
     */
    @GetMapping("/getAllPrimeNumbers")
    public ResponseEntity<List<Integer>> getAllPrimeNumbers(@RequestParam("number") int number){
        return ResponseEntity.ok(perfectNumberService.getAllPrimeNumbers(number));
    }

}

