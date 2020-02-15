package com.switchvov.manual.config.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author switch
 * @since 2020/2/6
 */
@Profile("Java8")
@Service
public class Java8CalculateService implements CalculateService {

    @Override
    public Integer sum(Integer... nums) {
        System.out.println("Java8CalculateService");
        return Arrays.stream(nums).reduce(0, Integer::sum);
    }
}
