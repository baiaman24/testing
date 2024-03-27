package co.wedevx.digitalbank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MockData {
    private FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-US"), new RandomService());

    public Map<String, String> generateRandomUser(){
        String title = getRandomTitle();
        String firstName = fakeValuesService.bothify(new Faker().name().firstName());
        String lastName = fakeValuesService.bothify(new Faker().name().lastName());
        String gender = fakeValuesService.bothify(new Faker().bool().bool() ? "M" : "F");

        String dateOfBirth = formatDateOfBirth(String.valueOf(new Faker().date().birthday()));
        String ssn = fakeValuesService.bothify(new Faker().idNumber().ssnValid());
        String email = fakeValuesService.bothify(new Faker().internet().emailAddress());
        String password = fakeValuesService.bothify(new Faker().internet().password(8, 12, true,true));
        String streetAddress = fakeValuesService.bothify(new Faker().address().streetAddress());
        String locality = fakeValuesService.bothify(new Faker().address().city());
        String region = fakeValuesService.bothify(new Faker().address().stateAbbr());
        String postalCode = fakeValuesService.bothify(new Faker().address().zipCode());
        String country = fakeValuesService.bothify(new Faker().address().country());
        String homePhone = fakeValuesService.bothify(new Faker().phoneNumber().cellPhone());
        String mobilePhone = fakeValuesService.bothify(new Faker().phoneNumber().cellPhone());
        String workPhone = fakeValuesService.bothify(new Faker().phoneNumber().cellPhone());

        Map<String, String> fakeData = new HashMap<>();
        fakeData.put("title", title);
        fakeData.put("firstName", firstName);
        fakeData.put("lastName", lastName);
        fakeData.put("gender", gender);
        fakeData.put("dob", dateOfBirth);
        fakeData.put("ssn", ssn);
        fakeData.put("email", email);
        fakeData.put("password", password);
        fakeData.put("address", streetAddress);
        fakeData.put("locality", locality);
        fakeData.put("region", region);
        fakeData.put("postalCode", postalCode);
        fakeData.put("country", country);
        fakeData.put("homePhone", homePhone);
        fakeData.put("mobilePhone",mobilePhone);
        fakeData.put("workPhone", workPhone);

        return fakeData;
    }
    private String getRandomTitle() {
        String[] titles = {"Mrs.", "Mr.", "Ms."};
        return titles[new Random().nextInt(titles.length)];
    }

    private String formatDateOfBirth(String dateOfBirth) {
        String day = dateOfBirth.substring(8, 10);
        String month = dateOfBirth.substring(4,7);
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for(int i = 0; i < months.length; i++){
            if(months[i].equals(month)){
                month = String.format("%02d", i+1);
                break;
            }
        }
        String year = dateOfBirth.substring(dateOfBirth.length() - 4,dateOfBirth.length());
        String formattedDOB = month + "/" + day + "/" + year;
        return formattedDOB;
    }
    public Map<String, String> generateRandomEmailAndName(){
        Map<String, String> fakeData = new HashMap<>();
        String name = fakeValuesService.bothify(new Faker().name().firstName());
        String secondName = fakeValuesService.bothify(new Faker().name().lastName());
        int number = new Random().nextInt(1000);
        String email = name + number + "@gmail.com";
        fakeData.put("name", name);
        fakeData.put("secondName", secondName);
        fakeData.put("email", email);
        return fakeData;
    }

    public String generateRandomEmail(){
        String email = fakeValuesService.bothify(new Faker().name().firstName() + "####@gmail.com");
        return email;
    }

    public String generateRandomSsn(){
        String ssn = String.format("%09d", new Random().nextInt(1000000000));
        return ssn;
    }
}
