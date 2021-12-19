package what.eat.recipe.domain.model;

public abstract class RecipeDishFinal implements RecipeDish {

    private final String internalId;
    private final String label;

    public RecipeDishFinal(String internalId, String label) {
        this.internalId = internalId;
        this.label = label;
    }

    public String internalId() {
        return internalId;
    }

    public String label() {
        return label;
    }

    @Override
    public boolean isFinal() {
        return true;
    }


}
