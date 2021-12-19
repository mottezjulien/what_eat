package what.eat.recipe.infrastructure.persistence.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RecipeDishRepository_childrenSimple_Test {

    @Autowired
    private RecipeDishRepository repository;

    private RecipeDishEntity parent;
    private RecipeDishEntity child1;
    private RecipeDishEntity child2;
    private RecipeDishEntity other;

    @BeforeEach
    void setUp() {

        parent = new RecipeDishEntity();
        parent.setId(UUID.randomUUID().toString());
        parent.setLabel("parent");
        parent = repository.save(parent);

        child1 = create("child1");
        child2 = create("child2");
        other = create("other");

        parent.getChildren().add(child1);
        parent.getChildren().add(child2);
        parent = repository.save(parent);
    }

    @AfterEach
    void tearDown() {
        repository.delete(other);
        repository.delete(parent);
        repository.delete(child1);
        repository.delete(child2);
    }

    @Test
    public void shouldFindChildrenSimple() {
        Optional<RecipeDishEntity> found = repository.findById_FetchAllChildren(parent.getId());
        assertThat(found)
                .isNotEmpty()
                .hasValueSatisfying(satisfy
                        -> {
                    assertThat(satisfy.getLabel()).isEqualTo("parent");
                    assertThat(satisfy.getChildren()).hasSize(2)
                            .anySatisfy(satisfyChild1 -> assertThat(satisfyChild1.getLabel()).isEqualTo("child1"))
                            .anySatisfy(satisfyChild2 -> assertThat(satisfyChild2.getLabel()).isEqualTo("child2"));
                });

    }

    private RecipeDishEntity create(String label) {
        RecipeDishEntity entity = new RecipeDishEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setLabel(label);
        return repository.save(entity);
    }

}