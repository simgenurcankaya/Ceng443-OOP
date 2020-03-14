
public class Paragraph implements DocElement{
    private String parag;
    
    public Paragraph(String text){
        parag = text;
    }
    public String getText(){
        return parag;
    }
}