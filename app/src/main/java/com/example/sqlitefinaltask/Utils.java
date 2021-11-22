package com.example.sqlitefinaltask;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Utils {
    final static String DATABASE_NAME = "db";

    final static String TABLE_STUDENT_NAME = "tbl_student";
    final static String TABLE_STUDENT_COL_ID = "student_id";
    final static String TABLE_STUDENT_COL_NAME = "student_name";
    final static String TABLE_STUDENT_COL_SURNAME = "student_surname";
    final static String TABLE_STUDENT_COL_CLASS_ID = "student_class_id";
    final static String TABLE_STUDENT_COL_AVERAGE = "student_average";

    final static String TABLE_CLASS_NAME = "tbl_class";
    final static String TABLE_CLASS_COL_ID = "class_id";
    final static String TABLE_CLASS_COL_NAME = "class_name";
    final static String TABLE_CLASS_COL_TEACHER = "class_teacher";

    final static String TABLE_TEACHER_NAME = "tbl_teacher";
    final static String TABLE_TEACHER_COL_ID = "teacher_id";
    final static String TABLE_TEACHER_COL_NAME = "teacher_name";
    final static String TABLE_TEACHER_COL_SURNAME = "teacher_surname";
    final static String TABLE_TEACHER_COL_PROFESSION = "teacher_profession";

    public static void createAllTables(SQLiteDatabase db){
        db.execSQL("create table if not exists" +
                " tbl_student(student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_name text, student_surname text, student_class_id integer, student_average integer)");

        db.execSQL("create table if not exists" +
                " tbl_class(class_id integer primary key autoincrement, class_name integer, class_teacher text)");

        db.execSQL("create table if not exists" +
                " tbl_teacher(teacher_id integer primary key autoincrement, teacher_name text, teacher_surname text, teacher_profession text)");
    }
    public static void deleteAllTables(SQLiteDatabase db){
        db.execSQL("drop table if exists tbl_student");

        db.execSQL("drop table if exists tbl_class");

        db.execSQL("drop table if exists tbl_teacher");
    }

    public static void addStudent(Student s, SQLiteDatabase db){
        System.out.println(s.getId());
        System.out.println(s.getName());
        System.out.println(s.getSurname());
        System.out.println(s.getClassId());
        System.out.println(s.getAvg());

        if(s.getId() == 0){
            ContentValues cv = new ContentValues();
            cv.put(Utils.TABLE_STUDENT_COL_NAME, s.getName());
            cv.put(Utils.TABLE_STUDENT_COL_SURNAME, s.getSurname());
            cv.put(Utils.TABLE_STUDENT_COL_CLASS_ID, s.getClassId());
            cv.put(Utils.TABLE_STUDENT_COL_AVERAGE, s.getAvg());

            db.insert("tbl_student", "student_id", cv);
            //db.execSQL("insert into "+Utils.TABLE_STUDENT_NAME+" values(null, '"+s.getName()+"', '"+s.getSurname()+"', "+s.getClassId()+", "+s.getAvg()+")");
        }
        else {
            db.execSQL("insert into "+Utils.TABLE_STUDENT_NAME+" values("+s.getId()+", '"+s.getName()+"', '"+s.getSurname()+"', "+s.getClassId()+", "+s.getAvg()+")");
        }
    }

    public static ArrayList<Student> getStudentsByName(String studentName, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);

            if(studentName == name){
                int id = cursor.getInt(0);
                String surname = cursor.getString(2);
                int classId = cursor.getInt(3);
                int avg = cursor.getInt(4);

                Student student = new Student(id, name, surname, classId,  avg);
                students.add(student);
            }
        }
        return students;
    }
    public static ArrayList<Student> getStudentsByClass(int classId, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student where student_class_id =" + classId, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            int avg = cursor.getInt(4);

            Student student = new Student(id, name, surname, classId, avg);
            students.add(student);
        }
        return students;
    }


    public static ArrayList<Student> getStudentsWithHigherAvg(int studentAvg, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        //Cursor cursor = db.rawQuery("select * from tbl_student where student_average >" + studentAvg, null);
        while(cursor.moveToNext()){
            int avg = cursor.getInt(4);

            if(avg > studentAvg){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                int classId = cursor.getInt(3);

                Student student = new Student(id, name, surname, classId, avg);
                students.add(student);
            }
        }
        return students;
    }

    public static void deleteStudent(int studentId, SQLiteDatabase db){
        //db.execSQL("delete from tbl_student where student_id =" +studentId);
        db.delete("tbl_student", "student_id =" + studentId, null);
    }

    public static void updateStudent(Student student, SQLiteDatabase db){
        int id = student.getId();
        String name = student.getName();
        String surname = student.getSurname();
        int classId = student.getClassId();
        int avg = student.getAvg();

        db.execSQL("update tbl_student set student_name ='" +name+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_username ='" +surname+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_class_id ='" +classId+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_average ='" +avg+ "' where student_id=" + id);
    }

    public static void addDefaultStudents(SQLiteDatabase db){
        Student s1 = new Student("Murad", "Rahimli", 851, 100);
        Student s2 = new Student("Rafi", "Albagli Zagha", 815, 100);
        Student s3 = new Student("Itai", "Maman", 824, 100);

        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        for(Student s : students){
            db.execSQL("insert into tbl_student values(null, '"+s.getName()+"', '"+s.getSurname()+"', "+s.getClassId()+", "+s.getAvg()+")");
        }
    }
}
