package hw1;

import java.util.ArrayList;

public class PrintHTMLVisitor implements  TextVisitor<String> {
    @Override
    public String visit(Document document) {
        String ret = "<html><head><title>";
        ret = ret.concat(document.getTitle());
        ret = ret.concat("</title></head><body>");
        ArrayList<DocElement> elements = document.getElements();
        for(int i = 0 ; i< elements.size() ; i++){
            ret= ret.concat(elements.get(i).accept(new PrintHTMLVisitor()));
            ret = ret.concat("");
        }
        ret = ret.concat("</body></html>");
        return ret;

    }

    @Override
    public String visit(EquationText equationText) {
        String ret = "<math>";
        ret = ret.concat(equationText.getInnerMath().accept(new PrintMathMLVisitor()));
        ret = ret.concat("</math>");
        return ret;
    }

    @Override
    public String visit(Paragraph paragraph) {
        String ret = "<p>";
        ret = ret.concat(paragraph.getText());
        ret = ret.concat("</p>");
        return ret;
    }
}
