package com.example.newsapp;

public class News {

    // Section name of the news article.
    private String mNewsSection;

    // Title of the news article.
    private String mNewsTitle;

    // Date and time of the news article.
    private String mTime;

    // Website URL of the news article.
    private String mUrl;

    /**
     * Constructs a new {@link News} object.
     *
     * @param newsSection is the section name of the news article(.e.g Politics, Economy e.t.c).
     * @param newsTitle   is the title of the news article.
     * @param time        is the date and time when the news article was released.
     * @param url         is the website URL to find more details about the news article.
     */
    public News(String newsSection, String newsTitle, String time, String url) {
        mNewsSection = newsSection;
        mNewsTitle = newsTitle;
        mTime = time;
        mUrl = url;
    }

    /**
     * @return the News Section of the news article.
     */
    public String getNewsSection() {
        return mNewsSection;
    }

    /**
     * @return the News Title of the news article.
     */
    public String getNewsTitle() {
        return mNewsTitle;
    }

    /**
     * @return the Time and Date of the news article.
     */
    public String getTime() {
        return mTime;
    }

    /**
     * @return the URL of the news article.
     */
    public String getUrl() {
        return mUrl;
    }
}
