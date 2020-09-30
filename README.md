<h1 align="center"><br><br>
  Veracross SDK
</h1>

<h4 align="center">
  A Java SDK library for Veracross Student API
</h4>

<h5 align="center">
  <a href="#intro">Introduction</a>&nbsp;&nbsp;
  <a href="#development">Development</a>&nbsp;&nbsp;
  <a href="#license">License</a>
</h5><br><br><br>



<a name="intro"></a>
Introduction:
--------

### Features:

* Log in to Veracross with single-sign-on.
* Get the list of courses the student is taking.
* Get a list of all the assignments in a course.
* Get the list of messages, or notifications.
* Get the list of calendar events in between dates.
* Supports both legacy apis on `portals.veracross.com` and new apis on `portals-app.veracross.com`.

**Note**: Currently, this SDK library only works for St. John's with single-sign-on, 
because logging in directly to Veracross would require an authenticity token from Recaptcha.
If you want to use this library and you are from another school,
you need to find out how your school handles single-sign-on and implement it yourself.
(Look into [StJohnsHttpClient.java](./src/main/java/org/hydev/veracross/sdk/StJohnsHttpClient.java) for details)

<br>

<a name="maven"></a>
Maven Import:
--------

Add the JitPack repo into your `pom.xml` first:

```xml
<repositories>
    <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then you can add this library as dependency:

```xml
<dependency>
    <groupId>com.github.hydevelop</groupId>
    <artifactId>VeracrossAnalyzer.SDK</artifactId>
    <version>3.2.0.327</version>
</dependency>
```

Make sure you reimport if you're using IntelliJ IDEA!

<br>

<a name="development"></a>
Development:
--------

### Setting Up:

Look at the Maven section,
if you have successfully imported the maven dependency,
you should be able to access the classes.
If you don't know what maven is, [click here](https://lmgtfy.com/?q=maven).

### Usage Tutorial:

#### 1. Login from St. John's:

```java
// Create a new http client object
StJohnsHttpClient stJohns = new StJohnsHttpClient();

// Login with username and password
stJohns.login("username", "password");
```

#### 2. Use single-sign-on to login to Veracross:

```java
// Login and store it in a variable
VeracrossHttpClient veracross = stJohns.veracrossLoginSSO();
```

#### 3. Get the list of courses the student is taking:

```java
VeraCourses courses = veracross.getCourses();
```

#### 4. Get a list of all the assignments in a course:

```java
// Get the assignments id from a course.
long assignmentsId = courses.get(0).getAssignmentsId();

// Pass in the assignments id to get the list of assignments.
// Note: the assignments id is different from the course id!
VeraAssignments assignments = veracross.getAssignments(assignmentsId);
```

#### 5. Get the list of messages, or notifications:

```java
// Get messages starting at index 0 (Which is all the messages).
List<VeraMessage> messages = veracross.getMessages(0);
```

#### 6. Get the list of calendar events in between dates:

```java
// Get calendar events from 5 days ago to 5 days later.
List<VeraCalendarEvent> events = veracross.getEvents(-5, 5);

// Get calendar events in between two dates.
List<VeraCalendarEvent> events2 = veracross.getEvents(new Date(), new Date());
```

#### 7. Save cookies for later:

```java
// Get cookies
List<Cookie> cookies = veracross.getCookies().getCookies();

// Do whatever you want to save them.
// In this case, I'll just use Gson to save them as JSON.
String cookiesJson = new Gson().toJson(cookies);
```

#### 8. Restore cookies:

```java
// Get the original list of cookies back
List<Cookie> restoredCookies = new Gson().fromJson(cookiesJson, 
        new TypeToken<List<BasicClientCookie>>(){}.getType());
        
// Restore them to a new Veracross client
VeracrossHttpClient restoredVeracross = new VeracrossHttpClient();
restoredVeracross.restoreCookies(restoredCookies);
```

### If you need help:

1. **Ask Google**
2. Post an issue [here](https://github.com/HyDevelop/VeracrossAnalyzer.SDK/issues).

<br>

<a name="license"></a>
License: [MIT](https://choosealicense.com/licenses/mit/)
--------

The MIT license basically means that this project is open-soucred and you can do whatever you want with it, as long as you include a copy of this license in your distribution. You don't have to ask for permissions to use or anything. However, if you do bad things with it, I'm not responsible.
