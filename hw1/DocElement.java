package hw1;

interface DocElement{
    <T> T accept(TextVisitor<T> visitor);
}