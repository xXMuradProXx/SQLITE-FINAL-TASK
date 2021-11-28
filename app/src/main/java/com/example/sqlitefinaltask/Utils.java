package com.example.sqlitefinaltask;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class Utils {
    final static String DATABASE_NAME = "db";

    final static String TABLE_STUDENT_NAME = "tbl_student";
    final static String TABLE_STUDENT_COL_ID = "student_id";
    final static String TABLE_STUDENT_COL_FIRST_NAME = "student_first_name";
    final static String TABLE_STUDENT_COL_LAST_NAME = "student_last_name";
    final static String TABLE_STUDENT_COL_CLASS_NAME = "student_class_name";
    final static String TABLE_STUDENT_COL_AVG = "student_avg";

    final static String INTENT_KEY_STUDENT_ID = "key_student_id";
    final static String INTENT_KEY_STUDENT_FIRST_NAME = "key_student_first_name";
    final static String INTENT_KEY_STUDENT_LAST_NAME = "key_student_last_name";
    final static String INTENT_KEY_STUDENT_CLASS_NAME = "key_student_class_name";
    final static String INTENT_KEY_STUDENT_AVG = "key_student_avg";

    final static String TABLE_CLASS_NAME = "tbl_class";
    final static String TABLE_CLASS_COL_ID = "class_id";
    final static String TABLE_CLASS_COL_NAME = "class_name";
    final static String TABLE_CLASS_COL_TEACHER = "class_teacher";

    final static String TABLE_TEACHER_NAME = "tbl_teacher";
    final static String TABLE_TEACHER_COL_ID = "teacher_id";
    final static String TABLE_TEACHER_COL_FIRST_NAME = "teacher_first_name";
    final static String TABLE_TEACHER_COL_LAST_NAME = "teacher_last_name";
    final static String TABLE_TEACHER_COL_SUBJECT = "teacher_subject";

    public static void createAllTables(SQLiteDatabase db){

        db.execSQL("create table if not exists " + TABLE_STUDENT_NAME + "(" +
                TABLE_STUDENT_COL_ID + " integer primary key autoincrement" + ", " +
                TABLE_STUDENT_COL_FIRST_NAME + " text" + ", " +
                TABLE_STUDENT_COL_LAST_NAME + " text" + ", " +
                TABLE_STUDENT_COL_CLASS_NAME + " text " + ", " +
                TABLE_STUDENT_COL_AVG + " real" + ")" );

        db.execSQL("create table if not exists " + TABLE_CLASS_NAME + "(" +
                TABLE_CLASS_COL_ID + " integer primary key autoincrement" + ", " +
                TABLE_CLASS_COL_NAME + " text" + ", " +
                TABLE_CLASS_COL_TEACHER + " text" + ")" );

        db.execSQL("create table if not exists " + TABLE_TEACHER_NAME + "(" +
                TABLE_TEACHER_COL_ID + " integer " + ", " +
                TABLE_TEACHER_COL_FIRST_NAME + " text" + ", " +
                TABLE_TEACHER_COL_LAST_NAME + " text" + ", " +
                TABLE_TEACHER_COL_SUBJECT + " text " + ")");

    }

    public static void deleteAllTables(SQLiteDatabase db){
        db.execSQL("drop table " + Utils.TABLE_STUDENT_NAME);
        db.execSQL("drop table " + Utils.TABLE_CLASS_NAME);
        db.execSQL("drop table " + Utils.TABLE_TEACHER_NAME);
    }

    public static void addStudent(Student s, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(Utils.TABLE_STUDENT_COL_ID, s.getId());
        cv.put(Utils.TABLE_STUDENT_COL_FIRST_NAME, s.getFirst_name());
        cv.put(Utils.TABLE_STUDENT_COL_LAST_NAME, s.getLast_name());
        cv.put(Utils.TABLE_STUDENT_COL_CLASS_NAME, s.getClass_name());
        cv.put(Utils.TABLE_STUDENT_COL_AVG, s.getAvg());

        if(s.getId() == 0){
            db.insert("tbl_student", "student_id", cv);
        }
        else {
            db.insert("tbl_student", null, cv);
        }
    }

    public static ArrayList<Student> showStudentsByName(String studentName, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_STUDENT_NAME, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);

            if(studentName.equals(name)){
                int id = cursor.getInt(0);
                String last_name = cursor.getString(2);
                String class_name = cursor.getString(3);
                int avg = cursor.getInt(4);

                Student student = new Student(id, name, last_name, class_name,  avg);
                students.add(student);
            }
        }
        cursor.close();

        return students;
    }

    public static ArrayList<Student> showStudentsByHigherAvg(int studentAvg, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_STUDENT_NAME, null);
        while(cursor.moveToNext()){
            int avg = cursor.getInt(4);

            if(avg > studentAvg){
                int id = cursor.getInt(0);
                String first_name = cursor.getString(1);
                String last_name = cursor.getString(2);
                String class_name = cursor.getString(3);

                Student student = new Student(id, first_name, last_name, class_name, avg);
                students.add(student);
            }
        }
        cursor.close();

        return students;
    }

    public static void deleteStudent(int studentId, SQLiteDatabase db){
        //db.delete(Utils.TABLE_STUDENT_NAME, Utils.TABLE_STUDENT_COL_ID+" =" + studentId, null);
        db.execSQL("delete from tbl_student where student_id =" + studentId);
    }

    public static void updateStudent(Student student, SQLiteDatabase db){
        int id = student.getId();
        String first_name = student.getFirst_name();
        String last_name  = student.getLast_name();
        String class_name = student.getClass_name();
        int avg = student.getAvg();

        ContentValues cv = new ContentValues();
        cv.put(Utils.TABLE_STUDENT_COL_ID, id);
        cv.put(Utils.TABLE_STUDENT_COL_FIRST_NAME, first_name);
        cv.put(Utils.TABLE_STUDENT_COL_LAST_NAME, last_name);
        cv.put(Utils.TABLE_STUDENT_COL_CLASS_NAME, class_name);
        cv.put(Utils.TABLE_STUDENT_COL_AVG, avg);

        db.update(Utils.TABLE_STUDENT_NAME, cv, Utils.TABLE_STUDENT_COL_ID + "=" + id, null);
    }

    public static ArrayList<Student> showStudentsByClass(String studentClassName, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_STUDENT_NAME, null);

        while(cursor.moveToNext()){
            String class_name = cursor.getString(3);
            if(class_name.equals(studentClassName)){
                int id = cursor.getInt(0);
                String first_name = cursor.getString(1);
                String last_name = cursor.getString(2);
                int avg = cursor.getInt(4);

                Student student = new Student(id, first_name, last_name, class_name, avg);
                students.add(student);
            }
        }
        cursor.close();

        return students;
    }

    public static void addDefaultStudents(SQLiteDatabase db){
        Student s1 = new Student("Itai", "Maman", "851", 9000);
        Student s2 = new Student("Rafi", "Zagha", "851", 100);
        Student s3 = new Student("Muradik", "TheBest", "851", 100);
        Student s4 = new Student("Alex", "Georgia", "851", 99);
        Student s5 = new Student("Ash", "Ketchum", "14", 1);


        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);


        for(Student s : students){
            db.execSQL("insert into tbl_student values(null, '"+s.getFirst_name()+"', '"+s.getLast_name()+"', "+s.getClass_name()+", "+s.getAvg()+")");
        }
    }

}
