public class CsvRow {
    private String id;
    private String name;
    private String age;
    private String balance;
    private String no2bthreadid;
    private String no3threadid;
    private String prevbalance;
    private String avgbalance;
    private String no1threadid;
    private String freetf;
    private String no2athreadid;

    CsvRow(String id,
            String name,
            String age,
            String balance,
            String prevbalance,
            String freetf) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.prevbalance = prevbalance;
        this.freetf = freetf;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }

    public float getBalance() {
        return Float.parseFloat(this.balance);
    }

    public float getPrevBalance() {
        return Float.parseFloat(this.prevbalance);
    }

    public void setAverage(float avg) {
        this.avgbalance = String.valueOf(avg);
    }

    public float getAverage() {
        return Float.parseFloat(this.avgbalance);
    }

    public void setno1threadid(String threadid) {
        this.no1threadid = threadid;
    }

    public String getno1threadid() {
        return this.no1threadid;
    }

    public String getFreetf() {
        return this.freetf;
    }

    public void setbalance(String balance) {
        this.balance = balance;
    }

    public void setfreetf(String freetf) {
        this.freetf = freetf;
    }

    public void setno2athreadid(String threadid) {
        this.no2athreadid = threadid;
    }

    public void setno2bthreadid(String threadid) {
        this.no2bthreadid = threadid;
    }

    public String getno2athreadid() {
        return this.no2athreadid;
    }

    public String getno2bthreadid() {
        return this.no2bthreadid;
    }

    public String getno3threadid() {
        return this.no3threadid;
    }

    public void setno3threadid(String threadid) {
        this.no3threadid = threadid;
    }
}
