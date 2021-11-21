package com.example.sqlitefinaltask;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Utils {

    final static String DB_name = "school_db";

    final static String TABLE_NAME_STUDENT = "tbl_student";
    final static String TABLE_STUDENT_COL_ID = "student_id";
    final static String TABLE_STUDENT_COL_NAME = "student_name";
    final static String TABLE_STUDENT_COL_SURNAME = "student_surname";
    final static String TABLE_STUDENT_COL_Class = "student_class";
    final static String TABLE_STUDENT_COL_AVERAGE = "student_average";

    final static String TABLE_NAME_CLASS = "tbl_class";
    final static String TABLE_CLASS_COL_ID = "class_id";
    final static String TABLE_CLASS_COL_NAME = "class_name";
    final static String TABLE_CLASS_COL_TEACHER = "class_teacher";

    final static String TABLE_NAME_TEACHER = "tbl_teacher";
    final static String TABLE_TEACHER_COL_ID = "teacher_id";
    final static String TABLE_TEACHER_COL_NAME = "teacher_name";
    final static String TABLE_TEACHER_COL_SURNAME = "teacher_surname";
    final static String TABLE_TEACHER_COL_SUBJECT = "teacher_subject";


    public static void createTables(SQLiteDatabase db){

        db.execSQL("create table if not exists " + TABLE_NAME_STUDENT + "(" +
                TABLE_STUDENT_COL_ID + " integer primary key autoincrement" + ", " +
                TABLE_STUDENT_COL_NAME + " text" + ", " +
                TABLE_STUDENT_COL_SURNAME + " text" + ", " +
                TABLE_STUDENT_COL_Class + " text " + ", " +
                TABLE_STUDENT_COL_AVERAGE + " real" + ")" );

        db.execSQL("create table if not exists " + TABLE_NAME_CLASS + "(" +
                TABLE_CLASS_COL_ID + " integer primary key autoincrement" + ", " +
                TABLE_CLASS_COL_NAME + " text" + ", " +
                TABLE_CLASS_COL_TEACHER + " text" + ")" );

        db.execSQL("create table if not exists " + TABLE_NAME_TEACHER + "(" +
                TABLE_TEACHER_COL_ID + " integer " + ", " +
                TABLE_TEACHER_COL_NAME + " text" + ", " +
                TABLE_TEACHER_COL_SURNAME + " text" + ", " +
                TABLE_TEACHER_COL_SUBJECT + " text " + ")");

    }
    public static void resetTables(SQLiteDatabase db){
        db.execSQL("drop table " + Utils.TABLE_NAME_STUDENT);
        db.execSQL("drop table " + Utils.TABLE_NAME_CLASS);
        db.execSQL("drop table " + Utils.TABLE_NAME_TEACHER);
        createTables(db);
    }

    public static void addStudent(Student s, SQLiteDatabase db){
        db.execSQL("insert into " + TABLE_NAME_STUDENT + " values( null, '"+
                s.getName() + "', '" +
                s.getSurname() + "', '" +
                s.getSt_class() + "', " +
                s.getAvg() + ")"  );
    }

    @SuppressLint("range")
    public static ArrayList<Student> getStudent(String name, SQLiteDatabase db){
        String query = "select * from " + Utils.TABLE_NAME_STUDENT + " where " + Utils.TABLE_STUDENT_COL_NAME + " = '" + name + "'";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Student> students= new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_ID));
            String st_name = cursor.getString(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_NAME));
            String st_surname = cursor.getString(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_SURNAME));
            String st_class = cursor.getString(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_Class));
            double st_average = cursor.getDouble(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_AVERAGE));

            Student tmp = new Student(id, st_name, st_surname, st_class, st_average);
            students.add(tmp);
        }

        return students;

    }

    @SuppressLint("range")
    public static ArrayList<Student> getHigherAvg(double avg, SQLiteDatabase db){
        String query = "select * from " + Utils.TABLE_NAME_STUDENT + " where " + TABLE_STUDENT_COL_AVERAGE + " > " + avg;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Student> students= new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_ID));
            String st_name = cursor.getString(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_NAME));
            String st_surname = cursor.getString(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_SURNAME));
            String st_class = cursor.getString(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_Class));
            double st_average = cursor.getDouble(cursor.getColumnIndex(Utils.TABLE_STUDENT_COL_AVERAGE));

            Student tmp = new Student(id, st_name, st_surname, st_class, st_average);
            students.add(tmp);
        }

        return students;

    }

    public static void deleteStudent(int id, SQLiteDatabase db){
        db.delete(Utils.TABLE_NAME_STUDENT, Utils.TABLE_STUDENT_COL_ID + " = " + id, null);
    }

    public static void updateStudent(Student s, SQLiteDatabase db){

    }









}
