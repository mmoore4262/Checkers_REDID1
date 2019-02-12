package sample;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//This should be called from Runner. Needs to be overloaded by changing the parameter from String[] args to something else.
//Use String{} args to call immediately.
public class CSVutils{
    public static void main(String[] args) {
        String readIn="C:\\Users\\BT_1N3_01\\IdeaProjects\\Checkers\\src\\sample\\Checkers.csv";
        String readIn2="C:\\Users\\BT_1N3_33\\IdeaProjects\\Checkers\\src\\sample\\Checkers.csv";
        String readIn3= "src/sample/Checkers.csv";

        BufferedReader br = null;
        String row = "";
        String col = ",";


        try
        {
            //Buffered Reader only reads. Here is a wiki with all of it's methods. https://www.javatpoint.com/java-bufferedreader-class
            br = new BufferedReader(new FileReader(readIn3));
            //While there is a line with text in it, this loop will trigger.
            while((row=br.readLine())!=null)
            {
                String[] stats=row.split(col);

                System.out.print(stats[0]+" ");
                //Need to make a for loop, to get a every cell in a row.
                for (int i=1; i<stats.length;i++)
                {
                    try {
                        System.out.print(stats[i]+" ");
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        System.out.println("\n");
                        break;
                    }

                }
                System.out.println("\n");

            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Hey");
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println("Hey");
            e.printStackTrace();
        }

    }
    }