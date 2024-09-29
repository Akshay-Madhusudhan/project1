package project1;

public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    private final int hours;
    private final int minutes;

    Timeslot(int startTime, int endTime) {
        this.hours = startTime;
        this.minutes = endTime;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }


}
