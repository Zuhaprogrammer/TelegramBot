package com.zuhriddin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zuhriddin.model.Admin;
import com.zuhriddin.model.Order;
import com.zuhriddin.service.util.UtilityJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class AdminService implements BaseService<Admin, UUID> {
    private static final String PATH = "src/file/admins.json";

    @Override
    public Admin add(Admin admin) {
        List<Admin> admins = read();

        // Agar admin allaqachon mavjud bo'lsa, xabarni qaytarish
        if (hasAdmin(admin.getUsername())) {

            throw new RuntimeException("This admin already exist");
        }
        // Admin mavjud bo'lmasa, uni qo'shish
        admins.add(admin);
        write(admins);
        return admin;
    }


    public boolean hasAdmin(String username) {
        return read().stream()
                .anyMatch(admin -> admin.getUsername().equals(username));
    }

    @Override
    public Admin get(UUID id) {
        return read().stream()
                .filter(admin -> admin.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Admin not found."));
    }

    @Override
    public void delete(UUID id) {
        List<Admin> admins = read();
        admins = admins.stream()
                .filter(admin -> admin.getId().equals(id))
                .collect(Collectors.toList());
        write(admins);
    }

    @Override
    public List<Admin> list() {
        return read();
    }

    private List<Admin> read() {
        return UtilityJson.read(PATH, new TypeReference<>() {
        });
    }

    private void write(List<Admin> admins) {
        UtilityJson.write(PATH, admins);
    }
}
