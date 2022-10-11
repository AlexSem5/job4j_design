package ru.job4j.generics;

public class Role extends Base {

    private final String roleName;

    public Role(String id, String rolename) {
        super(id);
        this.roleName = rolename;
    }

    public String getRoleName() {
        return roleName;
    }
}
