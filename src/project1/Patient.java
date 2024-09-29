package project1;

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits (completed appt.)

    //traverse the linked list to compute the charge
    public int charge() {
        return 0;
    }

    @Override
    public int compareTo(Patient p) {
        return 0;
    }
}
