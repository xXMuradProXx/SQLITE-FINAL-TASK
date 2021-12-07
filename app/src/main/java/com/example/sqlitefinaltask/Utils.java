package com.example.sqlitefinaltask;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class Utils {
    final static String DATABASE_NAME = "db2";

    final static String TABLE_STUDENT_NAME = "tbl_student";
    final static String TABLE_STUDENT_COL_ID = "student_id";
    final static String TABLE_STUDENT_COL_FIRST_NAME = "student_first_name";
    final static String TABLE_STUDENT_COL_LAST_NAME = "student_last_name";
    final static String TABLE_STUDENT_COL_CLASS_NAME = "student_class_name";
    final static String TABLE_STUDENT_COL_AVG = "student_avg";
    final static String TABLE_STUDENT_COL_SUBJECT = "student_subject";

    final static String INTENT_KEY_STUDENT_ID = "key_student_id";
    final static String INTENT_KEY_STUDENT_FIRST_NAME = "key_student_first_name";
    final static String INTENT_KEY_STUDENT_LAST_NAME = "key_student_last_name";
    final static String INTENT_KEY_STUDENT_CLASS_NAME = "key_student_class_name";
    final static String INTENT_KEY_STUDENT_AVG = "key_student_avg";
    final static String INTENT_KEY_STUDENT_SUBJECT = "key_student_subject";

    final static String INTENT_KEY_SORTED_STUDENT_IDS = "key_sorted_student_ids";
    final static String INTENT_KEY_SORTED_STUDENT_FIRST_NAMES = "key_sorted_student_first_names";
    final static String INTENT_KEY_SORTED_STUDENT_LAST_NAMES = "key_sorted_student_last_names";
    final static String INTENT_KEY_SORTED_STUDENT_CLASS_NAMES = "key_sorted_student_class_names";
    final static String INTENT_KEY_SORTED_STUDENT_AVGS = "key_sorted_student_avgs";
    final static String INTENT_KEY_SORTED_STUDENT_SUBJECTS = "key_sorted_student_subjects";

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
                TABLE_STUDENT_COL_AVG + " integer " + ", " +
                TABLE_STUDENT_COL_SUBJECT + " text " + ")" );

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
        db.execSQL("drop table if exists " + Utils.TABLE_STUDENT_NAME);
        db.execSQL("drop table if exists " + Utils.TABLE_CLASS_NAME);
        db.execSQL("drop table if exists " + Utils.TABLE_TEACHER_NAME);
    }

    public static void addStudent(Student s, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(Utils.TABLE_STUDENT_COL_ID, s.getId());
        cv.put(Utils.TABLE_STUDENT_COL_FIRST_NAME, s.getFirst_name());
        cv.put(Utils.TABLE_STUDENT_COL_LAST_NAME, s.getLast_name());
        cv.put(Utils.TABLE_STUDENT_COL_CLASS_NAME, s.getClass_name());
        cv.put(Utils.TABLE_STUDENT_COL_AVG, s.getAvg());
        cv.put(Utils.TABLE_STUDENT_COL_SUBJECT, "");

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
            String first_name = cursor.getString(1);

            if(studentName.equals(first_name)){
                int id = cursor.getInt(0);
                String last_name = cursor.getString(2);
                String class_name = cursor.getString(3);
                int avg = cursor.getInt(4);

                Student student = new Student(id, first_name, last_name, class_name,  avg);
                students.add(student);
            }
        }
        cursor.close();

        return students;
    }

    public static ArrayList<Student> showStudentsByHigherAvg(int studentAvg, SQLiteDatabase db){
        ArrayList<Student> students_avg = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_STUDENT_NAME, null);
        while(cursor.moveToNext()){
            int avg = cursor.getInt(4);

            if(avg > studentAvg){
                int id = cursor.getInt(0);
                String first_name = cursor.getString(1);
                String last_name = cursor.getString(2);
                String class_name = cursor.getString(3);

                Student student = new Student(id, first_name, last_name, class_name, avg);
                students_avg.add(student);
            }
        }
        cursor.close();

        return students_avg;
    }

    public static void deleteStudent(int id, SQLiteDatabase db){
        db.delete(Utils.TABLE_STUDENT_NAME, Utils.TABLE_STUDENT_COL_ID + " = " + id, null);
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
        cv.put(Utils.TABLE_STUDENT_COL_SUBJECT, "");

        db.update(Utils.TABLE_STUDENT_NAME, cv, Utils.TABLE_STUDENT_COL_ID + "=" + id, null);
    }

    public static ArrayList<Student> showStudentsByClass(String studentClassName, SQLiteDatabase db){
        ArrayList<Student> students_class = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + Utils.TABLE_STUDENT_NAME, null);

        while(cursor.moveToNext()){
            String class_name = cursor.getString(3);
            if(class_name.equals(studentClassName)){
                int id = cursor.getInt(0);
                String first_name = cursor.getString(1);
                String last_name = cursor.getString(2);
                int avg = cursor.getInt(4);

                Student student = new Student(id, first_name, last_name, class_name, avg);
                students_class.add(student);
            }
        }
        cursor.close();

        return students_class;
    }

    public static void addDefaultSubjects(SQLiteDatabase db){
        Cursor cursor_student = db.rawQuery("select * from " + Utils.TABLE_STUDENT_NAME, null);

        while(cursor_student.moveToNext()) {

            int id = cursor_student.getInt(0);
            String first_name = cursor_student.getString(1);
            String last_name = cursor_student.getString(2);
            String class_name = cursor_student.getString(3);
            int avg = cursor_student.getInt(4);

            Cursor cursor_class = db.rawQuery("select * from " + Utils.TABLE_CLASS_NAME, null);
            String teacher = "none";

            while(cursor_class.moveToNext()){
                if(cursor_class.getString(1).equals(class_name)){
                    teacher = cursor_class.getString(2);
                }
            }
            cursor_class.close();

            Cursor cursor_teacher = db.rawQuery("select * from tbl_teacher", null);
            String subject = "none";

            while(cursor_teacher.moveToNext()){
                if(cursor_teacher.getString(1).equals(teacher)){
                    subject = cursor_teacher.getString(3);
                }
            }
            cursor_teacher.close();

            db.execSQL("update " + Utils.TABLE_STUDENT_NAME + " set " + Utils.TABLE_STUDENT_COL_SUBJECT + " = '" + subject + "' where " + Utils.TABLE_STUDENT_COL_ID + " = " + id);
        }
        cursor_student.close();
    }

    public static ArrayList<Student> sortStudentsBySubject(ArrayList<Student> students, SQLiteDatabase db){

        ArrayList<Student> students_subject = new ArrayList<>();
        for(int i=0; i<students.size(); i++){
            Cursor cursor = db.rawQuery("select * from "+Utils.TABLE_STUDENT_NAME+" where "+Utils.TABLE_STUDENT_COL_ID+" = " + students.get(i).getId(), null);
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String first_name = cursor.getString(1);
            String last_name = cursor.getString(2);
            String class_name = cursor.getString(3);
            int avg = cursor.getInt(4);
            String subject = cursor.getString(5);

            Student s = new Student(id, first_name, last_name, class_name, avg, subject);
            students_subject.add(s);

            cursor.close();
        }

        ArrayList<Student> students_sort = new ArrayList<>();

        while(!students_subject.isEmpty()){
            int i = 0;
            Student tmp = students_subject.remove(0);
            students_sort.add(tmp);
            while(!students_subject.isEmpty() && students_subject.size() > 1 && i<students_subject.size()){
                if(tmp.getSubject().equals(students_subject.get(i).getSubject())){
                    students_sort.add(students_subject.remove(i));
                    i--;
                }
                i++;
            }
        }

        return students_sort;
    }

    public static void DefaultStudents(SQLiteDatabase db){
        Student s1 = new Student("Itai", "Maman", "851", 9000);
        Student s2 = new Student("Muradik", "TheBest", "851", 100);
        Student s3 = new Student("Rafi", "Zagha", "851", 100);
        Student s4 = new Student("Alex", "Georgia", "851", 99);
        Student s5 = new Student("Ash", "Ketchum", "21", 1);


        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);

        String str = "";
        for(Student s : students){
            db.execSQL("insert into tbl_student values(null, '"+s.getFirst_name()+"', '"+s.getLast_name()+"', '"+s.getClass_name()+"', "+s.getAvg()+", '" + str + "')");
        }
    }

    public static void DefaultClasses(SQLiteDatabase db){
        Class c1 = new Class("851", "Ronny");
        Class c2 = new Class("608", "Sara");
        Class c3 = new Class("600", "Didi");
        Class c4 = new Class("99", "Mario");
        Class c5 = new Class("0", "Kofiko");

        ArrayList<Class> classes = new ArrayList<>();
        classes.add(c1);
        classes.add(c2);
        classes.add(c3);
        classes.add(c4);
        classes.add(c5);

        for(Class c : classes){
            db.execSQL("insert into tbl_class values(null, '"+ c.getClass_name()+"', '"+ c.getTeacher_name() +"')");
        }
    }

    public static void DefaultTeachers(SQLiteDatabase db){
        Teacher t1 = new Teacher("Ronny", "Ruben", "Computer Science");
        Teacher t2 = new Teacher("Sara", "Akiva", "English");
        Teacher t3 = new Teacher("Didi", "Vaknin", "Tanach");
        Teacher t4 = new Teacher("Mario", "Mario", "Plumbing");
        Teacher t5 = new Teacher("Kofiko", "Kofikofski", "Kof");

        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(t1);
        teachers.add(t2);
        teachers.add(t3);
        teachers.add(t4);
        teachers.add(t5);

        for(Teacher t : teachers){
            db.execSQL("insert into tbl_teacher values(null, '"+t.getFirst_name()+"', '"+t.getLast_name()+"', '"+t.getSubject()+"')");
        }
    }
}
