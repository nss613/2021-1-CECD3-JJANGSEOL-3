package com.UARTLoopback;

import java.util.Map;
import java.util.HashMap;

public class PeriodData {
    public int heart_beat;
    public int motion_cnt;
    public int door_cnt;
    public int human_sensing;

    public PeriodData() {
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public PeriodData(String heart_beat, String motion_cnt, String door_cnt, String human_sensing) {
        this.heart_beat = Integer.parseInt(heart_beat);
        this.motion_cnt = Integer.parseInt(motion_cnt);
        this.door_cnt = Integer.parseInt(door_cnt);
        this.human_sensing = Integer.parseInt(human_sensing);
    }

   // @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("heart_beat", heart_beat);
        result.put("motion_cnt", motion_cnt);
        result.put("door_cnt", door_cnt);
        result.put("human_sensing", human_sensing);
        return result;
    }
}
