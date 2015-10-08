## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Access the application](#access-the-application)**  

## Resource location
* Web article - <https://spring.io/guides/gs/gradle/>
* Git repository - <https://github.com/spring-guides/gs-gradle>

## Overview
You’ll create a simple app and then build it using Gradle.

## System requirements
All you need to build this project is Java 6.0 (Java SDK 1.6) or better.

The application this project produces is designed to be run on Wildfly.

## Project notes

### Set up the project

First you set up a Java project for Gradle to build. To keep the focus on Gradle, make the project as simple as possible for now.

Within the `src/main/java/hello` directory, you can create any Java classes you want. For simplicity's sake and for consistency with the rest of this guide, Spring recommends that you create two classes: `HelloWorld.java` and `Greeter.java`.

### Install Gradle

Now that you have a project that you can build with Gradle, you can install Gradle.

Gradle is downloadable as a zip file at <http://www.gradle.org/downloads>. Only the binaries are required, so look for the link to gradle-_version_-bin.zip. (You can also choose gradle-_version_-all.zip to get the sources and documentation as well as the binaries.)

Unzip the file to your computer, and add the bin folder to your path.

To test the Gradle installation, run Gradle from the command-line:

    gradle

If all goes well, you see a welcome message:

    :help
    
    Welcome to Gradle 2.3.
    
    To run a build, run gradle <task> ...
    
    To see a list of available tasks, run gradle tasks
    
    To see a list of command-line options, run gradle --help
    
    BUILD SUCCESSFUL
    
    Total time: 2.675 secs

You now have Gradle installed.

### Find out what Gradle can do

Now that Gradle is installed, see what it can do. Before you even create a build.gradle file for the project, you can ask it what tasks are available:


    gradle tasks

You should see a list of available tasks. Assuming you run Gradle in a folder that doesn’t already have a `build.gradle` file, you’ll see some very elementary tasks such as this:

    :tasks
    
    == All tasks runnable from root project
    
    == Build Setup tasks
    setupBuild - Initializes a new Gradle build. [incubating]
    wrapper - Generates Gradle wrapper files. [incubating]
    
    == Help tasks
    dependencies - Displays all dependencies declared in root project 'gs-gradle'.
    dependencyInsight - Displays the insight into a specific dependency in root project 'gs-gradle'.
    help - Displays a help message
    projects - Displays the sub-projects of root project 'gs-gradle'.
    properties - Displays the properties of root project 'gs-gradle'.
    tasks - Displays the tasks runnable from root project 'gs-gradle'.
    
    To see all tasks and more detail, run with --all.
    
    BUILD SUCCESSFUL
    
    Total time: 3.077 secs

Even though these tasks are available, they don’t offer much value without a project build configuration. As you flesh out the build.gradle file, some tasks will be more useful. The list of tasks will grow as you add plugins to build.gradle, so you’ll occasionally want to run tasks again to see what tasks are available.

Speaking of adding plugins, next you add a plugin that enables basic Java build functionality.

### Build Java code

You’ll use the `gradle build` task frequently. This task compiles, tests, and assembles the code into a JAR file. You can run it like this:

    gradle build

After a few seconds, "BUILD SUCCESSFUL" indicates that the build has completed.

To see the results of the build effort, take a look in the build folder. Therein you’ll find several directories, including these three notable folders:

- classes. The project’s compiled .class files.
- reports. Reports produced by the build (such as test reports).
- libs. Assembled project libraries (usually JAR and/or WAR files). The classes folder has .class files that are generated from compiling the Java code. Specifically, you should find HelloWorld.class and Greeter.class.

At this point, the project doesn’t have any library dependencies, so there’s nothing in the `dependency_cache` folder.

The reports folder should contain a report of running unit tests on the project. Because the project doesn’t yet have any unit tests, that report will be uninteresting.

The libs folder should contain a JAR file that is named after the project’s folder. Further down, you’ll see how you can specify the name of the JAR and its version.

### Declare dependencies

If you ran gradle build to build the project, the build would fail because if you have not declared Joda Time as a compile dependency in the build.

For starters, you need to add a source for 3rd party libraries.

    repositories {
    	mavenCentral()
    }

The `repositories` block indicates that the build should resolve its dependencies from the Maven Central repository. Gradle leans heavily on many conventions and facilities established by the Maven build tool, including the option of using Maven Central as a source of library dependencies.

Now that we’re ready for 3rd party libraries, let’s declare some.

    sourceCompatibility = 1.8
    targetCompatibility = 1.8
    
    dependencies {
    	compile "joda-time:joda-time:2.2"
    }

With the `dependencies` block, you declare a single dependency for Joda Time. Specifically, you’re asking for (reading right to left) version 2.2 of the joda-time library, in the joda-time group.

Another thing to note about this dependency is that it is a `compile` dependency, indicating that it should be available during compile-time (and if you were building a WAR file, included in the /WEB-INF/libs folder of the WAR). Other notable types of dependencies include:

- providedCompile. Required dependencies for compiling the project code, but that will be provided at runtime by a container running the code (for example, the Java Servlet API).
- testCompile. Dependencies used for compiling and running tests, but not required for building or running the project’s runtime code.

Finally, let’s specify the name for our JAR artifact.

	jar {
	    baseName = 'gs-gradle'
	    version =  '0.1.0'
	}

The `jar` block specifies how the JAR file will be named. In this case, it will render gs-`gradle-0.1.0.jar`.

Now if you run `gradle build`, Gradle should resolve the Joda Time dependency from the Maven Central repository and the build will succeed.

### Build your project with Gradle Wrapper

The Gradle Wrapper is the preferred way of starting a Gradle build. It consists of a batch script for Windows and a shell script for OS X and Linux. These scripts allow you to run a Gradle build without requiring that Gradle be installed on your system. To make this possible, add the following block to the bottom of your `build.gradle`.

	task wrapper(type: Wrapper) {
	    gradleVersion = '2.3'
	}

Run the following command to download and initialize the wrapper scripts:

    gradle wrapper

After this task completes, you will notice a few new files. The two scripts are in the root of the folder, while the wrapper jar and properties files have been added to a new `gradle/wrapper` folder.

	└── initial
	    └── gradlew
	    └── gradlew.bat
	    └── gradle
	        └── wrapper
	            └── gradle-wrapper.jar
	            └── gradle-wrapper.properties

The Gradle Wrapper is now available for building your project. Add it to your version control system, and everyone that clones your project can build it just the same. It can be used in the exact same way as an installed version of Gradle. Run the wrapper script to perform the build task, just like you did previously:

	./gradlew build

The first time you run the wrapper for a specified version of Gradle, it downloads and caches the Gradle binaries for that version. The Gradle Wrapper files are designed to be committed to source control so that anyone can build the project without having to first install and configure a specific version of Gradle.

At this stage, you will have built your code. You can see the results here:

	build
	├── classes
	│   └── main
	│       └── hello
	│           ├── Greeter.class
	│           └── HelloWorld.class
	├── dependency-cache
	├── libs
	│   └── gs-gradle-0.1.0.jar
	└── tmp
	    └── jar
	        └── MANIFEST.MF

Included are the two expected class files for `Greeter` and `HelloWorld`, as well as a JAR file. Take a quick peek:
	
	$ jar tvf build/libs/gs-gradle-0.1.0.jar
	  0 Fri May 30 16:02:32 CDT 2014 META-INF/
	 25 Fri May 30 16:02:32 CDT 2014 META-INF/MANIFEST.MF
	  0 Fri May 30 16:02:32 CDT 2014 hello/
	369 Fri May 30 16:02:32 CDT 2014 hello/Greeter.class
	988 Fri May 30 16:02:32 CDT 2014 hello/HelloWorld.class

The class files are bundled up. It’s important to note, that even though you declared joda-time as a dependency, the library isn’t included here. And the JAR file isn’t runnable either.

To make this code runnable, we can use gradle’s `application` plugin. Add this to your `build.gradle` file.

    apply plugin: 'application'
    
    mainClassName = 'hello.HelloWorld'

Then you can run the app!

	$ ./gradlew run
	:compileJava UP-TO-DATE
	:processResources UP-TO-DATE
	:classes UP-TO-DATE
	:run
	The current local time is: 16:16:20.544
	Hello world!
	
	BUILD SUCCESSFUL
	
	Total time: 3.798 secs

To bundle up dependencies requires more thought. For example, if we were building a WAR file, a format commonly associated with packing in 3rd party dependencies, we could use gradle’s WAR plugin. If you are using Spring Boot and want a runnable JAR file, the spring-boot-gradle-plugin is quite handy. At this stage, gradle doesn’t know enough about your system to make a choice. But for now, this should be enough to get started using gradle.

## Server deployment

### Start Wildfly
1. Open a command line and navigate to the root of the Wildfly directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/startup.sh
        For Windows: WILDFLY_HOME\bin\startup.bat

### Build and Deploy the application
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. Type this command to build and create the `war` file:

        gradle build

4. This will package the application in `build/libs/gs-gradle-0.1.0.war` folder.
5. Deploy above created `war` file manually to the wildfly server.

## Access the application
The application can be tested with following URLs:       
* <http://localhost:8080/gs-gradle-0.1.0/greeting/>

You should see something like this.

	14:08:51.572 Hello world!