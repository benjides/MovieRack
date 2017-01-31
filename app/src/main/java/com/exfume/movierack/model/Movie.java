package com.exfume.movierack.model;



import java.util.Calendar;
import java.util.Date;


public class Movie  {

    public int id;
    public String title;
    private Date release_date;
    private String poster_path;


    public int getYear()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.release_date);
        return cal.get(Calendar.YEAR);
    }

    public String getPoster()
    {
        return "https://image.tmdb.org/t/p/w780/"+this.poster_path;
    }



}
