import java.util.Scanner;

// This is the main class of the program.
// It shows the menus, reads user input, and delegates event data management.
public class Main {
    // This manager stores all data entered by the user while the program is running.
    private final EventManager eventManager = new EventManager();

    // This scanner is used to read input from the keyboard.
    private final Scanner scanner = new Scanner(System.in);

    // Program execution starts here.
    public static void main(String[] args) {
        new Main().run();
    }

    // This method keeps the program running until the user chooses to exit.
    private void run() {
        boolean running = true;

        // Keep showing the main menu until the user chooses to exit.
        while (running) {
            printMainMenu();
            int choice = readInt("Choose an option: ");

            // Use an if-else ladder to decide what to do in the main menu.
            if (choice == 1) {
                handleEventMenu();
            } else if (choice == 2) {
                handleVenueMenu();
            } else if (choice == 3) {
                handleDepartmentMenu();
            } else if (choice == 0) {
                System.out.println("Exiting Campus Event Manager. Goodbye.");
                running = false;
            } else {
                System.out.println("Invalid menu choice. Please select a listed option.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n======================================");
        System.out.println("      Campus Event Management");
        System.out.println("======================================");
        System.out.println("1. Event Menu");
        System.out.println("2. Venue Menu");
        System.out.println("3. Department Menu");
        System.out.println("0. Exit");
        System.out.println("======================================");
    }

    // This method handles the event submenu.
    private void handleEventMenu() {
        boolean back = false;

        // This loop lets the user stay inside the event menu.
        while (!back) {
            System.out.println("\n----------- Event Menu -----------");
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Cancel Event");
            System.out.println("4. Search Event");
            System.out.println("0. Back");

            int choice = readInt("Choose an option: ");
            
            // Use an if-else ladder to decide what to do in the event menu.
            if (choice == 1) {
                addEvent();
            } else if (choice == 2) {
                viewEvents();
            } else if (choice == 0) {
                back = true;
            } else {
                System.out.println("Invalid menu choice. Please select a listed option.");
            }
        }
    }

    // This method handles the venue submenu.
    private void handleVenueMenu() {
        boolean back = false;

        // This loop lets the user stay inside the venue menu.
        while (!back) {
            System.out.println("\n----------- Venue Menu -----------");
            System.out.println("1. Add Venue");
            System.out.println("2. View Venues");
            System.out.println("0. Back");

            int choice = readInt("Choose an option: ");

            // Use an if-else ladder to decide what to do in the venue menu.
            if (choice == 1) {
                addVenue();
            } else if (choice == 2) {
                viewVenues();
            } else if (choice == 0) {
                back = true;
            } else {
                System.out.println("Invalid menu choice. Please select a listed option.");
            }
        }
    }

    // This method handles the department submenu.
    private void handleDepartmentMenu() {
        boolean back = false;

        // This loop lets the user stay inside the department menu.
        while (!back) {
            System.out.println("\n-------- Department Menu --------");
            System.out.println("1. Add Department");
            System.out.println("2. View Departments");
            System.out.println("0. Back");

            int choice = readInt("Choose an option: ");
            
            // Use an if-else ladder to decide what to do in the department menu.
            if (choice == 1) {
                addDepartment();
            } else if (choice == 2) {
                viewDepartments();
            } else if (choice == 0) {
                // Setting back to true exits this submenu and returns to the main menu.
                back = true;
            } else {
                System.out.println("Invalid menu choice. Please select a listed option.");
            }
        }
    }

    // This method asks the user for venue details and creates the correct venue object.
    private void addVenue() {
        System.out.println("\nAdd Venue");
        System.out.println("1. Sports Area");
        System.out.println("2. Lecture Hall");
        System.out.println("3. Conference Hall");
        System.out.println("4. Public Space");

        int venueType = readInt("Choose venue type: ");
        String venueName = readNonEmptyString("Enter venue name: ");
        int maximumCapacity = readPositiveInt("Enter maximum capacity: ");

        // This variable will store the new venue object after it is created.
        Venue venue;

        // Use an if-else ladder to create the correct venue child class.
        if (venueType == 1) {
            String sportType = readNonEmptyString("Enter supported sport type: ");
            boolean indoor = readYesNo("Is the sports area indoor");
            venue = new SportsArea(venueName, maximumCapacity, sportType, indoor);
        } else if (venueType == 2) {
            int projectorCount = readNonNegativeInt("Enter projector count: ");
            boolean smartBoardAvailable = readYesNo("Is a smart board available");
            venue = new LectureHall(venueName, maximumCapacity, projectorCount, smartBoardAvailable);
        } else if (venueType == 3) {
            boolean hasTranslationSystem = readYesNo("Does it have a translation system");
            int breakoutRoomCount = readNonNegativeInt("Enter breakout room count: ");
            venue = new ConferenceHall(
                    venueName,
                    maximumCapacity,
                    hasTranslationSystem,
                    breakoutRoomCount
            );
        } else if (venueType == 4) {
            boolean openAir = readYesNo("Is it an open-air public space");
            boolean electricityAccess = readYesNo("Does it have electricity access");
            venue = new PublicSpace(venueName, maximumCapacity, openAir, electricityAccess);
        } else {
            // Stop the method if the user chooses a number outside the valid list.
            System.out.println("Invalid venue type. Venue was not added.");
            return;
        }

        // Add the new venue object to the list after it is created successfully.
        eventManager.addVenue(venue);
        System.out.println("Venue added successfully.");
    }

    // This method creates a department and its responsible person.
    private void addDepartment() {
        System.out.println("\nAdd Department");
        String departmentName = readNonEmptyString("Enter department name: ");
        String responsiblePersonName = readNonEmptyString("Enter responsible person name: ");

        // A department has a Person object to represent the responsible person.
        Person responsiblePerson = new Person(responsiblePersonName);
        AcademicDepartment department = new AcademicDepartment(departmentName, responsiblePerson);
        eventManager.addDepartment(department);

        System.out.println("Department added successfully.");
    }

    // This method asks the user for event details, validates them,
    // and creates the correct event object.
    private void addEvent() {
        // An event must belong to an existing venue.
        if (!eventManager.hasVenues()) {
            System.out.println("At least one venue must exist before adding an event.");
            return;
        }

        // An event must also belong to an existing department.
        if (!eventManager.hasDepartments()) {
            System.out.println("At least one department must exist before adding an event.");
            return;
        }

        System.out.println("\nAdd Event");
        System.out.println("1. Sports Event");
        System.out.println("2. Social Event");
        System.out.println("3. Religious Event");
        System.out.println("4. Academic Event");

        int eventType = readInt("Choose event type: ");
        String eventName = readNonEmptyString("Enter event name: ");
        SimpleDateTime startDateTime = readDateTime("Enter start date/time (yyyy-MM-dd HH:mm): ");
        SimpleDateTime endDateTime = readDateTime("Enter end date/time (yyyy-MM-dd HH:mm): ");

        // The event start must happen before the event end.
        if (!eventManager.isValidEventTime(startDateTime, endDateTime)) {
            System.out.println("Invalid event time range. Start time must be before end time.");
            return;
        }

        int expectedAttendance = readPositiveInt("Enter expected attendance: ");

        Venue selectedVenue = selectVenue();
        if (selectedVenue == null) {
            return;
        }

        if (!eventManager.canVenueHoldEvent(selectedVenue, expectedAttendance)) {
            System.out.println("Event rejected. Expected attendance exceeds the selected venue capacity.");
            return;
        }

        AcademicDepartment selectedDepartment = selectDepartment();
        if (selectedDepartment == null) {
            return;
        }

        // This variable will store the new event object after it is created.
        Event event;

        // Use an if-else ladder to create the correct event child class.
        if (eventType == 1) {
            String sportCategory = readNonEmptyString("Enter sport category: ");
            String teamFormat = readNonEmptyString("Enter team format: ");
            event = new SportsEvent(
                    eventName,
                    startDateTime,
                    endDateTime,
                    expectedAttendance,
                    selectedVenue,
                    selectedDepartment,
                    sportCategory,
                    teamFormat
            );
        } else if (eventType == 2) {
            String dressCode = readNonEmptyString("Enter dress code: ");
            boolean refreshmentsProvided = readYesNo("Will refreshments be provided");
            event = new SocialEvent(
                    eventName,
                    startDateTime,
                    endDateTime,
                    expectedAttendance,
                    selectedVenue,
                    selectedDepartment,
                    dressCode,
                    refreshmentsProvided
            );
        } else if (eventType == 3) {
            String speakerName = readNonEmptyString("Enter speaker name: ");
            boolean separateSeating = readYesNo("Will there be separate seating");
            event = new ReligiousEvent(
                    eventName,
                    startDateTime,
                    endDateTime,
                    expectedAttendance,
                    selectedVenue,
                    selectedDepartment,
                    speakerName,
                    separateSeating
            );
        } else if (eventType == 4) {
            String topic = readNonEmptyString("Enter academic topic: ");
            boolean certificateProvided = readYesNo("Will certificates be provided");
            event = new AcademicEvent(
                    eventName,
                    startDateTime,
                    endDateTime,
                    expectedAttendance,
                    selectedVenue,
                    selectedDepartment,
                    topic,
                    certificateProvided
            );
        } else {
            // Stop the method if the user chooses a number outside the valid list.
            System.out.println("Invalid event type. Event was not added.");
            return;
        }

        // Reject the event if the chosen venue is already booked during this time.
        if (eventManager.hasVenueOverlap(event)) {
            System.out.println("Event rejected. The selected venue is already booked during that time.");
            return;
        }

        eventManager.addEvent(event);
        System.out.println("Event added successfully.");
    }

    // This method shows the list of venues and lets the user choose one.
    private Venue selectVenue() {
        System.out.println("\nAvailable Venues");
        for (int i = 0; i < eventManager.getVenues().size(); i++) {
            Venue venue = eventManager.getVenueByIndex(i);
            System.out.printf(
                    "%d. %s (%s, Capacity: %d)%n",
                    i + 1,
                    venue.getVenueName(),
                    venue.getVenueType(),
                    venue.getMaximumCapacity()
            );
        }

        int selection = readInt("Select a venue by number: ");
        if (selection < 1 || selection > eventManager.getVenues().size()) {
            System.out.println("Invalid venue selection.");
            return null;
        }
        return eventManager.getVenueByIndex(selection - 1);
    }

    // This method shows the list of departments and lets the user choose one.
    private AcademicDepartment selectDepartment() {
        System.out.println("\nAvailable Departments");
        for (int i = 0; i < eventManager.getDepartments().size(); i++) {
            AcademicDepartment department = eventManager.getDepartmentByIndex(i);
            System.out.printf(
                    "%d. %s (Responsible Person: %s)%n",
                    i + 1,
                    department.getDepartmentName(),
                    department.getResponsiblePerson().getPersonName()
            );
        }

        int selection = readInt("Select a department by number: ");
        if (selection < 1 || selection > eventManager.getDepartments().size()) {
            System.out.println("Invalid department selection.");
            return null;
        }
        return eventManager.getDepartmentByIndex(selection - 1);
    }

    // This method displays all saved events.
    private void viewEvents() {
        System.out.println("\nEvent List");
        if (!eventManager.hasEvents()) {
            System.out.println("No events have been added yet.");
            return;
        }

        for (int i = 0; i < eventManager.getEvents().size(); i++) {
            System.out.printf("%d.%n%s%n", i + 1, eventManager.getEvents().get(i).getDisplayDetails());
            System.out.println("--------------------------------------");
        }
    }

    // This method lets the user cancel one saved event.
    // It shows event numbers, checks the user's choice, and removes the selected event.
    private void cancelEventMenu() {
        System.out.println("\nCancel Event");

        // Do not ask for an event number when the list is empty.
        if (!eventManager.hasEvents()) {
            System.out.println("No events to cancel.");
            return;
        }

        // Show all events with numbers so the user knows which number to enter.
        for (int i = 0; i < eventManager.getEvents().size(); i++) {
            System.out.printf("%d.%n%s%n", i + 1, eventManager.getEvents().get(i).getDisplayDetails());
            System.out.println("--------------------------------------");
        }

        int eventNumber = readInt("Enter the event number to cancel: ");

        // The valid numbers start at 1 and end at the number of events in the list.
        if (eventNumber < 1 || eventNumber > eventManager.getEvents().size()) {
            System.out.println("Invalid event number.");
            return;
        }

        // The number is valid, so ask EventManager to remove that event.
        eventManager.cancelEvent(eventNumber);
        System.out.println("Event canceled successfully.");
    }

    // This method asks for an exact event name and displays the event if it exists.
    private void searchEventMenu() {
        System.out.println("\nSearch Event");

        // Read the event name the user wants to find.
        String eventName = readNonEmptyString("Enter the exact event name: ");

        // Ask EventManager to search the events list.
        Event foundEvent = eventManager.searchEvent(eventName);

        // If the search returns an Event object, display its details.
        // If it returns null, no matching event was found.
        if (foundEvent != null) {
            System.out.println(foundEvent.getDisplayDetails());
        } else {
            System.out.println("No event found.");
        }
    }

    // This method displays all saved venues.
    private void viewVenues() {
        System.out.println("\nVenue List");
        if (!eventManager.hasVenues()) {
            System.out.println("No venues have been added yet.");
            return;
        }

        for (int i = 0; i < eventManager.getVenues().size(); i++) {
            System.out.printf("%d.%n%s%n", i + 1, eventManager.getVenues().get(i).getDisplayDetails());
            System.out.println("--------------------------------------");
        }
    }

    // This method displays all saved departments.
    private void viewDepartments() {
        System.out.println("\nDepartment List");
        if (!eventManager.hasDepartments()) {
            System.out.println("No departments have been added yet.");
            return;
        }

        for (int i = 0; i < eventManager.getDepartments().size(); i++) {
            AcademicDepartment department = eventManager.getDepartments().get(i);
            System.out.printf(
                    "%d.%nDepartment Name: %s%nResponsible Person: %s%n--------------------------------------%n",
                    i + 1,
                    department.getDepartmentName(),
                    department.getResponsiblePerson().getPersonName()
            );
        }
    }

    // This method reads an integer from the user.
    // It keeps asking until the user enters a valid whole number.
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a whole number.");
            }
        }
    }

    // This method reads an integer that must be greater than 0.
    private int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Value must be greater than 0.");
        }
    }

    // This method reads an integer that can be 0 or more.
    private int readNonNegativeInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value >= 0) {
                return value;
            }
            System.out.println("Value must be 0 or greater.");
        }
    }

    // This method reads a text value that cannot be empty.
    private String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    // This method reads a yes/no answer and converts it to true or false.
    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            }
            if (input.equals("no") || input.equals("n")) {
                return false;
            }
            System.out.println("Please answer with yes or no.");
        }
    }

    // This method reads a date and time from the user
    // and converts it into a SimpleDateTime object.
    private SimpleDateTime readDateTime(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return SimpleDateTime.parse(input);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid date/time format. Use yyyy-MM-dd HH:mm.");
            }
        }
    }
}
