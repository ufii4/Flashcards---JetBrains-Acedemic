package flashcards.FlashCard;

public class Card {
    private String description;
    private String definition;
    private int errorCount;

    Card(String description) {
        this(description, "unknown", 0);
    }

    Card(String description, String definition) {
        this(description, definition, 0);
    }

    Card(String description, String definition, int errorCount) {
        this.description = description;
        this.definition = definition;
        this.errorCount = errorCount;
    }

    @Override
    public boolean equals(Object obj) {
        Card anotherCard = (Card) obj;
        return this.description.equals(anotherCard.getDescription());
    }

    public String getDescription() {
        return description;
    }

    public String getDefinition() {
        return definition;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public void addErrorCount() {
        errorCount++;
    }

    public void clearErrorCount() {
        errorCount = 0;
    }
}
