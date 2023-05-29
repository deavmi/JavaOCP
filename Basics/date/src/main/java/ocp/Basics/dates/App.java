package ocp.Basics.dates;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Dates, times and durations!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/**
    	 * We can use the <code>Instant</code> class
    	 * to represent an instanenous timestamp
    	 * 
    	 * Let's get the current instant!
    	 */
    	Instant currentTime = Instant.now();
    	System.out.println("Current time: "+currentTime);
    	
    	/**
    	 * Let's add some offset to the current instant.
    	 * 
    	 * This is done by adding a <code>TemporalAmount</code>, one
    	 * such implementing class is a <code>Duration</code>, here
    	 * we will get a duration of 2 seconds, the add it to
    	 * then current instant to get an instant two seconds
    	 * ahead of the original 
    	 */
    	Duration duration = Duration.ofSeconds(2);
    	Instant futureTime = currentTime.plus(duration);
    	System.out.println("Future time: "+futureTime);
    	
    	/**
    	 * We can also obtain an instant fixed at a certain time
    	 * by a string as shown below.
    	 * 
    	 * Or we can derive it from seconds since 1970
    	 * (Epoch time)
    	 */
    	Instant.parse("2007-12-03T10:15:30.00Z");
    	System.out.println("2000 seconds from UNIX Epoch: "+Instant.ofEpochSecond(2000));
    	
    	/**
    	 * The <code>Date</code> is like Instant but for dates, so time plus
    	 * day, below we obtain the current date using an Instant.
    	 * 
    	 * We can compare dates as well, using <code>before(Date)</code>
    	 * and <code>after(Date)</code>
    	 */
    	Date curDate = Date.from(Instant.now());
    	System.out.println("Current date: "+curDate);
    	
    	Date futureDate = Date.from(futureTime);
    	System.out.println("Future date: "+futureDate);

    	System.out.println(curDate.before(futureDate)); // true
    	
    	/**
    	 * The <code>LocalDate</code> is the newer way to work with
    	 * dates, as <code>Date</code> is deprecated
    	 */
    	LocalDate today = LocalDate.now();
    	System.out.println("Today date (LocalDate): "+today);
    	
    	/**
    	 * Recall how a <code>Duration</code> was a kind-of
    	 * <code>TemporalAmount</code> and how it represented
    	 * a difference of time and could give us a new Instant
    	 * after said duration
    	 * 
    	 * Well a <code>Period</code> is a kind-of 
    	 * <code>TemporalAccessor</code> which let's us do that
    	 * with dates
    	 */
    	LocalDate future = today.plus(Period.ofDays(2));
    	System.out.println("Future date (LocalDate): "+future);
    	
    	/**
    	 * LocalDateTime contains time AND date, we can use
    	 * it as shown below
    	 * 
    	 * We will take our current DATE and then get a 
    	 * <code>LocalDateTime</code. based on the current
    	 * day but with time starting right at the beginning
    	 * of said day.
    	 */
    	LocalDateTime dateTime = today.atStartOfDay();
    	System.out.println("Today (LocalDateTime): "+dateTime);
    	
    	/**
    	 * We then go ahead and add some time to it, via
    	 * TemporalAmunt. So we can add a <code>Duration</code>.
    	 * 
    	 * Here we take the start of the day and get 30
    	 * seconds into it
    	 */
    	LocalDateTime futureDateTime = dateTime.plus(Duration.ofSeconds(30));
    	System.out.println("Today ahead 30 seconds (LocalDateTime): "+futureDateTime);
    	
    	// TODO: Do TimeZone stuff here
    	
    	/**
    	 * Let's create a <code>LocalDateTime</code> that has a
    	 * timezone associated with it, we can do this based on
    	 * our current <code>futureDateTime</code> as shown below
    	 */
    	ZonedDateTime zonedDateTime = futureDateTime.atZone(ZoneId.of("America/Puerto_Rico"));
    	System.out.println("Future time (zoned): "+zonedDateTime);
    	
    	/**
    	 * I'm going to create one in a different time zone now
    	 * but using same date and time.
    	 * 
    	 * What we can see is that the time is adjusted for its
    	 * equivalent in that region. Here this is -7 hours
    	 * (I guess) from central time BUT the one in Puerto Rico
    	 * is only -4 hours (hence more in future than us - 
    	 * they wake up earlier than us)
    	 */
    	ZonedDateTime zonedDateTime2 = futureDateTime.atZone(ZoneId.of("America/Los_Angeles"));
    	System.out.println("Future time (zoned2): "+zonedDateTime2);
    	
    	
    }
}
