package what.eat.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import what.eat.SpringApp;
import what.eat.data.persistence.repository.DataDishCacheRepository;
import what.eat.data.plop.DataDishQuery;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class DataDishCache_findAll_IntegrationTest {

    @Autowired
    private SpringApp app;

    @Autowired
    private DataDishCacheRepository cacheRepository;

    @BeforeEach
    void setUp() {
        app.init();
    }

    @AfterEach
    void tearDown() {
        app.stop();
    }

    @Test
    public void checkSize() {
        assertThat(cacheRepository.findAll())
                .hasSize(10);
    }

    @Test
    public void checkSaucissePuree() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.FR_LABEL, DataDishQuery.Operation.EQUALS, "Saucisse purée");
        assertThat(cacheRepository.find(query))
                .hasSize(1)
                .anySatisfy(dish -> {
                    assertThat(dish.id().value()).isNotNull();
                    assertThat(dish.id().frLabel()).isEqualTo("Saucisse purée");
                    assertThat(dish.tags())
                            .hasSize(3)
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Porc");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Viande");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Féculent");
                            });
                    // TODO -> , , , , simple, rapide, économique
                });
    }

    @Test
    public void checkSaucisseLentille() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.FR_LABEL, DataDishQuery.Operation.EQUALS, "Saucisse lentille");
        assertThat(cacheRepository.find(query))
                .hasSize(1)
                .anySatisfy(dish -> {
                    assertThat(dish.id().value()).isNotNull();
                    assertThat(dish.id().frLabel()).isEqualTo("Saucisse lentille");
                    assertThat(dish.tags())
                            .hasSize(3)
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Porc");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Viande");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Légumineux");
                            });
                    //TODO -> , simple, rapide, économique
                });
    }

    @Test
    public void checkSaucisseLentilleCarotte() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.FR_LABEL, DataDishQuery.Operation.EQUALS, "Saucisse lentille carotte");
        assertThat(cacheRepository.find(query))
                .hasSize(1)
                .anySatisfy(dish -> {
                    assertThat(dish.id().value()).isNotNull();
                    assertThat(dish.id().frLabel()).isEqualTo("Saucisse lentille carotte");
                    assertThat(dish.tags())
                            .hasSize(4)
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Porc");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Viande");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Légumineux");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Légume");
                            });
                    //TODO -> , , simple, rapide, économique
                });
    }

    @Test
    public void checkTagliatelleCarbo() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.FR_LABEL, DataDishQuery.Operation.EQUALS, "Tagliatelle Carbonara");
        assertThat(cacheRepository.find(query))
                .hasSize(1)
                .anySatisfy(dish -> {
                    assertThat(dish.id().value()).isNotNull();
                    assertThat(dish.id().frLabel()).isEqualTo("Tagliatelle Carbonara");
                    assertThat(dish.tags())
                            .hasSize(5)
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Pate");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Féculent");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Viande");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Porc");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Laitage");
                            });
                    //TODO -> , , rapide, économique,
                });
    }

    @Test
    public void checkSpaghettiBolo() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.FR_LABEL, DataDishQuery.Operation.EQUALS, "Spaghetti bolognaise");
        assertThat(cacheRepository.find(query))
                .hasSize(1)
                .anySatisfy(dish -> {
                    assertThat(dish.id().value()).isNotNull();
                    assertThat(dish.id().frLabel()).isEqualTo("Spaghetti bolognaise");
                    assertThat(dish.tags())
                            .hasSize(4)
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Pate");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Féculent");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Boeuf");
                            })
                            .anySatisfy(tag -> {
                                assertThat(tag.id().value()).isNotNull();
                                assertThat(tag.id().frLabel()).isEqualTo("Viande");
                            });
                    //TODO -> , rapide, économique
                });
    }

    @Test
    public void checkGratinChouxFleur() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.FR_LABEL, DataDishQuery.Operation.EQUALS, "Gratin de choux-fleur");
        assertThat(cacheRepository.find(query)).anySatisfy(dish -> {
            assertThat(dish.id().value()).isNotNull();
            assertThat(dish.id().frLabel()).isEqualTo("Gratin de choux-fleur");
            assertThat(dish.tags())
                    .hasSize(4)
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Végétarien");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Familial");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Laitage");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Légume");
                    });
        });
    }

    @Test
    public void checkGratinPate() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.FR_LABEL, DataDishQuery.Operation.EQUALS, "Gratin de pate");
        assertThat(cacheRepository.find(query)).anySatisfy(dish -> {
            assertThat(dish.id().value()).isNotNull();
            assertThat(dish.id().frLabel()).isEqualTo("Gratin de pate");
            assertThat(dish.tags())
                    .hasSize(5)
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Végétarien");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Pate");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Féculent");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Laitage");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Familial");
                    });
        });
    }

    @Test
    public void checkHamburgerPouletFrite() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.RELATION_FR_LABEL, DataDishQuery.Operation.EQUALS, "Hamburger Poulet");
        query.add(DataDishQuery.Field.RELATION_FR_LABEL, DataDishQuery.Operation.EQUALS, "Frite");
        assertThat(cacheRepository.find(query)).anySatisfy(dish -> {
            assertThat(dish.id().value()).isNotNull();
            assertThat(dish.id().frLabel()).contains("Hamburger Poulet", "Frite");
            assertThat(dish.tags())
                    .hasSize(3)
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Poulet");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Viande");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Féculent");
                    });
        });
    }

    @Test
    public void checkHamburgerSteakFrite() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.RELATION_FR_LABEL, DataDishQuery.Operation.EQUALS, "Hamburger Steak");
        query.add(DataDishQuery.Field.RELATION_FR_LABEL, DataDishQuery.Operation.EQUALS, "Frite");
        assertThat(cacheRepository.find(query)).anySatisfy(dish -> {
            assertThat(dish.id().value()).isNotNull();
            assertThat(dish.id().frLabel()).contains("Hamburger Steak", "Frite");
            assertThat(dish.tags())
                    .hasSize(3)
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Boeuf");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Viande");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Féculent");
                    });
        });
    }

    @Test
    public void checkHamburgerVégéChampiFrite() {
        DataDishQuery query = new DataDishQuery();
        query.add(DataDishQuery.Field.RELATION_FR_LABEL, DataDishQuery.Operation.EQUALS, "Hamburger végétarien au champignon");
        query.add(DataDishQuery.Field.RELATION_FR_LABEL, DataDishQuery.Operation.EQUALS, "Frite");
        assertThat(cacheRepository.find(query)).anySatisfy(dish -> {
            assertThat(dish.id().value()).isNotNull();
            assertThat(dish.id().frLabel()).contains("Hamburger végétarien au champignon", "Frite");
            assertThat(dish.tags())
                    .hasSize(2)
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Végétarien");
                    })
                    .anySatisfy(tag -> {
                        assertThat(tag.id().value()).isNotNull();
                        assertThat(tag.id().frLabel()).isEqualTo("Féculent");
                    });
        });
    }

}