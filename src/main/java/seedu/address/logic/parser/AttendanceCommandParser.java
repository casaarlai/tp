package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a parser that parses input arguments and creates a new AttendanceCommand object.
 */
public class AttendanceCommandParser implements Parser<AttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AttendanceCommand
     * and returns a AttendanceCommand object for execution.
     *
     * @param args String of arguments to be parsed.
     * @return AttendanceCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public AttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ATTENDANCE_DATE);

        Set<Index> indexes;
        try {
            indexes = ParserUtil.parseIndexes(List.of(argMultimap.getPreamble().split(" ")));
            LocalDate date = LocalDate.parse(argMultimap.getValue(PREFIX_ATTENDANCE_DATE).orElse(""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return new AttendanceCommand(indexes, date);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceCommand.MESSAGE_USAGE), ive);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceCommand.MESSAGE_USAGE), e);
        }
    }

}
