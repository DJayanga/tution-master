package dto;

public class StudentClassDTO {

    private String stu_id;
    private String full_name;
    private String address;
    private String gender;
    private String telephone_no;
    private int class_id;
    private String className;
    private String classYear;

    public StudentClassDTO(String stu_id, String full_name, String address, String gender, String telephone_no, int class_id, String className, String classYear) {
        this.stu_id = stu_id;
        this.full_name = full_name;
        this.address = address;
        this.gender = gender;
        this.telephone_no = telephone_no;
        this.class_id = class_id;
        this.className = className;
        this.classYear = classYear;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone_no() {
        return telephone_no;
    }

    public void setTelephone_no(String telephone_no) {
        this.telephone_no = telephone_no;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    @Override
    public String toString() {
        return "StudentClassDTO{" +
                "stu_id='" + stu_id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", telephone_no='" + telephone_no + '\'' +
                ", class_id=" + class_id +
                ", className='" + className + '\'' +
                ", classYear='" + classYear + '\'' +
                '}';
    }
}
