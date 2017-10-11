package vn.sogo.lmscms.common;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import vn.sogo.lmscms.common.ValidateParam;


public class RelateDateTime {


    public static final String EMC_IS_NUMBER = "0123456789";

    public static final int[] baseStartDay = {2000, 1, 1, 0, 0, 0};

    public static final int[] baseEndDay = {2037, 12, 31, 23, 59, 59};

    public static final int ID_YEAR = 0;
    public static final int ID_MONTH = 1;
    public static final int ID_DAY = 2;
    public static final int ID_HOUR = 3;
    public static final int ID_MIN = 4;
    public static final int ID_SEC = 5;

    public static final String YYYYMMDDHHMISS = "yyyy/MM/dd HH:mm:ss";
    public static final String YYYYMMDDHHMI = "yyyy/MM/dd HH:mm";

    private static final String ERR_DATE_LOST = "1";


    public static Timestamp getTimeNow() {
        Calendar rightNow = Calendar.getInstance();
        Timestamp tmsp = new Timestamp(rightNow.getTimeInMillis());

        return tmsp;
    }


    public static Timestamp getTimeNowAddDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        Timestamp tmsp = new Timestamp(cal.getTimeInMillis());

        return tmsp;
    }

    public static String formatDate(String format, Timestamp date) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date.getTime());
    }


    public static Timestamp toTimestamp(boolean fromDateFlg, String txtYear, String txtMonth, String txtDay,
                                        String txtHour, String txtMin, String txtSec) {


        int[] editBaseDate = editBaseDate(fromDateFlg, txtYear, txtMonth, txtDay, txtHour, txtMin, txtSec);

        Calendar cal = Calendar.getInstance();

        cal.setLenient(false);
        cal.set(editBaseDate[0], editBaseDate[1] - 1, editBaseDate[2], editBaseDate[3], editBaseDate[4], editBaseDate[5]);
        if (fromDateFlg) {
            cal.set(Calendar.MILLISECOND, 0);
        } else {
            cal.set(Calendar.MILLISECOND, 999);
        }

        Timestamp time;
        try {
            time = new Timestamp(cal.getTimeInMillis());
        } catch (IllegalArgumentException e) {

            return null;
        }
        return time;
    }

    public static int[] editBaseDate(boolean fromDateFlg, String txtYear, String txtMonth, String txtDay,
                                     String txtHour, String txtMin, String txtSec) {

        int[] date = new int[6];
        if (fromDateFlg) {
            date[0] = baseStartDay[0];
            date[1] = baseStartDay[1];
            date[2] = baseStartDay[2];
            date[3] = baseStartDay[3];
            date[4] = baseStartDay[4];
            date[5] = baseStartDay[5];
        } else {
            date[0] = baseEndDay[0];
            date[1] = baseEndDay[1];
            date[2] = baseEndDay[2];
            date[3] = baseEndDay[3];
            date[4] = baseEndDay[4];
            date[5] = baseEndDay[5];
        }

        try {
            if (txtYear == null || txtYear.equals("")) {

            } else if (txtMonth == null || txtMonth.equals("")) {
                date[0] = Integer.parseInt(txtYear);
            } else if (txtDay == null || txtDay.equals("")) {
                date[0] = Integer.parseInt(txtYear);
                date[1] = Integer.parseInt(txtMonth);
                if (!fromDateFlg) {

                    int lastDay = getLastDay(date[0], date[1]);
                    date[2] = lastDay;
                }
            } else if (txtHour == null || txtHour.equals("")) {
                date[0] = Integer.parseInt(txtYear);
                date[1] = Integer.parseInt(txtMonth);
                date[2] = Integer.parseInt(txtDay);
            } else if (txtMin == null || txtMin.equals("")) {
                date[0] = Integer.parseInt(txtYear);
                date[1] = Integer.parseInt(txtMonth);
                date[2] = Integer.parseInt(txtDay);
                date[3] = Integer.parseInt(txtHour);
            } else if (txtSec == null || txtSec.equals("")) {
                date[0] = Integer.parseInt(txtYear);
                date[1] = Integer.parseInt(txtMonth);
                date[2] = Integer.parseInt(txtDay);
                date[3] = Integer.parseInt(txtHour);
                date[4] = Integer.parseInt(txtMin);
            } else {
                date[0] = Integer.parseInt(txtYear);
                date[1] = Integer.parseInt(txtMonth);
                date[2] = Integer.parseInt(txtDay);
                date[3] = Integer.parseInt(txtHour);
                date[4] = Integer.parseInt(txtMin);
                date[5] = Integer.parseInt(txtSec);
            }

        } catch (NumberFormatException e) {
            return null;
        }

        return date;
    }

    private static int getLastDay(int year, int month) {


        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);


        int lastDay = cal.getActualMaximum(Calendar.DATE);
        return lastDay;
    }


    private static boolean checkDateNumber(String[] date) {

        ValidateParam validate = new ValidateParam();


        for (int i = 0; i < date.length; i++) {
            if (date[i] != null && !date[i].equals("") && !validate.isInteger(date[i])) {
                return false;
            }
        }


        if ((date[0] != null && !date[0].equals("")) && (Integer.parseInt(date[0]) < 2000 || Integer.parseInt(date[0]) > 2037)) {
            return false;
        }

        if ((date[1] != null && !date[1].equals("")) && (Integer.parseInt(date[1]) < 1 || Integer.parseInt(date[1]) > 12)) {
            return false;
        }

        if ((date[2] != null && !date[2].equals("")) && (Integer.parseInt(date[2]) < 1 || Integer.parseInt(date[2]) > 31)) {
            return false;
        }

        if ((date[3] != null && !date[3].equals("")) && (Integer.parseInt(date[3]) < 0 || Integer.parseInt(date[3]) > 23)) {
            return false;
        }

        if ((date[4] != null && !date[4].equals("")) && (Integer.parseInt(date[4]) < 0 || Integer.parseInt(date[4]) > 59)) {
            return false;
        }

        if ((date[5] != null && !date[5].equals("")) && (Integer.parseInt(date[5]) < 0 || Integer.parseInt(date[5]) > 59)) {
            return false;
        }

        return true;
    }

    public static java.sql.Time getMinuteToTime(int minutes) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, minutes / 60);
        calendar.set(Calendar.MINUTE, minutes - (minutes / 60));
        calendar.set(Calendar.SECOND, 0);

        return new Time(calendar.getTime().getTime());
    }
}