import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.*;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    public void removeDuplicate(ArrayList<String> list){
        ArrayList<String> newList = new ArrayList<String>();

        for(String sub : list){
            if(!newList.contains(sub)){
                newList.add(sub);
            }
        }

            for(int i = 0; i<list.size() ; i++){
                list.remove(i);
                i--;
            }
            for(int i = 0 ; i<newList.size() ; i++ ){
                list.add(newList.get(i));

            }
        }



    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        ArrayList<String> castList = new ArrayList<String>();
        ArrayList <String> match = new ArrayList<String>();
        ArrayList<Movie> castMovie = new ArrayList<Movie>();
        // add every cast member into the castList
        for(int i = 0; i < movies.size() ; i++){
            String [] list = movies.get(i).getCast().split("\\|");
            for(int k = 0; k<list.length ; k++){
                castList.add(list[k]);
            }
        }
        // remove all the duplicate in the castList
        removeDuplicate(castList);
        // Ask input for a cast member
        System.out.println("Enter a cast member: ");
    String s = scanner.nextLine();
        s = s.toLowerCase();
        // Add every cast member to the match list that matches the input
        for(int r = 0; r<castList.size() ; r++){
            if(castList.get(r).toLowerCase().indexOf(s)!=-1){
                match.add(castList.get(r));
            }
        }
        Collections.sort(match);
        // print out every cast member that match the input
        for(int i = 0; i<match.size() ; i++){
            System.out.println(i+1 + ". " + match.get(i));
        }

        //Ask user to select a cast
        System.out.println("Select a cast member:");
        Scanner cN = new Scanner(System.in);
        int castNum = cN.nextInt();
        String cast = match.get(castNum-1);
        cast = cast.toLowerCase();
        //Add all the movies that the cast appeared in to the CastMovie Arraylist
        for(int i = 0 ; i < movies.size() ; i++){
            if(movies.get(i).getCast().toLowerCase().indexOf(cast)!=-1){
                castMovie.add(movies.get(i));
            }
        }
        sortResults(castMovie);
        // print out all the movies that the cast appeared in
        for(int i = 0 ; i<castMovie.size() ; i++){
            System.out.println( i+1 + ". "+ castMovie.get(i).getTitle());
        }
        // takes user's input
        Scanner c = new Scanner(System.in);
        System.out.println("Which movie would like to learn more about?");
        int choice = c.nextInt();
        System.out.println(castMovie.get(choice-1).toString());



    }

    private void searchKeywords()
    {
        System.out.println("Enter a keyword");
        String search = scanner.nextLine();
        search = search.toUpperCase();
        ArrayList<Movie> match = new ArrayList<Movie>();
        for(int i = 0; i<movies.size() ; i++){
            String keyword = movies.get(i).getKeywords();
            keyword = keyword.toUpperCase();
            if(keyword.indexOf(search)!=-1){
                match.add(movies.get(i));
            }
        }
        sortResults(match);
       for(int i = 0; i<match.size() ; i++){
           String key = match.get(i).getKeywords();
           int choiceNUm = i+1;
           System.out.println("" + choiceNUm + ". " + match.get(i).getTitle());
       }
       System.out.println("Which movie would you like to learn more about?");
       Scanner in = new Scanner(System.in);
       int s = in.nextInt();
       if(s>match.size()){
           while(s>match.size()){
               System.out.println("Please enter a correct number");
              s= in.nextInt();
           }
       } else{
           System.out.println(match.get(s-1));
       }
    }

    private void listGenres()
    {
        ArrayList<String> genres = new ArrayList<String>();
        for(int i = 0 ; i< movies.size() ; i++){
           String [] listOfGenres = movies.get(i).getGenres().split("\\|");
           for(int k = 0; k<listOfGenres.length ; k++){
               genres.add(listOfGenres[k]);
           }
        }
        removeDuplicate(genres);
        Collections.sort(genres);
        // print out all the genres without duplicates
        for(int i = 0 ; i<genres.size() ; i++){
            System.out.println( i+1 + ". " + genres.get(i));
        }
        ArrayList<Movie> m = new ArrayList<Movie>();
        System.out.println("Which genre would you like to learn more about?");
        Scanner c = new Scanner(System.in);
        int choice = c.nextInt();
        String selectedGenre = genres.get(choice-1);
        // store all the movies that match the genre
        for(int i = 0 ; i<movies.size() ; i++){
            if(movies.get(i).getGenres().indexOf(selectedGenre)!=-1){
                m.add(movies.get(i));
            }
        }
        sortResults(m);
        for(int i = 0 ; i<m.size() ; i++){
            System.out.println(i+1 + ". " + m.get(i).getTitle());
        }
        System.out.println("Which movie would you like to learn more about?");
        Scanner num = new Scanner(System.in);
        int s = num.nextInt();
        System.out.println(m.get(s-1));
    }

    private void listHighestRated()
    {
        ArrayList<Movie> highestRated = new ArrayList<Movie>();
        ArrayList<Double> rating = new ArrayList<Double>();
        for(int i = 0 ; i<movies.size() ; i++){
      rating.add(movies.get(i).getUserRating());
        }
        for(int i = 0 ; i<50 ; i++){
           int index = rating.indexOf(Collections.max(rating));
           highestRated.add(movies.get(index));
           movies.remove(index);
           rating.remove(index);

        }
        for(int i = 0 ; i<highestRated.size() ; i++){
            System.out.println(i+1 + ". " + highestRated.get(i).getTitle() + ": " + highestRated.get(i).getUserRating());
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Which movie would you like to learn more about?");
        int c = s.nextInt();
        System.out.println(highestRated.get(c-1).toString());
    }

    private void listHighestRevenue()
    {
        ArrayList<Movie> highestRevenue = new ArrayList<Movie>();
        ArrayList<Integer> revenue = new ArrayList<Integer>();
        for(int i = 0 ; i<movies.size() ; i++){
            revenue.add(movies.get(i).getRevenue());
        }
        for(int i = 0 ; i<50 ; i++){
            int index = revenue.indexOf(Collections.max(revenue));
            highestRevenue.add(movies.get(index));
            movies.remove(index);
            revenue.remove(index);
        }
        for(int i = 0 ; i<highestRevenue.size() ; i++){
            System.out.println(i+1 + ". " + highestRevenue.get(i).getTitle() + ": " + highestRevenue.get(i).getRevenue());
        }
        Scanner c = new Scanner(System.in);
        System.out.println("Which movie would you like to learn more about?");
        int choice = c.nextInt();
        System.out.println(highestRevenue.get(choice-1).toString());


    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}