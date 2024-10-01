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

    private int partitionRecord(int sIdx, int eIdx){
        Patient piv = this.patients[eIdx];
        int i = (sIdx - 1);
        for(int j = sIdx; j < eIdx; j++){
            if(this.patients[j].getProfile().compareTo(piv.getProfile())<=0){
                i++;
                Patient temp = this.patients[i];
                this.patients[i] = this.patients[j];
                this.patients[j] = temp;
            }
        }
        Patient temp = this.patients[i+1];
        this.patients[i+1] = this.patients[eIdx];
        this.patients[eIdx] = temp;

        return i+1;
    }

    public void sortRecord(int sIdx, int eIdx){
        if(sIdx < eIdx){
            int pIdx = partitionRecord(sIdx, eIdx);
            sortRecord(sIdx, pIdx-1);
            sortRecord(pIdx+1, eIdx);
        }
    }

    public Patient[] getPatients(){ return this.patients; }
    public int getSize(){ return this.size; }
}