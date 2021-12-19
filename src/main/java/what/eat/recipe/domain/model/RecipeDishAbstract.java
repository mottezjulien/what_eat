package what.eat.recipe.domain.model;

public class RecipeDishAbstract implements RecipeDish {

    private final String internalId;
    private final String label;

    public RecipeDishAbstract(String internalId, String label) {
        this.internalId = internalId;
        this.label = label;
    }

    public String internalId() {
        return internalId;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    public String label() {
        return label;
    }

}
