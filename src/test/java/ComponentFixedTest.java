/*
 * Copyright 2019 igur.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import model.MyBean;
import model.MyBeanConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ComponentFixedTest.MyBeanComponent.class})
public class ComponentFixedTest {

    @Autowired
    private MyBean myBean;

    @Autowired
    private MyBeanConsumer myBeanConsumer;

    @Autowired
    private MyBeanConsumer myBeanConsumer2;

    @Test
    public void test() {
        assertNotNull(myBean);
        assertNotNull(myBeanConsumer);
        assertNotNull(myBeanConsumer2);
        assert(myBeanConsumer == myBeanConsumer2);
        assert(myBeanConsumer.getMyBean() == myBean);
    }

    @Component
    static class MyBeanComponent {

        @Autowired
        private MyBean myBean;

        @Bean
        public MyBean providesMyBean() {
            return new MyBean("testBean");
        }

        @Bean
        public MyBeanConsumer providesMyBeanConsumer() {
            return new MyBeanConsumer(myBean);
        }
    }
}
