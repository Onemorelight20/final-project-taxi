package ua.boretskyi.webtask.logic;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeManager {

	public Timestamp calculateStartTime() {
		Calendar curr = Calendar.getInstance();
		curr.add(Calendar.MINUTE, 2);
		Timestamp currTimePlus10Min = new Timestamp(curr.getTimeInMillis());
		return currTimePlus10Min;
	}

	public Timestamp calculateFinishTime() {
		Calendar curr = Calendar.getInstance();
		curr.add(Calendar.MINUTE, 5);
		Timestamp currTimePlus40Min = new Timestamp(curr.getTimeInMillis());
		return currTimePlus40Min;
	}
}
