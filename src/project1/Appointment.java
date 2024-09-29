package project1;
import java.util.Calendar;
import java.util.Date;

public class Appointment implements Comparable<Appointment>{
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    @Override
    public int compareTo(Appointment appointment){
        return 0;
    }

}
