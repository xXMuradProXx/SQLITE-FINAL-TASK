package com.example.sqlitefinaltask;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Utils {
    final static String DATABASE_NAME = "db";

    final static String TABLE_STUDENT_NAME = "tbl_student";
    final static String TABLE_STUDENT_COL_ID = "student_id";
    final static String TABLE_STUDENT_COL_NAME = "student_name";
    final static String TABLE_STUDENT_COL_SURNAME = "student_surname";
    final static String TABLE_STUDENT_COL_CLASS_NAME = "student_class_name";
    final static String TABLE_STUDENT_COL_AVERAGE = "student_average";

    final static String INTENT_KEY_STUDENT_ID = "key_student_id";
    final static String INTENT_KEY_STUDENT_NAME = "key_student_name";
    final static String INTENT_KEY_STUDENT_SURNAME = "key_student_surname";
    final static String INTENT_KEY_STUDENT_CLASS_NAME = "key_student_class_name";
    final static String INTENT_KEY_STUDENT_AVERAGE = "key_student_average";

    final static String TABLE_CLASS_NAME = "tbl_class";
    final static String TABLE_CLASS_COL_ID = "class_id";
    final static String TABLE_CLASS_COL_NAME = "class_name";
    final static String TABLE_CLASS_COL_TEACHER = "class_teacher";

    final static String TABLE_TEACHER_NAME = "tbl_teacher";
    final static String TABLE_TEACHER_COL_ID = "teacher_id";
    final static String TABLE_TEACHER_COL_NAME = "teacher_name";
    final static String TABLE_TEACHER_COL_SURNAME = "teacher_surname";
    final static String TABLE_TEACHER_COL_SUBJECT = "teacher_subject";

    final static String INTENT_KEY_GET_STUDENTS = "get_students";

    final static String INTENT_KEY_GET_STUDENTS_BY_NAME = "get_students_by_name";
    final static String INTENT_KEY_GET_STUDENTS_BY_CLASS = "get_students_by_class";
    final static String INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE = "get_students_by_higher_average";


    public static void createAllTables(SQLiteDatabase db){
        db.execSQL("create table if not exists" +
                " tbl_student(student_id integer primary key autoincrement, student_name text, student_surname text, student_class_name text, student_average integer)");

        db.execSQL("create table if not exists" +
                " tbl_class(class_id integer primary key autoincrement, class_name integer, class_teacher text)");

        db.execSQL("create table if not exists" +
                " tbl_teacher(teacher_id integer primary key autoincrement, teacher_name text, teacher_surname text, teacher_subject text)");
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
        System.out.println(s.getClassName());
        System.out.println(s.getAvg());

        if(s.getId() == 0){
            ContentValues cv = new ContentValues();
            cv.put(Utils.TABLE_STUDENT_COL_NAME, s.getName());
            cv.put(Utils.TABLE_STUDENT_COL_SURNAME, s.getSurname());
            cv.put(Utils.TABLE_STUDENT_COL_CLASS_NAME, s.getClassName());
            cv.put(Utils.TABLE_STUDENT_COL_AVERAGE, s.getAvg());

            db.insert("tbl_student", "student_id", cv);
            //db.execSQL("insert into "+Utils.TABLE_STUDENT_NAME+" values(null, '"+s.getName()+"', '"+s.getSurname()+"', "+s.getClassId()+", "+s.getAvg()+")");
        }
        else {
            db.execSQL("insert into "+Utils.TABLE_STUDENT_NAME+" values("+s.getId()+", '"+s.getName()+"', '"+s.getSurname()+"', '"+s.getClassName()+"', "+s.getAvg()+")");
        }
    }

    public static ArrayList<Student> getStudentsByName(String studentName, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);

            if(studentName.equals(name)){
                int id = cursor.getInt(0);
                String surname = cursor.getString(2);
                String className = cursor.getString(3);
                int avg = cursor.getInt(4);

                Student student = new Student(id, name, surname, className,  avg);
                students.add(student);
            }
        }
        return students;
    }
    public static ArrayList<Student> getStudentsByClass(String studentClassName, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        while(cursor.moveToNext()){
            String className = cursor.getString(3);
            if(className.equals(studentClassName)){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                int avg = cursor.getInt(4);

                Student student = new Student(id, name, surname, className, avg);
                students.add(student);
            }
        }
        return students;
    }


    public static ArrayList<Student> getStudentsByHigherAvg(int studentAvg, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        //Cursor cursor = db.rawQuery("select * from tbl_student where student_average >" + studentAvg, null);
        while(cursor.moveToNext()){
            int avg = cursor.getInt(4);

            if(avg > studentAvg){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                String className = cursor.getString(3);

                Student student = new Student(id, name, surname, className, avg);
                students.add(student);
            }
        }
        return students;
    }

    public static void deleteStudent(int studentId, SQLiteDatabase db){
        db.execSQL("delete from tbl_student where student_id =" +studentId);
        //db.delete("tbl_student", "student_id =" + studentId, null);
    }

    public static void updateStudent(Student student, SQLiteDatabase db){
        int id = student.getId();
        String name = student.getName();
        String surname = student.getSurname();
        String className = student.getClassName();
        int avg = student.getAvg();

        db.execSQL("update tbl_student set student_name ='" +name+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_surname ='" +surname+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_class_name ='" +className+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_average =" +avg+ " where student_id=" + id);
    }

    public static void sortStudentsBySubject(SQLiteDatabase db){
        ArrayList<String> subjects = new ArrayList<>();
        ArrayList<String> teachers = new ArrayList<>();
        ArrayList<Class> classes = new ArrayList<>();


        Cursor cursorSubject = db.rawQuery("select teacher_subject from tbl_teacher", null);
        while(cursorSubject.moveToNext()){
            String subject = cursorSubject.getString(3);
            subjects.add(subject);
        }

        for(int i=0; i < subjects.size(); i++){
            for(int j=1; j < subjects.size(); j++){
                if(subjects.get(i) == subjects.get(j))
                    subjects.remove(j);
            }
        }

        Cursor cursorClass = db.rawQuery("select * from tbl_class", null);
        while(cursorClass.moveToNext()){
            int id = cursorClass.getInt(0);
            String name = cursorClass.getString(1);
            String teacher = cursorClass.getString(2);

            Class c = new Class(id, name, teacher);
            classes.add(c);
        }

        Cursor cursorTeacher = db.rawQuery("select class_teacher from tbl_class", null);
        while(cursorTeacher.moveToNext()){
            String teacher = cursorTeacher.getString(2);
            teachers.add(teacher);
        }

        for(int i=0; i < subjects.size(); i++){
            for(int j=0; j < teachers.size(); j++){

            }

        }
    }

    /*public static void sort(SQLiteDatabase db) {
        ArrayList<Student> students = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String className = cursor.getString(3);
            int avg = cursor.getInt(4);

            Student student = new Student(name, surname, className, avg);
            students.add(student);
        }
        int i=0;
        Class c = null;
        Teacher t = null;
        String subject = t.getSubject();

        for(int i=0; i< students.size(); i++){
            for(int j=0; j < students.size(); j++){
                
            }
        }
    }*/

    public static ArrayList<Teacher> sortTeachersBySubject(SQLiteDatabase db){
        ArrayList<Teacher> teachers = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_teacher", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String subject = cursor.getString(3);

            Teacher teacher = new Teacher(id, name, surname, subject);
            teachers.add(teacher);
        }
        ArrayList<Teacher> sort = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        boolean b = true;
        for(int i=0; i < teachers.size()-1; i++){
            for(int k=0; k < teachers.size(); k++){
                if(!sort.isEmpty() && teachers.get(i).getSubject().equals(sort.get(k).getSubject())){
                    b = false;
                }
            }
            if(!b) {
                sort.add(teachers.get(i));
                for(int j=i+1; j < teachers.size(); j++){
                    if(teachers.get(i).getSubject().equals(teachers.get(j).getSubject())){
                        sort.add(teachers.get(j));
                    }
                }
            }
        }
        return sort;
    }

    public static void addDefaultStudents(SQLiteDatabase db){
        Student s1 = new Student("Murad", "Rahimli", "851", 100);
        Student s2 = new Student("Rafi", "Albagli Zagha", "815", 100);
        Student s3 = new Student("Itai", "Maman", "824", 100);
        Student s4 = new Student("Ashe", "Esha", "587", 14);
        Student s5 = new Student("No one", "One no", "14", 0);
        Student s6 = new Student("Someone", "Enoemos", "463", 58);
        Student s7 = new Student("Doctor Who", "Who Doctor", "1000", 1000);

        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);

        for(Student s : students){
            db.execSQL("insert into tbl_student values(null, '"+s.getName()+"', '"+s.getSurname()+"', "+s.getClassName()+", "+s.getAvg()+")");
        }
    }

    public static void addDefaultTeachers(SQLiteDatabase db){
        Teacher t1 = new Teacher("fdfds", "sdfdfds", "chemistry");
        Teacher t2 = new Teacher("Albagli Zagha", "815", "biology");
        Teacher t3 = new Teacher("Maman", "824", "physics");
        Teacher t4 = new Teacher("Esha", "587", "biology");
        Teacher t5 = new Teacher("One no", "14", "math");
        Teacher t6 = new Teacher("Enoemos", "463", "physics");
        Teacher t7 = new Teacher("Who Doctor", "1000", "physics");

        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(t1);
        teachers.add(t2);
        teachers.add(t3);
        teachers.add(t4);
        teachers.add(t5);
        teachers.add(t6);
        teachers.add(t7);

        for(Teacher t : teachers){
            db.execSQL("insert into tbl_teacher values(null, '"+t.getName()+"', '"+t.getSurname()+"', '"+t.getSubject()+"')");
        }
    }
}
