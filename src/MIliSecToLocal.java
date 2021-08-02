import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MIliSecToLocal {
	public static void main(String[] args) {
		long milliseconds = 1486815313230L;
		LocalDateTime cvDate =
		    Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		System.out.println(cvDate);
	}
}
