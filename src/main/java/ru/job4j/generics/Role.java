package ru.job4j.generics;

public class Role extends  Base {
    private final String role;

    public Role(String id, String describe) {
        super(id);
        this.role = describe;
    }

    public String getRole() {
        return role;
    }
}
