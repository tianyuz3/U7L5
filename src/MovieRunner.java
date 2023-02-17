import java.util.ArrayList;
    public class MovieRunner
    {
        public static void main(String arg[])
        {

            MovieCollection my = new MovieCollection("src/movies_data.csv");
            MovieCollection myCollection = new MovieCollection("src/movies_data.csv");
            myCollection.menu();
        }
    }

