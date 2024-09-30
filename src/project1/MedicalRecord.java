package project1;

public class MedicalRecord {
    private Patient[] patients;
    private int size; //number of patient objects in the array

    private void grow(){
        Patient[] newPatients = new Patient[this.patients.length + 4];
        for(int i = 0; i<this.size; i++){
            newPatients[i] = this.patients[i];
        }
        this.patients = newPatients;
    }

    public void add(Patient patient){
        if(this.patients==null){
            this.patients = new Patient[4];
        }
        if(contains(patient)) return;
        if(this.size==this.patients.length){
            this.grow();
        }
        this.patients[this.size] = patient;
        this.size++;
    }

    // Helper method to check if record already contains given Patient
    private boolean contains(Patient patient){
        for(int i = 0; i < this.size; i++){
            if(this.patients[i].equals(patient)){
                return true;
            }
        }
        return false;
    }

    // Helper method to find a given Patient's index
    public int patientIdx(Patient patient){
        for(int i = 0; i < this.size; i++){
            if(this.patients[i].equals(patient)){
                return i;
            }
        }
        return -1;
    }

    public Patient[] getPatients(){ return this.patients; }
    public int getSize(){ return this.size; }
}