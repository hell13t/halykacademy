package kz.halykacademy.dz;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws ParseException {

        Scanner scan = new Scanner(System.in);

        String strDate = scan.next();

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

        Date date = s.parse(strDate);

        scan.close();

        if (!isDateValid(strDate)){
            System.out.println("Ошибка! Вы ввели неверные параметры.");
            System.exit(0);
        }

        System.out.println("-будет через 30 дней:");
        getPochtiMonth(date);

        System.out.println("-будет последним воскресеньем в месяце от этой даты:");
        getLastSunday(strDate);

        System.out.println("-сколько дней составляет между этой датой и Новым Годом 31 декабря того же года:");
        getDaysTillNY(strDate);
    }

    final static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isDateValid(String date){
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static void getPochtiMonth(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 30);
        String newDate = sdf.format(c.getTime());
        System.out.println(newDate);
    }
    public static void getLastSunday(String dt){
        String[] dt1 = dt.split("-");
        int month = Integer.parseInt(dt1[1]);
        int year = Integer.parseInt(dt1[0]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.set( year, month, 1 );
        cal.add(Calendar.DATE, -1);
        cal.add( Calendar.DAY_OF_MONTH, -( cal.get( Calendar.DAY_OF_WEEK ) - 1 ) );
        System.out.println(sdf.format(cal.getTime()));
    }
    public static void getDaysTillNY(String dt) throws ParseException {
        String[] dt1 = dt.split("-");
        String dt2 = dt1[0] + "-01-31";


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dt);
        Date date2 = sdf.parse(dt2);

        long difference = date.getTime() - date2.getTime();
        long daysBetween = (difference / (1000*60*60*24));
        System.out.println(daysBetween);
    }
}