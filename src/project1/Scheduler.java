package project1;
import java.util.Scanner;

public class Scheduler {
    private Scanner scanner;
    List appointments = new List();

    public Scheduler(){
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Scheduler is running.");

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) System.out.println("Please provide an input.");

            else if (input.charAt(0) == 'Q') {
                System.out.println("Scheduler terminated.");
                break;
            }
            else processCommand(input);
        }
        scanner.close();
    }

    // Direct commands to respective functions
    private void processCommand(String input) {
        String command = getCommand(input);
        String data = getData(input); // Rest of information after first comma
        String[] separated_data = data.split(",");

        switch (command) {
            case "S":
                scheduleAppointment(separated_data);
                break;
            case "C":
                cancelAppointment(separated_data);
                break;
            case "R":
                rescheduleAppointment(separated_data);
                break;
            case "PA":
                appointments.printByAppointment();
                break;
            case "PP":
                appointments.printByPatient();
                break;
            case "PL":
                appointments.printByLocation();
                break;
            case "PS":
                break;
            default:
                System.out.println("Invalid command.");
                break;
            }
    }

    // Takes user input, returns command portion (S, C, PL, etc)
    private String getCommand(String input) {
        int commaIndex = input.indexOf(',');
        return input.substring(0, commaIndex);
    }

    // Takes user input, returns all information after command portion
    private String getData(String input) {
        int commaIndex = input.indexOf(',');
        return input.substring(commaIndex + 1);
    }

    // Takes array of Strings containing data after command, adds an appointment to the List if valid
    private void scheduleAppointment(String[] separated_data) {
        String[] dateStrings = separated_data[0].split("/");
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        Date appointmentDate = new Date(month, day, year);

        String timeslotString = separated_data[1];
        if(Integer.parseInt(timeslotString) < 1 || Integer.parseInt(timeslotString) > Timeslot.values().length){
            System.out.println("Invalid appointment.");
            return;
        }
        Timeslot timeslot = Timeslot.valueOf("SLOT" + Integer.parseInt(timeslotString));

        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);

        String providerString = separated_data[5];
        if(!checkProviderExists(providerString.toUpperCase())){
            System.out.println("Invalid appointment.");
            return;
        }
        Provider provider = Provider.valueOf(providerString.toUpperCase());

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);

        if(appointment.appointmentValid(appointment, appointments) && !providerBooked(timeslot, appointment)) {
            appointments.add(appointment);
            System.out.println("Added appointment.");
        }
        else System.out.println("Invalid appointment.");
    }

    // Takes array of Strings containing data after command, removes an appointment on list if it exists
    private void cancelAppointment(String[] separated_data) {
        String[] dateStrings = separated_data[0].split("/");
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        Date appointmentDate = new Date(month, day, year);

        String timeslotString = separated_data[1];
        Timeslot timeslot = Timeslot.valueOf("SLOT" + Integer.parseInt(timeslotString));

        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, Provider.PATEL);

        if(appointments.contains(appointment)){
            appointments.remove(appointment);
            System.out.println("Removed appointment.");
        }
        else System.out.println("No appointment to remove.");
    }

    // Takes array of Strings containing data after command, reschedules an appointment to a different timeslot (same day, same provider)
    private void rescheduleAppointment(String[] separated_data) {
        String[] dateStrings = separated_data[0].split("/");
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        Date appointmentDate = new Date(month, day, year);

        String timeslotString = separated_data[1];
        Timeslot timeslot = Timeslot.valueOf("SLOT" + Integer.parseInt(timeslotString));

        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, Provider.PATEL);
        if(!appointments.contains(appointment)) {
            System.out.println("No appointment to reschedule.");
            return;
        }
        Appointment oldAppointment = appointments.getAppointments()[appointments.findIdx(appointment)];

        String newTimeslotString = separated_data[5];
        Timeslot newTimeslot = Timeslot.valueOf("SLOT" + Integer.parseInt(newTimeslotString));

        Appointment newAppointment = new Appointment(appointmentDate, newTimeslot, patient, oldAppointment.getProvider());

        if(appointments.contains(oldAppointment) && !providerBooked(newTimeslot, newAppointment)){
            appointments.remove(oldAppointment);
            appointments.add(newAppointment);
            System.out.println("Rescheduled appointment.");
        } else System.out.println("No appointment to reschedule.");
    }

    private boolean checkProviderExists(String providerString) {
        for (Provider p : Provider.values()) {
            if (p.name().equals(providerString)) {
                return true;
            }
        }
        return false;
    }

    // Takes a given timeslot and appointment, determines if provider of that appointment is busy at that timeslot on that day
    private boolean providerBooked(Timeslot timeslot, Appointment appointment) {
        for(int i = 0; i < appointments.getSize(); i++){
            Appointment pointer = appointments.getAppointments()[i];
            if(pointer.equals(appointment) && pointer.getProvider().equals(appointment.getProvider())){
                return true;
            }
        }
        return false;
    }

}
