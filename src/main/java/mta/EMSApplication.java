package mta;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Collections;

@SpringBootApplication
public class   EMSApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EMSApplication.class, args);
        ///////////////////////////

    }
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver r = new InternalResourceViewResolver();
        // r.setPrefix("/WEB-INF/jsp/");
        r.setPrefix("/");
        r.setSuffix(".jsp");
        return r;
    }
}

/*
http://adnjavainterview.blogspot.com/2019/02/spring-boot-with-jsp-crud-example.html

http://localhost:8081/registration

*/
/*
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) DEFAULT NULL,
  `last_name` varchar(40) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `email_id` varchar(80) DEFAULT NULL,
  `emp_id` varchar(10) DEFAULT NULL,
  `blood_gp` varchar(6) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `personal_email` varchar(80) DEFAULT NULL,
  `mobile_no` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

      ApplicationContext context = SpringApplication.run(AppMain.class, args);
        AppController app = context.getBean(AppController.class);
        List<Product> lst = app.getALL( );
        for (Product p: lst) {
            System.out.println(p.toString());

*/
/*
https://adnjavainterview.blogspot.com/2018/12/spring-boot-starters.html
*/