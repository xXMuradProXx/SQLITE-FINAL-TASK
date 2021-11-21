package com.example.sqlitefinaltask;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Utils {
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
                " tbl_student(student_id integer primary key autoincrement, student_name text, student_surname text, student_class_id integer, student_average integer)");

        db.execSQL("create table if not exists" +
                " tbl_class(class_id integer primary key autoincrement, class_name integer, class_teacher text)");

        db.execSQL("create table if not exists" +
                " tbl_teacher(teacher_id integer primary key autoincrement, teacher_name text, teacher_surname text, teacher_profession text)");
    }

    public static void addStudent(Student student, SQLiteDatabase db){
        String name = student.getName();
        String surname = student.getSurname();
        int classId = student.getClassId();
        int avg = student.getAvg();

        db.execSQL("insert into tbl_student values(null, name, surname, classId, avg)");
    }

    public static ArrayList<Student> getStudents(String studentName, SQLiteDatabase db){
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

    public static ArrayList<Student> getStudentsWithHigherAvg(int studentAvg, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student", null);
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



}
