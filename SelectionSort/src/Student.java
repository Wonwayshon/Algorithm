import java.util.Objects;

public class Student implements Comparable<Student> {
    private String stuName;
    private int stuNo;
    private String stuClass;
    private int score;

    @Override
    public int compareTo(Student stu){
//        if(this.score<stu.score){
//            return -1;
//        }else if(this.score==stu.score){
//            return 0;
//        }else{
//            return 1;
//        }
        return this.score-stu.score;
        //return stu.score-this.score;
    }

    @Override
    public String toString(){
        return String.format("Student(name: %s,score: %d)",stuName,score);
    }

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

    public Student(String stuName, int stuNo, String stuClass, int stuScore) {
        this.stuName = stuName;
        this.stuNo = stuNo;
        this.stuClass = stuClass;
        this.score= stuScore;
    }
    public Student(String stuName, int stuScore) {
        this.stuName = stuName;
        this.score= stuScore;
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
