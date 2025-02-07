package project1;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */

public class Appointment implements Comparable<Appointment>{
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    // Check if a given appointment is valid
    public String appointmentValid(Appointment appointment, List appointments) {
        if(!date.isValidDate()) return ("Appointment date: " + appointment.getDate().toString() + " is not a valid calendar date.");
        if(date.isBeforeToday()) return ("Appointment date: " + appointment.getDate().toString() + " is today or a date before today.");
        if(date.isWeekend()) return ("Appointment date: " + appointment.getDate().toString() + " is Saturday or Sunday.");
        if(!date.withinSix()) return ("Appointment date: " + appointment.getDate().toString() + " is not within six months.");
        if(!patient.getDob().isValidBirth()) return ("Birth date: " + appointment.getDate().toString() + " is today or a date after today.");
        if(appointments.contains(appointment)) return (appointment.getProfile().getFname() + " " + appointment.getProfile().getLname() + " " +
                                                        appointment.getProfile().getDob().toString() + " has an existing appointment at the same time slot.");

        return null;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        Appointment app = (Appointment)obj;
        if(app.date.equals(this.date) && app.timeslot.equals(this.timeslot) && app.patient.equals(this.patient)){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Appointment appointment){
        if(this.date.equals(appointment.date)){
            if(this.timeslot.equals(appointment.timeslot)){
                return this.patient.compareTo(appointment.patient);
            }
            return this.timeslot.compareTo(appointment.timeslot);
        }
        return this.date.compareTo(appointment.date);
    }

    public Provider getProvider(){
        return this.provider;
    }

    public Profile getProfile(){
        return this.patient;
    }

    public Timeslot getTimeslot(){
        return this.timeslot;
    }

    public Date getDate(){
        return this.date;
    }

}
