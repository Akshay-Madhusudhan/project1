package project1;

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
    public boolean appointmentValid(Appointment appointment) {
        if(!date.isValidDate()) return false;
        if(date.isBeforeToday()) return false;
        if(date.isWeekend()) return false;
        if(!date.withinSix()) return false;
        if(!patient.getDob().isValidBirth()) return false;
        // add checks here for appointment already exists

        return true;
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
        return 0;
    }

}
