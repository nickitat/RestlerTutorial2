package com.github.nickitat.restler.tutorial.client;

import org.restler.Restler;
import org.restler.Service;
import org.restler.spring.mvc.SpringMvcSupport;

import com.github.nickitat.restler.tutorial.server.controller.ServerController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

public class SendRequest {

    ServerController serverController;

    public static void main(String[] args) {
        new SendRequest().run();
    }

    private void run() {
        SpringMvcSupport springSupport = new SpringMvcSupport();
        Restler builder = new Restler("http://localhost:8080/webapi", springSupport);
        Service service = builder.build();
        serverController = service.produceClient(ServerController.class);
        try {
            String response = serverController.handleQuery("1");
            //System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}