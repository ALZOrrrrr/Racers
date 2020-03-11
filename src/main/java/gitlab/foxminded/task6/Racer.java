package gitlab.foxminded.task6;
public class Racer implements Comparable<Racer>{
   private int place;
   private long time;
   private String name;
   private String company;

    public Racer() {
    }

    public Racer(String name, String company, long time) {
        this.name = name;
        this.company = company;
        this.time = time;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
   @Override
   public int compareTo(Racer o) {
        return this.time > o.getTime() ? 1 : this.time < o.getTime() ? -1 : 0;  }
}
