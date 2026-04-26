// This class is a simple replacement for LocalDateTime.
// It stores date and time values in an easy-to-understand way.
public class SimpleDateTime {
    // These fields store the individual parts of the date and time.
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;

    // This constructor creates a date and time object
    // and checks that the values are inside reasonable limits.
    public SimpleDateTime(int year, int month, int day, int hour, int minute) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12.");
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31.");
        }
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour must be between 0 and 23.");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute must be between 0 and 59.");
        }

        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    // This method converts a text value like "2026-05-01 10:30"
    // into a SimpleDateTime object.
    public static SimpleDateTime parse(String text) {
        String[] mainParts = text.split(" ");
        if (mainParts.length != 2) {
            throw new IllegalArgumentException("Date and time must be separated by one space.");
        }

        String[] dateParts = mainParts[0].split("-");
        String[] timeParts = mainParts[1].split(":");

        if (dateParts.length != 3 || timeParts.length != 2) {
            throw new IllegalArgumentException("Wrong date/time format.");
        }

        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        return new SimpleDateTime(year, month, day, hour, minute);
    }

    // Compare from the biggest unit to the smallest unit.
    public boolean isBefore(SimpleDateTime other) {
        if (year != other.year) {
            return year < other.year;
        }
        if (month != other.month) {
            return month < other.month;
        }
        if (day != other.day) {
            return day < other.day;
        }
        if (hour != other.hour) {
            return hour < other.hour;
        }
        return minute < other.minute;
    }

    // This method checks whether the current object is after another object.
    public boolean isAfter(SimpleDateTime other) {
        return other.isBefore(this);
    }

    // This method converts the object into a nicely formatted string.
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
    }
}
