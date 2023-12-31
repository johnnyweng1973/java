package io.reflectoring.springwebflux.service;

import io.reflectoring.springwebflux.model.User;
import io.reflectoring.springwebflux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final UserRepository userRepository;

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Mono<User> findById(String userId){
        return userRepository.findById(userId);
    }

    public Mono<User> updateUser(String userId,  User user){
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                });
    }

    public Mono<User> deleteUser(String userId){
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                        .then(Mono.just(existingUser)));
    }

    public Flux<User> fetchUsers(String name) {
        Query query = new Query()
                .with(Sort
                        .by(Collections.singletonList(Sort.Order.asc("age")))
                );
        query.addCriteria(Criteria
                .where("name")
                .regex(name)
        );

        return reactiveMongoTemplate
                .find(query, User.class);
    }
    
    public Flux<User> fetchUsersBySearchParams(Map<String, String> searchParams) {
        Query query = new Query().with(Sort.by(Collections.singletonList(Sort.Order.asc("age"))));
        log.info("service: searchall {}", searchParams);
        if (searchParams != null && !searchParams.isEmpty()) {
            for (Map.Entry<String, String> entry : searchParams.entrySet()) {
            	String key = entry.getKey();
                String value = entry.getValue();
                
                if (value != null && !value.isEmpty()) {
                    if (key.equals("age")) {
                        int age = Integer.parseInt(value);
                        query.addCriteria(Criteria.where(key).is(age));
                    } else if (key.equals("salary")) {
                        double salary = Double.parseDouble(value);
                        query.addCriteria(Criteria.where(key).is(salary));
                    } else {
                        query.addCriteria(Criteria.where(key).regex(value));
                    }
                }
            }
        }

        return reactiveMongoTemplate.find(query, User.class);
    }

}
