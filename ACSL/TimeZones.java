import java.util.*;
import java.io.*;

public class TimeZones {

	public static int calcTime(int start, int end, int index) {
		int counter = 0;
		if (index == 1) {
			for (int i = end + 1; i < start; i++) {
				if (i < 0) {
					if ((-1 * i) % 15 == 0) counter++;
				}
				if (i == 0) counter++;
				if (i > 0) {
					if (i % 15 == 0) counter++;
				}
			}
		} else if (index == 2) {
			for (int i = start + 1; i <= 180; i++) {
				if (i < 0) {
					if ((-1 * i) % 15 == 0) counter++;
				}
				if (i == 0) counter++;
				if (i > 0) {
					if (i % 15 == 0) counter++;
				}
			}
			for (int i = -179; i < end; i++) {
				if (i < 0) {
					if ((-1 * i) % 15 == 0) counter++;
				}
				if (i == 0) counter++;
				if (i > 0) {
					if (i % 15 == 0) counter++;
				}
			}
		} else if (index == 3) {
			for (int i = start + 1; i < end; i++) {
				if (i < 0) {
					if ((-1 * i) % 15 == 0) counter++;
				}
				if (i == 0) counter++;
				if (i > 0) {
					if (i % 15 == 0) counter++;
				}
			}
		} else if (index == 4) {
			for (int i = end + 1; i <= 180; i++) {
				if (i < 0) {
					if ((-1 * i) % 15 == 0) counter++;
				}
				if (i == 0) counter++;
				if (i > 0) {
					if (i % 15 == 0) counter++;
				}
			}
			for (int i = -179; i < start; i++) {
				if (i < 0) {
					if ((-1 * i) % 15 == 0) counter++;
				}
				if (i == 0) counter++;
				if (i > 0) {
					if (i % 15 == 0) counter++;
				}
			}
		}
		return counter;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/time"));
		in.useDelimiter("\\s");
		for (int q = 0; q < 10; q++) {
			String start = in.next(), day = in.next(), time = in.next(), end = in.next(), endTime = "";
			int startl = 0, endl = 0;
			in.nextLine();
			if (start.charAt(start.length() - 1) == 'W') startl = -1 * Integer.parseInt(start.substring(0, start.length() - 1));
			else startl = Integer.parseInt(start.substring(0, start.length() - 1));
			if (end.charAt(end.length() - 1) == 'W') endl = -1 * Integer.parseInt(end.substring(0, end.length() - 1));
			else endl = Integer.parseInt(end.substring(0, end.length() - 1));
			if (startl > endl) {
				if (startl - endl < 165 - startl + endl + 165) {
					int hour = Integer.parseInt(time.substring(0, 2));
					hour -= calcTime(startl, endl, 1);
					if (hour < 1) {
						while (hour < 1) {
							hour += 12;
							if (time.substring(2, 4).equals("AM")) {
								endTime = hour + "PM";
								switch(day) {
								case("MON"):day = "SUN"; break;
								case("TUE"):day = "MON"; break;
								case("WED"):day = "TUE"; break;
								case("THU"):day = "WED"; break;
								case("FRI"):day = "THU"; break;
								case("SAT"):day = "FRI"; break;
								case("SUN"):day = "SAT"; break;
								}
							}
							else endTime = hour + "AM";
						}
					} else endTime = hour + time.substring(2, 4);
				} else {
					switch(day) {
					case("MON"):day = "SUN"; break;
					case("TUE"):day = "MON"; break;
					case("WED"):day = "TUE"; break;
					case("THU"):day = "WED"; break;
					case("FRI"):day = "THU"; break;
					case("SAT"):day = "FRI"; break;
					case("SUN"):day = "SAT"; break;
					}
					int hour = Integer.parseInt(time.substring(0, 2));
					hour += calcTime(startl, endl, 2);
					if (hour > 12) {
						while (hour > 12) {
							hour = hour - 12;
							if (time.substring(2, 4).equals("AM")) endTime = hour + "PM";
							else {
								endTime = hour + "AM";
								switch(day) {
								case("MON"):day = "TUE"; break;
								case("TUE"):day = "WED"; break;
								case("WED"):day = "THU"; break;
								case("THU"):day = "FRI"; break;
								case("FRI"):day = "SAT"; break;
								case("SAT"):day = "SUN"; break;
								case("SUN"):day = "MON"; break;
								}
							}
						}
					} else endTime = hour + time.substring(2, 4);
				}
			} else {
				if (endl - startl < 165 - endl + startl + 165) {
					int hour = Integer.parseInt(time.substring(0, 2));
					hour += calcTime(startl, endl, 3);
					if (hour > 12) {
						while (hour > 12) {
							hour = hour - 12;
							if (time.substring(2, 4).equals("AM")) endTime = hour + "PM";
							else {
								endTime = hour + "AM";
								switch(day) {
								case("MON"):day = "TUE"; break;
								case("TUE"):day = "WED"; break;
								case("WED"):day = "THU"; break;
								case("THU"):day = "FRI"; break;
								case("FRI"):day = "SAT"; break;
								case("SAT"):day = "SUN"; break;
								case("SUN"):day = "MON"; break;
								}
							}
						}
					} else endTime = hour + time.substring(2, 4);
				} else {
					switch(day) {
					case("MON"):day = "TUE"; break;
					case("TUE"):day = "WED"; break;
					case("WED"):day = "THU"; break;
					case("THU"):day = "FRI"; break;
					case("FRI"):day = "SAT"; break;
					case("SAT"):day = "SUN"; break;
					case("SUN"):day = "MON"; break;
					}
					int hour = Integer.parseInt(time.substring(0, 2));
					hour -= calcTime(startl, endl, 4);
					if (hour < 1) {
						while (hour < 1) {
							hour += 12;
							if (time.substring(2, 4).equals("AM")) {
								endTime = hour + "PM";
								switch(day) {
								case("MON"):day = "SUN"; break;
								case("TUE"):day = "MON"; break;
								case("WED"):day = "TUE"; break;
								case("THU"):day = "WED"; break;
								case("FRI"):day = "THU"; break;
								case("SAT"):day = "FRI"; break;
								case("SUN"):day = "SAT"; break;
								}
							}
							else endTime = hour + "AM";
						}
					} else endTime = hour + time.substring(2, 4);
				}
			}
			System.out.println(day + ", " + ("0000").substring(0, 4 - endTime.length()) + endTime);
		}
		in.close();
	}

}