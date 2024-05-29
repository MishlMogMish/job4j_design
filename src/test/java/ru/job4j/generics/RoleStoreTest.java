package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsDoctor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Doctor"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Doctor");
    }

    @Test
    void whenAddAndFindNullRoleNameThenRoleNameIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", null));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo(null);
    }

    @Test
    void whenAddAndFindEmptyRoleNameThenRoleNameIsEmpty() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", ""));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("");
    }

    @Test
    void whenAddMultiAndFindNullIdNullRoleNameThenNullRolINullIdFirst() {
        RoleStore store = new RoleStore();
        Role role = new Role(null, null);
        store.add(role);
        store.add(new Role(null, "Fox"));
        Role result = store.findById(null);
        assertThat(result).isEqualTo(role);
    }

    @Test
    void whenAddFindNullIdFoxRoleNameThenFoxRolINullId() {
        RoleStore store = new RoleStore();
        store.add(new Role(null, "Fox"));
        Role result = store.findById(null);
        assertThat(result.getRoleName()).isEqualTo("Fox");
    }

    @Test
    void whenReplaceThenRolNameIsAlice() {
        RoleStore store = new RoleStore();
        store.add(new Role("34", "Kate"));
        store.replace("34", new Role("3", "Alice"));
        Role result = store.findById("34");
        assertThat(result.getRoleName()).isEqualTo("Alice");
        assertThat(store.findById("3")).isEqualTo(null);
    }

    @Test
    void whenReplaceThenLooseNewRoleIdIfDifferent() {
        RoleStore store = new RoleStore();
        store.add(new Role("34", "Kate"));
        store.replace("34", new Role("3", "Alice"));
        Role result = store.findById("34");
        assertThat(store.findById("3")).isEqualTo(null);
    }

    @Test
    void whenAddAndFindThenRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Petr");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.add(new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceThenRoleNameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.replace("1", new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.replace("10", new Role("10", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Petr");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean result = store.replace("1", new Role("1", "Maxim"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean result = store.replace("10", new Role("10", "Maxim"));
        assertThat(result).isFalse();
    }
}