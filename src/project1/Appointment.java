package project1;

public class Appointment implements Comparable<Appointment>{
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

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
