package cs350s22.startup;

import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.component.ui.parser.Parser;
import cs350s22.component.ui.parser.ParserHelper;

public class Startup
{
   private final A_ParserHelper _parserHelper = new ParserHelper();
   
   public Startup()
   {
      System.out.println("STARTUP");
   }


   public static void main(final String[] arguments) throws Exception
   {
      Startup startup = new Startup();

      // this command must come first. The filenames do not matter here
      //startup.parse("@CONFIGURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\"");
      //startup.parse("CREATE ACTUATOR LINEAR myActuator0 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 10 INITIAL 2 JERK LIMIT 3");
      //startup.parse("@RUN file1.txt");
      //startup.parse("CREATE MAPPER myMapper EQUATION NORMALIZE 10 20");
      //startup.parse("SEND MESSAGE PING");
      //startup.parse("@CLOCK");
      //startup.parse("@CLOCK PAUSE");
      //startup.parse("@CLOCK RESUME");
      //startup.parse("@CLOCK PAUSE");
      //startup.parse("@CLOCK ONESTEP 100");
      //startup.parse("@CLOCK RESUME");
      //startup.parse("@CLOCK SET RATE 20");
      //startup.parse("@CLOCK WAIT FOR 1.5");
      //startup.parse("@CLOCK WAIT UNTIL 1.5");
      // startup.parse("CREATE WATCHDOG NOTCH myWatchdog2 MODE AVERAGE 10 THRESHOLD LOW 1 HIGH 3 GRACE 4");
      //startup.parse("CREATE WATCHDOG LOW myWatchdog1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE 4");
      //startup.parse("CREATE REPORTER CHANGE myReporter1 NOTIFY IDS myActuator1 myActuator2 DELTA 3");
      //startup.parse("CREATE REPORTER CHANGE myReporter1 NOTIFY IDS myActuator1 myActuator2 GROUPS myGroup2 myGroup3 DELTA 3");
      //startup.parse("CREATE REPORTER FREQUENCY myReporter6 NOTIFY IDS myActuator1 myActuator2 GROUPS myGroup3 FREQUENCY 4");
      //startup.parse("CREATE REPORTER FREQUENCY myReporter6 NOTIFY myActuator1 myActuator2 FREQUENCY 3");
      //startup.parse("BUILD NETWORK WITH COMPONENT myController");
      //startup.parse("BUILD NETWORK WITH COMPONENTS myController1 myActuator1");
      //startup.parse("CREATE SENSOR POSITION mySensor16 WATCHDOGS myWatchdog1 myWatchdog2 MAPPER myMapper1");
      // run your tests like this
      startup.parse("@exit");
   }
   
   private void parse(final String parse) throws Exception
   {
      System.out.println("PARSE> "+ parse);
      
      Parser parser = new Parser(_parserHelper, parse);
      
      parser.parse();
   }
}
