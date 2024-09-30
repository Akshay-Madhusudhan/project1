package project1;

public class List {
    private Appointment[] appointments;
    private int size; //number of appointments in the array
    private final int NOT_FOUND = -1;

    //helper method
    private int find(Appointment appointment){
        int i = 0;
        for(Appointment app : this.appointments){
            if(app == null) return NOT_FOUND;
            if(app.equals(appointment)){
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }

    //helper method to increase the capacity by 4
    private void grow(){
        Appointment[] newAppointments = new Appointment[this.appointments.length + 4];
        for(int i = 0; i<this.size; i++){
            newAppointments[i] = this.appointments[i];
        }
        this.appointments = newAppointments;
    }

    //check before add/remove
    public boolean contains(Appointment appointment){
        if(this.appointments == null) return false;
        for(int i = 0; i < this.size; i++){
            if(this.appointments[i].equals(appointment)){
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
        if(this.size == this.appointments.length){
            this.grow();
        }
        this.appointments[this.size] = appointment;
        this.size++;
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
    }

    public Appointment[] getAppointments(){
        return this.appointments;
    }

    private int partitionByP(int sIdx, int eIdx){
        Appointment piv = this.appointments[eIdx];
        int i = (sIdx - 1);
        for(int j = sIdx; j < eIdx; j++){
            if(this.appointments[j].getProfile().compareTo(piv.getProfile())<0){
                i++;
                Appointment temp = this.appointments[i];
                this.appointments[i] = this.appointments[j];
                this.appointments[j] = temp;
            } else if(this.appointments[j].getProfile().compareTo(piv.getProfile())==0){
                if(this.appointments[j].getDate().compareTo(piv.getDate())<0){
                    i++;
                    Appointment temp = this.appointments[i];
                    this.appointments[i] = this.appointments[j];
                    this.appointments[j] = temp;
                } else if(this.appointments[j].getDate().compareTo(piv.getDate())==0){
                    if(this.appointments[j].getTimeslot().compareTo(piv.getTimeslot())<=0){
                        i++;
                        Appointment temp = this.appointments[i];
                        this.appointments[i] = this.appointments[j];
                        this.appointments[j] = temp;
                    }
                }
            }
        }
        Appointment temp = this.appointments[i+1];
        this.appointments[i+1] = this.appointments[eIdx];
        this.appointments[eIdx] = temp;

        return i+1;
    }

    private int partitionByL(int sIdx, int eIdx){
        Appointment piv = this.appointments[eIdx];
        int i = (sIdx - 1);
        for(int j = sIdx; j < eIdx; j++){
            if(this.appointments[j].getProvider().getLocation().getCounty().compareTo(piv.getProvider().getLocation().getCounty())<0){
                i++;
                Appointment temp = this.appointments[i];
                this.appointments[i] = this.appointments[j];
                this.appointments[j] = temp;
            } else if(this.appointments[j].getProvider().getLocation().getCounty().compareTo(piv.getProvider().getLocation().getCounty())==0){
                if(this.appointments[j].getDate().compareTo(piv.getDate())<0){
                    i++;
                    Appointment temp = this.appointments[i];
                    this.appointments[i] = this.appointments[j];
                    this.appointments[j] = temp;
                } else if(this.appointments[j].getDate().compareTo(piv.getDate())==0){
                    if(this.appointments[j].getTimeslot().compareTo(piv.getTimeslot())<=0){
                        i++;
                        Appointment temp = this.appointments[i];
                        this.appointments[i] = this.appointments[j];
                        this.appointments[j] = temp;
                    }
                }
            }
        }
        Appointment temp = this.appointments[i+1];
        this.appointments[i+1] = this.appointments[eIdx];
        this.appointments[eIdx] = temp;

        return i+1;
    }

    private int partitionByA(int sIdx, int eIdx){
        Appointment piv = this.appointments[eIdx];
        int i = (sIdx - 1);
        for(int j = sIdx; j < eIdx; j++){
            if(this.appointments[j].getDate().compareTo(piv.getDate())<0){
                i++;
                Appointment temp = this.appointments[i];
                this.appointments[i] = this.appointments[j];
                this.appointments[j] = temp;
            } else if(this.appointments[j].getDate().compareTo(piv.getDate())==0){
                if(this.appointments[j].getTimeslot().compareTo(piv.getTimeslot())<0){
                    i++;
                    Appointment temp = this.appointments[i];
                    this.appointments[i] = this.appointments[j];
                    this.appointments[j] = temp;
                } else if(this.appointments[j].getTimeslot().compareTo(piv.getTimeslot())==0){
                    if(this.appointments[j].getProvider().compareTo(piv.getProvider())<=0){
                        i++;
                        Appointment temp = this.appointments[i];
                        this.appointments[i] = this.appointments[j];
                        this.appointments[j] = temp;
                    }
                }
            }
        }
        Appointment temp = this.appointments[i+1];
        this.appointments[i+1] = this.appointments[eIdx];
        this.appointments[eIdx] = temp;

        return i+1;
    }

    private void sortByP(int sIdx, int eIdx){
        if(sIdx < eIdx){
            int pIdx = partitionByP(sIdx, eIdx);
            sortByP(sIdx, pIdx-1);
            sortByP(pIdx+1, eIdx);
        }
    }

    private void sortByL(int sIdx, int eIdx){
        if(sIdx < eIdx){
            int pIdx = partitionByL(sIdx, eIdx);
            sortByL(sIdx, pIdx-1);
            sortByL(pIdx+1, eIdx);
        }
    }

    private void sortByA(int sIdx, int eIdx){
        if(sIdx < eIdx){
            int pIdx = partitionByA(sIdx, eIdx);
            sortByA(sIdx, pIdx-1);
            sortByA(pIdx+1, eIdx);
        }
    }

    //ordered by patient profile, date/timeslot
    public void printByPatient(){
        this.sortByP(0, this.size-1);
        System.out.println("** Appointments ordered by patient/date/time **");
        for(int i = 0; i<this.size; i++){
            System.out.println(this.appointments[i].getDate().toString() + " " + this.appointments[i].getTimeslot().toString() + " " + this.appointments[i].getProfile().toString() + " [" + this.appointments[i].getProvider().toString() + ", " + this.appointments[i].getProvider().getLocation().toString() + ", " + this.appointments[i].getProvider().getLocation().getCounty() + " " + this.appointments[i].getProvider().getLocation().getZip() + ", " + this.appointments[i].getProvider().getSpecialty().toString() + "]");
        }
        System.out.println("** end of list **");
    }

    //ordered by county, date/timeslot
    public void printByLocation(){
        this.sortByL(0, this.size-1);
        System.out.println("** Appointments ordered by county/date/time **");
        for(int i = 0; i<this.size; i++){
            System.out.println(this.appointments[i].getDate().toString() + " " + this.appointments[i].getTimeslot().toString() + " " + this.appointments[i].getProfile().toString() + " [" + this.appointments[i].getProvider().toString() + ", " + this.appointments[i].getProvider().getLocation().toString() + ", " + this.appointments[i].getProvider().getLocation().getCounty() + " " + this.appointments[i].getProvider().getLocation().getZip() + ", " + this.appointments[i].getProvider().getSpecialty().toString() + "]");
        }
        System.out.println("** end of list **");
    }

    //ordered by date/timeslot, provider name
    public void printByAppointment(){
        this.sortByA(0, this.size-1);
        System.out.println("** Appointments ordered by date/time/provider **");
        for(int i = 0; i<this.size; i++){
            System.out.println(this.appointments[i].getDate().toString() + " " + this.appointments[i].getTimeslot().toString() + " " + this.appointments[i].getProfile().toString() + " [" + this.appointments[i].getProvider().toString() + ", " + this.appointments[i].getProvider().getLocation().toString() + ", " + this.appointments[i].getProvider().getLocation().getCounty() + " " + this.appointments[i].getProvider().getLocation().getZip() + ", " + this.appointments[i].getProvider().getSpecialty().toString() + "]");
        }
        System.out.println("** end of list **");
    }
}
