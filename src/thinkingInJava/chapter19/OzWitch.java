package thinkingInJava.chapter19;

public enum OzWitch {

    WEST("WEST"),
    NORTH("North"),
    EAST("East"),
    SOUTH("South");

    private String desciption;

    private OzWitch(String desciption){
        this.desciption = desciption;
    }

    public String getDesciption(){
        return desciption;
    }

    public static void main(String[] args) {
        for (OzWitch o:OzWitch.values()){
            System.out.println(o +": "+o.getDesciption());
        }
    }
}
