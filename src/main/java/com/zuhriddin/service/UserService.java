package com.zuhriddin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zuhriddin.model.User;
import com.zuhriddin.service.util.UtilityJson;

import java.util.List;
import java.util.UUID;

public class UserService implements BaseService<User, UUID>{
    private final String PATH = "src/file/users.json";

    @Override
    public User add(User user) {
        List<User> users = read();
        if (!hasUser(users, user)) {
            users.add(user);
            write(users);
            return user;
        }
        throw new RuntimeException("This user already exists");
    }

    private boolean hasUser(List<User> users, User user) {
        return users.stream()
                .anyMatch(u -> u.getPhoneNumber().equals(user.getPhoneNumber()));
    }

    @Override
    public User get(UUID id) {
        List<User> users = read();
        return users.stream()
               .filter(u -> u.getId().equals(id))
               .findFirst()
               .orElseThrow(() -> new RuntimeException("No such user, found "));
    }

    @Override
    public void delete(UUID id) {
        List<User> users = read();
            users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .ifPresent(users::remove);
        write(users);
    }

    @Override
    public List<User> list() {
        return read();
    }

    private List<User> read() {
        return UtilityJson.read(PATH, new TypeReference<>() {});
    }

    private void write(List<User> users) {
        UtilityJson.write(PATH, users);
    }
}
