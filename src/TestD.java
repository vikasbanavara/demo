import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class PayLoad implements Comparable<PayLoad> {
	private double x;
	private long y;
	private long time;
	
	public PayLoad( String time, String x, String y ) {
		this.x = Double.parseDouble(x);
		this.y = Long.parseLong(y);
		this.time = Long.parseLong(time);
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	
	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	@Override
	public int compareTo(PayLoad payLoad) {
		if(this.time > payLoad.time) {
			return -1;
		} else if(this.time < payLoad.time) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
}

public class TestD {
	public static void main(String[] args) {
		List<Long> mil = new ArrayList<Long>();
		List<Long> milad = new ArrayList<Long>();
		List<LocalDateTime> dt = new ArrayList<LocalDateTime>();
		List<PayLoad> payList = new ArrayList<PayLoad>();
		
		String data2 = "1607341341814,0.0442672968,1282509067\n"
				+ "1607341341814,0.0442672968,1282509067\n"
				+ "1607341341815,0.0442672968,1282509067\n"
				+ "1607341341816,0.0442672968,1282509067\n"
				+ "1607341341816,0.0442672968,1282509067\n"
				+ "1607341339815,0.0473002568,1785397644\n"
				+ "1607341739815,0.0473002568,1785397644\n"
				+ "1607341739815,0.0473002568,1785397644\n"
				+ "1607341755815,0.0473002568,1785397644\n"
				+ "1607341695815,0.0473002568,1785397644\n"
				+ "1607341331816,0.0899538547,1852154378";
		
		String data = "1607341341814,0.0442672968,1282509067\n"
				+ "1607341341814,0.0442672968,1282509067\n"
				+ "1607341341815,0.0442672968,1282509067\n"
				+ "1607341341816,0.0442672968,1282509067\n"
				+ "1607341341816,0.0442672968,1282509067\n"
				+ "1607341339815,0.0473002568,1785397644\n"
				+ "1607341331816,0.0899538547,1852154378\n"
				+ "1607341271814,0.0586780608,111212767\n"
				+ "1607341261814,0.0231608748,1539565646\n"
				+ "1607341331814,0.7796950936,1820653751\n"
				+ "1607341291814,0.0876221433,1194727708\n"
				+ "1607341338814,0.0302456915,1760856792\n"
				+ "1607341311814,0.0554600768,2127711810\n"
				+ "1607340341814,0.0360791311,1563887095";
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSSSSS");
		
		String lines[] = data.split(System.getProperty("line.separator"));
		mil.clear();
		dt.clear();
		for(String s : lines) {
			String ar[] = s.split(",");
			PayLoad ref = new PayLoad(ar[0],ar[1],ar[2]);
			payList.add(ref);
			LocalDateTime cvDate =
				    Instant.ofEpochMilli(Long.parseLong(ar[0])).atZone(ZoneId.systemDefault()).toLocalDateTime();
			mil.add(Long.parseLong(ar[0]));
			milad.add(Long.parseLong(ar[0])+60000);
			dt.add(cvDate);
		}
		Collections.sort(mil,Collections.reverseOrder());
		Collections.sort(dt,Collections.reverseOrder());
		Collections.sort(payList);
		
		for(int i=0;i<mil.size();i++)
		{
			System.out.println(dt.get(i));
		}
		System.out.println("-------------------------------");
		for(int i=0;i<mil.size();i++)
		{
			System.out.println(Instant.ofEpochMilli(mil.get(i)).atZone(ZoneId.systemDefault()).toLocalDateTime());
		}
		System.out.println("-------------------------------");
		for(int i=0;i<mil.size();i++)
		{
			System.out.println(Instant.ofEpochMilli(milad.get(i)).atZone(ZoneId.systemDefault()).toLocalDateTime());
		}
		System.out.println("-------------------------------");
		for(int i=0;i<payList.size();i++)
		{
			System.out.println(df.format(Instant.ofEpochMilli(payList.get(i).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()));
		}
		final long t = payList.get(0).getTime() - 60000;
		System.out.println(t);
		System.out.println("---->"+df.format(Instant.ofEpochMilli(t).atZone(ZoneId.systemDefault()).toLocalDateTime()));
		System.out.println("---------------MP----------------");
		for(int i=0;i<payList.size();i++)
		{
			System.out.println(payList.get(i).getTime()+" "+ (payList.get(i).getTime()>t));
		}
		payList = payList.stream().filter( p ->  p.getTime()>=t).collect(Collectors.toList());
		double sx = 0,sy=0;
		for(int i=0;i<payList.size();i++)
		{
			sx = sx + payList.get(i).getX();
			System.out.println("--->"+payList.get(i).getY());
			sy = sy + payList.get(i).getY();
		}
		System.out.println("Size---->"+payList.size());
		System.out.println("X sum "+sx);
		System.out.println("Y sum "+String.format("%.0f", sy));
		System.out.println("Avg-X "+(sx/payList.size()));
		System.out.println("Avg-Y "+(sy/payList.size()));
		System.out.println("S---->"+String.format("%.0f", (sy/payList.size())));
		double avgX = payList.stream().mapToDouble(p->p.getX()).average().getAsDouble();
		System.out.println(avgX);
		System.out.println("---------------Data----------------");
		for(int i=0;i<payList.size();i++)
		{
			System.out.println(payList.get(i).getTime());
			System.out.println(df.format(Instant.ofEpochMilli(payList.get(i).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()));
		}
		
		System.out.println("--------done-------------");
		int total = payList.size();
		double sumX = 0;
		long sumY = 0;
		for(int i=0;i<total;i++)
		{
			sumX = sumX + payList.get(i).getX();
			sumY = sumY + payList.get(i).getY();
		}
		System.out.println( total+", "+sumX+", "+(sumX/total)+", "+sumY+", "+String.format("%.5f",(sumY/(double)total)));
	}
}
