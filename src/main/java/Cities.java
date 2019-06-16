import entity.City;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Cities {
    private static final String PATH_FILE = "WEB-INF/cities500.txt";
    public static final long MAX_LIMIT = 30;

    static List<City> loadCities(InputStream input){

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        return reader.lines().map(line -> {
            City city = new City();
            city.setName(line.split("\\t")[1]);
            city.setAlternateNames(line.split("\\t")[3]);
            city.setId(Long.parseLong(line.split("\\t")[0]));
            city.setAsciiName(line.split("\\t")[2]);
            city.setLatitude(Float.parseFloat(line.split("\\t")[4]));
            city.setLongitude(Float.parseFloat(line.split("\\t")[5]));
            city.setCode(line.split("\\t")[8]);
            city.setPopulation(Integer.parseInt(line.split("\\t")[14]));
            city.setTimezone(line.split("\\t")[17]);
            return city;
        }).collect(Collectors.toList());
    }

    static String getPathFile() {
        return PATH_FILE;
    }
}
