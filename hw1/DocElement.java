public interface DocElement {
    <T> T accept(TextVisitor<T> visitor);
}
