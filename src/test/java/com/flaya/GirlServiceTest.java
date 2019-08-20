package com.flaya;

import com.flaya.domain.GirlInfo;
import com.flaya.repository.GirlRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlRepository girlRepository;

    @Test
    public void findOneTest() {
        GirlInfo girlInfo = girlRepository.findById(3).orElse(null);
        Assert.assertEquals(new Integer(12), girlInfo.getAge());
    }
}
