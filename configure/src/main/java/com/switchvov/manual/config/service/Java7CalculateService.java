package com.switchvov.manual.config.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author switch
 * @since 2020/2/6
 */
@Profile("Java7")
@Service
public class Java7CalculateService implements CalculateService {

    @Override
    public Integer sum(Integer... nums) {
        System.out.println("Java7CalculateService");
        Integer sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        return sum;
    }
}
