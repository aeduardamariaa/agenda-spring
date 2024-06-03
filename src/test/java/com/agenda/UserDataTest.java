package com.agenda;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.agenda.model.UserData;

public class UserDataTest {
    private final UserData user = new UserData();

    @Test
    void instance_validate() {
        assertTrue(user instanceof UserData);
    }
}