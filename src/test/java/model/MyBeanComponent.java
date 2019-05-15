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
package model;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyBeanComponent {
    /**
     *
     * @return the MyBean singleton instance
     */
    @Bean
    public MyBean providesMyBean() {
        return new MyBean("testBean");
    }

    /**
     * new MyBeanConsumer(myBean()) just calls a pure java method
     * @return the MyBeanConsumer singleton instance
     */
    @Bean
    public MyBeanConsumer providesMyBeanConsumer() {
        return new MyBeanConsumer(providesMyBean());
    }
}
