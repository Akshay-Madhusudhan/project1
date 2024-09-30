package project1;

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits (completed appt.)

    //traverse the linked list to compute the charge
    public int charge() {
        return 0;
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
