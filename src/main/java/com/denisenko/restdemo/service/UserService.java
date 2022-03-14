package com.denisenko.restdemo.service;

import com.denisenko.restdemo.model.User;
import com.denisenko.restdemo.repository.UserRepository;
import com.denisenko.restdemo.repository.hibernateImpl.HibernateUserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository = new HibernateUserRepository();

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean delete(Integer id) {
        return userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User update(User user) {
        return userRepository.update(user);
    }

}
