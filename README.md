If you use Astor, please cite this technical report:

Matias Martinez, Martin Monperrus. "ASTOR: Evolutionary Automatic Software Repair for Java". Technical Report hal-01075976, Inria; 2014. 

    @techreport{hal-01075976,
     TITLE = {{ASTOR: Evolutionary Automatic Software Repair for Java}},
     AUTHOR = {Martinez, Matias and Monperrus, Martin},
     URL = {https://hal.archives-ouvertes.fr/hal-01075976},
     INSTITUTION = {{Inria}},
     YEAR = {2014}
    }

Astor is an evolutionary Automatic Software Repair framework that contains implementation of state of the art repair approaches such as GenProg and Kali.


Getting started
-------

Note that this project requires the use of **Java 1.7**; it does not build or does not run (on OS X 10.10.3) with Java 1.8. Please install a JDK 1.7 and configure Maven or your IDE to use it. Fill property jvm4testexecution in `src/main/resources/configuration.properties`.

To compile using maven, first execute:

    mvn clean
    mvn compile

We recommend to remove all package-info.java files from the project to repair (You can use command: `find . -name "package-info.java" -type f -delete`).


We provide an implementation of GenProg repair algorithm called jGenProg.The class to run it is:
fr.inria.main.evolution.MainjGenProg

After the execution of a repair attempt, Astor writes in the output folder (property 'workingDirectory'in the mentioned file), a folder with all the variants that fulfill the goals i.e., repair the bugs.
Each variant folder contains the files that Astor have analyzed (and eventually modified). Additionally, it contains a file called 'Patch.xml' that summarized all changes done in the variant.


**jGenProg**

We present an command line with the required arguments for executing jGenProg.  Optional arguments can find using option -help are listed below. They arguments can also be changed  in "configuration.properties".

Minimum arguments:


    java  -cp "location"/astor.jar fr.inria.main.evolution.MainjGenProg 
      -srcjavafolder "source code folder"
      -srctestfolder "test source code folder"
      -binjavafolder "class folder" 
      -bintestfolder "test class folder" 

    
Other options:

    -mode statement 

    -location "location of the project to repair" 

    -dependencies "folder with the dependencies of the application to repair" 

    -failing "failing test case>"
    
    -package "package to manipulate" (only the statements from this package are manipulated to find a patch)

    -jvm4testexecution "jdklocation"/java-1.7.0/bin/ 

    -javacompliancelevel "compliance level of source code e.g. 5"

    -stopfirst true 

    -flthreshold "minimun suspicious value for fault localization e.g. 0.1"




**jKali**

For executing Astor in jKali mode, we use the option `-mode statement-remove`. jKali and jGenProg share the same arguments.

    java  -cp astor.jar fr.inria.main.evolution.MainjGenProg -mode statement-remove -location <>......


**Bug Example**

The distribution contains a version of Apache commons Math with a real defect (reported in issue 280 https://issues.apache.org/jira/browse/MATH-280).

To run it using jGenProg, type: `java fr.inria.main.evolution.MainjGenProg -bug280`

or 

    java -cp "jar_location"/astor-x.y.z-jar-with-dependencies.jar fr.inria.main.evolution.MainjGenProg -bug280


Contacts
--------
matias.sebastian.martinez@usi.ch
martin.monperrus@univ-lille1.fr
