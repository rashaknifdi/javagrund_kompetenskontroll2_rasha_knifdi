package com.rasha.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {
    @Test
    void testValidName() {
        assertTrue(InputValidator.isValidName("Rasha"));
        assertFalse(InputValidator.isValidName(""));
        assertFalse(InputValidator.isValidName("   "));
        assertFalse(InputValidator.isValidName(null));
    }

    @Test
    void testValidAge() {
        assertTrue(InputValidator.isValidAge(25));
        assertFalse(InputValidator.isValidAge(-1));
        assertFalse(InputValidator.isValidAge(130));
    }

    @Test
    void testValidBranch() {
        assertTrue(InputValidator.isValidBranch("IT"));
        assertFalse(InputValidator.isValidBranch(""));
        assertFalse(InputValidator.isValidBranch("   "));
        assertFalse(InputValidator.isValidBranch(null));
    }

    @Test
    void testValidExperience() {
        assertTrue(InputValidator.isValidExperience(5));
        assertFalse(InputValidator.isValidExperience(-3));
        assertFalse(InputValidator.isValidExperience(150));
    }

}
