package dto;

public class ClassDTO {
    private int classID;
    private String className;
    private String classYear;

    public ClassDTO(int classID, String className, String classYear) {
        this.classID = classID;
        this.className = className;
        this.classYear = classYear;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
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
        return className;
    }
}
