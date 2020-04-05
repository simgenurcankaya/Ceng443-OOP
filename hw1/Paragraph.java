public class Paragraph implements DocElement{
    private String par;

    public Paragraph(String text){
        this.par = text;
    }
    public String getText(){
        return this.par;
    }

    @Override
    public <T> T accept(TextVisitor<T> visitor) {

        return visitor.visit(this);
    }
}