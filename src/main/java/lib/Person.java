package lib;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.lang3.StringUtils;

public class Person {

    private Integer csvId;

    private String firstName;

    private String lastName;

    private String company;

    private String email;

    private String address1;

    private String address2;

    private String zip;

    private String city;

    private String stateLong;

    private String state;

    private String phone;

    private String csv;


    public Person(Integer id, String firstName, String lastName, String company, String email, String address1,
                  String address2, String zip, String city, String stateLong, String state, String phone, String csv) {

        this.csvId = id;

        this.firstName = firstName;

        this.lastName = lastName;

        this.company = company;

        this.email = email;

        this.address1 = address1;

        this.address2 = address2;

        this.zip = zip;

        this.city = city;

        this.stateLong = stateLong;

        this.state = state;

        this.phone = phone;

        this.csv = csv;
    }

    public Integer getCsvId() {
        return csvId;
    }

    public String getCsv() {
        return csv;
    }

    public boolean isDuplicate(Person p) {

        DoubleMetaphone dm = new DoubleMetaphone();

        if(!StringUtils.isBlank(this.firstName) && !StringUtils.isBlank(p.firstName) &&
                !StringUtils.isBlank(this.lastName) && !StringUtils.isBlank(p.lastName) &&
                (dm.isDoubleMetaphoneEqual(this.firstName + " " + this.lastName, p.firstName + " " + p.lastName) ||
                StringUtils.getLevenshteinDistance(this.firstName + " " + this.lastName, p.firstName + " " + p.lastName) <= 3)) {
            return true;
        }

        if(!StringUtils.isBlank(this.email) && !StringUtils.isBlank(p.email) && dm.isDoubleMetaphoneEqual(this.email, p.email)) {
            return true;
        }

        if(!StringUtils.isBlank(this.address1) && !StringUtils.isBlank(p.address1) && dm.isDoubleMetaphoneEqual(this.address1, p.address1)) {
            return true;
        }

        if(!StringUtils.isBlank(this.phone) && !StringUtils.isBlank(p.phone) &&
                StringUtils.getLevenshteinDistance(this.phone, p.phone) <= 3) {
            System.out.println(StringUtils.getLevenshteinDistance(this.phone, p.phone));
            System.out.println(this.phone + " " + p.phone);
            return true;
        }

        return false;
    }
}
