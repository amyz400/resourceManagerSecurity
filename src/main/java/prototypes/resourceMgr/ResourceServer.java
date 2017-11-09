package prototypes.resourceMgr;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;


/**
 * Created by aziring on 11/7/16.
 */
@Configuration
@RestController
@EnableResourceServer
@EnableWebSecurity
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/test", "/hello").permitAll()
                .anyRequest().authenticated();
    }

    @RequestMapping(value="/test", method=RequestMethod.GET)
    public String notSecuredCall() { return "not secured"; }

    @RequestMapping(value="/service", method=RequestMethod.POST)
    public String securedCall() {
        System.out.println("!!!! I'm IN SERVICE!");
        return "success (id: " + UUID.randomUUID().toString().toUpperCase() + ")";
    }

    @RequestMapping(value="/execute", method=RequestMethod.POST)
    public void securedCallFromGoogleOAuth() {
        System.out.println("!!!! I'm IN EXCUTE - doing something!");
    }

    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        String message = "Welcome to my hello world!";
        return new ModelAndView("hello", "message", message);
    }

}