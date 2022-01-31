package dto;

public class ClassDescriptionDTO {
    private String className;
    private String yearName;
    private int count;

    public ClassDescriptionDTO(String className, String yearName, int count) {
        this.className = className;
        this.yearName = yearName;
        this.count = count;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ClassDescriptionDTO{" +
                "className='" + className + '\'' +
                ", yearName='" + yearName + '\'' +
                ", count=" + count +
                '}';
    }
}
