package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.DataObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationHelperTest {

    private static final List<Integer> noDuplicates = Arrays.asList(12, 4, 6, 89, 23, 17, 5, 3);
    private static final List<Integer> duplicates = Arrays.asList(12, 5, 4, 6, 89, 23, 17, 5, 3);

    @Test
    public void validateNoDuplicates() {
        try {
            ValidationHelper.validateDuplication(noDuplicates.stream().map(DataObject::new).collect(Collectors.toList()));
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateDuplicates() {
        try {
            ValidationHelper.validateDuplication(duplicates.stream().map(DataObject::new).collect(Collectors.toList()));
            Assert.fail("No expected exception");
        } catch (ConstraintValidationException e) {
            // this can be ignored.
        }
    }
}
