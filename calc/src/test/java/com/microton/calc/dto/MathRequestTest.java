package com.microton.calc.dto;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for math request data transfer object {@link MathRequest}
 *
 * @author Jan Šípek
 */
public class MathRequestTest {

    private static final String UUID = "987654567";
    private static final Integer KEY_CODE = 50;

    @Test
    public void createInstance_test() {
        MathRequest request = ImmutableMathRequest.builder()
                .keyCode(KEY_CODE)
                .uuid(UUID)
                .build();
        Assert.assertEquals(UUID, request.getUuid());
        Assert.assertEquals(KEY_CODE, request.getKeyCode());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createInstanceWithNullUuid_test() {
        MathRequest request = ImmutableMathRequest.builder()
                .keyCode(KEY_CODE)
                .uuid(null)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createInstanceWithNullKeyCOde_test() {
        MathRequest request = ImmutableMathRequest.builder()
                .keyCode(null)
                .uuid(UUID)
                .build();
    }

    @Test
    public void getUuid_test() {
        MathRequest request = ImmutableMathRequest.builder()
                .keyCode(KEY_CODE)
                .uuid(UUID)
                .build();
        Assert.assertEquals(UUID, request.getUuid());
    }

    @Test
    public void getKeyCode_test() {
        MathRequest request = ImmutableMathRequest.builder()
                .keyCode(KEY_CODE)
                .uuid(UUID)
                .build();
        Assert.assertEquals(KEY_CODE, request.getKeyCode());
    }
}
