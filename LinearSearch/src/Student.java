import java.util.Objects;

public class Student {
    private String stuName;
    private int stuNo;
    private String stuClass;

    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if(o==this){
            return true;
        }
        if(o.getClass()!=this.getClass()){
            return false;
        }
        Student another=(Student)o;
        return another.stuName.toLowerCase().equals(this.stuName.toLowerCase());
    }

    private Student(){

    }

    public Student(String stuName, int stuNo, String stuClass) {
        this.stuName = stuName;
        this.stuNo = stuNo;
        this.stuClass = stuClass;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }
}
