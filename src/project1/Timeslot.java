package project1;

/**
 * @author Aidan Pembleton
 * @author Akshay Madhusudhan
 */

public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    private final int hours;
    private final int minutes;

    Timeslot(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    static public boolean contains(String slot){
        try{
            Timeslot newSlot = Timeslot.valueOf(slot);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    // Convert an Timeslot into a String, formatted e.g. "9:00 AM"
    public String toString(){
        int hour12;
        if (hours == 0 || hours == 12) {
            hour12 = 12;
        } else if (hours > 12) {
            hour12 = hours - 12;
        } else {
            hour12 = hours;
        }

        String amPm;
        if (hours < 12) {
            amPm = "AM";
        } else {
            amPm = "PM";
        }

        String minuteStr;
        if (minutes < 10) {
            minuteStr = "0" + minutes; // Add leading zero for single-digit minutes
        } else {
            minuteStr = Integer.toString(minutes); // Convert minutes to string
        }

        return hour12 + ":" + minuteStr + " " + amPm;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }


}
