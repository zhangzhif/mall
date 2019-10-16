package org.mall.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul网关注册
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
