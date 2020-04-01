
public class Paragraph implements DocElement{
    private String parag;
    
    public Paragraph(String text){
        parag = text;
    }
    public String getText(){
        return parag;
    }

    @Override
    public <T> T accept(TextVisitor<T> visitor) {
        return visitor.visit(this);
    }
}