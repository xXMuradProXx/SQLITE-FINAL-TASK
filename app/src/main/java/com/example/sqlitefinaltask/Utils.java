package com.example.sqlitefinaltask;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Utils {
    static boolean CHECKED = false;

    final static String DATABASE_NAME = "db4";

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

    final static String TABLE_SUBJECT_NAME = "tbl_subject";
    final static String TABLE_SUBJECT_COL_STUDENT_ID = "student_id";
    final static String TABLE_SUBJECT_COL_STUDENT_NAME = "student_name";
    final static String TABLE_SUBJECT_COL_STUDENT_SURNAME = "student_surname";
    final static String TABLE_SUBJECT_COL_CLASS_NAME = "student_class_name";
    final static String TABLE_SUBJECT_COL_AVERAGE = "student_average";
    final static String TABLE_SUBJECT_COL_SUBJECT = "student_subject";


    final static String INTENT_KEY_GET_STUDENTS = "get_students";

    final static String INTENT_KEY_GET_STUDENTS_BY_NAME = "get_students_by_name";
    final static String INTENT_KEY_GET_STUDENTS_BY_NAME_STUDENT_NAME = "get_students_by_name_student_name";

    final static String INTENT_KEY_GET_STUDENTS_BY_CLASS = "get_students_by_class";
    final static String INTENT_KEY_GET_STUDENTS_BY_CLASS_STUDENT_CLASS_NAME = "get_students_by_class_student_class_name";

    final static String INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE = "get_students_by_higher_average";
    final static String INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE_STUDENT_AVERAGE = "get_students_by_higher_average_student_average";

    final static String INTENT_KEY_SHOW_ID = "to show student_id";

    final static String INTENT_KEY_STUDENT = "students";

    final static String INTENT_KEY_TEACHER = "teacher";




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

    public static void resetSubjectTable(SQLiteDatabase db){
        db.execSQL("drop table if exists tbl_subject");

        db.execSQL("create table if not exists" +
                " tbl_subject(student_id integer, student_name text, student_surname text, student_class_name text, student_average integer, subject text)");
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
        cursor.close();
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
        cursor.close();
        return students;
    }

    public static ArrayList<Student> getStudentsByClassv2(String studentClassName, SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student where student_class_name =" + studentClassName, null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String surname = cursor.getString(2);
        int avg = cursor.getInt(4);

        Student student = new Student(id, name, surname, studentClassName, avg);
        students.add(student);

        cursor.close();
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
        cursor.close();
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

        db.execSQL("update tbl_student set student_name = '" +name+ "' where student_id = " + id);
        db.execSQL("update tbl_student set student_surname = '" +surname+ "' where student_id = " + id);
        db.execSQL("update tbl_student set student_class_name = '" +className+ "' where student_id = " + id);
        db.execSQL("update tbl_student set student_average = " +avg+ " where student_id = " + id);
    }

    public static void addDefaultSubjects(SQLiteDatabase db){
        Cursor cursorStudent = db.rawQuery("select * from tbl_student", null);

        while(cursorStudent.moveToNext()) {

            int id = cursorStudent.getInt(0);
            String name = cursorStudent.getString(1);
            String surname = cursorStudent.getString(2);
            String className = cursorStudent.getString(3);
            int avg = cursorStudent.getInt(4);

            Log.d("check", "id: " + id +" class: " + className);

            Cursor cursorClass = db.rawQuery("select * from tbl_class", null);
            String teacher = "-";

            while(cursorClass.moveToNext()){
                Log.d("check", "entered to first while");
                if(cursorClass.getString(1).equals(className)){
                    Log.d("check", "Same class_name found");
                    teacher = cursorClass.getString(2);
                    break;
                }
            }
            cursorClass.close();
            Log.d("check", "class: " + className + " teacher: " + teacher);

            Cursor cursorTeacher = db.rawQuery("select * from tbl_teacher", null);
            String subject = "-";
            while(cursorTeacher.moveToNext()){
                if(cursorTeacher.getString(1).equals(teacher)){
                    subject = cursorTeacher.getString(3);
                    break;
                }
            }
            cursorTeacher.close();

            Log.d("check", "teacher: " + teacher +"\n subject: " + subject);

            db.execSQL("insert into tbl_subject values("+id+", '"+name+"', '"+surname+"', '"+className+"', "+avg+", '"+subject+"')");
            Log.d("check", "--------------------------------------------------------------");
        }
        cursorStudent.close();
    }

    public static void addDefaultSubjectsv2(SQLiteDatabase db){
        Cursor cursorStudent = db.rawQuery("select * from tbl_student", null);

        while(cursorStudent.moveToNext()) {

            int id = cursorStudent.getInt(0);
            String name = cursorStudent.getString(1);
            String surname = cursorStudent.getString(2);
            String className = cursorStudent.getString(3);
            int avg = cursorStudent.getInt(4);

            String teacher;
            try {
                Cursor cursorClass = db.rawQuery("select class_teacher from tbl_class where class_name = " + className, null);
                cursorClass.moveToNext();
                teacher = cursorClass.getString(0);
                cursorClass.close();
            } catch(Exception e) {
                e.printStackTrace();
                teacher = "-";
            }

            String subject;
            try {
                Cursor cursorTeacher = db.rawQuery("select teacher_subject from tbl_teacher where teacher_name = '" + teacher +"'", null);
                cursorTeacher.moveToNext();
                subject = cursorTeacher.getString(0);
                cursorTeacher.close();
            } catch(Exception e) {
                e.printStackTrace();
                subject = "-";
            }

            db.execSQL("insert into tbl_subject values("+id+", '"+name+"', '"+surname+"', '"+className+"', "+avg+", '"+subject+"')");
        }
        cursorStudent.close();
    }

    public static void addDefaultSubjectsv3(SQLiteDatabase db){
        Cursor cursorStudent = db.rawQuery("select * from tbl_student", null);

        while(cursorStudent.moveToNext()) {

            int id = cursorStudent.getInt(0);
            String name = cursorStudent.getString(1);
            String surname = cursorStudent.getString(2);
            String className = cursorStudent.getString(3);
            int avg = cursorStudent.getInt(4);

            Log.d("check", "id: " + id +" class: " + className);

            String teacher;
            try {
                Cursor cursorClass = db.rawQuery("select * from tbl_class where class_name = " + className, null);
                cursorClass.moveToNext();
                teacher = cursorClass.getString(2);
                cursorClass.close();
            } catch(Exception e) {
                e.printStackTrace();
                teacher = "-";
            }

            Log.d("check", "class: " + className + " teacher: " + teacher);

            String subject;
            try {
                Cursor cursorTeacher = db.rawQuery("select * from tbl_teacher where teacher_name = '" + teacher +"'", null);
                cursorTeacher.moveToNext();
                subject = cursorTeacher.getString(3);
                cursorTeacher.close();
            } catch(Exception e) {
                e.printStackTrace();
                subject = "-";
            }

            Log.d("check", "teacher: " + teacher + " subject: " + subject);

            db.execSQL("insert into tbl_subject values("+id+", '"+name+"', '"+surname+"', '"+className+"', "+avg+", '"+subject+"')");
            Log.d("check", "--------------------------------------------------------------");
        }
        cursorStudent.close();
    }

    public static ArrayList<Student> addSubjectToStudents(ArrayList<Student> students, SQLiteDatabase db){

        ArrayList<Student> added = new ArrayList<>();
        for(int i=0; i<students.size(); i++){
            Log.d("check", "the id " + students.get(i).getId());
            Cursor cursor = db.rawQuery("select * from tbl_subject where student_id =" + students.get(i).getId(), null);
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String className = cursor.getString(3);
            int avg = cursor.getInt(4);
            String subject = cursor.getString(5);

            Student s = new Student(id, name, surname, className, avg, subject);
            added.add(s);

            cursor.close();
        }

        return added;
    }

    public static ArrayList<Student> sortStudents(ArrayList<Student> students){
        ArrayList<Student> sorted = new ArrayList<>();

        while(!students.isEmpty()){
            int j = 0;
            Student tmp = students.remove(0);
            sorted.add(tmp);
            while(!students.isEmpty() && students.size() > 1 && j<students.size()){
                if(tmp.getSubject().equals(students.get(j).getSubject())){
                    sorted.add(students.remove(j));
                    j--;
                }
                j++;
            }
        }

        return sorted;
    }

    public static ArrayList<Teacher> sortTeachers(ArrayList<Teacher> teachers){
        ArrayList<Teacher> sorted = new ArrayList<>();

        while(!teachers.isEmpty()){
            int j = 0;
            Teacher tmp = teachers.remove(0);
            sorted.add(tmp);
            while(!teachers.isEmpty() && teachers.size() > 1 && j<teachers.size()){
                if(tmp.getSubject().equals(teachers.get(j).getSubject())){
                    sorted.add(teachers.remove(j));
                    j--;
                }
                j++;
            }
        }

        return sorted;
    }

    public static void addDefaultStudents(SQLiteDatabase db){
        Student s1 = new Student("Murad", "Rahimli", "851", 100);
        Student s2 = new Student("Itai", "Maman", "852", 100);
        Student s3 = new Student("Ashe", "Esha", "844", 14);
        Student s4 = new Student("Rafi", "Albagli Zagha", "815", 100);
        Student s5 = new Student("Someone", "Enoemos", "120", 58);
        Student s6 = new Student("No one", "One no", "844", 0);
        Student s7 = new Student("Doctor Who", "Who Doctor", "582", 1000);

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

    public static void addDefaultClasses(SQLiteDatabase db){
        Class c1 = new Class("851", "Ronny");
        Class c2 = new Class("120", "Noga");
        Class c3 = new Class("121", "Irena");
        Class c4 = new Class("122", "Inna");
        Class c5 = new Class("815", "Nizan");
        Class c6 = new Class("852", "Vachtang");
        Class c7 = new Class("844", "Oleg");

        ArrayList<Class> classes = new ArrayList<>();
        classes.add(c1);
        classes.add(c2);
        classes.add(c3);
        classes.add(c4);
        classes.add(c5);
        classes.add(c6);
        classes.add(c7);

        for(Class c : classes){
            db.execSQL("insert into tbl_class values(null, '"+ c.getName()+"', '"+ c.getTeacher() +"')");
        }
    }

    public static void addDefaultTeachers(SQLiteDatabase db){
        Teacher t1 = new Teacher("Noga", "Chanani", "mathematics");
        Teacher t2 = new Teacher("Nizan", "Karasenti", "computer science");
        Teacher t3 = new Teacher("Vachtang", "Zinzandze", "physics");
        Teacher t4 = new Teacher("Inna", "Mayorova", "mathematics");
        Teacher t5 = new Teacher("Oleg", "Davidovich", "physics");
        Teacher t6 = new Teacher("Ronny", "Ruben", "computer science");
        Teacher t7 = new Teacher("Irena", "Obodobskiy", "mathematics");

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

    public static boolean ifClassExists(String className, SQLiteDatabase db){
        Cursor cursor = db.rawQuery("select class_name from tbl_class", null);
        while(cursor.moveToNext()){
            if(cursor.getString(0).equals(className))
                return true;
        }
        cursor.close();
        return false;
    }

    public static Intent defaultIntentToGetStudents(Intent intent, Intent gotten_intent){
        String table = gotten_intent.getStringExtra(INTENT_KEY_GET_STUDENTS);

        intent.putExtra(INTENT_KEY_GET_STUDENTS, table);

        switch(table) {
            case INTENT_KEY_GET_STUDENTS_BY_NAME:
                String name = gotten_intent.getStringExtra(INTENT_KEY_GET_STUDENTS_BY_NAME_STUDENT_NAME);
                intent.putExtra(INTENT_KEY_GET_STUDENTS_BY_NAME_STUDENT_NAME, name);
                break;
            case INTENT_KEY_GET_STUDENTS_BY_CLASS:
                String className = gotten_intent.getStringExtra(INTENT_KEY_GET_STUDENTS_BY_CLASS_STUDENT_CLASS_NAME);
                intent.putExtra(INTENT_KEY_GET_STUDENTS_BY_CLASS_STUDENT_CLASS_NAME, className);
                break;
            case INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE:
                int avg = gotten_intent.getIntExtra(INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE_STUDENT_AVERAGE, 0);
                intent.putExtra(INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE_STUDENT_AVERAGE, avg);
                break;
        }

        return intent;
    }

    public static ArrayList<Student> SQLPractice(SQLiteDatabase db){
        ArrayList<Student> students = new ArrayList<>();

        db.execSQL("select * from tbl_student");

        db.execSQL("select * from tbl_student where student_average =" + 100);

        db.execSQL("select * from tbl_student where student_average =" + 100 + "and student_class_name = '" + 844 + "'");

        db.execSQL("select * from tbl_student where student_class_name like '%" + 4 + "'");

        db.execSQL("select student_id, student_name from tbl_student where student_average =" + 100);

        db.execSQL("select * from tbl_student where student_class_name is null");

        db.execSQL("update tbl_student set student_average =" + 100 + " where student_id =" + 5);

        db.execSQL("update tbl_student set student_class_name ='" + 800 + "' where student_class_name is null ");

        db.execSQL("select * from tbl_student where student_class_name = '" + 844 + "' and student_class_name = " + 0 + " or " + 100);

        db.execSQL("update tbl_student set student_average =" + 1000 + " where student_class_name ='" + 851+ "' or student_class_name ='" + 815+ "'");

        db.execSQL("update tbl_student set student_average = student_average + " + 100 + " where student_id = " + 1);



        return students;
    }

}
