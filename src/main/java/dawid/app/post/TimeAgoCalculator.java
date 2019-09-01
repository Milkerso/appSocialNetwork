package dawid.app.post;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeAgoCalculator {
    public static final List<Long> times = Arrays.asList(
            TimeUnit.DAYS.toMillis(365),
            TimeUnit.DAYS.toMillis(30),
            TimeUnit.DAYS.toMillis(1),
            TimeUnit.HOURS.toMillis(1),
            TimeUnit.MINUTES.toMillis(1),
            TimeUnit.SECONDS.toMillis(1));
    public static final List<String> timesString = Arrays.asList("lat", "miesięcy", "dni", "godziny", "minut", "sekund");

    public static String toDuration(long duration) {

        StringBuffer res = new StringBuffer();
        for (int i = 0; i < TimeAgoCalculator.times.size(); i++) {
            Long current = TimeAgoCalculator.times.get(i);
            long temp = duration / current;
            if (temp > 0) {
                res.append(temp).append(" ").append(TimeAgoCalculator.timesString.get(i)).append(temp != 1 ? "" : "").append(" temu");
                break;
            }
        }
        if ("".equals(res.toString()))
            return "0 sukund temu";
        else
            return res.toString();
    }
}
