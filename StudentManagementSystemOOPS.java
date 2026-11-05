import java.util.*;

public class StudentManagementSystemOOPS {
  static class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;

    }

    public int getAge() {
      return age;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }

  static class Student extends Person {
    private double marks;
    private int id;

    public Student(String name, int age, int id, double marks) {
      super(name, age);
      this.id = id;
      this.marks = marks;
    }

    public int getId() {
      return id;
    }

    public double getMarks() {
      return marks;
    }

    public void setId(int id) {
      this.id = id;
    }

    public void setMarks(double marks) {
      this.marks = marks;
    }

    public String calculateGrade() {
      if (marks >= 90) {
        return "A+";
      }

      else if (marks >= 80) {
        return "A";
      } else if (marks >= 70) {
        return "B";
      } else if (marks >= 60) {
        return "C";
      } else if (marks >= 40) {
        return "D";
      } else {
        return "F";
      }
    }

  }

  interface StudentOperation {
    void addStudent(Student s);

    void displayStudents();

    void searchStudents(int id);

    void deleteStudents(int id);

    void updateStudents(int id);
  }

  static class StudentManager implements StudentOperation {
    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
      try {
        if (s.getName() == null || s.getName().trim().isEmpty()) {
          System.out.println("Name cannot be empty!");
          return;
        }

        if (s.getAge() <= 0) {
          System.out.println("Invalid Age!");
          return;
        }

        if (s.getMarks() < 0 || s.getMarks() > 100) {
          System.out.println("Marks must be between 0 and 100!");
          return;
        }
        for (Student student : students) {
          if (student.getId() == s.getId()) {
            System.out.println("Student with this ID already exists!");
            return;
          }
        }
        students.add(s);
        System.out.println("Student added successfully...");
      } catch (Exception e) {
        System.out.println("Error adding student: " + e.getMessage());
      }
    }

    public void displayStudents() {
      if (students.isEmpty()) {
        System.out.println("No student recorda found...");
        return;
      }
      for (Student s : students) {
        System.out.println("Name:" + s.getName());
        System.out.println("Age:" + s.getAge());
        System.out.println("Id:" + s.getId());
        System.out.println("Marks:" + s.getMarks());
        System.out.println("Grade: " + s.calculateGrade());
        System.out.println("................................");
      }
    }

    public void searchStudents(int id) {
      boolean found = false;
      for (Student s : students) {
        if (s.getId() == id) {
          System.out.println("Name:" + s.getName());
          System.out.println("Age:" + s.getAge());
          System.out.println("Marks:" + s.getMarks());
          found = true;
          return;
        }
      }
      if (!found) {
        System.out.println("Student Not found...");
      }
    }

    public void deleteStudents(int id) {
      try {
        boolean found = false;
        for (Student s : students) {
          if (s.getId() == id) {
            students.remove(s);
            found = true;
            break;
          }
        }
        if (found) {

          System.out.println("Student record deleted successfully....");
        } else {
          System.out.println("Student Not found!");
        }
      } catch (Exception e) {
        System.out.println("Error deleting student: " + e.getMessage());
      }
    }

    public void updateStudents(int id) {
      try {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        for (Student s : students) {
          if (s.getId() == id) {
            System.out.println("Enter student name:");
            s.setName(sc.nextLine());
            System.out.println("Enter the student age:");
            s.setAge(sc.nextInt());
            System.out.println("Enter the student marks:");
            s.setMarks(sc.nextDouble());
            found = true;
            System.out.println("Students record updated successfully....");
            return;
          }
        }
        if (!found) {
          System.out.println("Student Not Found!");
        }
      } catch (Exception e) {
        System.out.println("Error updating student: " + e.getMessage());
      }

    }
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    StudentManager manager = new StudentManager();

    int choice;

    do {
      System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
      System.out.println("1. Add Student");
      System.out.println("2. Display Students");
      System.out.println("3. Search Student");
      System.out.println("4. Update Student");
      System.out.println("5. Delete Student");
      System.out.println("6. Calculate Grade");
      System.out.println("7. Exit");

      System.out.println("Enter your choice:");
      choice = sc.nextInt();
      sc.nextLine();

      switch (choice) {

        case 1:
          System.out.println("Enter student name:");
          String name = sc.nextLine();

          System.out.println("Enter student age:");
          int age = sc.nextInt();

          System.out.println("Enter student ID:");
          int id = sc.nextInt();

          System.out.println("Enter student marks:");
          double marks = sc.nextDouble();
          sc.nextLine();

          Student s = new Student(name, age, id, marks);
          manager.addStudent(s);
          break;

        case 2:
          manager.displayStudents();
          break;

        case 3:
          System.out.println("Enter student ID:");
          int searchId = sc.nextInt();
          manager.searchStudents(searchId);
          break;

        case 4:
          System.out.println("Enter student ID to update:");
          int updateId = sc.nextInt();
          sc.nextLine();
          manager.updateStudents(updateId);
          break;

        case 5:
          System.out.println("Enter student ID to delete:");
          int deleteId = sc.nextInt();
          manager.deleteStudents(deleteId);
          break;

        case 6:
          System.out.println("Enter student ID:");
          int gradeId = sc.nextInt();

          boolean found = false;

          for (Student st : manager.students) {
            if (st.getId() == gradeId) {
              System.out.println("Grade: " + st.calculateGrade());
              found = true;
              break;
            }
          }

          if (!found) {
            System.out.println("Student not found!");
          }
          break;

        case 7:
          System.out.println("Thank you for using Student Management System!");
          break;

        default:
          System.out.println("Invalid choice!");
      }

    } while (choice != 7);

    sc.close();
  }
}
