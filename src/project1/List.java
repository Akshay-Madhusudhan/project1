package project1;

public class List {
    private Appointment[] appointments;
    private int size; //number of appointments in the array
    private final int NOT_FOUND = -1;

    //helper method
    private int find(Appointment appointment){
        int i = 0;
        for(Appointment app : this.appointments){
            if(app.equals(appointment)){
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }

    //helper method to increase the capacity by 4
    private void grow(){
        Appointment[] newAppointments = new Appointment[appointments.length + 4];
        for(int i = 0; i<this.size; i++){
            newAppointments[i] = this.appointments[i];
        }
        this.appointments = newAppointments;
    }

    //check before add/remove
    public boolean contains(Appointment appointment){
        if(this.appointments == null) return false;
        if(this.appointments[0] == null) System.out.println("First appointment is null");
        for(int i = 0; i < this.size; i++){
            if(appointments[i].equals(appointment)){
                return true;
            }
        }
        return false;
    }

    public void add(Appointment appointment){
        if(this.contains(appointment)){
            return;
        }
        if(this.appointments==null){
            this.appointments = new Appointment[4];
        }
        if(this.size == appointments.length){
            this.grow();
        }
        this.appointments[this.size] = appointment;
        this.size++;
        return;
    }

    public void remove(Appointment appointment){
        int idx = this.find(appointment);
        if(idx == NOT_FOUND){
            return;
        }
        for(int i = idx; i < this.size-1; i++){
            this.appointments[i] = this.appointments[i+1];
        }
        this.appointments[this.size-1] = null;
        this.size--;
        return;
    }

    //ordered by patient profile, date/timeslot
    public void printByPatient(){

    }

    //ordered by county, date/timeslot
    public void printByLocation(){

    }

    //ordered by date/timeslot, provider name
    public void printByAppointment(){

    }
}
