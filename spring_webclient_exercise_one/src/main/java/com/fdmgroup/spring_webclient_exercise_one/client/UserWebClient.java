package com.fdmgroup.spring_webclient_exercise_one.client;

import java.util.List;

import com.fdmgroup.spring_webclient_exercise_one.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.ClientResponse;

@Controller
public class UserWebClient {

    private WebClient webClient;

    @Autowired
    public UserWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<User> getUsers() {
        return webClient.get().uri(builder -> builder.path("/api/v1/users").build()).retrieve().bodyToFlux(User.class)
                .collectList().block();
    }

    public User getUser(String username) {
        return webClient.get().uri(builder -> builder.path("/api/v1/users/{username}").build(username)).retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, ClientResponse::createException).bodyToMono(User.class).block();
    }

    public User createUser(User user) {
        return webClient.post().uri(builder -> builder.path("/api/v1/users").build()).bodyValue(user).retrieve()
                .bodyToMono(User.class).block();
    }

    public User updateUser(User user, String username) {
        return webClient.put().uri(builder -> builder.path("/api/v1/users/{username}").build(username)).bodyValue(user)
                .retrieve().bodyToMono(User.class).block();
    }

    public void deleteUser(String username) {
        webClient.delete().uri(builder -> builder.path("/api/v1/users/{username}").build(username)).retrieve()
                .toBodilessEntity().block();
    }
}