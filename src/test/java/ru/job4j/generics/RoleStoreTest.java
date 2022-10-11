package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RoleStoreTest {
    @Test
    void whenAddAndReplace() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role"));
        Boolean result = store.replace("1", new Role("1", "NewRole"));
        assertThat(result).isTrue();
    }

    @Test
    void whenAddAndDelete() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role"));
        Boolean result = store.delete("3");
        assertThat(result).isFalse();
    }

    @Test
    void whenAddAndFindById() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Role");
    }

    @Test
    void whenAddDuplicateAndFindRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("5", "Role"));
        store.add(new Role("5", "Role555"));
        Role result = store.findById("5");
        assertThat(result.getRoleName()).isEqualTo("Role");
    }
}