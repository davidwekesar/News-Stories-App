package com.example.newsapp;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The {@link NewsAdapter} creates a list item layout for each news story in the
 * data source (a list of {@link News} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private static final String LOG_TAG = NewsAdapter.class.getName();

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context     of the app.
     * @param newsStories is the list of news stories, which is the data source of the adapter.
     */
    public NewsAdapter(Activity context, ArrayList<News> newsStories) {
        super(context, 0, newsStories);
    }

    /**
     * @return a list item view that displays information about the news story at the given position
     * in the list of news stories
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news story at the given position in the list of news stories.
        News currentNews = getItem(position);

        // Find the TextView with the view ID news_section.
        TextView newsSectionView = listItemView.findViewById(R.id.news_section);
        // Get the news section of the news story.
        String newsSection = currentNews.getNewsSection();
        // Display the section name of the current news in that TextView.
        newsSectionView.setText(newsSection);

        // Find the TextView with the view ID news_title.
        TextView newsTitleView = listItemView.findViewById(R.id.news_title);
        // Get the Title of the news story.
        String newsTitle = currentNews.getNewsTitle();
        // Display the Title of the current news in that TextView.
        newsTitleView.setText(newsTitle);

        // Get the original time from the News object.
        String originalTime = currentNews.getTime();

        // Split the original time string into two parts, based on the "T" and "Z" texts from the
        // original time text of "2019-05-11T05:00:12Z"
        String[] parts = originalTime.split("(T)|(Z)");
        // The date string should be in the format "2019-05-11"
        String date = parts[0];
        // The time string should be in the format "05:00:12"
        String time = parts[1];

        // Change the date format from this "2019-05-11" format...
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
        // ...to this format "Mar 10, 2010"
        SimpleDateFormat ldyFormat = new SimpleDateFormat("LLL dd, yyyy");

        // Format the date string into the format "Mar 10, 2010"
        String outputDateStr = parseDate(date, ymdFormat, ldyFormat);
        Log.i(LOG_TAG, "This is the  current date: " + outputDateStr);

        // Find the TextView with the view ID date.
        TextView dateView = listItemView.findViewById(R.id.date);
        // Display the date of the current news in that TextView.
        dateView.setText(outputDateStr);

        // Change the time format from this "05:00:12" format...
        SimpleDateFormat hmsFormat = new SimpleDateFormat("hh:mm:ss");
        // ...to this format "3.00 PM"
        SimpleDateFormat hmaFormat = new SimpleDateFormat("h:mm a");

        // Format the time string into the format "3.00 PM"
        String outputTimeStr = parseDate(time, hmsFormat, hmaFormat);
        Log.i(LOG_TAG, "This is the current time: " + outputTimeStr);

        // Find the TextView with the view ID time.
        TextView timeView = listItemView.findViewById(R.id.time);
        // Display the time of the current news in that TextView.
        timeView.setText(outputTimeStr);

        // Return the list item view that is now showing the appropriate data.
        return listItemView;
    }

    /**
     * Helper method to change the format of the time and date strings
     */
    public static String parseDate(String inputDateString, SimpleDateFormat inputDateFormat,
                                   SimpleDateFormat outputDateFormat) {
        Date date;
        String outputDateString = null;
        try {
            date = inputDateFormat.parse(inputDateString);
            outputDateString = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateString;
    }
}
