<h1 align="center"><br><br>
  Veracross SDK
</h1>

<h4 align="center">
  A Java SDK library for Veracross
</h4>

<h5 align="center">
  <a href="#intro">Introduction</a>&nbsp;&nbsp;
  <a href="#development">Development</a>&nbsp;&nbsp;
  <a href="#license">License</a>
</h5><br><br><br>



<a name="intro"></a>
Introduction:
--------

**Note**: Currently, this SDK library only works for St. John's with single-sign-on, 
because logging in directly to Veracross would require an authenticity token from Recaptcha.
If you want to use this library and you are from another school,
you need to find out how your school handles single-sign-on and implement it yourself.
(Look into [StJohnsHttpClient.java](./src/main/java/org/hydev/veracross/sdk/StJohnsHttpClient.java) for details)

### Features:

* Log in to Veracross with single-sign-on.
* Get the list of courses the student is taking.
* Get a list of all the assignments in a course.
* Get the list of messages, or notifications.
* Get the list of calendar events in between dates.

<br>

<a name="development"></a>
Development:
--------

### Setting Up:

#### For IntelliJ IDEA Users:

* I didn't set up a Maven repo yet, so you have to manually copy some files.
* Clone or download this repo as a zip first if you don't want to copy all code manually.
* Just copy the `src/main/java` folder of the module you want to import to your `src/main/java` folder.
* Then copy the Maven dependencies from the `pom.xml` of the module you want to your `pom.xml`.

#### For Eclipse Users:

* Well... Eclipse sucks!
* IDK where the src folder for Eclipse is. 
* Download [IntelliJ IDEA](https://www.jetbrains.com/idea/) for free [here](https://www.jetbrains.com/idea/download/).
* And if you're a student like me, you can get a free IntelliJ Ultimate license with your school email [here](https://www.jetbrains.com/student/).

### Veracross SDK:

```java
// Import packages first:
//   import cc.moecraft.school.veracross.*;
//   import cc.moecraft.school.veracross.pojo.*;

// Initialize object with base url, username and password.
VeracrossReader reader = new VeracrossReader("https://portals-app.veracross.com/schoolname", "username", "password");

// Initialize browser driver with the path to chrome driver.
// The boolean means use headless mode or not.
//   true = Headless, false = Not headless.
//   I recommend false when debugging and true when deploying.
//   Because if it's headless, you can't see the browser window.
reader.initialize("./GPACalc.Core/drivers/chromedriver.exe", true);

// Login to Veracross
reader.login();

// Get course list
List<VeracrossCourse> courses = reader.getCourses();
log(courses);

// Get assignments of the course at index 1 of the list.
VeracrossAssignments assignments = reader.getAssignments(courses.get(1).getAssignmentsId());
log(assignments);

// Get messages starting at index 0.
List<VeracrossMessage> messages = reader.getMessages(0);
log(messages);

// Get calendar events from 5 days ago to 5 days later.
List<VeracrossCalendarEvent> events = reader.getEvents(-5, 5);
log(events);

// Get calendar events from a specific date to a specific date.
events = reader.getEvents(new Date(), new Date());
log(events);

// Kill browser task.
reader.destroy();
```

<br>

<a name="license"></a>
License: [MIT](https://choosealicense.com/licenses/mit/)
--------
