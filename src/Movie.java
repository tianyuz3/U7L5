/**
 * Class that represents a single Movie object
 */
public class Movie
{
    private String title;
    private String cast;
    private String director;
    private String tagline;
    private String keywords;
    private String overview;
    private int runtime;
    private String genres;
    private double userRating;
    private int year;
    private int revenue;

    public Movie(String title, String cast, String director, String tagline,
                 String keywords, String overview, int runtime, String genres,
                 double userRating, int year, int revenue)
    {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.tagline = tagline;
        this.keywords = keywords;
        this.overview = overview;
        this.runtime = runtime;
        this.genres = genres;
        this.userRating = userRating;
        this.year = year;
        this.revenue = revenue;
    }

    public String getTitle()
    {
        return title;
    }

    public String getCast()
    {
        return cast;
    }

    public String getDirector()
    {
        return director;
    }

    public String getTagline()
    {
        return tagline;
    }

    public String getKeywords()
    {
        return keywords;
    }

    public String getOverview()
    {
        return overview;
    }

    public int getRuntime()
    {
        return runtime;
    }

    public String getGenres()
    {
        return genres;
    }

    public double getUserRating()
    {
        return userRating;
    }

    public int getYear()
    {
        return year;
    }

    public int getRevenue()
    {
        return revenue;
    }

    public String toString()
    {
        String t = "Title: " + title + "\n";
        String tag = "Tagline: " + tagline + "\n";
        String r = "Runtime: " + runtime + "\n";
        String y = "Year: " + year + "\n";
        String d = "Directed by: " + director + "\n";
        String c = "Cast: " + cast + "\n";
        String o = "Overview: " + overview + "\n";
        String u = "User rating: " + userRating + "\n";
        String b = "Box office revenue: " + revenue + "\n";
        return t + tag + r + y + d + c + o + u + b;
    }

}