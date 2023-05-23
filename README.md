# Epoka-Student-System
This project is a desktop app that has a log-in system for two types of accounts - that is, administrators and students - with all data stored on phpMyAdmin database.
Special design features are used to distinguish titles by using Shadow object and other designs like button hovering, all found in the overriden *initialize* method and in the *application.css* file.
![Screenshot 5_23_2023 9_59_03 PM](https://github.com/theMartian30/Epoka-Student-System/assets/99083039/8ba15f0b-c1f2-496b-a708-57c27ea6610c)
# **Admin Page**
At the admin page, every action is initiated in a single anchor pane where functions like **add**, **remove**, **update** and **grade** a student are located. Each of them have different data fields, based on the funcionality, but we should firmly say that the username field is the most important one, since it is considered as the primary key of the database to distinguish each individual. 
A side pane holds the photo and the unique information of the admin who has entered through the log-in system. A new admin cannot be added to the database system from the app, but only by modifying the database directly.
![Screenshot 5_23_2023 10_01_27 PM](https://github.com/theMartian30/Epoka-Student-System/assets/99083039/8c81e0c0-fbb3-44e6-96c6-1e102163fd8a)
# **Student Page**
On this page, four buttons (*Home, Profile, Courses, Transcript* respectively) control the transitions between subpages. At the same pane where these buttons are located, a text field and another button can be used to change the password of the student. One thing that stands out to the user is an animation at the top which is in motion for a duration of 15 seconds each time a transparent button is clicked on its place.

At **Home page**, superficial information about the university is shown together with a pane welcoming the user.
![Screenshot 5_23_2023 10_01_58 PM](https://github.com/theMartian30/Epoka-Student-System/assets/99083039/436b256d-31bb-4c1c-bf2f-ccdda10063f3)
The **profile page**, as its name stands, displays all the information found in the database for the student, with a button to add a photo (it is the only non-functional part of the program since a web server is needed to store images non-locally). CGPA is consistently updated.
![Screenshot 5_23_2023 10_02_23 PM](https://github.com/theMartian30/Epoka-Student-System/assets/99083039/e10e7103-2949-4414-892f-4870c59b99f1)
At **Course page**, three list-views are used to select 3 compulsory subjects through multiple selection, 1 technical elective and 1 non-technical elective. Subsequently, a button named "Add Courses" is clicked to add the selected courses to the database and simultaneously to a table view that is part of the same page.
![Screenshot 5_23_2023 10_02_45 PM](https://github.com/theMartian30/Epoka-Student-System/assets/99083039/84ee100d-2d34-4b13-950e-77cbc49b351f)
At **Transcript page**, another table view catches the eye of the user, but besides course code and name, it displays the grades in midterm and final exams. At the moment two grades are uploaded, the subject is considered finalized and CGPA is updated. All courses and their respective grades for each exam and each student are stored in the students database. At the bottom, a large button named "Download Transcript" has the function of reading the profile and writing a file with all the information of the student, not forgetting the updated CGPA. The user has the option of selecting the location where the file can be saved through a window.

![Screenshot 5_23_2023 10_15_58 PM](https://github.com/theMartian30/Epoka-Student-System/assets/99083039/d962d1f8-a2ae-40f3-bfb5-fcd72d87efe3)
![Save As 5_23_2023 10_03_13 PM](https://github.com/theMartian30/Epoka-Student-System/assets/99083039/558fd095-5299-4f38-b7b7-7234017866e3)
Every data inserted is stored in a local host in phpMyAdmin.
![theMartian30_Epoka-Student-System_ A desktop app that has a log-in system for administrators and students with all data stored on phpMyAdmin database  - Google Chrome 5_23_2023 10_27_24 PM](https://github.com/theMartian30/Epoka-Student-System/assets/99083039/7cc603de-061e-4d4e-98e7-b8bd23ca2e96)
