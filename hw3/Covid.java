import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.text.DecimalFormat;

public class Covid
{
    // You can add your own variables between them.
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public int compare(List<String> o1, List<String> o2) {
        return Integer.valueOf(o1.get(4))- Integer.valueOf(o2.get(4));
    }
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
        List<List<String>> onlyCases =  this.rows.stream().filter(x -> x.get(1).equals(location)).filter(x -> x.get(2).equals(date)).collect(Collectors.toList());

        int t = 0;

        for (List<String> onlyCase : onlyCases) {
            t += Integer.valueOf(onlyCase.get(3));
            t -= Integer.valueOf(onlyCase.get(5));
        }
        System.out.printf("Result: %d",t);
    }

    public long getDateCount(String location)
    {
        long toReturn = this.rows.stream().filter(x -> x.get(1).equals(location)).count();

        return toReturn;
    }

    public int getCaseSum(String date)
    {
        int toReturn = 0;
        List<List<String>> ListSum =  this.rows.stream().filter(x -> x.get(2).equals(date)).collect(Collectors.toList());

        for(List<String> sumCase : ListSum){
            toReturn+=Integer.valueOf(sumCase.get(4));
        }

        return toReturn;
    }

    public long getZeroRowsCount(String location) {

        try {
            List<List<String>> zeroRows = this.rows.stream().filter(x -> x.get(3).equals("0")).filter(x -> x.get(4).equals("0"))
                    .filter(x -> x.get(5).equals("0")).filter(x -> x.get(6).equals("0")).filter(x -> x.get(1).equals(location))
                    .collect(Collectors.toList());

            return zeroRows.size();
        }
        catch (Exception i){
            return 0;
        }
    }

    public double getAverageDeath(String location)
    {
        double toReturn = 0;

        List<List<String>> avgList = this.rows.stream().filter(x -> x.get(1).equals(location)).collect(Collectors.toList());


        if(avgList.size()>0) {
            for (List<String> avg : avgList) toReturn += Integer.valueOf(avg.get(6));

            toReturn /= avgList.size();
        }
        toReturn = Double.parseDouble(new DecimalFormat("##.00").format(toReturn));
        return toReturn;
    }

    public String getFirstDeathDayInFirstTenRows(String location) {
        String toReturn = "Not Found";

        try {
        List<String> firstDay = this.rows.stream().filter(x -> x.get(1).equals(location)).limit(10).filter(x -> {
            if (x.get(6).equals("0") == false)
                return true;
            return false;
        }).findFirst().get();


            return firstDay.get(2);
        } catch (Exception i) {
            return toReturn;
        }
    }

    public String[] getDateCountOfAllLocations()
    {
        String[] toReturn = null;
        List<List<String>> uniqueList = this.rows.stream().
                filter(distinctByKey(p -> p.get(0))).collect(Collectors.toList());

        if(uniqueList.size() >0 ) {
            toReturn = new String[uniqueList.size()];
            int i = 0;
            for (List<String> listElem: uniqueList
                 ) {
                String s ="";
                s = s.concat(listElem.get(0));
                s = s.concat(": ");
                s = s.concat(String.valueOf(getDateCount(listElem.get(1))));
                toReturn[i++] = s;
            }
        }
        return toReturn;
    }

    public List<String> getLocationsFirstDeathDay() {
        List<String> toReturn =  new ArrayList<String>();

        List<List<String>> uniqueList = this.rows.stream().
                filter(p -> {
                    if (p.get(5).equals(p.get(6)) && !p.get(5).equals("0")) {
                        return true;
                    }
                    return false;
                }).filter(distinctByKey(p-> p.get(0))).collect(Collectors.toList());


        if (uniqueList.size() > 0) {

            int i = 0;
            for (List<String> listElem : uniqueList
            ) {
                String s = "";
                s = s.concat( listElem.get(1));
                s = s.concat(": ");
                s = s.concat(listElem.get(2));
                toReturn.add(s);
            }
        }
        return toReturn;
    }
    public String trimAndGetMax(String location, int trimCount)
    {
        String toReturn = null;

        List<List<String>> locList = this.rows.stream().filter(p-> p.get(1).equals(location)).collect(Collectors.toList());

        //System.out.printf("\n Before: %s",locList);

        locList.sort(this::compare);

       // System.out.printf("\n After: %s",locList);

        List<List<String>> trimmedLocList = locList.subList(trimCount,locList.size() -trimCount);

        int max = 0;
        for(List<String> elem:trimmedLocList){
            if(max < Integer.valueOf(elem.get(6))) {
                max = Integer.valueOf(elem.get(6));
                toReturn = elem.get(2);
            }
        }

        toReturn = toReturn.concat(": ");
        toReturn = toReturn.concat(String.valueOf(max));

        return toReturn;
    }

    public List<List<String>> getOnlyCaseUpDays(String location)
    {
        List<List<String>> toReturn ;

        toReturn = this.rows.stream().filter(p-> p.get(1).equals(location)).filter(p-> ! p.get(4).equals("0")).collect(Collectors.toList());

        System.out.printf("Result: %s",toReturn.size());
        return toReturn;
    }

    public static void main(String[] args)
    {
        Covid covid = new Covid();

        covid.printOnlyCases("Turkey", "2020-03-20"); // 355

        covid.printOnlyCases("United States", "2020-02-25"); // 53

        System.out.printf("\n45 = %d\n",covid.getDateCount("Turkey")); // 45
        System.out.printf("119  = %d\n",covid.getDateCount("Italy")); // 119

        System.out.printf("2239 = %d\n", covid.getCaseSum("2020-03-05")); // 2239
        System.out.printf("86484 = %d\n",covid.getCaseSum("2020-04-05")); // 86484

        System.out.printf("0 = %d\n",covid.getZeroRowsCount("Turkey"));
        System.out.printf("25 = %d\n",covid.getZeroRowsCount("Australia"));

        System.out.printf("62.33 = %.2f\n",covid.getAverageDeath("Turkey"));
        System.out.printf("223.9 = %.2f\n",covid.getAverageDeath("Italy"));

        System.out.printf("2020-03-19 = %s \n",covid.getFirstDeathDayInFirstTenRows("Turkey"));
        System.out.printf("Not Found = %s \n",covid.getFirstDeathDayInFirstTenRows("Italy"));

        System.out.printf("[\"ABW: 36\", \"AFG: 109\", \"AGO: 37\", \"AIA: 32\", \"ALB: 50\", ...," +
                "\"ZMB: 40\", \"ZWE: 38\"] \n%s\n",Arrays.toString(covid.getDateCountOfAllLocations()));

        System.out.printf("[\"Aruba: 2020-04-16\", \"Afghanistan: 2020-03-24\", Angola: 2020-03-30\", ..., \"Zimbabwe: 2020-03-24\"] \n%s\n", Arrays.toString(covid.getLocationsFirstDeathDay().toArray()));

        System.out.printf("\n [[\"TUR\", \"Turkey\", \"2020-03-12\", \"1\", \"1\", \"0\", \"0\"], [\"TUR\",\n" +
                "\"Turkey\", \"2020-03-13\", \"2\", \"1\", \"0\", \"0\", ..., [\"TUR\", \"Turkey\", \"2020-04-27\", \"110130\",\n" +
                "\"2357\", \"2805\", \"99\"]]\n%s",covid.getOnlyCaseUpDays("Turkey"));

        System.out.printf("\n [[\"ABW\", \"Aruba\", \"2020-03-13\", \"2\", \"2\", \"0\", \"0\"], [\"ABW\"," +
                "\"Aruba\", \"2020-03-20\", \"4\", \"2\", \"0\", \"0\"], ..., [\"ABW\", \"Aruba\", \"2020-04-23\", \"100\"," +
                "\"3\", \"2\", \"0\"]]\n%s",covid.getOnlyCaseUpDays("Aruba"));


        System.out.printf("\n \"2020-04-20 : 127\" %s",covid.trimAndGetMax("Turkey",5  ));


    }
}
