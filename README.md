Demo project that explores the difference between spring @Configuration and @Component annotations.
Simply put, two application contexts that look exactly the same, just one is annotated with @Component and the with @Configuration
behave in totally different way.
  
```java
/**
 * All methods marked as @Bean will be wrapped into a CGLIB wrapper where
 * the first time the method is called, all resulting objects will be registered in the spring context
 * All further calls just return the bean retrieved from the context
 */
@Configuration
public class MyBeanConfiguration {

    /**
     * @return the MyBean Singleton instance
     */
    @Bean
    public MyBean providesMyBean() {
        return new MyBean("testBean");
    }

    /**
     * @return the MyBeanConsumer singleton instance with the MyBean singleton instance as a member
     */
    @Bean
    public MyBeanConsumer providesMyBeanConsumer() {
        return new MyBeanConsumer(providesMyBean());
    }
}

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
     * @return the MyBeanConsumer singleton instance with a new allocated MyBean instance different from the bean above
     */
    @Bean
    public MyBeanConsumer providesMyBeanConsumer() {
        return new MyBeanConsumer(providesMyBean());
    }
}

```  

Name     | Status |
-------- | ------ |
Build    | [![CircleCI](https://circleci.com/gh/ipgur/spring-config-playground.svg?style=svg)](https://circleci.com/gh/ipgur/spring-config-playground) |