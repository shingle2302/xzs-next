package com.mindskip.xzs.domain;

import com.mindskip.xzs.domain.aggregate.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateStudentUser() {
        User user = new User("student1", "pass123", 1);
        assertEquals("student1", user.getUserName());
        assertEquals(1, user.getRole());
        assertEquals(1, user.getStatus());
        assertFalse(user.getDeleted());
        assertNotNull(user.getCreateTime());
    }

    @Test
    void shouldCreateAdminUser() {
        User user = new User("admin", "admin123", 3);
        assertEquals(3, user.getRole());
        assertTrue(user.isAdmin());
        assertFalse(user.isStudent());
    }

    @Test
    void shouldIdentifyStudentRole() {
        User user = new User("s1", "pwd", 1);
        assertTrue(user.isStudent());
        assertFalse(user.isAdmin());
    }

    @Test
    void shouldChangePassword() {
        User user = new User("u1", "old", 1);
        user.changePassword("newPassword");
        assertEquals("newPassword", user.getPassword());
        assertNotNull(user.getModifyTime());
    }

    @Test
    void shouldThrowOnNullPassword() {
        User user = new User("u1", "old", 1);
        assertThrows(NullPointerException.class, () -> user.changePassword(null));
    }

    @Test
    void shouldDisableUser() {
        User user = new User("u1", "pwd", 1);
        user.disable();
        assertEquals(2, user.getStatus());
        assertTrue(user.isDisabled());
    }

    @Test
    void shouldEnableUser() {
        User user = new User("u1", "pwd", 1);
        user.disable();
        user.enable();
        assertEquals(1, user.getStatus());
        assertFalse(user.isDisabled());
    }

    @Test
    void shouldBindWx() {
        User user = new User("u1", "pwd", 1);
        user.bindWx("wx_open_id_123");
        assertEquals("wx_open_id_123", user.getWxOpenId());
    }
}
