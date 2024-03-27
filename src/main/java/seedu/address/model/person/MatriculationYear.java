package seedu.address.model.person;

import java.time.Year;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's matriculation year in the address book.
 * Guarantees: immutable; is always valid
 */
public class MatriculationYear {
    public static final String MESSAGE_CONSTRAINTS =
            "Matriculation year should be in YYYY format";
    public static final String VALIDATION_REGEX = "\\d{4}";
    public final String value;

    /**
     * Constructs a {@code MatriculationYear}.
     *
     * @param matriculationYear A valid matriculation year.
     */
    public MatriculationYear(String matriculationYear) {
        requireNonNull(matriculationYear);
        checkArgument(isValidMatriculationYear(matriculationYear), MESSAGE_CONSTRAINTS);
        this.value = matriculationYear;
    }

    /**
     * Returns true if a given string is a valid matriculation year.
     */
    public static boolean isValidMatriculationYear(String test) {
        boolean formatIsCorrect = test.matches(VALIDATION_REGEX);

        Year thisYear = Year.now();
        Year yearEntered = Year.parse(test);
        boolean yearIsValid = (yearEntered.equals(thisYear)) || (yearEntered.isBefore(thisYear));

        return formatIsCorrect && yearIsValid;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationYear // instanceof handles nulls
                && value.equals(((MatriculationYear) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
