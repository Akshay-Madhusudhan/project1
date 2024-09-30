package project1;

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits (completed appt.)

    public Patient(Profile pro){
        this.profile = pro;
        this.visits = null;
    }

    public void add(Appointment app){
        Visit newVisit = new Visit(app);
        if(this.visits == null){
            this.visits = newVisit;
        } else {
            Visit curr = visits;
            while(curr.getNext() != null){
                curr = curr.getNext();
            }
            curr.setNext(newVisit);
        }
    }

    public void remove(Appointment app){
        Visit temp = visits;
        Visit prev = null;
        if(temp!=null && temp.getApp().equals(app)){
            visits = temp.getNext();
        }
        while(temp != null && !temp.getApp().equals(app)){
            prev = temp;
            temp = temp.getNext();
        }
        if(temp==null){
            return;
        }
        prev.setNext(temp.getNext());
    }

    //traverse the linked list to compute the charge
    public int charge() {
        if(this.visits==null){
            return 0;
        }
        Visit ptr = this.visits;
        int tot = 0;
        while(ptr.getNext()!=null){
            tot += ptr.getApp().getProvider().getSpecialty().getCharge();
            ptr = ptr.getNext();
        }
        return tot;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        Patient p = (Patient)obj;
        return p.profile.equals(this.profile);
    }

    @Override
    public int compareTo(Patient p) {
        return this.profile.compareTo(p.profile);
    }

    @Override
    public String toString(){
        return this.profile.toString();
    }
}
