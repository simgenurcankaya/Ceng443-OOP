import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.util.Arrays;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Covid
{
  // You can add your own variables between them.

  // You can add your own variables between them.

  // You must not change between them.
  private List<List<String>> rows;

  public Covid()
  {
    try
    {
      this.rows = Files
  				.lines(Paths.get("covid19.csv"))
  				.map(row -> Arrays.asList(row.split(",")))
  				.collect(Collectors.toList());
    }
    catch (IOException e)
    {
			e.printStackTrace();
		}
  }
  // You must not change between them.

  public void printOnlyCases(String location, String date)
  {

  }

  public long getDateCount(String location)
  {
    long toReturn = 0;

    return toReturn;
  }

  public int getCaseSum(String date)
  {
    int toReturn = 0;

    return toReturn;
  }

  public long getZeroRowsCount(String location)
  {
    long toReturn = 0;

    return toReturn;
  }

  public double getAverageDeath(String location)
  {
    double toReturn = 0;

    toReturn = Double.parseDouble(new DecimalFormat("##.00").format(toReturn));
    return toReturn;
  }

  public String getFirstDeathDayInFirstTenRows(String location)
  {
    String toReturn = null;

    return toReturn;
  }

  public String[] getDateCountOfAllLocations()
  {
    String[] toReturn = null;

    return toReturn;
  }

  public List<String> getLocationsFirstDeathDay()
  {
    List<String> toReturn = null;

    return toReturn;
  }

  public String trimAndGetMax(String location, int trimCount)
  {
    String toReturn = null;

    return toReturn;
  }

  public List<List<String>> getOnlyCaseUpDays(String location)
  {
    List<List<String>> toReturn = null;

    return toReturn;
  }

  public static void main(String[] args)
  {
    // You can test your function in here.
    // This part is not graded.
  }
}
